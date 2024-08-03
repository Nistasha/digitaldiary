package com.example.diaryapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.diaryapp.R
import com.example.diaryapp.data.UserDBHelper
import com.example.diaryapp.model.UserModel

class LoginActivity : AppCompatActivity() {
    lateinit var userDBHelper: UserDBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val signInButton=findViewById<Button>(R.id.signIn)

        val userName=findViewById<EditText>(R.id.edittext_name)
        val passWord=findViewById<EditText>(R.id.editText_password)
        userDBHelper= UserDBHelper(this)



        signInButton.setOnClickListener{
//            val loginuser : UserModel = userDBHelper.readUser(userName.toString())
//            if (!loginuser.password.equals(passWord)){
//                Toast.makeText(applicationContext,"Incorrect password",Toast.LENGTH_SHORT).show()
//            }
//            else{
                startActivity(Intent(applicationContext, MainActivity::class.java))
//
//            }
//            if (userName.text.toString()=="nis" && passWord.text.toString()=="abc")
//                startActivity(Intent(applicationContext, MainActivity::class.java))
//            else
//                Toast.makeText(applicationContext,"Wrong credebtials",Toast.LENGTH_SHORT).show()
        }
    }
}
