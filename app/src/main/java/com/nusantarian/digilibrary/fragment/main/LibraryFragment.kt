package com.nusantarian.digilibrary.fragment.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nusantarian.digilibrary.R
import com.nusantarian.digilibrary.activity.ReadActivity
import com.nusantarian.digilibrary.adapter.BookAdapter
import com.nusantarian.digilibrary.databinding.FragmentLibraryBinding
import com.nusantarian.digilibrary.model.CustomOnclickListener


class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!
    private lateinit var bookAdapter: BookAdapter
    var titleList: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val view = binding.root
        (activity!! as AppCompatActivity).setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)
        titleList.add("Ancient Structure")
        titleList.add("Indonesian Ethnic")
        titleList.add("Phylomemetics of Batik")
        binding.rvBook.setHasFixedSize(true)
        binding.rvBook.layoutManager = LinearLayoutManager(context)

        fetchData()
        binding.rvBook.adapter = bookAdapter

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.library_menu, menu)
    }

    private fun fetchData() {
        bookAdapter = BookAdapter(context!!, titleList, object : CustomOnclickListener {
            override fun onClickItem(v: View, position: Int) {
                val intent = Intent(context, ReadActivity::class.java)
                intent.putExtra("titles", titleList[position])
                startActivity(intent)

                Toast.makeText(context, "Clicked" + titleList[position], Toast.LENGTH_SHORT).show()
            }
        })
    }
}
