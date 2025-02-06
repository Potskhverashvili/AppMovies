package com.example.appmovies.presentation.auth.login

import androidx.navigation.fragment.findNavController
import com.example.appmovies.core.base.BaseFragment
import com.example.appmovies.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override fun viewCreated() {
        setListeners()
    }

    private fun setListeners() {
        binding.signUpTv.setOnClickListener {
            goToSignupPage()
        }
    }

    private fun goToSignupPage() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignUpFragment())

    }
}