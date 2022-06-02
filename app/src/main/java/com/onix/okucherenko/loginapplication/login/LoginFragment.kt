package com.onix.okucherenko.loginapplication.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.onix.okucherenko.loginapplication.R
import com.onix.okucherenko.loginapplication.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            val userName = binding.editTextUserName.text.toString()
            val userPassword = binding.editTextPassword.text.toString()

            if (checkUserName(userName) && checkPassword(userPassword)) {
                val amount = viewModel.userName
                val action = LoginFragmentDirections.actionLoginFragmentToResultFragment(amount)
                findNavController().navigate(action)
            }
        }
    }

    private fun checkUserName(userName: String): Boolean {
        if (viewModel.checkUserName(userName)) {
            return true
        }
        binding.editTextUserName.error = getString(R.string.error_login_empty)
        return false
    }

    private fun checkPassword(userPassword: String): Boolean {

        when (viewModel.checkPassword(userPassword)) {
            1 -> {
                binding.editTextPassword.error = getString(R.string.error_lowercase)
                return false
            }
            2 -> {
                binding.editTextPassword.error = getString(R.string.error_uppercase)
                return false
            }
            3 -> {
                binding.editTextPassword.error = getString(R.string.error_number)
                return false
            }
            4 -> {
                binding.editTextPassword.error = getString(R.string.error_number)
                return false
            }
        }

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}