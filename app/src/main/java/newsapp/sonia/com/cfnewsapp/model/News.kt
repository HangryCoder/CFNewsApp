package newsapp.sonia.com.cfnewsapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by soniawadji on 01/06/18.
 */
data class News(val title: String,
                val description: String,
                @SerializedName("url")
                val newsUrl: String,
                @SerializedName("urlToImage")
                val newsImage: String)