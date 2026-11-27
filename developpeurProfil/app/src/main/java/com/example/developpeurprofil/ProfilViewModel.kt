package com.example.developpeurprofil

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class `ProfileViewModel` : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())

    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun onSearchQueryChange(query: String) {
        _uiState.update { currentState ->
            currentState.copy(searchQuery = query)
        }
    }

    fun onToggleFavoriteFilter() {
        _uiState.update { currentState ->
            currentState.copy(
                showOnlyFavorites = !currentState.showOnlyFavorites
            )
        }
    }

    fun toggleFavorite(projectId: Int) {
        _uiState.update { currentState ->

            val newFavorites =
                if (currentState.favoriteProjectIds.contains(projectId)) {
                    currentState.favoriteProjectIds - projectId
                } else {
                    currentState.favoriteProjectIds + projectId
                }

            currentState.copy(
                favoriteProjectIds = newFavorites
            )
        }
    }
}
