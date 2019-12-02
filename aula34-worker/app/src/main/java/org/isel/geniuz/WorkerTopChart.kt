package org.isel.geniuz

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.isel.geniuz.lastfm.dto.TopChartDto
import org.isel.geniuz.model.TopChartTrack
import java.lang.Exception
import java.util.concurrent.CompletableFuture

class WorkerTopChart(context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
){
    override fun doWork(): Result {
        val cf = CompletableFuture<TopChartDto>()
        Log.v(TAG, "Get TopChart in background...")
        GeniuzApp.lastfm.getTopChart({
            cf.complete(it)
        }, {
            cf.completeExceptionally(it)
        })
        return try {
            val dto: TopChartDto = cf.get()
            Log.v(TAG, "Get TopChart COMPLETED")
            val arr = dtoToModel(dto)
            GeniuzApp.db.topchartDao().insertAll(*arr)
            Result.success()
        } catch (e: Exception) {
            Log.v(TAG, "Get TopChart FAILED")
            Result.failure()
        }
    }
    private fun dtoToModel(dto: TopChartDto): Array<TopChartTrack> {
        return dto
            .tracks
            .track
            .map { TopChartTrack( it.name, it.url, it.duration, it.playcount, it.listeners)}
            .toTypedArray()
    }

}