package com.example.appmovies.presentation.auth.passwordRecovery

import androidx.navigation.fragment.findNavController
import com.example.appmovies.core.base.BaseFragment
import com.example.appmovies.databinding.FragmentConfirmEmailBinding

class ConfirmEmailFragment :
    BaseFragment<FragmentConfirmEmailBinding>(FragmentConfirmEmailBinding::inflate) {

    override fun viewCreated() {
        setListeners()
    }

    private fun setListeners() = with(binding) {
        btnLogInPage.setOnClickListener {
            findNavController().navigate(ConfirmEmailFragmentDirections.actionConfirmEmailFragmentToLoginFragment())
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}