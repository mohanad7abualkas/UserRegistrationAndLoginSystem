package com.example.userregistrationandloginsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.userregistrationandloginsystem.databinding.ActivityLoginBinding

class login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbH = DatabaseHelper(this)

        binding.btnLogIn.setOnClickListener {
            val password = binding.etPassword.text.toString()
            val userName = binding.etUserName.text.toString()

            if (TextUtils.isEmpty(userName)|| TextUtils.isEmpty(password)){
                Toast.makeText(this, "Add Username & Password", Toast.LENGTH_SHORT).show()

            } else {
                val check = dbH.checkPassword(userName, password)
                if (check){
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, success_login::class.java)
                    startActivity(intent)

                } else{
                    Toast.makeText(this, "Wrong Username Or Password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}