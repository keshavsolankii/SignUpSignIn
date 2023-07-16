package com.example.signinsignout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class mainScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val email = intent.getStringExtra(SignInActivity.KEY1)
        val name = intent.getStringExtra(SignInActivity.KEY2)
        val id = intent.getStringExtra(SignInActivity.KEY3)

        val uid = findViewById<TextView>(R.id.user_id)
        val uname = findViewById<TextView>(R.id.user_name)
        val umail = findViewById<TextView>(R.id.user_mail)

        uname.text = "Welcome $name"
        uid.text = "Your ID is $id"
        umail.text = "Your e-mail is $email"
    }
}