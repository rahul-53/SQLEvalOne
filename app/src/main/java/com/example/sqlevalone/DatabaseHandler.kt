package com.example.sqlevalone

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DatabaseHandler(private val context: Context): SQLiteOpenHelper(context, "event_database",null, 1) {
    companion object{
        const val TABLE_NAME="event_db"
        const val  EVENT_ID = "event_id"
        const val EVENT_NAME = "event_title"
        const val EVENT_DESC = "event_desc"
        const val EVENT_DATE = "event_date"
        const val EVENT_LOCATION = "event_location"
        const val EVENT_PRICE =  "event_price"

    }
    override fun onCreate(db: SQLiteDatabase?) {
       val createQuery = "CREATE TABLE $TABLE_NAME(" +
               "$EVENT_ID INTEGER PRIMARY KEY" +
               "$EVENT_NAME TEXT" +
               "$EVENT_DESC TEXT" +
               "$EVENT_DATE TEXT" +
               "$EVENT_LOCATION TEXT" +
               "$EVENT_PRICE INTEGER )"
        db?.execSQL(createQuery)
    }
    fun insertRoutine(eventName:String, eventDesc : String, eventDate:String, eventLocation:String, eventPrice:Int){
        val db:SQLiteDatabase = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(EVENT_NAME, eventName)
        contentValues.put(EVENT_DESC, eventDesc)
        contentValues.put(EVENT_DATE, eventDate)
        contentValues.put(EVENT_LOCATION, eventLocation)
        contentValues.put(EVENT_PRICE, eventPrice)

        val id:Long = db.insert(TABLE_NAME, null, contentValues)
        if (id.toInt() ==-1){
            Toast.makeText(context, "Error while inserting data", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "data inserted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}