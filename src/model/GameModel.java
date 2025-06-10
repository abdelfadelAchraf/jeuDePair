package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// GameModel.java - Gère la logique du jeu
public class GameModel {
    private List<Card> cards;
    private Card firstCard;
    private Card secondCard;
    private int remainingAttempts;
    private int score;
    private long startTime;
    private long endTime;
    private String playerName;

    public GameModel(String playerName) {
        this.playerName = playerName;
        this.cards = new ArrayList<>();
        this.remainingAttempts = 3;
        this.score = 0;
        this.startTime = System.currentTimeMillis();
        initializeCards();
        shuffleCards();
    }

    private void initializeCards() {
        // Créer 8 paires (16 cartes) pour une grille 4x4
        String[] images = {"A", "B",
                "C", "D",
                "E", "F", "G", "H"
        };

        int id = 0;
        for (String image : images) {
            // Ajouter deux exemplaires de chaque image
            cards.add(new Card(id, image));
            cards.add(new Card(id, image));
            id++;
        }
    }

    private void shuffleCards() {
        Collections.shuffle(cards);
    }

    public boolean flipCard(int index) {
        if (index < 0 || index >= cards.size()) return false;

        Card card = cards.get(index);

        // Ignorer si la carte est déjà retournée ou appariée
        if (card.isFaceUp() || card.isMatched()) return false;

        // Retourner la carte
        card.setFaceUp(true);

        // Si c'est la première carte retournée
        if (firstCard == null) {
            firstCard = card;
            return true;
        }

        // Si c'est la deuxième carte retournée
        secondCard = card;

        // Vérifier si les cartes forment une paire
        boolean isMatch = firstCard.getId() == secondCard.getId();

        if (isMatch) {
            // Si c'est une paire, marquer les deux cartes comme appariées
            firstCard.setMatched(true);
            secondCard.setMatched(true);
            score++;
        } else {
            // Si ce n'est pas une paire, décrémenter le nombre d'essais
            remainingAttempts--;
        }

        return true;
    }

    public void resetFlippedCards() {
        // Ne retourne que les cartes non appariées
        if (firstCard != null && !firstCard.isMatched()) {
            firstCard.setFaceUp(false);
        }
        if (secondCard != null && !secondCard.isMatched()) {
            secondCard.setFaceUp(false);
        }

        firstCard = null;
        secondCard = null;
    }

    public boolean isGameWon() {
        return score == 8; // 8 paires à trouver
    }

    public boolean isGameLost() {
        return remainingAttempts <= 0;
    }

    public boolean isGameOver() {
        if (isGameWon() || isGameLost()) {
            endTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

    public long getElapsedTime() {
        if (endTime == 0) {
            return System.currentTimeMillis() - startTime;
        }
        return endTime - startTime;
    }
    // Ajoutez cette méthode à la classe GameModel
    public Card getSecondCard() {
        return secondCard;
    }

    // Ajoutez également ce getter pour la première carte
    public Card getFirstCard() {
        return firstCard;
    }

    // Getters
    public List<Card> getCards() { return cards; }
    public int getRemainingAttempts() { return remainingAttempts; }
    public int getScore() { return score; }
    public String getPlayerName() { return playerName; }
}