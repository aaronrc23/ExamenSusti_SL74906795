package com.example.consumoapirest

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.consumoapirest.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleLauncher: ActivityResultLauncher<Intent>
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var sharedPreferences:SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()

        sharedPreferences= getSharedPreferences(SESSION_PREFERENCE, MODE_PRIVATE)

        firebaseAuth = Firebase.auth

        googleLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val task: Task<GoogleSignInAccount> =
                        GoogleSignIn.getSignedInAccountFromIntent(it.data)
                    try {
                        val account = task.getResult(ApiException::class.java)
                        signInFirebaseWithGoogle(account.idToken)

                    } catch (e: Exception) {

                    }
                }
            }
    }

    private fun signInFirebaseWithGoogle(idToken: String?) {
        val authCredential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(authCredential)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val user: FirebaseUser = firebaseAuth.currentUser!!
                    sharedPreferences.edit().apply(){
                        putString(EMAIL,user.email)
                            .apply()
                    }
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Ocurrio un error ", Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun setupView() {

        binding.btnlogin.setOnClickListener {
            signInWithEmailPassword()
        }
        binding.btnGoogle.setOnClickListener {
            signInWithGoogle()
        }
        binding.btnSingUp.setOnClickListener {
            signUpWithEmailPassword()
        }

    }

    private fun signUpWithEmailPassword() {
        val email = binding.txtUser.editText?.text.toString()
        val password = binding.txtPass.editText?.text.toString()

        if (validateInputs(email, password)) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Usuario Creado exitosamente ", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this, "Usuario no creado ", Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }

    private fun signInWithEmailPassword() {

        val email = binding.txtUser.editText?.text.toString()
        val password = binding.txtPass.editText?.text.toString()

        signInFirebaseWihEmail(email, password)

    }

    private fun signInFirebaseWihEmail(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val user = firebaseAuth.currentUser

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "El Usuario no se Encontro", Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun isCredentialsValidated(): Boolean {
        return validateInputs(
            binding.txtUser.editText?.text.toString(),
            binding.txtPass.editText?.text.toString()
        )
    }

    private fun signInWithGoogle() {
        val googleSignOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail().build()
        val client: GoogleSignInClient = GoogleSignIn.getClient(this, googleSignOptions)
        val intent = client.signInIntent
        googleLauncher.launch(intent)

    }

    private fun validateInputs(email: String, password: String): Boolean {

        val isEmailOk = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordOk = password.length >= 6
        return isPasswordOk && isEmailOk

    }
    companion object{
        const val SESSION_PREFERENCE:String ="SESSION_PREFERENCES"
        const val EMAIL:String ="EMAIL"
    }

}