# Jeu de Mémoire (Memory Game)

Une application Java desktop avec interface graphique permettant de jouer au jeu de mémoire classique (jeu des paires). Le projet utilise **Java Swing** pour l'interface utilisateur et **MySQL** pour la persistance des scores, suivant l'architecture **MVC (Modèle-Vue-Contrôleur)**.

## Aperçu

Le jeu de mémoire consiste à retourner des cartes pour former des paires identiques. Le joueur dispose de 3 essais pour trouver toutes les 8 paires disposées sur une grille 4x4. Le système enregistre automatiquement les meilleurs scores avec le temps de jeu et la date.

## Fonctionnalités

- **Jeu de mémoire classique** : Grille 4x4 avec 8 paires d'images à associer
- **Système de score** : Suivi du score et du temps de jeu
- **Base de données** : Sauvegarde automatique des résultats
- **Tableau des scores** : Affichage des 10 meilleurs scores
- **Interface intuitive** : Interface graphique moderne avec Java Swing
- **Gestion des joueurs** : Saisie du nom du joueur au démarrage
- **Redémarrage rapide** : Possibilité de relancer une partie
- **Auto-création BD** : Création automatique de la base de données si inexistante

## Technologies utilisées

- **Java 17** - Langage de programmation principal
- **Java Swing** - Interface graphique utilisateur
- **JDBC** - Connectivité avec la base de données
- **MySQL 8.0** - Base de données pour la persistance
- **Architecture MVC** - Séparation des responsabilités
- **IntelliJ IDEA** - Environnement de développement

## Prérequis

- **Java Development Kit (JDK) 17** ou supérieur
- **MySQL Server 8.0** ou supérieur
- **MySQL Connector/J 8.0.18** (inclus dans le projet)
- IDE Java (IntelliJ IDEA)

## Installation

### 1. Repo

```bash
git clone https://github.com/abdelfadelAchraf/jeuDePair.git
cd jeuDePair
...
```

### 2. Importation de projet

### 3. Dépendances

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

### Lancement du jeu

1. **Compile & execute** :
   ```bash
   javac -cp ".:mysql-connector-java-8.0.18.jar" src/*.java src/**/*.java
   java -cp ".:mysql-connector-java-8.0.18.jar:src" Main
   ```

2. **depuis IDE** : `Main.java`

### Règles du jeu

1. **Saisissez nom** au démarrage
2. **Cliquez sur les cartes** pour les retourner (2 à la fois)
3. **Formez des paires** en associant des images identiques
4. **Objectif** : Trouver toutes les 8 paires en maximum 3 erreurs
5. **Score** : Plus vous trouvez de paires rapidement, meilleur est votre score

### Fonctionnalités

- **Redémarrer** : Nouvelle partie avec le même joueur
- **High Scores** : Consulter le tableau des meilleurs scores
- **Timer** : Suivi du temps de jeu en temps réel

## Architecture du projet

Le projet suit l'architecture **MVC (Modèle-Vue-Contrôleur)** :

### Modèle (Model)
- **`GameModel.java`** : Logique de jeu, gestion des cartes et des règles
- **`Card.java`** : Représentation d'une carte de jeu
- **`DatabaseManager.java`** : Gestion des interactions avec la base de données
- **`ScoreEntry.java`** : Modèle de données pour les scores

### Vue (View)
- **`GameView.java`** : Interface principale de jeu
- **`GameOverView.java`** : Écran de fin de partie
- **`HighScoresView.java`** : Tableau des meilleurs scores

### Contrôleur (Controller)
- **`GameController.java`** : Coordination entre le modèle et la vue
- **`Main.java`** : Point d'entrée de l'application

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

##

### Interface principale
- Grille 4x4 avec cartes retournables
- Affichage du score, tentatives restantes et timer
- Boutons pour redémarrer et consulter les scores

### Écran de fin de partie
- Message de victoire ou défaite
- Récapitulatif des statistiques
- Options pour rejouer ou quitter

### Gestion et Tableau des scores
- Classement des 10 meilleurs joueurs
- Affichage du score, temps et date
- Tri automatique par score décroissant, puis temps croissant

## groupe

- **Samir Ait said** [github.com/samirzoidac55](https://github.com/samirzoidac55)
- **Achraf Abdelfadel** [github.com/abdelfadelAchraf](https://github.com/abdelfadelAchraf)
- **Saad El Aroui** [github.com/Saadar0](https://github.com/Saadar0)
- **zakaria ennaqui** [github.com/zakariaennaqui](https://github.com/zakariaennaqui)
