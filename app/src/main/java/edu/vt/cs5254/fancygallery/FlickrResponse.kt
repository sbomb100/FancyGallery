package edu.vt.cs5254.fancygallery

import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class FlickrResponse(
    val photos: PhotoResponse
)