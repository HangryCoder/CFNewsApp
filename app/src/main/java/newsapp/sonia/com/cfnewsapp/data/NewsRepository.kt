package newsapp.sonia.com.cfnewsapp.data

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import newsapp.sonia.com.cfnewsapp.network.RestClient
import newsapp.sonia.com.cfnewsapp.utils.Constants
import newsapp.sonia.com.cfnewsapp.utils.Utils

class NewsRepository : NewsDataSource {

    private val TAG = NewsRepository::class.java.simpleName
    private lateinit var disposable: Disposable

    override fun getNewsList(category: String, date: String, sortBy: String,
                             onFinishedListener: NewsDataSource.OnFinishedListener) {

        disposable = RestClient.getNewsAPI()
                .fetchNews(category, date, sortBy, Constants.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    Utils.showLogE(TAG, "response " + response.status)
                    val status = response.status
                    if (status == Constants.API_STATUS) {
                        onFinishedListener.onSuccess(response.newsList)
                    } else {
                        onFinishedListener.onError("Something went wrong")
                    }
                }, { error ->
                    onFinishedListener.onError(error.localizedMessage)
                })

    }

    override fun disposeFetchingNewsList() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}