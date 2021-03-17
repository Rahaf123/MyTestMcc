package com.example.mytestmcc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var db: FirebaseFirestore?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = FirebaseFirestore.getInstance()

         save.setOnClickListener {
            var name =Name.text.toString()
            var phone = Number.text.toString()
            var address = Address.text.toString()
            var id = UUID.randomUUID().toString()
            addData(id,name,phone,address)

        }
        get.setOnClickListener {
            var r = Intent(this,UserActivity::class.java)
            startActivity(r)
        }


    }
    private fun addData(name:String,phone:String,address:String,id : String){
        var Users = hashMapOf("name" to name,"phone" to phone,"address" to address,"id" to id)

        db!!.collection("users").add(Users).addOnSuccessListener {documentReference ->
            Log.e("TAG","Saved successfully")

        }.addOnFailureListener { exception -> Log.e("TAG","Error adding document $exception")
        }
    }

}