package com.example.expressotest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java

class LoginActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.emailInput)
        val password = findViewById<EditText>(R.id.passwordInput)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val emailText = email.text.toString()
            val passText = password.text.toString()

            if (emailText.isBlank()){
                email.error = "Email Required"
                return@setOnClickListener
            }

            if (passText.isBlank()){
                password.error = "Password required"
                return@setOnClickListener
            }

            startActivity(Intent(this, WelcomeActivity::class.java))
        }
    }
}