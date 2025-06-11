//package view;
//
//import controller.GameController;
//import model.Card;
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.Image;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//import javax.swing.BorderFactory;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.Timer;
//
//// GameView.java
//public class GameView extends JFrame {
//    private GameController controller;
//    private JPanel cardPanel;
//    private JLabel attemptsLabel;
//    private JLabel scoreLabel;
//    private JLabel timerLabel;
//    private Timer timerUpdateTimer;
//    final int nombreDeCartes = 4 ;
//
//    private JButton[][] cardButtons;
//
//    public GameView(GameController controller) {
//        this.controller = controller;
//
//        setTitle("Jeu de Paires");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(600, 700);
//        setLocationRelativeTo(null);
//
//        initializeUI();
//        startTimerUpdate();
//
//        setVisible(true);
//    }
//
//    private void initializeUI() {
//        // Panel principal
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BorderLayout());
//
//        // Panel d'information (tentatives, score, timer)
//        JPanel infoPanel = new JPanel();
//        infoPanel.setLayout(new GridLayout(1, 3));
//
//        attemptsLabel = new JLabel("Essais restants: " + controller.getRemainingAttempts());
//        scoreLabel = new JLabel("Score: " + controller.getScore());
//        timerLabel = new JLabel("Temps: 0s" + controller.getScore());
//        System.out.println(timerLabel);
//        infoPanel.add(attemptsLabel);
//        infoPanel.add(scoreLabel);
//        infoPanel.add(timerLabel);
//
//        mainPanel.add(infoPanel, BorderLayout.NORTH);
//
//        // Panel de cartes (grille 4x4)
//        cardPanel = new JPanel();
//        cardPanel.setLayout(new GridLayout(4, 4, 10, 10));
//        cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//
//        cardButtons = new JButton[4][4];
//
//        for (int i = 0; i < nombreDeCartes; i++) {
//            for (int j = 0; j < nombreDeCartes; j++) {
//                final int index = i * nombreDeCartes + j;
//                JButton cardButton = new JButton();
//                cardButton.setPreferredSize(new Dimension(120, 120));
//
//                cardButton.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        controller.handleCardClick(index);
//                    }
//                });
//
//                cardButtons[i][j] = cardButton;
//                cardPanel.add(cardButton);
//            }
//        }
//
//        mainPanel.add(cardPanel, BorderLayout.CENTER);
//
//        // Bouton de redémarrage
//        JButton restartButton = new JButton("Redémarrer");
//        restartButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                controller.restartGame();
//            }
//        });
//
//        mainPanel.add(restartButton, BorderLayout.SOUTH);
//
//        add(mainPanel);
//    }
//
//    private void startTimerUpdate() {
//        timerUpdateTimer = new Timer(1000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                updateTimerLabel();
//            }
//        });
//        timerUpdateTimer.start();
//    }
//
//    public void updateView() {
////        List<Card> cards = controller.getCards();
//        List<Card> cards = controller.getCards();
//        for (int i = 0; i < nombreDeCartes; i++) {
//            for (int j = 0; j < nombreDeCartes; j++) {
//                int index = i * nombreDeCartes + j;
//                Card card = cards.get(index);
//                JButton button = cardButtons[i][j];
//
//                if (card.isFaceUp()) {
//                    // Afficher l'image de la carte
//                    try {
//                        ImageIcon icon = new ImageIcon(getClass().getResource("/images/" + card.getImagePath()));
//                        Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//                        button.setIcon(new ImageIcon(image));
//                        button.setText("");
//                    } catch (Exception e) {
//                        button.setIcon(null);
//                        button.setText(card.getImagePath());
//                    }
//                } else {
//                    // Carte face cachée
//                    button.setIcon(null);
//                    button.setText("?");
//                }
//
//                // Désactiver le bouton si la carte est appariée
//                button.setEnabled(!card.isMatched());
//            }
//        }
//
//        // Mettre à jour les labels d'information
//        attemptsLabel.setText("Essais restants: " + controller.getRemainingAttempts());
//        scoreLabel.setText("Score: " + controller.getScore());
////        attemptsLabel.setText("the bets scores are :" +);
//        updateTimerLabel();
//    }
//
//    private void updateTimerLabel() {
//        long elapsedSeconds = controller.getElapsedTime() / 1000;
//        timerLabel.setText("Temps: " + elapsedSeconds + "s");
//    }
//
//    public void showGameWonDialog() {
//        long elapsedTime = controller.getElapsedTime();
//        String message = "Félicitations ! Vous avez gagné en " + (elapsedTime / 1000) + " secondes !";
//        JOptionPane.showMessageDialog(this, message, "Victoire !", JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    public void showGameLostDialog() {
//        JOptionPane.showMessageDialog(this, "Dommage, vous avez perdu !", "Défaite", JOptionPane.INFORMATION_MESSAGE);
//    }
//}

