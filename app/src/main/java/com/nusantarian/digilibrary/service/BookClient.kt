package com.nusantarian.digilibrary.service

import android.util.Log
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

class BookClient {
    companion object {
        const val API_BASE_URL = "https://openlibrary.org/"
    }

    private val client = AsyncHttpClient()

    private fun getApiUrl(relativeUrl: String): String {
        return API_BASE_URL + relativeUrl
    }

    fun getBooks(query: String?, handler: JsonHttpResponseHandler?) {
        try {
            val url = getApiUrl("search.json?q=")
            client.get(url + URLEncoder.encode(query, "utf-8"), handler)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            Log.e("TAG", e.toString())
        }
    }
}