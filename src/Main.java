import controller.GameController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Créer la base de données si elle n'existe pas
        createDatabaseIfNotExists();

        // Demander le nom du joueur avec un bouton personnalisé
        String playerName = getPlayerName();
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Joueur";
        }

        new GameController(playerName);
    }

    private static String getPlayerName() {
        JTextField nameField = new JTextField(15);
        Object[] message = {"Entrez votre nom:", nameField};
        String[] options = {"Jouer", "Annuler"}; // "Valider" au lieu de "OK"

        int result = JOptionPane.showOptionDialog(
                null,
                message,
                "Jeu de Paires",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0] // "Valider" est sélectionné par défaut
        );

        if (result == JOptionPane.YES_OPTION) {
            return nameField.getText();
        } else {
            // Si l'utilisateur annule, quitter l'application
            System.exit(0);
            return null;
        }
    }

    private static void createDatabaseIfNotExists() {
        try {
            // Créer la connexion à MySQL sans spécifier de base de données
            final String url = "jdbc:mysql://localhost:3306/";
            final String user = "root";
            final String password = "";
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();

            // Créer la base de données si elle n'existe pas
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS memory_game");

            // Utiliser la base de données
            stmt.executeUpdate("USE memory_game");

            // Créer la table des scores si elle n'existe pas
            String createTableSQL = "CREATE TABLE IF NOT EXISTS scores (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "player_name VARCHAR(100) NOT NULL," +
                    "score INT NOT NULL," +
                    "elapsed_time BIGINT NOT NULL," +
                    "date_played DATETIME NOT NULL" +
                    ")";
            stmt.executeUpdate(createTableSQL);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erreur de connexion à la base de données. Vérifiez que MySQL est en cours d'exécution.",
                    "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}