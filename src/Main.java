
import controller.GameController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Créer la base de données si elle n'existe pas
        createDatabaseIfNotExists();

        // Demander le nom du joueur
        String playerName = JOptionPane.showInputDialog(null, "Entrez votre nom:", "Jeu de Paires", JOptionPane.QUESTION_MESSAGE);
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Joueur";
        }

       
        new GameController(playerName);

    }

    private static void createDatabaseIfNotExists() {
        try {
            // Créer la connexion à MySQL sans spécifier de base de données
             final   String url = "jdbc:mysql://localhost:3306/" ;
             final  String user = "root" ;
             final String password ="";
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