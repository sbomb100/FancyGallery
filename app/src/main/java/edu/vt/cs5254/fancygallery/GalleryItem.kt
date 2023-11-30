package edu.vt.cs5254.fancygallery

import android.net.Uri
import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class GalleryItem(
    val title: String,
    val id: String,
    @Json(name = "latitude") val latitude : Double,
    @Json(name = "longitude") val longitude : Double,
    @Json(name = "url_s") val url: String,
    val owner: String
) {
    val photoPageUri: Uri
        get() = Uri.parse("https://www.flickr.com/photos/")
            .buildUpon()
            .appendPath(owner)
            .appendPath(id)
            .build()
}

//https://www.flickr.com/photos/{user-id}/{photo-id}
