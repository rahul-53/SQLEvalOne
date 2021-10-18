package com.example.sqlevalone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var databaseHandler:DatabaseHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        databaseHandler = DatabaseHandler(this)
        floatingActionButtonAdd.setOnClickListener {
            databaseHandler.insertRoutine("Event One","this is first event",
                "12/08/2021","Bangalore",1200)
        }
    }
}