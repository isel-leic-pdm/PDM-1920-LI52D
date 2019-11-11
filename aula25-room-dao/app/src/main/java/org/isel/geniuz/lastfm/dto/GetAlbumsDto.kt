package org.isel.geniuz.lastfm.dto

class GetAlbumsDto(val topalbums: TopAlbumsDto)

class TopAlbumsDto(val album: Array<AlbumDto>)
