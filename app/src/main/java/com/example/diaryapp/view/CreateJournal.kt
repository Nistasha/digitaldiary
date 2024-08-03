package com.example.diaryapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.diaryapp.R

class CreateJournal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_journal)
        val add=findViewById<Button>(R.id.button2)
        add.setOnClickListener{
            Toast.makeText(applicationContext,"Added",Toast.LENGTH_SHORT).show()
        }
    }
}