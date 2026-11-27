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
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

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
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onFavoriteClick: (Int) -> Unit,
    favoriteProjectIds: List<Int>,
    showOnlyFavorites: Boolean,
    onToggleFavoriteFilter: () -> Unit,
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
                .padding(16.dp),
            searchQuery = searchQuery,
            onSearchQueryChange = onSearchQueryChange,
            onFavoriteClick = onFavoriteClick,
            favoriteProjectIds = favoriteProjectIds,
            showOnlyFavorites = showOnlyFavorites,
            onToggleFavoriteFilter = onToggleFavoriteFilter
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
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChange: (String) ->Unit,
    onFavoriteClick: (Int) -> Unit,
    favoriteProjectIds: List<Int>,
    showOnlyFavorites: Boolean,
    onToggleFavoriteFilter: () -> Unit
) {
    var showContactInfo by rememberSaveable {
        mutableStateOf(false)
    }
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        tonalElevation = 3.dp
    ) {
        LazyColumn(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                ProfileHeader(
                    name = name,
                    jobTitle = jobTitle,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Button(
                    onClick = {
                        showContactInfo = !showContactInfo
                    }
                ) {
                    Text(
                        text = if (showContactInfo)
                            "Masquer les coordonnées"
                        else
                            "Afficher les coordonnées"
                    )
                }
            }
            item {
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
                    if (showContactInfo) {
                        ContactInfo(
                            email = contactInfo[0],
                            phone = contactInfo[1],
                            address = contactInfo[2],
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
            item {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            item {
                AvailabilityInfo(
                    isAvailable = isAvailable,
                    onClick = onAvailabilityClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Text(
                    text = "Compétences",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            items(skills.size) { skill -> SkillItem(skills[skill]) }
            item {
                ProfileActions(
                    onContactClick = onContactClick,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Text(
                    text = "Mes projets",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            item {
                Button(
                    onClick = onToggleFavoriteFilter
                ) {
                    Text(
                        text = if (showOnlyFavorites)
                            "Afficher tous les projets"
                        else
                            "Afficher uniquement les favoris"
                    )
                }
            }

            item {
                Text(
                    text = "${projects.size} projet(s) affiché(s)",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    label = {
                        Text(text = "Rechercher un projet")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            items(projects.size) { project ->
                ProjectItem(
                    title = projects[project].title,
                    url = projects[project].url,
                    isFavorite = favoriteProjectIds.contains(projects[project].id),
                    onFavoriteClick = {
                        onFavoriteClick(projects[project].id)
                    }
                )
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
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            uriHandler.openUri(url)
                        },
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Voir le projet ↗",
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                Button(onClick = onFavoriteClick) {
                    Text(
                        text = if (isFavorite) "♥" else "♡"
                    )
                }
            }
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

