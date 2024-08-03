package com.example.diaryapp.data

import android.provider.BaseColumns

//Lab 7 Sqlite Tutorial
object DBContract {
    /* Inner class that defines the table contents */
    class UserEntry : BaseColumns {
        companion object {
            const val DB_VERSION =6
            const val TABLE_NAME = "users"
           // const val COLUMN_USER_ID = "userid"
            const val COLUMN_NAME = "username"
            const val COLUMN_PASSWORD = "password"
        }
    }
}