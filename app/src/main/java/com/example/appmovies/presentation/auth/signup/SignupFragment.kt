package com.example.appmovies.presentation.auth.signup

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.appmovies.core.base.BaseFragment
import com.example.appmovies.databinding.FragmentSignupBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignupFragment : BaseFragment<FragmentSignupBinding>(FragmentSignupBinding::inflate) {
    private val viewModel by viewModel<SignupViewModel>()

    override fun viewCreated() {
        setListeners()
        setCollectors()
    }

    private fun setListeners() = with(binding) {
        signUpBtn.setOnClickListener {
            val username = usernameEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val repeatPassword = repeatPasswordEditText.text.toString()
            val isValid = viewModel.isSignUpInputValid (username, email, password, repeatPassword)
            if (isValid) {
                viewModel.signupUser(username, email, password)
            }
        }

        logInTv.setOnClickListener {
            goToLogInPage()
        }
    }

    private fun setCollectors() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.signupFlow.collect {
                    findNavController().navigate(SignupFragmentDirections.actionSignUpFragmentToContainerFragment())
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.showError.collect { errorMessage ->
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoadingState.collect { isLoading ->
                    binding.progressIndicator.visibility =
                        if (isLoading) View.VISIBLE else View.GONE
                }
            }
        }
    }

    private fun goToLogInPage() {
        findNavController().navigate(SignupFragmentDirections.actionSignUpFragmentToLoginFragment())
    }
}