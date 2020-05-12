package com.nusantarian.digilibrary.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.nusantarian.digilibrary.R
import com.nusantarian.digilibrary.databinding.FragmentLandingBinding

class LandingFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentLandingBinding? = null
    private val binding get() = _binding!!
    private lateinit var ft: FragmentTransaction

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLandingBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnStart.setOnClickListener(this)
        ft = activity!!.supportFragmentManager.beginTransaction()
        return view
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_start) {
            ft.add(R.id.main_frame, RegisterFragment())
                .addToBackStack(null)
                .commit()
        }
    }

}
