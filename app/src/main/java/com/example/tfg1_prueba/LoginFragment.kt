package com.example.tfg1_prueba

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tfg1_prueba.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LoginFragment : Fragment() {


    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth = Firebase.auth
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.botonSiguiente.setOnClickListener {
            auth.signInWithEmailAndPassword(binding.correoUsuLogin.text.toString(),
                binding.contraUsuLogin.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                    }else{
                        Snackbar.make(binding.root,"Credenciales incorrectas",Snackbar.LENGTH_SHORT).show()
                    }
                }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}