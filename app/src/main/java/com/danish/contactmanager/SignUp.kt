package com.danish.contactmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.danish.contactmanager.databinding.ActivitySignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    private lateinit var binding:ActivitySignUpBinding
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Users")

        binding.btnSignup.setOnClickListener {
            val name = binding.etName.text.toString()
            val mail = binding.etMail.text.toString()
            val phone = binding.etPhone.text.toString()
            val password = binding.etPassword.text.toString()

            val user = User(name, mail, phone, password)

            database.child(phone).setValue(user).addOnSuccessListener {
                Toast.makeText(this,"User registered successfully",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Internal Error Occurred",Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSignin.setOnClickListener {
            startActivity(Intent(this, SignIn::class.java))
        }

    }
}