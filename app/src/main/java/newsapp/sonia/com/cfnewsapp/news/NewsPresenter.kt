package newsapp.sonia.com.cfnewsapp.news

import newsapp.sonia.com.cfnewsapp.data.NewsDataSource
import newsapp.sonia.com.cfnewsapp.data.NewsRepository
import newsapp.sonia.com.cfnewsapp.model.News

class NewsPresenter(private val newsRepository: NewsRepository, private val newsView: NewsContract.View)
    : NewsContract.Presenter {

    override fun fetchNews(category: String, date: String, sortBy: String) {
        newsView.showProgressDialog(true)
        newsRepository.getNewsList(category, date, sortBy, this as NewsDataSource.OnFinishedListener)
    }

    override fun showNewsDetails(newsList: ArrayList<News>) {
        newsView.showProgressDialog(false)
        newsView.displayNews(newsList)
    }
}