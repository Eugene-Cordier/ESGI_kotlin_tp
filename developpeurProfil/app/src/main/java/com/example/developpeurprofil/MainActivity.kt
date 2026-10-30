package com.example.developpeurprofil

import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
                    onContactClick = { },
                    onAvailabilityClick = { }
                )
            }
        }
    }
}

@Composable
fun ProfileScreen( // équivalent de main
    name: String,
    jobTitle: String,
    description: String,
    skills: List<String>,
    isAvailable: Boolean,
    onContactClick: () -> Unit,
    onAvailabilityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier) { innerPadding ->
        ProfileCard(
            name = name,
            jobTitle = jobTitle,
            description = description,
            skills = skills,
            isAvailable = isAvailable,
            onContactClick = onContactClick,
            onAvailabilityClick = onAvailabilityClick,
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        )
    }
}

@Composable
fun ProfileCard( //card
    name: String,
    jobTitle: String,
    description: String,
    skills: List<String>,
    isAvailable: Boolean,
    onContactClick: () -> Unit,
    onAvailabilityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        tonalElevation = 3.dp
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            ProfileHeader(
                name = name,
                jobTitle = jobTitle,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Compétences",
                style = MaterialTheme.typography.titleMedium
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                skills.forEach { skill ->
                    SkillItem(
                        skill = skill,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            AvailabilityInfo(
                isAvailable = isAvailable,
                onClick = onAvailabilityClick,
                modifier = Modifier.fillMaxWidth()
            )
            ProfileActions(
                onContactClick = onContactClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
@Composable
fun ProfileHeader( // sous composant de card (nom et métier)
    name: String,
    jobTitle: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = jobTitle,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
@Composable
fun SkillItem( // sous composant de card (list)
    skill: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = skill,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
@Composable
fun AvailabilityInfo( // sous composant de card
    isAvailable: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val availabilityText = if (isAvailable) {
        "Disponible pour un stage ou une alternance"
    } else {
        "Non disponible actuellement"
    }
    Surface(
        modifier = modifier
            .clickable(onClick = onClick)
            .semantics {
                contentDescription = availabilityText
                role = Role.Button
            },
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Text(
            text = availabilityText,
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
@Composable
fun ProfileActions( // sous composant de card
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start
    ) {
        Button(onClick = onContactClick) {
            Text(text = "Contacter")
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
            onAvailabilityClick = {}
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
            onAvailabilityClick = {}
        )
    }
}