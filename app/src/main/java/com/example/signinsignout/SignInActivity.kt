package com.example.signinsignout

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {
    private lateinit var dbReference : DatabaseReference

    companion object{
        const val KEY1 = "com.example.signinsignout.SignInActivity.mail"
        const val KEY2 = "com.example.signinsignout.SignInActivity.name"
        const val KEY3 = "com.example.signinsignout.SignInActivity.id"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val getId = findViewById<TextInputEditText>(R.id.idSignIn)
        val btnSignIn = findViewById<Button>(R.id.signInBtn)

        btnSignIn.setOnClickListener {
            val getUniqueId = getId.text.toString()
            if(getUniqueId.isNotEmpty())
            {
                readData(getUniqueId)
            }
            else{
                Toast.makeText(this, "Please enter the id!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(uniqueId: String) {
        dbReference = FirebaseDatabase.getInstance().getReference("User")

        dbReference.child(uniqueId).get().addOnSuccessListener {
            if(it.exists())
            {
                val email = it.child("email").value
                val name = it.child("name").value
                val id = it.child("uid").value

                val intent = Intent(this, mainScreen :: class.java)
                intent.putExtra(KEY1, email.toString())
                intent.putExtra(KEY2, name.toString())
                intent.putExtra(KEY3, id.toString())
                startActivity(intent)
            }
        }.addOnFailureListener{
            Toast.makeText(this, "User does not exists!", Toast.LENGTH_SHORT).show()
        }
    }
}