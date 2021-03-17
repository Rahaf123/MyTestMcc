package com.example.mytestmcc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_item.view.*
import kotlinx.android.synthetic.main.activity_user.*



class UserActivity : AppCompatActivity() {
    var db: FirebaseFirestore?=null
    var adapter:FirestoreRecyclerAdapter<User,UserViewHolder>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        db = Firebase.firestore
        getAllUser()

    }
    fun getAllUser(){
        val query = db!!.collection("users")
        val options = FirestoreRecyclerOptions.Builder<User>().setQuery(query,User::class.java).build()
        adapter = object :FirestoreRecyclerAdapter<User,UserViewHolder>(options){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
                val view = LayoutInflater.from(this@UserActivity).inflate(R.layout.activity_item,parent,false)
                return UserViewHolder(view)
            }

            override fun onBindViewHolder(holder: UserViewHolder, position: Int, model: User) {
                holder.name.text = model.name
                holder.phone.text = model.phone
                holder.address.text = model.address

            }
        }
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }

    class UserViewHolder(view:View):RecyclerView.ViewHolder(view){
        var name = view.name
        var phone = view.phone
        var address = view.address
    }

    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter!!.stopListening()
    }}
