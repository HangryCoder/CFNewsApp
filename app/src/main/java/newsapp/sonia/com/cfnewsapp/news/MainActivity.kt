package newsapp.sonia.com.cfnewsapp.news

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import newsapp.sonia.com.cfnewsapp.adapter.NewsAdapter
import newsapp.sonia.com.cfnewsapp.model.News
import newsapp.sonia.com.cfnewsapp.R
import newsapp.sonia.com.cfnewsapp.network.RestClient
import newsapp.sonia.com.cfnewsapp.utils.Constants
import newsapp.sonia.com.cfnewsapp.utils.Utils
import kotlinx.android.synthetic.main.activity_main.*
import newsapp.sonia.com.cfnewsapp.data.NewsRepository
import newsapp.sonia.com.cfnewsapp.newsdetails.NewsDetailsActivity
import android.support.v7.widget.helper.ItemTouchHelper


class MainActivity : AppCompatActivity(), NewsContract.View, NewsAdapter.NewsAdapterCallback {

    private val TAG = MainActivity::class.java.simpleName
    private var newsList: ArrayList<News> = ArrayList()
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private var selectedCategory = ""
    private lateinit var newsPresenter: NewsPresenter
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedCategory = resources.getString(R.string.general)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage(resources.getString(R.string.loading_message))
        progressDialog.setCanceledOnTouchOutside(false)

        newsPresenter = NewsPresenter(NewsRepository(), this)

        newsPresenter.fetchNews(category = selectedCategory, date = Constants.DATE,
                sortBy = Constants.SORT_BY)

        newsAdapter = NewsAdapter(context = this, newsList = newsList, newsAdapterCallback = this)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) = newsPresenter.dismissNews(viewHolder.layoutPosition)
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        recyclerView.adapter = newsAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        selectedCategory = when (item?.itemId) {
            R.id.generalCategory -> resources.getString(R.string.general)
            R.id.technologyCategory -> resources.getString(R.string.technology)
            R.id.scienceCategory -> resources.getString(R.string.science)
            R.id.sportCategory -> resources.getString(R.string.sports)
            R.id.entertainmentCategory -> resources.getString(R.string.entertainment)
            else -> resources.getString(R.string.general)
        }
        newsPresenter.fetchNews(category = selectedCategory, date = Constants.DATE,
                sortBy = Constants.SORT_BY)
        return true
    }

    override fun displayNews(newsList: ArrayList<News>) {
        this.newsList.clear()
        this.newsList = newsList
        newsAdapter = NewsAdapter(context = this, newsList = newsList, newsAdapterCallback = this)
        recyclerView.adapter = newsAdapter
        newsAdapter.notifyDataSetChanged()
    }

    override fun displayError(errorMessage: String) {
        Utils.showToast(this, "Error $errorMessage")
    }

    override fun showProgressDialog(show: Boolean) {
        if (show) {
            progressDialog.show()
        } else {
            if (progressDialog.isShowing) {
                progressDialog.dismiss()
            }
        }
    }

    override fun showNewsDetails(news: News) {
        val intent = Intent(this@MainActivity, NewsDetailsActivity::class.java)
        intent.putExtra(Constants.INTENT_NEWS, news)
        startActivity(intent)
    }

    override fun onClickListener(news: News) {
        newsPresenter.openNewsDetails(news)
    }

    override fun onSwipeToDismiss(position: Int) {
        newsList.removeAt(position)
        newsAdapter.notifyItemRemoved(position)
    }

}
