package com.riezki.composeprojectsubmission.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riezki.composeprojectsubmission.AnimeRepository
import com.riezki.composeprojectsubmission.model.Anime
import com.riezki.composeprojectsubmission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailScreenViewModel(private val repository: AnimeRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Anime>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Anime>>
        get() = _uiState

    fun getIdAnime(idAnime: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getDetailAnimeById(idAnime))
        }
    }
}