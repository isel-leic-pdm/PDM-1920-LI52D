package org.isel.geniuz

import androidx.lifecycle.ViewModel

class ChartViewModel : ViewModel() {
    val topchart = GeniuzApp.db.topchartDao().getAll()
}