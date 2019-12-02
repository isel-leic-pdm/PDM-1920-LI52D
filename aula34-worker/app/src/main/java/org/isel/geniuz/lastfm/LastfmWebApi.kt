package org.geniuz.lastfm

import android.content.Context
import android.os.AsyncTask
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.isel.geniuz.lastfm.dto.GetAlbumsDto
import org.isel.geniuz.lastfm.dto.SearchDto
import org.isel.geniuz.lastfm.dto.TopChartDto

const val LASTFM_API_KEY = "038cde478fb0eff567330587e8e981a4"
const val LASTFM_HOST = "http://ws.audioscrobbler.com/2.0/"
const val LASTFM_SEARCH = (LASTFM_HOST
        + "?method=artist.search&format=json&artist=%s&page=%d&api_key="
        + LASTFM_API_KEY)
const val LASTFM_GET_ALBUMS = (LASTFM_HOST
        + "?method=artist.gettopalbums&format=json&mbid=%s&page=%d&api_key="
        + LASTFM_API_KEY)
const val LASTFM_GET_ALBUM_INFO = (LASTFM_HOST
        + "?method=album.getinfo&format=json&mbid=%s&api_key="
        + LASTFM_API_KEY)
const val LASTFM_GET_TOP_CHART = (LASTFM_HOST
        + "?method=chart.gettoptracks&format=json&mbid=%s&api_key="
        + LASTFM_API_KEY)

class LastfmWebApi(ctx: Context) {

    // Instantiate the RequestQueue.
    val queue = Volley.newRequestQueue(ctx)
    val gson = Gson()

    fun searchArtist(
        name: String,
        page: Int,
        doOnSuccess: (SearchDto) -> Unit,
        onError: (VolleyError) -> Unit)
    {
        val url = String.format(LASTFM_SEARCH, name, page)
        // !!!!! ToDo: Students must refactor this code to avoid duplication of the HTTP request code !!!
        val task = object: AsyncTask<String, Int, Unit>() {
            override fun doInBackground(vararg resp: String) {
                // Thread.sleep(2000)
                val result = gson.fromJson<SearchDto>(resp[0], SearchDto::class.java)
                doOnSuccess(result)
            }
            // override fun onPostExecute(result: SearchDto) = doOnSuccess(result)
        }
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener<String> { response ->
                task.execute(response)
            },
            Response.ErrorListener { err -> onError(err)})
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    fun getAlbums(
        mbid: String,
        page: Int,
        doInSuccess: (GetAlbumsDto) -> Unit,
        onError: (VolleyError) -> Unit)
    {
        val url = String.format(LASTFM_GET_ALBUMS, mbid, page)
        // !!!!! ToDo: Students must refactor this code to avoid duplication of the

        val task = object: AsyncTask<String, Int, GetAlbumsDto>() {
            override fun doInBackground(vararg resp: String): GetAlbumsDto {
                // Thread.sleep(4000)
                val dto = gson.fromJson<GetAlbumsDto>(resp[0], GetAlbumsDto::class.java)
                doInSuccess(dto)
                return dto
            }
            // override fun onPostExecute(result: GetAlbumsDto) = doInSuccess(result)
        }

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener<String> { response -> task.execute(response) },
            Response.ErrorListener { err -> onError(err)})
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    fun getTopChart(
        doInSuccess: (TopChartDto) -> Unit,
        onError: (VolleyError) -> Unit)
    {
        val url = LASTFM_GET_TOP_CHART

        val task = object: AsyncTask<String, Int, TopChartDto>() {
            override fun doInBackground(vararg resp: String): TopChartDto {
                // Thread.sleep(4000)
                val dto = gson.fromJson<TopChartDto>(resp[0], TopChartDto::class.java)
                doInSuccess(dto)
                return dto
            }
            // override fun onPostExecute(result: GetAlbumsDto) = doInSuccess(result)
        }

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener<String> { response -> task.execute(response) },
            Response.ErrorListener { err -> onError(err)})
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}