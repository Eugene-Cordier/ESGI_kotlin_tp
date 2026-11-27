package com.example.developpeurprofil

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
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
    modifier: Modifier = Modifier
){
    var searchQuery by rememberSaveable{
        mutableStateOf("")
    }

    val filteredProjects = remember(projects, searchQuery) {
        projects.filter { project ->
            val multisearch =
                project.title.contains(searchQuery, ignoreCase = true) ||
                project.techno.contains(searchQuery,ignoreCase = true)

            multisearch
        }
    }
    ProfileScreen(
        name = name,
        jobTitle = jobTitle,
        description = description,
        skills = skills,
        isAvailable = isAvailable,
        onContactClick = {},
        onAvailabilityClick = {},
        projects = filteredProjects,
        contactInfo = contactInfo,
        modifier = Modifier,
        searchQuery = searchQuery,
        onSearchQueryChange = { searchQuery = it }
    )
}