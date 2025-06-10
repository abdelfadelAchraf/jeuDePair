//package model;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//// DatabaseManager.java - Gère les interactions avec la base de données
//public class DatabaseManager {
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/memory_game";
//    private static final String USER = "root";
//    private static final String PASS = "";
//
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(DB_URL, USER, PASS);
//    }
//
//    public static void saveGameResult(String playerName, int score, long elapsedTime) {
//        String sql = "INSERT INTO scores (player_name, score, elapsed_time, date_played) VALUES (?, ?, ?, NOW())";
//
//        try (Connection conn = getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, playerName);
//            pstmt.setInt(2, score);
//            pstmt.setLong(3, elapsedTime);
//            pstmt.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static List<ScoreEntry> getTopScores(int limit) {
//        List<ScoreEntry> scores = new ArrayList<>();
////        String sql = "SELECT player_name, score, elapsed_time, date_played FROM scores ORDER BY score DESC, elapsed_time ASC LIMIT ?";
//        String sql = "SELECT * FROM scores ORDER BY score DESC, elapsed_time ASC LIMIT ?";
//        try (Connection conn = getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setInt(1, limit);
//            ResultSet rs = pstmt.executeQuery();
//
//            while (rs.next()) {
//                String playerName = rs.getString("player_name");
//                int score = rs.getInt("score");
//                long elapsedTime = rs.getLong("elapsed_time");
//                Date datePlayed = rs.getDate("date_played");
//
//                scores.add(new ScoreEntry(playerName, score, elapsedTime, datePlayed));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return scores;
//    }
//}
//
//// ScoreEntry.java - Pour stocker les données de score



package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
// DatabaseManager.java - Gère les interactions avec la base de données
public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/memory_game";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void saveGameResult(String playerName, int score, long elapsedTime) {
        // Vérifier si le joueur existe déjà
        ScoreEntry existingScore = getPlayerScore(playerName);

        if (existingScore != null) {
            // Si le joueur existe, mettre à jour son score seulement si le nouveau score est meilleur
            if (score > existingScore.getScore() ||
                    (score == existingScore.getScore() && elapsedTime < existingScore.getElapsedTime())) {
                updatePlayerScore(playerName, score, elapsedTime);
            }
        } else {
            // Si le joueur n'existe pas, insérer un nouveau score
            insertNewScore(playerName, score, elapsedTime);
        }
    }

    private static void insertNewScore(String playerName, int score, long elapsedTime) {
        String sql = "INSERT INTO scores (player_name, score, elapsed_time, date_played) VALUES (?, ?, ?, NOW())";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, playerName);
            pstmt.setInt(2, score);
            pstmt.setLong(3, elapsedTime);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updatePlayerScore(String playerName, int score, long elapsedTime) {
        String sql = "UPDATE scores SET score = ?, elapsed_time = ?, date_played = NOW() WHERE player_name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, score);
            pstmt.setLong(2, elapsedTime);
            pstmt.setString(3, playerName);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ScoreEntry getPlayerScore(String playerName) {
        String sql = "SELECT * FROM scores WHERE player_name = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, playerName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int score = rs.getInt("score");
                long elapsedTime = rs.getLong("elapsed_time");
                Date datePlayed = rs.getDate("date_played");

                return new ScoreEntry(playerName, score, elapsedTime, datePlayed);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ScoreEntry> getTopScores(int limit) {
        List<ScoreEntry> scores = new ArrayList<>();
        String sql = "SELECT * FROM scores ORDER BY score DESC, elapsed_time ASC LIMIT ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String playerName = rs.getString("player_name");
                int score = rs.getInt("score");
                long elapsedTime = rs.getLong("elapsed_time");
                Date datePlayed = rs.getDate("date_played");

                scores.add(new ScoreEntry(playerName, score, elapsedTime, datePlayed));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scores;
    }
}