package com.riezki.composeprojectsubmission.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riezki.composeprojectsubmission.AnimeRepository
import com.riezki.composeprojectsubmission.model.Anime
import com.riezki.composeprojectsubmission.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: AnimeRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Anime>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Anime>>>
        get() = _uiState

    fun getAllAnime() {
        viewModelScope.launch {
            repository.getAllListAnime()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { animeData ->
                    _uiState.value = UiState.Success(animeData)
                }
        }
    }
}