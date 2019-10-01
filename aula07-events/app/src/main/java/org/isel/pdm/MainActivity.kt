package org.isel.pdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

const val DETAILS_MESSAGE : String = "Details Message"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bt = findViewById<Button>(R.id.buttonSendMessage)
        bt.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            val msg = findViewById<EditText>(R.id.editTextMessage)
            intent.putExtra(DETAILS_MESSAGE, msg.text.toString())
            startActivity(intent)
        }
        this.lifecycle.addObserver(LifecycleLogger(componentName.className))
    }

    /*
    override fun onStart() {
        super.onStart()
        println("Now I am visible (Start)")
    }

    override fun onResume() {
        super.onResume()
        println("Now I start interaction (Resume)")
    }

    override fun onPause() {
        super.onPause()
        println("Loose foreground (Paused)")
    }

    override fun onStop() {
        super.onStop()
        println("I am being closed (Stopped)")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("I am being Destroyed")
    }
     */
}
