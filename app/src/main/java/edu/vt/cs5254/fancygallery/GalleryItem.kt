package edu.vt.cs5254.fancygallery

import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class GalleryItem(
    val title: String,
    val id: String,
    @Json(name = "url_s") val url: String,
)