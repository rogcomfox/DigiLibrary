package com.nusantarian.digilibrary.fragment.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.nusantarian.digilibrary.R
import com.nusantarian.digilibrary.activity.LandingActivity
import com.nusantarian.digilibrary.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth:FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnSignOut.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_sign_out){
            firebaseAuth.signOut()
            startActivity(Intent(context, LandingActivity::class.java))
            activity?.finish()
        }
    }

}
