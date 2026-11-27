package com.example.developpeurprofil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.String

@Composable
fun ProfilScreenContainer(
    name: String,
    jobTitle: String,
    description: String,
    skills: List<String>,
    isAvailable: Boolean,
    onContactClick: () -> Unit,
    onAvailabilityClick: () -> Unit,
    projects: List<Project>,
    contactInfo: List<String>,
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel = viewModel()
){
    val uiState by profileViewModel.uiState.collectAsState()

    ProfileScreen(
        name = name,
        jobTitle = jobTitle,
        description = description,
        skills = skills,
        isAvailable = isAvailable,
        onContactClick = {},
        onAvailabilityClick = {},
        projects = uiState.filteredProjects,
        contactInfo = contactInfo,
        modifier = Modifier,
        searchQuery = uiState.searchQuery,
        onSearchQueryChange = { query -> profileViewModel.onSearchQueryChange(query) },
        favoriteProjectIds = uiState.favoriteProjectIds,
        onFavoriteClick = { projectId ->
            profileViewModel.toggleFavorite(projectId)
        },
    )
}