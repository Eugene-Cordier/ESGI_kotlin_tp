package com.example.developpeurprofil

data class Project(
    val title: String,
    val url: String
)
fun sampleprojectList(): List<Project> {
    return listOf(
        Project("project1", "https://github.com"),
        Project("project2", "https://gitlab.com"),
        Project("project3", "https://github.com")
    )
}
