package  model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Card {
    private int id;
    private String imagePath;
    private boolean matched;
    private boolean faceUp;

    public Card(int id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
        this.matched = false;
        this.faceUp = false;
    }

    // Getters et setters
    public int getId() { return id; }
    public String getImagePath() { return imagePath; }
    public boolean isMatched() { return matched; }
    public void setMatched(boolean matched) { this.matched = matched; }
    public boolean isFaceUp() { return faceUp; }
    public void setFaceUp(boolean faceUp) { this.faceUp = faceUp; }
}


