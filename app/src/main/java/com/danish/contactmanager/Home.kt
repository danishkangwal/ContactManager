package com.danish.contactmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.danish.contactmanager.databinding.ActivityHomeBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Home : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding :ActivityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val phone = intent.getStringExtra("com.danish.contactmanager.SignIn.phone")

        database = FirebaseDatabase.getInstance().getReference("Users")

        if (phone != null) {
            database.child(phone).child("Contacts").get().addOnSuccessListener {
                binding.tv.text = it.toString()
            }
        }

    }
}