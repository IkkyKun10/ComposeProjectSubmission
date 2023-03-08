package com.riezki.composeprojectsubmission.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riezki.composeprojectsubmission.AnimeRepository
import com.riezki.composeprojectsubmission.ui.detail.DetailScreenViewModel
import com.riezki.composeprojectsubmission.ui.home.HomeViewModel

class ViewModelFactory(private val repository: AnimeRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailScreenViewModel::class.java)) {
            return DetailScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}