# Jeu de Mémoire (Memory Game)

Une application Java desktop avec interface graphique permettant de jouer au jeu de mémoire classique (jeu des paires). Le projet utilise **Java Swing** pour l'interface utilisateur et **MySQL** pour la persistance des scores, suivant l'architecture **MVC (Modèle-Vue-Contrôleur)**.

## Aperçu

Le jeu de mémoire consiste à retourner des cartes pour former des paires identiques. Le joueur dispose de 3 essais pour trouver toutes les 8 paires disposées sur une grille 4x4. Le système enregistre automatiquement les meilleurs scores avec le temps de jeu et la date.

![Objectif principal du jeu](/images/Objectif-principal-du-jeu.png)

## Architecture du projet

Le projet suit l'architecture **MVC (Modèle-Vue-Contrôleur)** :

* Modèle (Model)
  - **`GameModel.java`** : Logique de jeu, gestion des cartes et des règles
  - **`Card.java`** : Représentation d'une carte de jeu
  - **`DatabaseManager.java`** : Gestion des interactions avec la base de données
  - **`ScoreEntry.java`** : Modèle de données pour les scores

* Vue (View)
  - **`GameView.java`** : Interface principale de jeu
  - **`GameOverView.java`** : Écran de fin de partie
  - **`HighScoresView.java`** : Tableau des meilleurs scores

* Contrôleur (Controller)
  - **`GameController.java`** : Coordination entre le modèle et la vue
  - **`Main.java`** : Point d'entrée de l'application

![Architecture MVC](/images/Architecture-MVC.png)

## Technologies utilisées

- **Java 17** - Langage de programmation principal
- **Java Swing** - Interface graphique utilisateur
- **JDBC** - Connectivité avec la base de données
- **MySQL 8.0** - Base de données pour la persistance
- **Architecture MVC** - Séparation des responsabilités
- **IntelliJ IDEA** - Environnement de développement

![Technologies utilisées](/images/Technologies-utilisées.jpg)

## Prérequis

- **Java Development Kit (JDK) 17** ou supérieur
- **MySQL Server 8.0** ou supérieur
- **MySQL Connector/J 8.0.18** (inclus dans le projet)
- IDE Java (IntelliJ IDEA)

## Structure des fichiers

```
MemoryGame/
├── .idea/                          # Configuration IntelliJ IDEA
├── src/
│   ├── Main.java                   # Point d'entrée
│   ├── controller/
│   │   └── GameController.java     # Contrôleur principal
│   ├── model/
│   │   ├── Card.java              # Modèle de carte
│   │   ├── DatabaseManager.java   # Gestion BDD
│   │   ├── GameModel.java         # Logique de jeu
│   │   └── ScoreEntry.java        # Modèle de score
│   └── view/
│       ├── GameView.java          # Interface de jeu
│       ├── GameOverView.java      # Écran de fin
│       └── HighScoresView.java    # Tableau des scores
├── images/                         # Ressources images
│   ├── img_1.png
│   ├── img_2.png
│   └── ... (8 images au total)
├── MemoryGame.iml                 # Configuration du module
├── .gitignore                     # Fichiers ignorés par Git
└── README.md                      # Documentation
```

![Structure du projet](/images/Annexe.png)

## Fonctionnalités

* **Jeu de mémoire classique** : grille 4×4 composée de 8 paires d'images à associer.
* **Système de score** : suivi du score, du nombre de tentatives et du temps de jeu.
* **Base de données** : sauvegarde automatique des résultats des parties.
* **Tableau des scores** : affichage des 10 meilleurs scores.
* **Gestion des joueurs** : saisie du nom du joueur au démarrage.
* **Redémarrage rapide** : possibilité de relancer une partie à tout moment.
* **Auto-création de la base de données** : création automatique de la base de données si elle n'existe pas.
* **Interface principale**
  * Grille 4×4 avec cartes retournables.
  * Affichage du score, des tentatives restantes et du chronomètre.
  * Boutons permettant de redémarrer la partie et de consulter le tableau des scores.
* **Écran de fin de partie**
  * Message de victoire ou de défaite.
  * Récapitulatif des statistiques de la partie.
  * Options permettant de rejouer ou de quitter l'application.
* **Gestion et tableau des scores**
  * Classement des 10 meilleurs joueurs.
  * Affichage du score, du temps réalisé et de la date de la partie.
  * Tri des résultats par score décroissant, puis par temps croissant.

![Fonctionnalités principales](/images/Fonctionnalités-principales.png)

## Installation
1. Repo
```bash
git clone https://github.com/abdelfadelAchraf/jeuDePair.git
cd jeuDePair
...
```
2. Importation de projet
3. Dépendances
- `mysql-connector-java-8.0.18.jar`
- ...

## Configuration de la base de données

1. **MySQL Server démarré**
2. **L'utilisateur root existe** (mot de passe vide par défaut)
3. **Disponible sur le port 3306**

```sql
-- Database
CREATE DATABASE memory_game;

USE memory_game;

-- Table des scores
CREATE TABLE scores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(100) NOT NULL,
    score INT NOT NULL,
    elapsed_time BIGINT NOT NULL,
    date_played DATETIME NOT NULL
);
```

4. **Paramètres de connexion** `DatabaseManager.java`

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/memory_game";
private static final String USER = "root";
private static final String PASS = "password";
```

## Utilisation

- Lancement du jeu

  * **Compile & execute** :
   ```bash
   javac -cp ".:mysql-connector-java-8.0.18.jar" src/*.java src/**/*.java
   java -cp ".:mysql-connector-java-8.0.18.jar:src" Main
   ```

  * **depuis IDE** : `Main.java`

- Règles du jeu

  * **Saisissez nom** au démarrage
  * **Cliquez sur les cartes** pour les retourner (2 à la fois)
  * **Formez des paires** en associant des images identiques
  * **Objectif** : Trouver toutes les 8 paires en maximum 3 erreurs
  * **Score** : Plus vous trouvez de paires rapidement, meilleur est votre score

![Diagramme de navigation](/images/Diagramme-de-navigation.png)

## Diagramme de classes UML

![Diagramme de classes UML](/images/Diagramme-classes-UML.png)

## Groupe

- **Samir Ait said** [github.com/samirzoidac55](https://github.com/samirzoidac55)
- **Achraf Abdelfadel** [github.com/abdelfadelAchraf](https://github.com/abdelfadelAchraf)
- **Saad El Aroui** [github.com/Saadar0](https://github.com/Saadar0)
- **zakaria ennaqui** [github.com/zakariaennaqui](https://github.com/zakariaennaqui)