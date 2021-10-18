package com.example.sqlevalone

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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
               "$EVENT_PRICE TEXT )"
        db?.execSQL(createQuery)
    }
    fun insertRoutine(eventName:String, eventDesc : String, eventDate:String, eventLocation:String, eventPrice:String){
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
    fun updateRoutine(id:Int, newEventName:String, newEventDesc : String, newEventDate:String, newEventLocation:String, newEventPrice:String) {
        val db:SQLiteDatabase = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(EVENT_NAME, newEventName)
        contentValues.put(EVENT_DESC, newEventDesc)
        contentValues.put(EVENT_DATE, newEventDate)
        contentValues.put(EVENT_LOCATION, newEventLocation)
        contentValues.put(EVENT_PRICE, newEventPrice)
        val updatedRows = db.update(TABLE_NAME, contentValues, "id=$id",null)

        if (updatedRows>0){
            Toast.makeText(context, "data updated successfully", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "error while updating data ", Toast.LENGTH_SHORT).show()
        }
    }
    fun deleteRoutine(id: Int){
        val db:SQLiteDatabase = writableDatabase
        val contentValues = ContentValues()
        val deletedRow = db.delete(TABLE_NAME,"id=$id",null)

        if (deletedRow>0){
            Toast.makeText(context, "data deleted successfully", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context, "error while deleting  ", Toast.LENGTH_SHORT).show()
        }
    }

    fun getRoutines():MutableList<EventModal>{
        val eventList:MutableList<EventModal> = mutableListOf()
        val db:SQLiteDatabase = readableDatabase
        val query = "select * from $TABLE_NAME"
        val queryCursor:Cursor = db.rawQuery(query, null)

        if (queryCursor !=null && queryCursor.count>0){
            queryCursor.moveToFirst()
            do {
                val idIndex:Int =queryCursor.getColumnIndex(EVENT_ID)
                val nameIndex:Int =queryCursor.getColumnIndex(EVENT_NAME)
                val descIndex:Int =queryCursor.getColumnIndex(EVENT_DESC)
                val dateIndex:Int =queryCursor.getColumnIndex(EVENT_DATE)
                val locationIndex:Int =queryCursor.getColumnIndex(EVENT_LOCATION)
                val priceIndex:Int =queryCursor.getColumnIndex(EVENT_PRICE)

                val id:Int = queryCursor.getInt(idIndex)
                val name:String = queryCursor.getString(nameIndex)
                val desc:String = queryCursor.getString(descIndex)
                val date:String = queryCursor.getString(dateIndex)
                val location:String = queryCursor.getString(locationIndex)
                val price:String = queryCursor.getString(priceIndex)

             val events = EventModal(id,name, desc, date,location, price)
             eventList.add(events)
            }while (queryCursor.moveToNext())
        }
        return eventList
    }

//    fun searchRoutine(){
//
//    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}