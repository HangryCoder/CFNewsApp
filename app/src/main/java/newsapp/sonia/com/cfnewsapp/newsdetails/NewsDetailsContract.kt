package newsapp.sonia.com.cfnewsapp.newsdetails

import newsapp.sonia.com.cfnewsapp.model.News

interface NewsDetailsContract {

    interface View {
        fun displayNewsDetails(news: News)
    }

    interface Presenter {
        fun setNewsDetailsView()
    }
}