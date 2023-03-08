package com.riezki.composeprojectsubmission.injection

import com.riezki.composeprojectsubmission.AnimeRepository


object Injection {
    fun provideRepository(): AnimeRepository {
        return AnimeRepository.getIntance()
    }
}