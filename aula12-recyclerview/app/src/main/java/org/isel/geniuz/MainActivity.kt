package org.isel.geniuz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.geniuz.lastfm.dto.ArtistDto

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Setup recyclerArtists with ArtistsAdapter
         */
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerArtists)
        recyclerView.adapter = ArtistsAdapter(arrayOfArtists())
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
    private fun arrayOfArtists(): Array<ArtistDto> =
        arrayOf(
            ArtistDto(
                "Muse",
                4129506,
                "fd857293-5ab8-40de-b29e-55a69d4e4d0f",
                "https://www.last.fm/music/Muse",
                emptyArray()
            ),
            ArtistDto(
                "Modest Mouse",
                2419127,
                "a96ac800-bfcb-412a-8a63-0a98df600700",
                "https://www.last.fm/music/Modest+Mouse",
                emptyArray()
            ),
            ArtistDto(
                "Danger Mouse",
                401108,
                "4b356f05-bcc2-4544-925b-fd9a1bf708be",
                "https://www.last.fm/music/Danger+Mouse",
                emptyArray()
            ),
            ArtistDto(
                "Mouse on Mars",
                219966,
                "4d4f5d92-0589-42fa-bc0e-1bd73e17a2ce",
                "https://www.last.fm/music/Mouse+on+Mars",
                emptyArray()
            ),
            ArtistDto(
                "Danger Mouse & Daniele Luppi",
                118847,
                "4b356f05-bcc2-4544-925b-fd9a1bf708be",
                "https://www.last.fm/music/Danger+Mouse+&+Daniele+Luppi",
                emptyArray()
            ),
            ArtistDto(
                "Mouse on the Keys",
                64385,
                "6a8744ac-5206-4d4d-84f0-78fc06924a8f",
                "https://www.last.fm/music/Mouse+on+the+Keys",
                emptyArray()
            ),
            ArtistDto(
                "Danger Mouse and Sparklehorse",
                113324,
                "4b356f05-bcc2-4544-925b-fd9a1bf708be",
                "https://www.last.fm/music/Danger+Mouse+and+Sparklehorse",
                emptyArray()
            ),
            ArtistDto(
                "Throwing Muses",
                118516,
                "b1b9d5cc-7975-43e5-960e-670583da7bc8",
                "https://www.last.fm/music/Throwing+Muses",
                emptyArray()
            ),
            ArtistDto(
                "Museum",
                211126,
                "e6129ed4-989e-4b39-ae3d-e48f1b64c28b",
                "https://www.last.fm/music/Museum",
                emptyArray()
            ),
            ArtistDto(
                "Faith and the Muse",
                102506,
                "f1019a9e-2937-42ea-b2be-291eb1003573",
                "https://www.last.fm/music/Faith+and+the+Muse",
                emptyArray()
            ),
            ArtistDto(
                "Sleepytime Gorilla Museum",
                72152,
                "7e916aa1-a00c-42e5-968d-a6f243206433",
                "https://www.last.fm/music/Sleepytime+Gorilla+Museum",
                emptyArray()
            ),
            ArtistDto(
                "Dangerous Muse",
                111250,
                "b06d1938-f4fd-4c68-bfce-13410b812706",
                "https://www.last.fm/music/Dangerous+Muse",
                emptyArray()
            ),
            ArtistDto(
                "Nine Muses",
                13875,
                "782f110a-58f9-448a-96e8-c4fd86e31de0",
                "https://www.last.fm/music/Nine+Muses",
                emptyArray()
            ),
            ArtistDto(
                "Danger Mouse & Jemini",
                83713,
                "8ea885e9-e0fd-4b80-a90a-26228501a07b",
                "https://www.last.fm/music/Danger+Mouse+&+Jemini",
                emptyArray()
            ),
            ArtistDto(
                "DE DE MOUSE",
                21045,
                "e8e26255-dd90-4745-b9e0-64c1b937684e",
                "https://www.last.fm/music/DE+DE+MOUSE",
                emptyArray()
            ),
            ArtistDto(
                "Charlie Musselwhite",
                70031,
                "034c320b-0f58-4eca-8eb2-66cafac06b3b",
                "https://www.last.fm/music/Charlie+Musselwhite",
                emptyArray()
            ),
            ArtistDto(
                "The Mouse Outfit",
                27673,
                "8e09a2d5-cd91-4b33-8477-4aba2221b250",
                "https://www.last.fm/music/The+Mouse+Outfit",
                emptyArray()
            ),
            ArtistDto(
                "Musette",
                14632,
                "7f9a10d3-3a5d-4a39-ba5c-3b36bd78a06c",
                "https://www.last.fm/music/Musette",
                emptyArray()
            ),
            ArtistDto(
                "Musée Mécanique",
                27659,
                "f1d70238-ff0b-4e1b-9e32-f96f92e8d4b0",
                "https://www.last.fm/music/Mus%C3%A9e+M%C3%A9canique",
                emptyArray()
            ),
            ArtistDto(
                "Musetta",
                49337,
                "709298e1-6685-4f54-bb5e-164f8fdbab01",
                "https://www.last.fm/music/Musetta",
                emptyArray()
            ),
            ArtistDto(
                "Field Mouse",
                24539,
                "9a16bdeb-ef43-44b4-b24d-1c2d0e9fabfe",
                "https://www.last.fm/music/Field+Mouse",
                emptyArray()
            ),
            ArtistDto(
                "Museum of Bellas Artes",
                30964,
                "958d722f-507e-48ef-95fc-31f63bc919d1",
                "https://www.last.fm/music/Museum+of+Bellas+Artes",
                emptyArray()
            ),
            ArtistDto(
                "Jordaan Mason & the Horse Museum",
                29169,
                "7c32811f-a995-4fb6-95dc-1c7fe58c904d",
                "https://www.last.fm/music/Jordaan+Mason+&+the+Horse+Museum",
                emptyArray()
            ),
            ArtistDto("Rad Museum", 9437, "", "https://www.last.fm/music/Rad+Museum", emptyArray()),
            ArtistDto(
                "Museo Rosenbach",
                11555,
                "810044af-647c-41a1-9a3c-ba7509f49454",
                "https://www.last.fm/music/Museo+Rosenbach",
                emptyArray()
            ),
            ArtistDto(
                "David Wax Museum",
                46043,
                "42b38ec4-8ecb-4241-9320-30d5dd9f6556",
                "https://www.last.fm/music/David+Wax+Museum",
                emptyArray()
            ),
            ArtistDto(
                "Mink Mussel Creek",
                22697,
                "3c4005f7-b7dd-4041-b38d-eab0fb84c030",
                "https://www.last.fm/music/Mink+Mussel+Creek",
                emptyArray()
            ),
            ArtistDto(
                "Johnny Mauser",
                3981,
                "a2d09798-b587-4551-8834-38bdb4c0dc54",
                "https://www.last.fm/music/Johnny+Mauser",
                emptyArray()
            ),
            ArtistDto(
                "Museum of Love",
                17916,
                "ae218235-f6ee-48ea-a0c5-f33b03bf08be",
                "https://www.last.fm/music/Museum+of+Love",
                emptyArray()
            ),
            ArtistDto(
                "Mighty Mouse",
                30049,
                "016cdcd4-6a4b-4353-9225-c0c821621fe7",
                "https://www.last.fm/music/Mighty+Mouse",
                emptyArray()
            ))
}
