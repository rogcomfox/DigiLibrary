package com.nusantarian.digilibrary.fragment.auth

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
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
        onClickLogin()
        return view
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_start) {
            ft.add(R.id.main_frame,
                RegisterFragment()
            )
                .addToBackStack(null)
                .commit()
        }
    }

    private fun onClickLogin() {
        val text = activity!!.resources.getString(R.string.tv_login)
        val ss = SpannableString(text)
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                ft.add(R.id.main_frame,
                    LoginFragment()
                )
                    .addToBackStack(null)
                    .commit()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = activity!!.getColor(R.color.colorPrimary)
                ds.typeface = Typeface.DEFAULT_BOLD
            }
        }
        //set substring to text view
        ss.setSpan(clickableSpan, 26, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvLogin.text = ss
        binding.tvLogin.movementMethod = LinkMovementMethod.getInstance()
    }

}
