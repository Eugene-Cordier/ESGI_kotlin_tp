package com.example.developpeurprofil

data class ProfileUiState(
    val projects: List<Project> = sampleprojectList(),
    val searchQuery: String = "",
    val showOnlyFavorites: Boolean = false,
    val favoriteProjectIds: List<Int> = emptyList()
) {
    val filteredProjects: List<Project>
        get() {
            return projects.filter { project ->

                val matchesSearch =
                    project.title.contains(searchQuery, ignoreCase = true) ||
                            project.techno.contains(searchQuery, ignoreCase = true)

                val matchesFavorite =
                    !showOnlyFavorites ||
                            favoriteProjectIds.contains(project.id)

                matchesSearch && matchesFavorite
            }
        }
}
