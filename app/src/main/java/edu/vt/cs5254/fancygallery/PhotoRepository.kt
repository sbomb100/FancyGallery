package edu.vt.cs5254.fancygallery

import retrofit2.*
import retrofit2.converter.moshi.*

class PhotoRepository {
    private val flickrApi: FlickrApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.flickr.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        flickrApi = retrofit.create()
    }
    suspend fun fetchPhotos(numPics: Int = 100): List<GalleryItem> =
        flickrApi.fetchPhotos(numPics).photos.galleryItems
}