package com.nusantarian.digilibrary.activity

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.nusantarian.digilibrary.R
import com.nusantarian.digilibrary.databinding.ActivityLandingBinding
import com.nusantarian.digilibrary.fragment.LandingFragment

class LandingActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    private lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set fullscreen activity
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = ActivityLandingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState != null) {
            getMainFragment()
        }
        supportFragmentManager.addOnBackStackChangedListener(this)
    }

    private fun getMainFragment() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, LandingFragment())
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) supportFragmentManager.popBackStack() else super.onBackPressed()
    }

    override fun onBackStackChanged() {

    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }

}
