package com.nusantarian.digilibrary.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nusantarian.digilibrary.R
import com.nusantarian.digilibrary.databinding.FragmentMainBinding

class MainFragment : Fragment(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var ft = activity!!.supportFragmentManager
    private val fragment1 = LibraryFragment()
    private val fragment2 = ProfileFragment()
    private var active = fragment1 as Fragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.bottomNav.setOnNavigationItemSelectedListener(this)
        ft.beginTransaction().add(R.id.secondary_frame, fragment2, "2").hide(fragment2).commit()
        ft.beginTransaction().add(R.id.secondary_frame, fragment1, "1").commit()
        return view
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_library -> {
                ft.beginTransaction().hide(active).show(fragment1).commit()
                active = fragment1
                return true
            }
            R.id.nav_profile -> {
                ft.beginTransaction().hide(active).show(fragment2).commit()
                active = fragment2
                return true
            }
        }
        return false
    }

}
