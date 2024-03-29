# Application Web de Localisation des Restaurants

Ce projet vise à concevoir et mettre en œuvre une application web pour localiser les restaurants dans une ville sélectionnée, avec la possibilité de filtrer par villes et zones. L'application utilise les technologies suivantes :

# Technologies utilisées

- Spring Boot (Backend) : Plateforme basée sur Java adoptant l'architecture MVC pour un développement efficace, en séparant l'application en classes modèle, vue et contrôleur.

- MySQL (Base de données) : Système de gestion de base de données relationnelle (SGBDR) pour stocker et gérer les données.

- Thymeleaf : Moteur de modèle pour la création de pages web.

- Bootstrap : Framework CSS pour la conception d'interfaces utilisateur réactives et esthétiques.

- ReactJS (Frontend) : Bibliothèque JavaScript utilisée pour construire l'interface utilisateur côté client.

- Leaflet : Bibliothèque JavaScript pour les cartes interactives, utilisée pour le traitement des fonctionnalités liées à la carte.





# Fonctionnalités


   ``` Pour les Administrateurs ```
   

- Gestion des Villes : Ajouter, modifier et supprimer des villes.

- Gestion des Zones : Définir et gérer différentes zones au sein des villes.

- Gestion des Spécialités : Gérer différentes spécialités de restaurants.

- Gestion des Séries : Gérer différentes séries de restaurants.

- Voir la Liste des Restaurants : Afficher une liste de restaurants.

- Validation des Restaurants : Approuver ou rejeter les soumissions de restaurants.


``` Pour les Propriétaires de Restaurants  ```

- Inscription : S'inscrire pour créer un compte de propriétaire de restaurant.

- Voir la Liste des Restaurants : Afficher une liste des restaurants possédés.

- État du Restaurant : Voir l'état actuel d'un restaurant.

- Création de Restaurant : Ajouter de nouveaux restaurants au système.

- Ajouter des Photos de Restaurant : Télécharger et gérer des photos pour un restaurant.

  https://github.com/ayaberhil/BESS-RESTO/assets/147451152/eb265837-7cc8-46f5-bd4a-d333a7a41223


``` Pour les clients ```

- Voir les Restaurants : Parcourir les restaurants avec la possibilité de voir des photos.

- Fonction de Recherche : Rechercher des restaurants par ville et zone.

- Intégration de la Carte : Visualiser les emplacements des restaurants et la position de l'utilisateur sur la carte.

  https://github.com/ayaberhil/BESS-RESTO/assets/147451152/be900043-e785-476e-bff8-9378806f9eb5

 # SonarQube

SonarQube est un outil précieux pour maintenir un code source de haute qualité tout au long du cycle de vie d'un projet logiciel.

 ![sonar](https://github.com/ayaberhil/BESS-RESTO/assets/147451152/ab4d7753-2583-4b94-a3a4-8bfd90470f68)


 # Lancement du Projet
 
- Vous pouvez faire le clone du projet:  ``` git clone ``` https://github.com/ayaberhil/TP_SpringBoot.git , puis lancez-le
- Créer une base de données nommée : ``` projet ```
- Ouvrez http://localhost:9097 pour visualisation dans votre navigateur. La page se rechargera automatiquement lorsque vous apportez des modifications.
- Pour le compte par défaut administrateur, username : ```admin@admin``` , password : ```admin@admin```
- Pour le compte par défaut propriétaire, username : ```owner@owner``` , password : ```owner@owner```




# Démmarage avec React 

Ce projet a été initialisé avec Create React App, offrant une configuration simplifiée pour les applications React.

# Scripts Disponibles
Dans le répertoire du projet, vous pouvez exécuter les scripts suivants :

``` npm install ```
Pour installer les dépendances nécessaires à ce projet.

``` npm start ```
Exécute l'application en mode développement. Ouvrez http://localhost:3000 pour la visualiser dans votre navigateur. La page se rechargera automatiquement lorsque vous apportez des modifications. Vous pouvez également voir les éventuelles erreurs de lint dans la console.

``` npm run build ```
Construit l'application pour la production dans le dossier build. Elle optimise React en mode production pour de meilleures performances. Le build est minifié, et les noms de fichiers incluent les hachages. Votre application est prête à être déployée !


# Lien DockerHub
```lien: ```  https://hub.docker.com/repository/docker/manaless/localisation_des_restaurants/general


# Auteurs
Ce projet a été développé par :

- BERHIL Aya
- ESSAOULAJY Manal


# Licence
Ce projet est sous licence MIT.
