package edu.vt.cs5254.fancygallery

import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
data class PhotoResponse(
    @Json(name = "photo") val galleryItems: List<GalleryItem>
)