package view;

import controller.GameController;
import model.Card;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

// GameView.java
public class GameView extends JFrame {
    private GameController controller;
    private JPanel cardPanel;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;
    private JLabel timerLabel;
    private Timer timerUpdateTimer;
    final int nombreDeCartes = 4;

    private JButton[][] cardButtons;

    public GameView(GameController controller) {
        this.controller = controller;

        setTitle("Jeu de Paires");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);

        initializeUI();
        startTimerUpdate();

        setVisible(true);
    }

    private void initializeUI() {
        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Panel d'information (tentatives, score, timer)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 3));

        attemptsLabel = new JLabel("Essais restants: " + controller.getRemainingAttempts());
        scoreLabel = new JLabel("Score: " + controller.getScore());
        timerLabel = new JLabel("Temps: 0s");

        infoPanel.add(attemptsLabel);
        infoPanel.add(scoreLabel);
        infoPanel.add(timerLabel);

        mainPanel.add(infoPanel, BorderLayout.NORTH);

        // Panel de cartes (grille 4x4)
        cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(4, 4, 10, 10));
        cardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cardButtons = new JButton[4][4];

        for (int i = 0; i < nombreDeCartes; i++) {
            for (int j = 0; j < nombreDeCartes; j++) {
                final int index = i * nombreDeCartes + j;
                JButton cardButton = new JButton();
                cardButton.setPreferredSize(new Dimension(120, 120));

                cardButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.handleCardClick(index);
                    }
                });

                cardButtons[i][j] = cardButton;
                cardPanel.add(cardButton);
            }
        }

        mainPanel.add(cardPanel, BorderLayout.CENTER);

        // Panel pour les boutons du bas
        JPanel buttonPanel = new JPanel();

        // Bouton de redémarrage
        JButton restartButton = new JButton("Redémarrer");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.restartGame();
            }
        });

        // Bouton pour afficher les high scores
        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showHighScores();
            }
        });

        buttonPanel.add(restartButton);
        buttonPanel.add(highScoresButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void startTimerUpdate() {
        timerUpdateTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimerLabel();
            }
        });
        timerUpdateTimer.start();
    }

    public void updateView() {
        List<Card> cards = controller.getCards();
        for (int i = 0; i < nombreDeCartes; i++) {
            for (int j = 0; j < nombreDeCartes; j++) {
                int index = i * nombreDeCartes + j;
                Card card = cards.get(index);
                JButton button = cardButtons[i][j];

                if (card.isFaceUp()) {
                    // Afficher l'image de la carte
                    try {
                        ImageIcon icon = new ImageIcon(getClass().getResource("/" + card.getImagePath()));
                        Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        button.setIcon(new ImageIcon(image));
                        button.setText("");
                    } catch (Exception e) {
                        button.setIcon(null);
                        button.setText(card.getImagePath());
                    }
                } else {
                    // Carte face cachée
                    button.setIcon(null);
                    button.setText("?");
                }

                // Désactiver le bouton si la carte est appariée
                button.setEnabled(!card.isMatched());
            }
        }

        // Mettre à jour les labels d'information
        attemptsLabel.setText("Essais restants: " + controller.getRemainingAttempts());
        scoreLabel.setText("Score: " + controller.getScore());
        updateTimerLabel();
    }

    private void updateTimerLabel() {
        long elapsedSeconds = controller.getElapsedTime() / 1000;
        timerLabel.setText("Temps: " + elapsedSeconds + "s");
    }
}