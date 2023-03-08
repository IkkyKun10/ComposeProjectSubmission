package com.riezki.composeprojectsubmission

import com.riezki.composeprojectsubmission.model.Anime
import com.riezki.composeprojectsubmission.model.AnimeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AnimeRepository {
    private val animeData = mutableListOf<Anime>()

    init {
        if (animeData.isEmpty()) {
            AnimeData.listAnime.forEach {
                animeData.add(
                    Anime(
                        id = it.id,
                        name = it.name,
                        overview = it.overview,
                        img = it.img,
                        date = it.date,
                        genre = it.genre,
                        desc = it.desc,
                        rating = it.rating,
                        studio = it.studio,
                    )
                )
            }
        }
    }

    fun getAllListAnime() : Flow<List<Anime>> {
        return flowOf(animeData)
    }

    fun getDetailAnimeById(idAnime: String) : Anime {
        return animeData.first {
            it.id == idAnime
        }
    }

    companion object {
        @Volatile
        private var instance: AnimeRepository? = null

        fun getIntance() : AnimeRepository =
            instance ?: synchronized(this) {
                AnimeRepository().apply {
                    instance = this
                }
            }
    }
}