
# Profil développeur Compose

## Présentation du projet

Ce projet est une petite application Android développée avec Kotlin + Jetpack Compose. Elle permet de présenter un profil de dév (coordonnées, compétences, liste de projets...).

## 1. Explications sur les composables

### Quels composables avez-vous créés ?

- `ProfileScreen`
- `ProfileCard`
- `ProfileHeader`
- `SkillItem`
- `AvailabilityInfo`
- `ProfileActions`

### Quel composable représente l'écran principal ?

`ProfileScreen` représente l'écran principal. Il utilise `Scaffold` et reçoit les différentes informations via des paramètres.

### Où utilisez-vous un padding externe ?

Dans `ProfileScreen`, le parent applique `Modifier.padding(innerPadding).padding(16.dp)` à `ProfileCard`.

Cela permet de placer la fiche à distance des bords de l'écran.

### Où utilisez-vous un padding interne ?

Dans `ProfileCard`, la `Column` utilise `Modifier.padding(20.dp)`.

Cela permet de laisser un espace entre le bord intérieur de la carte et son contenu.

### Quel composant reçoit un callback ?

Plusieurs composants reçoivent des callbacks, notamment `ProfileActions`, les boutons et `AvailabilityInfo`.

Ces fonctions permettent aux composants enfants de transmettre une action au composant parent.

### Quelle information d'accessibilité avez-vous ajoutée ?

La zone cliquable `AvailabilityInfo` possède une `contentDescription` qui décrit la disponibilité et le rôle `Role.Button`.

Un lecteur d'écran peut ainsi comprendre sa fonction.

### Pourquoi vos composants peuvent-ils être considérés comme réutilisables ?

Les composants reçoivent des paramètres (nom, rôle, compétence, disponibilité, callback, etc.).

Ils acceptent également un paramètre `modifier`, qui permet au composant parent de contrôler leur placement.

## 2. Gestion de l'état et fonctionnalités interactives

L'état principal de l'application est géré par `ProfileViewModel`, qui utilise `ProfileUiState` pour regrouper les informations de l'écran.

L'application propose plusieurs fonctionnalités :

- Rechercher des projets par titre ou technologie.
- Ajouter ou retirer des projets des favoris.
- Afficher tous les projets ou uniquement les favoris.
- Afficher le nombre de projets actuellement visibles.
- Afficher ou masquer les coordonnées du profil.

Le `ProfileViewModel` utilise `MutableStateFlow` pour modifier l'état et expose un `StateFlow` en lecture seule.

Le composable `ProfileScreen` reste sans état principal : il reçoit les données et les callbacks en paramètres.

Un état local est également utilisé avec `rememberSaveable` pour afficher ou masquer les coordonnées.

## 3. Autre remarque

Au départ, certains composants servaient principalement à l'affichage. Il était possible d'ajouter des fonctions aux boutons pour les rendre interactifs.

La plupart des paramètres fonctionnels sont transmis depuis les composants parents vers les composants enfants grâce aux paramètres et aux callbacks.

## 4. Aperçus de l'interface

Deux aperçus `@Preview` sont présents :

- Un profil sans recherche active et sans favoris.
- Un profil avec une recherche active et plusieurs projets favoris.

## 5. Capture d'écran

Ajouter ici une capture d'écran de l'application :

`![Capture d'écran de l'application](screenshoot/Screenshoot-final-1.png)`  
`![Capture d'écran de l'application](screenshoot/Screenshoot-final-2.png)`
