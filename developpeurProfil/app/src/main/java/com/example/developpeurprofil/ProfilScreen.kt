package com.example.developpeurprofil

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
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen( // équivalent de main
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
            projects =projects,
            contactInfo = contactInfo,
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
    projects: List<Project>,
    contactInfo: List<String>,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.utilisateur),
                    contentDescription = "Photo de profil",
                    modifier = Modifier
                        .size(100.dp),
                    contentScale = ContentScale.Crop
                )
                ContactInfo(
                    email = contactInfo[0],
                    phone = contactInfo[1],
                    address = contactInfo[2],
                    modifier = Modifier.weight(1f)
                )
            }
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
            AvailabilityInfo(
                isAvailable = isAvailable,
                onClick = onAvailabilityClick,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Compétences",
                style = MaterialTheme.typography.titleMedium
            )
            LazyColumn(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(skills.size) { skill -> SkillItem(skills[skill]) }
            }
            ProfileActions(
                onContactClick = onContactClick,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Mes projets",
                style = MaterialTheme.typography.titleMedium
            )
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(projects.size){
                        project -> ProjectItem(projects[project].title, projects[project].url)
                }
            }
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
    Column() {
        Text(
            text = "abcd@example.Com",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "1 grand rue Reims, 51100",
            style = MaterialTheme.typography.bodyMedium
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
        shape = MaterialTheme.shapes.small,
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
        shape = MaterialTheme.shapes.small,
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

@Composable
fun ProjectItem(
    title: String,
    url: String,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current

    Surface(
        modifier = modifier.clickable { // lien
            uriHandler.openUri(url)
        },
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Voir le projet ↗",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun ContactInfo(
    email: String,
    phone: String,
    address: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        email
        phone
        address
    }
}