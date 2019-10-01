package org.isel.pdm

import android.content.ComponentName
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LifecycleLogger(val name: String) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        println("$name: Now I am been CREATED")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        println("$name: Now I am visible (Start)")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        println("$name: Now I start interaction (Resume)")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        println("$name: Loose foreground (Paused)")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        println("$name: I am being closed (Stopped)")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        println("$name: I am being Destroyed")
    }
}
