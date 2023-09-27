package com.danish.contactmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.danish.contactmanager.databinding.ActivitySignInBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {
    companion object{
        const val KEY = "com.danish.contactmanager.SignIn.phone"
    }
    private lateinit var binding:ActivitySignInBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Users")
        binding.btnSignin.setOnClickListener {
            val phone = binding.etPhone.text.toString()
            val password = binding.etPassword.text.toString()

            database.child(phone).get().addOnSuccessListener {
                if(it.exists()){
                    if(it.child("password").value == password) {
                        Toast.makeText(this, "Logging In", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Home::class.java)
                        intent.putExtra(KEY, phone)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this,"Incorrect Password",Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this,"User doesn't exist",Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this,"Internal Error Occurred",Toast.LENGTH_SHORT).show()
            }


        }

    }
}