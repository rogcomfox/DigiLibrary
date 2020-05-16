package com.nusantarian.digilibrary.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nusantarian.digilibrary.R
import com.nusantarian.digilibrary.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment() {

    private var _binding:FragmentLibraryBinding? = null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

}
