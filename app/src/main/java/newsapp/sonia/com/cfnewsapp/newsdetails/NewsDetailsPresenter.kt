package newsapp.sonia.com.cfnewsapp.newsdetails

import newsapp.sonia.com.cfnewsapp.model.News

class NewsDetailsPresenter(private val news: News, private val newsDetailsView: NewsDetailsContract.View)
    : NewsDetailsContract.Presenter {

    override fun setNewsDetailsView() {
        newsDetailsView.displayNewsDetails(news)
    }

}