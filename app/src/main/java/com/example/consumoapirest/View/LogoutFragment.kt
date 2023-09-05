package com.example.consumoapirest.View

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.consumoapirest.LoginActivity
import com.example.consumoapirest.R
import com.example.consumoapirest.SplashActivity
import com.example.consumoapirest.databinding.ActivityLoginBinding
import com.example.consumoapirest.databinding.FragmentLogoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LogoutFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: FragmentLogoutBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences=requireActivity().getSharedPreferences(
            LoginActivity.SESSION_PREFERENCE,
            AppCompatActivity.MODE_PRIVATE
        )
        firebaseAuth=Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLogoutBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCerrar.setOnClickListener {
            firebaseAuth.signOut()
            sharedPreferences.edit().apply(){
                putString(LoginActivity.EMAIL,"")
                .apply()
            }
            val intent=Intent(requireActivity(),SplashActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }
    }


}