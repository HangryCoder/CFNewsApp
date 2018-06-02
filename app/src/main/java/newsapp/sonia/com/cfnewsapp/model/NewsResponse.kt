package newsapp.sonia.com.cfnewsapp.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(val status: String,
                        val totalResults: Int,
                        @SerializedName("articles")
                        val newsList: ArrayList<News>)