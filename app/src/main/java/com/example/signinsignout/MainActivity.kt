package com.example.signinsignout

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val namehere = findViewById<EditText>(R.id.name)
        val emailhere = findViewById<EditText>(R.id.email)
        val uidhere = findViewById<EditText>(R.id.uid)
        val passwordhere = findViewById<EditText>(R.id.pass)
        val btn = findViewById<Button>(R.id.button)
        val btn2 = findViewById<Button>(R.id.alreadybtn)

        btn.setOnClickListener {
            val username = namehere.text.toString()
            val uniqueid = uidhere.text.toString()
            val password = passwordhere.text.toString()
            val useremail = emailhere.text.toString()

            database = FirebaseDatabase.getInstance().getReference("User")
            val user = Users(useremail, username, password, uniqueid)
            database.child(uniqueid).setValue(user).addOnSuccessListener {
                Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this, "There's something wrong", Toast.LENGTH_SHORT).show()
            }
        }
        btn2.setOnClickListener {
            val jumptosignin = Intent(this, SignInActivity :: class.java)
            startActivity(jumptosignin)
        }

    }
}