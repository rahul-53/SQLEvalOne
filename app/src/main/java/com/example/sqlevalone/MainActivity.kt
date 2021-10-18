package com.example.sqlevalone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

abstract class MainActivity : AppCompatActivity() {

    private lateinit var databaseHandler:DatabaseHandler
    val eventList:MutableList<EventModal> = mutableListOf()
    lateinit var mAdapter:EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        databaseHandler = DatabaseHandler(this)
        floatingActionButton.setOnClickListener {
            databaseHandler.insertRoutine("Event One","this is first event",
                "12/08/2021","Bangalore",1200)
        }
    }


}