package newsapp.sonia.com.cfnewsapp.newsdetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_news_details.*
import newsapp.sonia.com.cfnewsapp.R
import newsapp.sonia.com.cfnewsapp.model.News
import newsapp.sonia.com.cfnewsapp.utils.Constants

class NewsDetailsActivity : AppCompatActivity() {

    private lateinit var news: News

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        news = intent.extras.getSerializable(Constants.INTENT_NEWS) as News

        newsTitle.text = news.title
        newsDescription.text = news.description
        newsAuthor.text = news.newsAuthor

        Glide.with(this)
                .load(news.newsImage)
                .into(newsImage)
    }
}