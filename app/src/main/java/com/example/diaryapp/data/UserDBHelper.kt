package com.example.diaryapp.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.diaryapp.model.UserModel

class UserDBHelper(context: Context): SQLiteOpenHelper(context,DB_NAME,null, DBContract.UserEntry.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)  /* Function to create the table in the Database */
    }
    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL(TABLE_ENTRY)  // Function to drop the existing table
        onCreate(db)  // Calling the  function to create the upgraded table
    }

    @kotlin.jvm.Throws(SQLiteConstraintException::class) //to throw exception
    fun addUser(user: UserModel) : Boolean{     // Function adding details to user information table
        val db = writableDatabase
        val values= ContentValues()
       // values.put(DBDetails.UserInfo.COLUMN_EMAIL, user.email)
        values.put(DBContract.UserEntry.COLUMN_NAME,user.username)
        values.put(DBContract.UserEntry.COLUMN_PASSWORD,user.password)
        db.insert(DBContract.UserEntry.TABLE_NAME, null,values)   //inserting the details to the table
        return true
    }

    @kotlin.jvm.Throws(SQLiteConstraintException::class)
    fun readUser(name: String): UserModel {
        val user: UserModel
        var name: String =""
        var password: String=""
        val cursor: Cursor?
        val db = writableDatabase
        cursor= db.rawQuery("select * from "+DBContract.UserEntry.TABLE_NAME+" WHERE "+DBContract.UserEntry.COLUMN_PASSWORD+"='"+name+"'", null)
        if (cursor.moveToFirst()){
            val nameTemp=cursor.getColumnIndex(DBContract.UserEntry.COLUMN_NAME)
            if (nameTemp>=0){
                name=cursor.getString(nameTemp)
            }
            val passwordTemp=cursor.getColumnIndex(DBContract.UserEntry.COLUMN_PASSWORD)
            if (passwordTemp>=0){
                password=cursor.getString(passwordTemp)
            }
        }
        cursor.close()
        user= UserModel(name,password)
        return user
    }

    @Throws(SQLiteConstraintException::class)
    fun insertUser(user: UserModel): Boolean {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues()
        //values.put(DBContract.UserEntry.COLUMN_USER_ID, user.userid)
        values.put(DBContract.UserEntry.COLUMN_NAME, user.username)
        values.put(DBContract.UserEntry.COLUMN_PASSWORD, user.password)

        // Insert the new row, returning the primary key value of the new row
        db.insert(DBContract.UserEntry.TABLE_NAME, null, values)

        return true
    }

    fun doesUseralreadyexist(email:String): Boolean{

        var u: UserModel= readUser(email)
        return !(u.username.equals("") && u.password.equals(""))

    }


    companion object {
        val DB_NAME="BMIDatabase.db"

        private val SQL_CREATE_ENTRIES=
            "CREATE TABLE " + DBContract.UserEntry.TABLE_NAME+ " (" +
                    DBContract.UserEntry.COLUMN_NAME+"  TEXT PRIMARY KEY,"+
                    DBContract.UserEntry.COLUMN_PASSWORD+" TEXT)"

        private val TABLE_ENTRY ="DROP TABLE IF EXISTS " + DBContract.UserEntry.TABLE_NAME
    }
}