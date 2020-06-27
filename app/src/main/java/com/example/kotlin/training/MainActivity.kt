package com.example.kotlin.training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputMessage = findViewById<EditText>(R.id.message)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            toast("Hello ${inputMessage.text}")
        }
//        val message = findViewById<TextView>(R.id.message)
//        message.text = "Hello Kotlin"

        // toast("Hello World")
    }

    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
