package com.example.developpeurprofil

data class Project(
    val id: Int,
    val title: String,
    val url: String,
    val techno: String
)
fun sampleprojectList(): List<Project> {
    return listOf(
        Project(1,"project1", "https://github.com", "PHP"),
        Project(2,"project2", "https://gitlab.com", "Java"),
        Project(3, "project3", "https://github.com", "Js")
    )
}
