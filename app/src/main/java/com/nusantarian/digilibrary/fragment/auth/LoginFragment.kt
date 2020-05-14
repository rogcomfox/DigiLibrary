package com.nusantarian.digilibrary.fragment.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.nusantarian.digilibrary.R
import com.nusantarian.digilibrary.activity.MainActivity
import com.nusantarian.digilibrary.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var ft: FragmentTransaction

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        firebaseAuth = FirebaseAuth.getInstance()
        ft = activity!!.supportFragmentManager.beginTransaction()
        binding.btnLogin.setOnClickListener(this)
        binding.tvForgotPass.setOnClickListener(this)
        binding.tvRegisterFromLogin.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> {
                binding.progressCircular.visibility = View.VISIBLE
                val email = binding.tilEmail.editText?.text.toString()
                val pass = binding.tilPass.editText?.text.toString()
                if (isValid(email, pass)) {
                    binding.progressCircular.visibility = View.GONE
                } else {
                    firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(context, R.string.success_login, Toast.LENGTH_SHORT).show()
                            startActivity(Intent(activity, MainActivity::class.java))
                            activity?.finish()
                        } else {
                            Toast.makeText(context, R.string.failed_login, Toast.LENGTH_SHORT).show()
                        }
                        binding.progressCircular.visibility = View.GONE
                    }.addOnFailureListener {
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                        binding.progressCircular.visibility = View.GONE
                    }
                }
            }
            R.id.tv_forgot_pass -> {
                ft.replace(R.id.main_frame,
                    ForgotPassFragment()
                )
                    .addToBackStack(null)
                    .commit()
            }
            R.id.tv_register_from_login -> {
                ft.replace(R.id.main_frame,
                    RegisterFragment()
                )
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    private fun isValid(email: String, pass: String): Boolean {
        val empty = activity!!.resources.getString(R.string.field_empty)
        val notValid = activity!!.resources.getString(R.string.email_not_valid)
        return if (email.isEmpty() || pass.isEmpty()) {
            binding.tilEmail.error = empty
            binding.tilPass.error = empty
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = notValid
            false
        } else {
            binding.tilEmail.error = null
            binding.tilPass.error = null
            binding.tilEmail.isErrorEnabled = true
            binding.tilEmail.isErrorEnabled = true
            true
        }
    }

}
