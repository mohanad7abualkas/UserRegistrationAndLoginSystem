package com.example.userregistrationandloginsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.userregistrationandloginsystem.databinding.ActivitySignupBinding

class signup : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DatabaseHelper(this)

        binding.btnSignUp.setOnClickListener {
            val username = binding.etUserName.text.toString()
            val password = binding.etPassword.text.toString()
            val cPassword = binding.etConformPassword.text.toString()
            val saveData = db.insertdata(username, password)

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(cPassword)) {
                Toast.makeText(
                    this,
                    "Add Username, Password & Conform Password",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                if (password == cPassword) {
                    if (saveData) {
                        Toast.makeText(this, "SignUp Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, login::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this, "User Exists", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(this, "Password Not Match !", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}