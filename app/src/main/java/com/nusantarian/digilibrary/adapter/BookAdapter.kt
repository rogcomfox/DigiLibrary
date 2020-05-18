package com.nusantarian.digilibrary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nusantarian.digilibrary.R
import com.nusantarian.digilibrary.model.CustomOnclickListener


class BookAdapter(
    private val myContext: Context,
    private val list: ArrayList<String>,
    private val listener: CustomOnclickListener
) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(myContext).inflate(R.layout.item_book, viewGroup, false)
        val holder = ViewHolder(view)
        view.setOnClickListener {
            listener.onClickItem(it, holder.adapterPosition)
        }
        return holder
    }

    override fun onBindViewHolder(
        myViewHlder: ViewHolder,
        i: Int
    ) {
        myViewHlder.titleText.text = list[i].replace("_", " ")
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleText: TextView = itemView.findViewById(R.id.tv_book_title)

    }

}