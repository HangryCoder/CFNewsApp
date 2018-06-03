package newsapp.sonia.com.cfnewsapp.data

import newsapp.sonia.com.cfnewsapp.model.News

interface NewsDataSource {

    interface OnFinishedListener {
        fun onSuccess(newsList: ArrayList<News>)

        fun onError(message: String)
    }

    fun getNewsList(category: String, date: String, sortBy: String, onFinishedListener: OnFinishedListener)

    fun disposeFetchingNewsList()
}