//package controller;
//
//import model.Card;
//import model.DatabaseManager;
//import model.GameModel;
//import view.GameView;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//import javax.swing.Timer;
//// GameController.java
//public class GameController {
//    private GameModel model;
//    private GameView view;
//
//    public GameController(String playerName) {
//        this.model = new GameModel(playerName);
//        this.view = new GameView(this);
//        view.updateView();
//    }
//
//    public void handleCardClick(int index) {
//        boolean cardFlipped = model.flipCard(index);
//
//        if (cardFlipped) {
//            view.updateView();
//
//            // Si deux cartes sont retournées, vérifier après un délai
//            if (model.getSecondCard() != null) {
//                Timer timer = new Timer(1000, new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        model.resetFlippedCards();
//                        view.updateView();
//
//                        // Vérifier si le jeu est terminé
//                        if (model.isGameOver()) {
//                            handleGameOver();
//
//                        }
//                    }
//                });
//                timer.setRepeats(false);
//                timer.start();
//            }
//        }
//    }
//
//    private void handleGameOver() {
//        // Sauvegarder le résultat dans la base de données
//        DatabaseManager.saveGameResult(
//                model.getPlayerName(),
//                model.getScore(),
//                model.getElapsedTime()
//        );
//
//        // Afficher l'écran de fin de jeu
//        if (model.isGameWon()) {
//            view.showGameWonDialog();
//        } else {
//            view.showGameLostDialog();
//        }
//    }
//
//    public void restartGame() {
//        model = new GameModel(model.getPlayerName());
//        view.updateView();
//    }
//
//
//    // Getters pour la vue
//    public GameModel getModel() { return model; }
//    public List<Card> getCards() { return model.getCards(); }
//    public int getRemainingAttempts() { return model.getRemainingAttempts(); }
//    public int getScore() { return model.getScore(); }
//    public long getElapsedTime() { return model.getElapsedTime(); }
//}


package controller;

import model.Card;
import model.DatabaseManager;
import model.GameModel;
import model.ScoreEntry;
import view.GameOverView;
import view.GameView;
import view.HighScoresView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Timer;

// GameController.java
public class GameController {
    private GameModel model;
    private GameView view;
    private static final int TOP_SCORES_LIMIT = 10;

    public GameController(String playerName) {
        this.model = new GameModel(playerName);
        this.view = new GameView(this);
        view.updateView();
    }

    public void handleCardClick(int index) {
        boolean cardFlipped = model.flipCard(index);

        if (cardFlipped) {
            view.updateView();

            // Si deux cartes sont retournées, vérifier après un délai
            if (model.getSecondCard() != null) {
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        model.resetFlippedCards();
                        view.updateView();

                        // Vérifier si le jeu est terminé
                        if (model.isGameOver()) {
                            handleGameOver();
                        }
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }

    private void handleGameOver() {
        // Sauvegarder le résultat dans la base de données
        DatabaseManager.saveGameResult(
                model.getPlayerName(),
                model.getScore(),
                model.getElapsedTime()
        );

        // Afficher l'écran de fin de jeu approprié
        new GameOverView(this, model.isGameWon());
    }

    public void restartGame() {
        // Utiliser le même nom de joueur pour la nouvelle partie
        model = new GameModel(model.getPlayerName());
        view.updateView();
    }

    public void showHighScores() {
        List<ScoreEntry> topScores = DatabaseManager.getTopScores(TOP_SCORES_LIMIT);
        new HighScoresView(topScores);
    }

    // Getters pour la vue
    public GameModel getModel() { return model; }
    public List<Card> getCards() { return model.getCards(); }
    public int getRemainingAttempts() { return model.getRemainingAttempts(); }
    public int getScore() { return model.getScore(); }
    public long getElapsedTime() { return model.getElapsedTime(); }
}