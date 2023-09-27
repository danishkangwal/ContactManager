package com.danish.contactmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.danish.contactmanager.databinding.ActivityContactBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddContact : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val Userphone = intent.getStringExtra(SignIn.KEY).toString()

        Toast.makeText(this,"${Userphone}",Toast.LENGTH_LONG).show()

        database = FirebaseDatabase.getInstance().getReference("Users").child(Userphone)
        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val mail = binding.etMail.text.toString()
            val phone = binding.etPhone.text.toString()

            val contact = Contact(name,mail,phone)

            database.child("Contacts").child(phone).get().addOnSuccessListener {
                if(it.exists()){
                    Toast.makeText(this,"Contact Already Exists",Toast.LENGTH_SHORT).show()
                }
                else{
                    database.child("Contacts").child(phone).setValue(contact).addOnSuccessListener {
                        Toast.makeText(this,"Contact added successfully",Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this,"Unable to add",Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}