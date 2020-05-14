package com.nusantarian.digilibrary.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.nusantarian.digilibrary.R
import com.nusantarian.digilibrary.databinding.ActivityMainBinding
import com.nusantarian.digilibrary.fragment.main.MainFragment

class MainActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        if (savedInstanceState != null) {
            initMainFragment()
        }
        supportFragmentManager.addOnBackStackChangedListener(this)
    }

    private fun initMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame,
                MainFragment()
            )
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) supportFragmentManager.popBackStack()
        else super.onBackPressed()
    }

    override fun onBackStackChanged() {

    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }
}
