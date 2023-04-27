package com.example.quizapp_ver1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button : Button = findViewById(R.id.btn)
        val editText : EditText = findViewById(R.id.edittext)
        val quitBtn : Button = findViewById(R.id.quitBtn)

        button.setOnClickListener {
            if (editText.text.isEmpty()) {
                Toast.makeText(this, "PLease enter your name",Toast.LENGTH_LONG).show()
            }
            else{
                User.memsetUser()
                User.setUserName(editText.text.toString())
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            }
        }

        quitBtn.setOnClickListener{
            this.finishAffinity()
        }
    }
}