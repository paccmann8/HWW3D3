package com.example.hww3d3.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.BaseAdapter
import android.widget.Toast
import com.example.hww3d3.R
import com.example.hww3d3.adaptor.GuestAdapter
import com.example.hww3d3.model.Guest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_view.*
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private val fileName = "MyGuestList4.txt"
    private var guestList = mutableListOf<Guest>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        check_in_button.setOnClickListener { _ ->

            val name = editTextName.text.toString()
            val room = editText2.text.toString().toInt()
            val price = editText3.text.toString().toInt()

            val reservation = Guest(name, room, price)

            val userIntent = Intent(this, Display_Activity::class.java)
            userIntent.putExtra("name_key", reservation)
            startActivity(userIntent)

        }

    }




}


