package newsapp.sonia.com.cfnewsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import newsapp.sonia.com.cfnewsapp.adapter.NewsAdapter
import newsapp.sonia.com.cfnewsapp.model.News
import kotlinx.android.synthetic.main.activity_main.*
import newsapp.sonia.com.cfnewsapp.network.RestClient
import newsapp.sonia.com.cfnewsapp.utils.Constants
import newsapp.sonia.com.cfnewsapp.utils.Utils

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private var newsList: ArrayList<News> = ArrayList()
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private var selectedCategory = ""
    private val date = "2018-06-02"
    private val popularity = "popularity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedCategory = resources.getString(R.string.general)
        getNewsList(selectedCategory, date, popularity)

        newsAdapter = NewsAdapter(context = this, newsList = newsList)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

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
        getNewsList(selectedCategory, date, popularity)
        return true
    }

    private fun getNewsList(category: String, date: String, sortBy: String) {

        val disposable = RestClient.getNewsAPI()
                .fetchNews(category, date, sortBy, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    Utils.showLogE(TAG, "response " + response.status)
                    val status = response.status
                    if (status == Constants.API_STATUS) {
                        newsList = response.newsList
                        newsAdapter = NewsAdapter(context = this, newsList = newsList)
                        recyclerView.adapter = newsAdapter
                        newsAdapter.notifyDataSetChanged()
                    } else {
                        Utils.showToast(this, resources.getString(R.string.something_went_wrong))
                    }
                }, { error ->
                    Utils.showToast(this, "Error " + error.localizedMessage)
                })

    }
}
