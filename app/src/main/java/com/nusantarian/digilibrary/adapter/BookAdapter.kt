package com.nusantarian.digilibrary.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nusantarian.digilibrary.R
import com.nusantarian.digilibrary.model.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book.view.*

class BookAdapter(private val books: ArrayList<Book>) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val bookTitle: TextView = itemView.tv_book_title
        val bookAuthor: TextView = itemView.tv_book_author
        val bookImage: ImageView = itemView.img_book_cover
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val bookView = inflater.inflate(R.layout.item_book, parent, false)
        return ViewHolder(bookView)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: BookAdapter.ViewHolder, position: Int) {
        val book: Book = books[position]
        val bookTitle = holder.bookTitle
        val bookAuthor = holder.bookAuthor
        val imageBook = holder.bookImage

        bookTitle.text = book.title
        bookAuthor.text = book.author
        Picasso.get().load(Uri.parse(book.getCoverUrl())).error(R.drawable.ic_no_data)
            .into(imageBook)
    }
}