package org.isel.pdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        this.lifecycle.addObserver(LifecycleLogger(componentName.className))
        val msg = intent.getStringExtra(DETAILS_MESSAGE)
        findViewById<TextView>(R.id.textViewMessage).text = msg
    }
}
