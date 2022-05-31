package com.onix.okucherenko.loginapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.onix.okucherenko.loginapplication.databinding.FragmentLoginBinding

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState )
        val editTextUserName = binding.editTextUserName
        val editTextPassword = binding.editTextPassword
       binding.button.setOnClickListener {
           val userName = editTextUserName.text.toString()
           val userPassword = editTextPassword.text.toString()

           if (checkUserName(userName) && checkPassword(userPassword)) {
               val amount = editTextUserName.text.toString()
               val action = LoginFragmentDirections.actionLoginFragmentToResultFragment(amount)
               findNavController().navigate(action)
           }
       }
    }

    private fun checkUserName(userName: String): Boolean {
        if (userName.isEmpty() || userName.contains( " ")) {
            binding.editTextUserName.error = "Field cannot be empty or or contain spaces"
            return false
        }
        return true
    }

    private fun checkPassword(password: String): Boolean {
        if (password.uppercase() == password) {
            binding.editTextPassword.error = "The password must contain lowercase letters"
            return false
        } else if ( password.lowercase() == password) {
            binding.editTextPassword.error = "The password must contain uppercase letters"
            return false
        } else if (!password.matches(Regex(".*[0-9]"))) {
            binding.editTextPassword.error = "The password must contain numbers"
            return false
        } else if (password.length < 8) {
            binding.editTextPassword.error = "The password must contain at least 8 characters"
            return false
        }
        return true
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}