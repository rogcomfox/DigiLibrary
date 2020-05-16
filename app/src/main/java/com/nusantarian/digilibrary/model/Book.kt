package com.nusantarian.digilibrary.model

import android.text.TextUtils
import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


data class Book(var openLibraryId: String = "", var author: String = "", var title: String = "") {
    fun getCoverUrl(): String {
        return "http://covers.openlibrary.org/b/olid/$openLibraryId-M.jpg?default=false";
    }

    fun getLargeCoverUrl(): String {
        return "http://covers.openlibrary.org/b/olid/$openLibraryId-L.jpg?default=false"
    }

    fun fromJson(jsonObject: JSONObject) : Book? {
        val book = Book()
        try {
            if (jsonObject.has(COVER_EDITION_KEY))
                book.openLibraryId = jsonObject.getString(COVER_EDITION_KEY)
            else if (jsonObject.has(EDITION_KEY)){
                val ids: JSONArray = jsonObject.getJSONArray(EDITION_KEY)
                book.openLibraryId = ids.getString(0)
            }
            book.title =
                if (jsonObject.has(TITLE_SUGGEST)) jsonObject.getString(TITLE_SUGGEST) else ""
            book.author = getAuthor(jsonObject)
        } catch (e: JSONException){
            e.printStackTrace()
            Log.e(TAG, e.toString())
            return null
        }
        return book
    }

    private fun getAuthor(jsonObject: JSONObject) : String{
        return try{
            val authors: JSONArray = jsonObject.getJSONArray(AUTHOR_NAME)
            val numAuthors = authors.length()
            val authorStrings = arrayOfNulls<String>(numAuthors)
            for (i in 0 until numAuthors) {
                authorStrings[i] = authors.getString(i)
            }
            TextUtils.join(", ", authorStrings)
        } catch (e: JSONException){
            e.printStackTrace()
            Log.e(TAG, e.toString())
            ""
        }
    }

    fun fromJson(jsonArray: JSONArray): ArrayList<Book>? {
        val books = ArrayList<Book>(jsonArray.length())

        for (i in 0 until jsonArray.length()) {
            var bookJson: JSONObject?
            bookJson = try {
                jsonArray.getJSONObject(i)
            } catch (e: Exception) {
                e.printStackTrace()
                continue
            }
            val book = Book()
            book.fromJson(bookJson)
            books.add(book)
        }
        return books
    }

    companion object{
        const val COVER_EDITION_KEY = "cover_edition_key"
        const val EDITION_KEY = "edition_key"
        const val TITLE_SUGGEST = "title_suggest"
        const val AUTHOR_NAME = "author_name"
        const val TAG = "Book Model"
    }
}