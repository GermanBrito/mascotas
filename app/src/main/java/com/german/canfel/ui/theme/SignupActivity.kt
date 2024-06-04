package com.german.canfel.ui.theme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.german.canfel.R
import com.german.canfel.databinding.ActivityLoginBinding
import com.german.canfel.databinding.ActivitySignupBinding
import java.net.PasswordAuthentication

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        databaseHelper = DatabaseHelper(this)


        binding.signupButton.setOnClickListener {
            val signupUsername = binding.signupUsername.text.toString()
            val signupPassword = binding.signupPassword.text.toString()
            val signupName = binding.signupName.text.toString()
            val signupSurname = binding.signupSurname.text.toString()
            val signupPhone = binding.signupPhone.text.toString()
            val signupMail = binding.signupMail.text.toString()


            signupDatabase(signupUsername, signupPassword, signupName, signupSurname, signupPhone, signupMail)
        }

        binding.loginRedirect.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    private fun signupDatabase(username: String, password: String, name: String, surname: String, phone: String, mail: String) {
        val insertedRowId = databaseHelper.insertUser(username, password, name, surname, phone, mail)
        if (insertedRowId != -1L){
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Registro fallido", Toast.LENGTH_SHORT).show()
        }
    }
}