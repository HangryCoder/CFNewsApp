package newsapp.sonia.com.cfnewsapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by soniawadji on 01/06/18.
 */
data class News(val title: String,
                val description: String,
                @SerializedName("url")
                val newsUrl: String,
                @SerializedName("urlToImage")
                val newsImage: String,
                @SerializedName("author")
                val newsAuthor: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(newsUrl)
        parcel.writeString(newsImage)
        parcel.writeString(newsAuthor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }
}