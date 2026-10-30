# Profil développeur Compose

## Explications demandées

1. **Quels composables avez-vous créés ?**  
   `ProfileScreen`, `ProfileCard`, `ProfileHeader`, `SkillItem`, `AvailabilityInfo` et `ProfileActions`.

2. **Quel composable représente l'écran principal ?**  
   `ProfileScreen`, Il utilise `Scaffold`. Il recoit les différentes infos via des paramètres.

3. **Où utilisez-vous un padding externe ?**  
   Dans `ProfileScreen`, le parent applique `Modifier.padding(innerPadding).padding(16.dp)` à `ProfileCard`. Cela place la fiche à distance des bords de l'écran.

4. **Où utilisez-vous un padding interne ?**  
   Dans `ProfileCard`-> `Column` -> `Modifier.padding(20.dp)`. Pour laisser un espace entre le bord intérieur de la carte et son contenu.

5. **Quel composant reçoit un callback ?**  
   `ProfileActions`, `Button`. `AvailabilityInfo`.

6. **Quelle information d'accessibilité avez-vous ajoutée ?**  
   La zone cliquable `AvailabilityInfo` possède une `contentDescription` qui décrit sa disponibilité et le rôle `Role.Button`. Un lecteur d'écran peut ainsi comprendre sa fonction.

7. **Pourquoi vos composants peuvent-ils être considérés comme réutilisables ?**  
   Ils reçoivent des paramètres (nom, rôle, compétence, disponibilité, callback, etc). Ils acceptent aussi un paramètre `modifier` utilisé pour permettre au parent de contrôler leur placement.

## Autre  

Il n'y a que de l'affichage. Il est possible de rajouter des fonctions pour les bouttons etc  
La plus part des paramètres fonctionnels sont transmis depuis les composants parents