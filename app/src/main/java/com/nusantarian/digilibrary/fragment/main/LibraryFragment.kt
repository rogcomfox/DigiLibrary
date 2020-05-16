package com.nusantarian.digilibrary.fragment.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.loopj.android.http.JsonHttpResponseHandler
import com.nusantarian.digilibrary.R
import com.nusantarian.digilibrary.adapter.BookAdapter
import com.nusantarian.digilibrary.databinding.FragmentLibraryBinding
import com.nusantarian.digilibrary.model.Book
import com.nusantarian.digilibrary.service.BookClient
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!
    private lateinit var bookAdapter: BookAdapter
    private lateinit var bookClient: BookClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val view = binding.root
        (activity!! as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity!! as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity!! as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        val books: ArrayList<Book> = ArrayList()
        bookAdapter = BookAdapter(books)
        binding.rvBook.adapter = bookAdapter

        fetchBooks()
        return view
    }

    private fun fetchBooks() {
        bookClient = BookClient()
        bookClient.getBooks("Oscar Wilde", object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<Header?>?,
                response: JSONObject?
            ) {
                try {
                    val docs: JSONArray?
                    if (response != null) {
                        // Get the docs json array
                        docs = response.getJSONArray("docs")
                        // Parse json array into array of model objects
                        val book = Book()
                        val books: ArrayList<Book>? = book.fromJson(docs)
                        // Remove all books from the adapter
                        books?.clear()
                        // Load model objects into the adapter
                        for (book in books!!) {
                            books.add(book) // add book through the adapter
                        }
                        bookAdapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    // Invalid JSON format, show appropriate error.
                    e.printStackTrace()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.library_menu, menu)
    }
}
