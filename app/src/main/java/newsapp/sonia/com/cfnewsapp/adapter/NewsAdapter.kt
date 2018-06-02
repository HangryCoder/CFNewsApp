package newsapp.sonia.com.cfnewsapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import newsapp.sonia.com.cfnewsapp.model.News
import newsapp.sonia.com.cfnewsapp.R
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by soniawadji on 01/06/18.
 */
class NewsAdapter(private val context: Context, private var newsList: ArrayList<News>) :
        RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false)
        return NewsHolder(view)
    }

    override fun onBindViewHolder(holder: NewsHolder?, position: Int) {
        val news = newsList[position]

        holder?.itemView?.newsTitle?.text = news.title
        holder?.itemView?.newsDescription?.text = news.description
    }

    override fun getItemCount(): Int = newsList.size

    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}