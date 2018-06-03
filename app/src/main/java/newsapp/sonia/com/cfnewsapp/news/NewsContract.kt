package newsapp.sonia.com.cfnewsapp.news

import newsapp.sonia.com.cfnewsapp.model.News

interface NewsContract {

    interface View {

        fun displayNews(newsList: ArrayList<News>)

        fun displayError(errorMessage: String)

        fun showProgressDialog(show: Boolean)

        fun showNewsDetails(news: News)

    }

    interface Presenter {

        fun fetchNews(category: String, date: String, sortBy: String)

        fun openNewsDetails(news: News)

    }
}