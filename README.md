# 🃏 Jeu de Mémoire (Memory Game)

Une application Java desktop avec interface graphique permettant de jouer au jeu de mémoire classique (jeu des paires). Le projet utilise **Java Swing** pour l'interface utilisateur et **MySQL** pour la persistance des scores, suivant l'architecture **MVC (Modèle-Vue-Contrôleur)**.

## 📋 Table des matières

- [Aperçu](#aperçu)
- [Fonctionnalités](#fonctionnalités)
- [Technologies utilisées](#technologies-utilisées)
- [Prérequis](#prérequis)
- [Installation](#installation)
- [Configuration de la base de données](#configuration-de-la-base-de-données)
- [Utilisation](#utilisation)
- [Architecture du projet](#architecture-du-projet)
- [Structure des fichiers](#structure-des-fichiers)
- [Screenshots](#screenshots)
- [Contribution](#contribution)

## 🎮 Aperçu

Le jeu de mémoire consiste à retourner des cartes pour former des paires identiques. Le joueur dispose de 3 essais pour trouver toutes les 8 paires disposées sur une grille 4x4. Le système enregistre automatiquement les meilleurs scores avec le temps de jeu et la date.

## ✨ Fonctionnalités

- **Jeu de mémoire classique** : Grille 4x4 avec 8 paires d'images à associer
- **Système de score** : Suivi du score et du temps de jeu
- **Base de données** : Sauvegarde automatique des résultats
- **Tableau des scores** : Affichage des 10 meilleurs scores
- **Interface intuitive** : Interface graphique moderne avec Java Swing
- **Gestion des joueurs** : Saisie du nom du joueur au démarrage
- **Redémarrage rapide** : Possibilité de relancer une partie
- **Auto-création BD** : Création automatique de la base de données si inexistante

## 🛠️ Technologies utilisées

- **Java 17** - Langage de programmation principal
- **Java Swing** - Interface graphique utilisateur
- **JDBC** - Connectivité avec la base de données
- **MySQL 8.0** - Base de données pour la persistance
- **Architecture MVC** - Séparation des responsabilités
- **IntelliJ IDEA** - Environnement de développement

## 📋 Prérequis

Avant d'exécuter le projet, assurez-vous d'avoir installé :

- **Java Development Kit (JDK) 17** ou supérieur
- **MySQL Server 8.0** ou supérieur
- **MySQL Connector/J 8.0.18** (inclus dans le projet)
- Un IDE Java (IntelliJ IDEA recommandé)

## 🚀 Installation

### 1. Cloner le repository

```bash
git clone https://github.com/abdelfadelAchraf/jeuDePair.git
cd jeuDePair
```

### 2. Importer le projet

- Ouvrez IntelliJ IDEA
- Sélectionnez "Open" et choisissez le dossier du projet
- Le projet se configure automatiquement avec le fichier `.iml` inclus

### 3. Vérifier les dépendances

Le connecteur MySQL est déjà configuré dans le projet. Vérifiez que le fichier JAR est bien présent :
- `mysql-connector-java-8.0.18.jar`

## 🗃️ Configuration de la base de données

### Configuration automatique

L'application crée automatiquement la base de données au premier lancement. Assurez-vous que :

1. **MySQL Server est démarré**
2. **L'utilisateur root existe** (mot de passe vide par défaut)
3. **Le port 3306 est disponible**

### Configuration manuelle (optionnelle)

Si vous préférez configurer manuellement :

```sql
-- Créer la base de données
CREATE DATABASE memory_game;

-- Utiliser la base de données
USE memory_game;

-- Créer la table des scores
CREATE TABLE scores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(100) NOT NULL,
    score INT NOT NULL,
    elapsed_time BIGINT NOT NULL,
    date_played DATETIME NOT NULL
);
```

### Modifier la configuration

Pour changer les paramètres de connexion, éditez le fichier `DatabaseManager.java` :

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/memory_game";
private static final String USER = "root";
private static final String PASS = "votre_mot_de_passe";
```

## 🎯 Utilisation

### Lancement du jeu

1. **Compiler et exécuter** :
   ```bash
   javac -cp ".:mysql-connector-java-8.0.18.jar" src/*.java src/**/*.java
   java -cp ".:mysql-connector-java-8.0.18.jar:src" Main
   ```

2. **Ou depuis votre IDE** : Exécutez la classe `Main.java`

### Règles du jeu

1. **Saisissez votre nom** au démarrage
2. **Cliquez sur les cartes** pour les retourner (2 à la fois)
3. **Formez des paires** en associant des images identiques
4. **Objectif** : Trouver toutes les 8 paires en maximum 3 erreurs
5. **Score** : Plus vous trouvez de paires rapidement, meilleur est votre score

### Fonctionnalités disponibles

- **Redémarrer** : Nouvelle partie avec le même joueur
- **High Scores** : Consulter le tableau des meilleurs scores
- **Timer** : Suivi du temps de jeu en temps réel

## 🏗️ Architecture du projet

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

## 📁 Structure des fichiers

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

## 🎨 Screenshots

### Interface principale
- Grille 4x4 avec cartes retournables
- Affichage du score, tentatives restantes et timer
- Boutons pour redémarrer et consulter les scores

### Écran de fin de partie
- Message de victoire ou défaite
- Récapitulatif des statistiques
- Options pour rejouer ou quitter

### Tableau des scores
- Classement des 10 meilleurs joueurs
- Affichage du score, temps et date
- Tri automatique par score décroissant

## 🔧 Fonctionnalités techniques

### Gestion des scores
- **Sauvegarde intelligente** : Met à jour uniquement si le nouveau score est meilleur
- **Critères de classement** : Score décroissant, puis temps croissant
- **Persistance** : Stockage permanent en base de données MySQL

### Interface utilisateur
- **Responsive** : Interface adaptative selon la taille de fenêtre
- **Feedback visuel** : Animation des cartes et mise à jour en temps réel
- **Ergonomie** : Navigation intuitive et contrôles simples

### Performance
- **Gestion mémoire** : Utilisation optimisée des ressources
- **Connexions BDD** : Ouverture/fermeture automatique des connexions
- **Images** : Redimensionnement automatique pour l'affichage

## 🤝 Contribution

Les contributions sont les bienvenues ! Pour contribuer :

1. **Fork** le projet
2. **Créez** votre branche de fonctionnalité (`git checkout -b feature/AmazingFeature`)
3. **Committez** vos changements (`git commit -m 'Add some AmazingFeature'`)
4. **Push** vers la branche (`git push origin feature/AmazingFeature`)
5. **Ouvrez** une Pull Request

### Guidelines de contribution

- Suivez l'architecture MVC existante
- Documentez votre code
- Testez vos modifications
- Respectez les conventions de nommage Java

## 📝 Licence

Ce projet est distribué sous licence MIT. Voir le fichier `LICENSE` pour plus d'informations.

## 👥 Auteurs

- **Votre Nom** - *Développeur principal* - [VotreGitHub](https://github.com/votre-username)

## 🆘 Support

Si vous rencontrez des problèmes :

1. **Vérifiez** que MySQL est démarré
2. **Consultez** les logs de l'application
3. **Ouvrez** une issue sur GitHub avec :
   - Description du problème
   - Étapes pour reproduire
   - Configuration système
   - Messages d'erreur éventuels

---

⭐ N'hésitez pas à laisser une étoile si ce projet vous a été utile !
