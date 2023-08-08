package com.example.userregistrationandloginsystem

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, "Userdata", null, 1)  {


    /*
    // Define Database Constance
    companion object {
        private const val databaseName = "user_credentials.db"
        private const val databaseVersion = 1
        private const val tableName = "users"
        private const val columnUserName = "username"
        private const val columnPassword = "password"
    }

     */


    // User Table
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("Create table Userdata (username TEXT primary key, password TEXT)")
    }

    // Upgrade Database If Needed
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists Userdata")
    }

    // Insert Data
    fun insertdata (username: String, password: String): Boolean {
        val db = this.writableDatabase


        val contentValues = ContentValues().apply {
            put("username", username)
            put("password", password)
            /*
            OR .. Another way
            val contentValues = ContentValues()
            contentValues.put("username", username)
            contentValues.put("password", password)
             */
        }
        val result = db.insert("Userdata", null, contentValues)
        if (result > 0){ // ??
            return false
        }
        return true
        //db.close() // ??
    }

    //
    fun checkPassword (username: String, password: String): Boolean{
        val result = this.writableDatabase
        val query = "select * from Userdata where username= '$username' and password = '$password'"
        val cursor = result.rawQuery(query,null)
        if (cursor.count<=0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

}










/*
  fun getPrivateUserName(): String{
        return columnUserName
    }

    fun getPrivatePassword(): String{
        return columnPassword
    }

    fun getPrivateTableName(): String{
        return tableName
    }

    fun getPrivateDatabaseName(): String{
        return databaseName
    }
 */