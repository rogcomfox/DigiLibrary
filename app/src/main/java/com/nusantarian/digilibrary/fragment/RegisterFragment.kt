package com.nusantarian.digilibrary.fragment

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.nusantarian.digilibrary.R
import com.nusantarian.digilibrary.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth
    private val empty = activity!!.resources.getString(R.string.field_empty)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnSignUp.setOnClickListener(this)
        firebaseAuth = FirebaseAuth.getInstance()
        return view
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_sign_up) {
            binding.progressCircular.visibility = View.VISIBLE
            val name = binding.tilName.editText?.text.toString()
            val email = binding.tilEmail.editText?.text.toString()
            val pass = binding.tilPass.editText?.text.toString()
            val conf = binding.tilConfirmPass.editText?.text.toString()

            if (isValid(name, email, pass, conf)) {
                binding.progressCircular.visibility = View.GONE
            } else {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful){

                    } else {

                    }
                    binding.progressCircular.visibility = View.GONE
                }.addOnFailureListener {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressCircular.visibility = View.GONE
                }
            }
        }
    }

    private fun isValid(
        name: String,
        email: String,
        pass: String,
        conf: String
    ): Boolean {
        val notValid = activity!!.resources.getString(R.string.email_not_valid)
        val notMatch = activity!!.resources.getString(R.string.pass_not_match)
        val below = activity!!.resources.getString(R.string.pass_below_eight)
        if (name.isEmpty() || email.isEmpty() || pass.isEmpty() || conf.isEmpty()) {
            binding.tilEmail.error = empty
            binding.tilName.error = empty
            binding.tilConfirmPass.error = empty
            binding.tilPass.error = empty
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = notValid
            return false
        } else if (pass != conf) {
            binding.tilPass.error = notMatch
            binding.tilConfirmPass.error = notMatch
            return false
        } else if (pass.length < 8) {
            binding.tilPass.error = below
            return false
        } else {
            binding.tilEmail.error = null
            binding.tilName.error = null
            binding.tilPass.error = null
            binding.tilConfirmPass.error = null

            binding.tilEmail.isErrorEnabled = true
            binding.tilName.isErrorEnabled = true
            binding.tilConfirmPass.isErrorEnabled = true
            binding.tilPass.isErrorEnabled = true
            return true
        }
    }

}
