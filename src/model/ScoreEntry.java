package model;

import java.util.Date;

public class ScoreEntry {
    private String playerName;
    private int score;
    private long elapsedTime;
    private Date datePlayed;

    public ScoreEntry(String playerName, int score, long elapsedTime, Date datePlayed) {
        this.playerName = playerName;
        this.score = score;
        this.elapsedTime = elapsedTime;
        this.datePlayed = datePlayed;
    }

    // Getters
    public String getPlayerName() { return playerName; }
    public int getScore() { return score; }
    public long getElapsedTime() { return elapsedTime; }
    public Date getDatePlayed() { return datePlayed; }
}