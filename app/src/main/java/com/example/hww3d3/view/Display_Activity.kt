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
import kotlinx.android.synthetic.main.list_item_view.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.InputStreamReader

private val fileName = "MyGuestList4.txt"
private var guestList = mutableListOf<Guest>()

class Display_Activity : AppCompatActivity(), GuestAdapter.GuestAdapterDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_item_view)


        val reserve: Guest = ("name_key") as Guest

        reserve.let { reservation ->
            val name_to_use = reservation.name
            val room_to_use = reservation.roomNumber
            val price_to_use = reservation.price


            writeToFile(name_to_use, room_to_use, price_to_use)


        }


        check_in_button.setOnClickListener { _ ->

            val backIntent = Intent(this, MainActivity::class.java)
            startActivity(backIntent)

        }


    }

    private fun writeToFile(name: String?, room: Int?, price: Int?) {
        val fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND)
        val inputString =
            "\n$name:$room:$price"
        fileOutputStream.write(inputString.toByteArray())
        Toast.makeText(
            this,
            "Room #$room checked into!",
            Toast.LENGTH_SHORT
        ).show()

        //clearFields()
        readFromExternal()
    }

    private fun readFromExternal() {
        val fileInputStream = openFileInput(fileName)
        val fileInputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(fileInputStreamReader)

        var readSting: String? = null
        val delimiter = ":"

        guestList = mutableListOf()
        while ({ readSting = bufferedReader.readLine(); readSting }() != null) {
            val myInput = readSting?.split(delimiter)
            if (myInput?.size ?: 0 > 1) {
                val readGuest = Guest(
                    myInput?.get(0),
                    Integer.parseInt(myInput?.get(1) ?: "0"),
                    Integer.parseInt(myInput?.get(2) ?: "0")
                )
                guestList.add(readGuest)
            }
        }

        val myBaseAdapter = GuestAdapter(guestList, this)
        hotel_list_view.adapter = myBaseAdapter
    }


    override fun deleteBooking(guestPosition: Int) {
        deleteItem(guestPosition)
    }

    private fun deleteItem(position: Int) {
        guestList.removeAt(position)
        var fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
        for (i in 0 until guestList.size) {
            val guestAsString =
                "${guestList.get(i).name}:${guestList.get(i).roomNumber}:${guestList.get(i).price}:"
            if (i == 0) {
                fileOutputStream.write(guestAsString.toByteArray())



            }

            else {
                fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND)
                fileOutputStream.write(guestAsString.toByteArray())

            }
        }
        Toast.makeText(this, "Guest checked out!", Toast.LENGTH_SHORT).show()
        (hotel_list_view.adapter as BaseAdapter).notifyDataSetChanged()
    }

}