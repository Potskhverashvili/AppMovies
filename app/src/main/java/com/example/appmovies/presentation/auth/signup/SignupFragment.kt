package com.example.appmovies.presentation.auth.signup

import androidx.navigation.fragment.findNavController
import com.example.appmovies.core.base.BaseFragment
import com.example.appmovies.databinding.FragmentSignupBinding

class SignupFragment : BaseFragment<FragmentSignupBinding>(FragmentSignupBinding::inflate) {
    override fun viewCreated() {
        setListeners()
    }


    private fun setListeners() {
        binding.logInTv.setOnClickListener {
            goToLogInPage()
        }

        binding.signUpBtn.setOnClickListener{
            findNavController().navigate(SignupFragmentDirections.actionSignUpFragmentToContainerFragment())
        }
    }


    private fun goToLogInPage() {
        findNavController().navigate(SignupFragmentDirections.actionSignUpFragmentToLoginFragment())
    }
}