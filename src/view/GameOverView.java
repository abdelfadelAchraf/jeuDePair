package view;

import controller.GameController;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameOverView extends JFrame {
    private GameController controller;
    private boolean isWin;

    public GameOverView(GameController controller, boolean isWin) {
        this.controller = controller;
        this.isWin = isWin;

        setTitle(isWin ? "Victoire !" : "Partie terminée");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        initializeUI();

        setVisible(true);
    }

    private void initializeUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Message
        String message;
        if (isWin) {
            message = "Félicitations ! Vous avez gagné en " +
                    (controller.getElapsedTime() / 1000) + " secondes !";
        } else {
            message = "Vous avez épuisé vos 3 essais !";
        }

        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(messageLabel, BorderLayout.NORTH);

        // Stats
        JPanel statsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel scoreLabel = new JLabel("Score: " + controller.getScore(), SwingConstants.CENTER);
        JLabel timeLabel = new JLabel("Temps: " + (controller.getElapsedTime() / 1000) + " secondes", SwingConstants.CENTER);

        statsPanel.add(scoreLabel);
        statsPanel.add(timeLabel);

        mainPanel.add(statsPanel, BorderLayout.CENTER);

        // Boutons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));

        JButton restartButton = new JButton("Rejouer");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.restartGame();
                dispose();
            }
        });

        JButton highScoresButton = new JButton("High Scores");
        highScoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.showHighScores();
            }
        });

        JButton exitButton = new JButton("Quitter");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(restartButton);
        buttonPanel.add(highScoresButton);
        buttonPanel.add(exitButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}