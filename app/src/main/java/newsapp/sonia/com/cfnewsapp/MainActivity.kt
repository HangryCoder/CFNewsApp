package newsapp.sonia.com.cfnewsapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var newsList: ArrayList<News> = ArrayList()
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNewsList()

        newsAdapter = NewsAdapter(context = this, newsList = newsList)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        recyclerView.adapter = newsAdapter

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
