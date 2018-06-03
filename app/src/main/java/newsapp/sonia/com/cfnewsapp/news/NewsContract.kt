package newsapp.sonia.com.cfnewsapp.news

import newsapp.sonia.com.cfnewsapp.model.News

interface NewsContract {

    interface View {

        fun displayNews(newsList: ArrayList<News>)

        fun displaySuccess(successMessage: String)

        fun displayError(errorMessage: String)

        fun displayNoNews()

        fun showProgressDialog(show: Boolean)

        fun showFilteringDialog()

    }

    interface Presenter {

        fun fetchNews(category: String)

        fun showNewsDetails(news: News)

    }
}