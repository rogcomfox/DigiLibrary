package com.nusantarian.digilibrary.fragment.auth

import android.app.AlertDialog
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.nusantarian.digilibrary.R
import com.nusantarian.digilibrary.databinding.FragmentForgotPassBinding

class ForgotPassFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentForgotPassBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentForgotPassBinding.inflate(inflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        binding.btnForgotPassword.setOnClickListener(this)
        binding.imgBack.setOnClickListener(this)
        firebaseAuth = FirebaseAuth.getInstance()
        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_forgot_password -> resetPass()
            R.id.img_back -> activity?.onBackPressed()
        }
    }

    private fun resetPass() {
        binding.progressCircular.visibility = View.VISIBLE
        val email = binding.tilEmail.editText?.text.toString()
        if (isEmailValid(email)) {
            binding.progressCircular.visibility = View.GONE
        } else {
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, R.string.success_to_reset, Toast.LENGTH_SHORT).show()
                    raiseAlertDialog()
                } else {
                    Toast.makeText(context, R.string.failed_to_reset, Toast.LENGTH_SHORT).show()
                }
                binding.progressCircular.visibility = View.GONE
            }.addOnFailureListener {

            }
        }
    }

    private fun raiseAlertDialog() {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setMessage(R.string.message_reset)
        alertDialog.setTitle(R.string.success_to_reset)
        alertDialog.setNeutralButton("OK") { _, _ ->

        }
        alertDialog.show()
    }

    private fun isEmailValid(email: String): Boolean {
        val empty = activity!!.resources.getString(R.string.field_empty)
        val notValid = activity!!.resources.getString(R.string.email_not_valid)
        return if (email.isEmpty()) {
            binding.tilEmail.error = empty
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = notValid
            false
        } else {
            binding.tilEmail.error = null
            binding.tilEmail.isErrorEnabled = true
            true
        }
    }

}
