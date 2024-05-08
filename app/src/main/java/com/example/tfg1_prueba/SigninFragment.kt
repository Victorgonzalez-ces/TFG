package com.example.tfg1_prueba

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tfg1_prueba.databinding.FragmentSigninBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SigninFragment : Fragment() {

    private lateinit var binding: FragmentSigninBinding
    private lateinit var auth: FirebaseAuth

    override fun onAttach(context: Context) {
        super.onAttach(context)
        auth = Firebase.auth
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.botonConfirmar.setOnClickListener {
            if(isEmailValid(binding.correoUsuSignIn.text.toString())){
                if (binding.contraUsuSignIn.text.toString() == binding.contra2UsuSignIn.text.toString()){
                    auth.createUserWithEmailAndPassword(binding.correoUsuSignIn.text.toString(),
                        binding.contraUsuSignIn.text.toString())
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                findNavController().navigate(R.id.action_signinFragment_to_loginFragment)
                            }else{
                                 Snackbar.make(binding.root,"Datos inválidos",Snackbar.LENGTH_SHORT).show()
                            }
                        }
                }else{
                    Snackbar.make(binding.root,"La contraseña no coincide con la otra contraseña",Snackbar.LENGTH_SHORT).show()
                }
            }else{
                Snackbar.make(binding.root,"El correo electrónico no es válido",Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}