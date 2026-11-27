package com.example.developpeurprofil

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.developpeurprofil.ui.theme.DeveloppeurProfilTheme

class MainActivity : androidx.activity.ComponentActivity() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ProfileScreen(
                    name = "Cordier Eugene",
                    jobTitle = "Dev junior",
                    description = "Étudiant en informatique",
                    skills = listOf("Kotlin", "Jetpack Compose", "Git", "Android"),
                    isAvailable = true,
                    onContactClick = {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+33123456789"))
                        startActivity(intent)
                    },
                    onAvailabilityClick = { },
                    projects = listOf(
                        Project("project1", "https://github.com"),
                        Project("project2", "https://gitlab.com"),
                        Project("project3", "https://github.com")
                    ),
                    contactInfo = listOf("abcd@example.com", "0123456789", "1 grand rue, Reims 51100")
                )
            }
        }
    }
}

// autres exemples de card
@Preview(showBackground = true, name = "Profil Android junior")
@Composable
fun PreviewProfilAndroidJunior() {
    MaterialTheme {
        ProfileScreen(
            name = "Camille Martin",
            jobTitle = "Développeuse Android junior",
            description = "Étudiante en informatique, motivée par le développement mobile et la création d'applications utiles.",
            skills = listOf("Kotlin", "Jetpack Compose", "Git", "Android"),
            isAvailable = true,
            onContactClick = {},
            onAvailabilityClick = {},
            projects = sampleprojectList(),
            contactInfo = listOf("adfefez@example.com", "0123456789", "1 rue truc, Reims 51100")
        )
    }
}

@Preview(showBackground = true, name = "Profil développeur full-stack")
@Composable
fun PreviewProfilFullStack() {
    MaterialTheme {
        ProfileScreen(
            name = "Alex Bernard",
            jobTitle = "Développeur full-stack",
            description = "Développeur curieux, j'aime travailler sur des projets web complets, du backend à l'interface utilisateur.",
            skills = listOf("Java", "JavaScript", "SQL", "HTML / CSS"),
            isAvailable = false,
            onContactClick = {},
            onAvailabilityClick = {},
            projects = sampleprojectList(),
            contactInfo = listOf("adfefez@example.com", "0123456789", "1 rue truc, Reims 51100")
        )
    }
}



