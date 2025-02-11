package com.example.appmovies.presentation.auth.passwordRecovery

import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.appmovies.core.base.BaseFragment
import com.example.appmovies.databinding.FragmentPasswordRecoveryBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PasswordRecoveryFragment :
    BaseFragment<FragmentPasswordRecoveryBinding>(FragmentPasswordRecoveryBinding::inflate) {
    private val viewmodel by viewModel<PasswordRecoveryViewModel>()

    override fun viewCreated() {
        setListeners()
        setCollectors()
    }

    private fun setListeners() = with(binding) {
        btnSend.setOnClickListener {
            val email = emailAddress.text.toString()
            if (viewmodel.isEmailValid(email)) {
                viewmodel.passwordRecovery(email = email)
            } else {
                emailAddress.error = "Please enter a valid email"
                return@setOnClickListener
            }
        }

        backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setCollectors() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.isEmailSend.collect {
                findNavController().navigate(PasswordRecoveryFragmentDirections.actionPasswordRecoveryFragmentToConfirmEmailFragment())
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.showError.collect { error ->
                Toast.makeText(
                    requireContext(),
                    "Error: ${error.toString()}", Toast.LENGTH_SHORT
                ).show()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.isLoadingState.collect { isLoading ->
                binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }
    }
}