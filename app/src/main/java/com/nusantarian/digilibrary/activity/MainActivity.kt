package com.nusantarian.digilibrary.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nusantarian.digilibrary.R
import com.nusantarian.digilibrary.databinding.ActivityMainBinding
import com.nusantarian.digilibrary.fragment.main.LibraryFragment
import com.nusantarian.digilibrary.fragment.main.ProfileFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private val fragment1: Fragment = LibraryFragment()
    private val fragment2: Fragment = ProfileFragment()
    private val fm = supportFragmentManager
    var active = fragment1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.bottomNav.setOnNavigationItemSelectedListener(this)
        fm.beginTransaction().add(R.id.secondary_frame, fragment2, "2").hide(fragment2).commit()
        fm.beginTransaction().add(R.id.secondary_frame, fragment1, "1").commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_library -> {
                fm.beginTransaction().hide(active).show(fragment1).commit()
                active = fragment1
                return true
            }
            R.id.nav_profile -> {
                fm.beginTransaction().hide(active).show(fragment2).commit()
                active = fragment2
                return true
            }
        }
        return false
    }
}
