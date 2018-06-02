package newsapp.sonia.com.cfnewsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import newsapp.sonia.com.cfnewsapp.adapter.NewsAdapter
import newsapp.sonia.com.cfnewsapp.model.News
import kotlinx.android.synthetic.main.activity_main.*
import newsapp.sonia.com.cfnewsapp.network.RestClient

class MainActivity : AppCompatActivity() {

    private var newsList: ArrayList<News> = ArrayList()
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setNewsList()

        getNewsList("technology", "2018-06-02", "popularity", Constants.API_KEY)

        newsAdapter = NewsAdapter(context = this, newsList = newsList)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        recyclerView.adapter = newsAdapter

    }

    private fun getNewsList(category: String, date: String, sortBy: String, apiKey: String) {

        val disposable = RestClient.getNewsAPI()
                .fetchNews(category, date, sortBy, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                }

    }

    private fun setNewsList() {
        var news = News("JHbdajh", "kajbscjinascakjsbcahbsc", "sjdhcjsd",
                "skjdhcljsdn")
        newsList.add(news)

        news = News("qbwjkd", "oiu92yewdiquhwl", "sjdhcjsd",
                "skjdhcljsdn")
        newsList.add(news)

        news = News("wkjsdnda", "123rd2we", "sjdhcjsd",
                "skjdhcljsdn")
        newsList.add(news)

        news = News("kjk", "901u3wjridjslkdfskmdnkjsnev", "sjdhcjsd",
                "skjdhcljsdn")
        newsList.add(news)

        news = News("mnmxn cmn x", "al;kdpiqwldknalnd", "sjdhcjsd",
                "skjdhcljsdn")
        newsList.add(news)
    }
}
