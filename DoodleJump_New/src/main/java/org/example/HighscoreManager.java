package org.example;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighscoreManager {
    private static final String HIGHSCORE_FILE = "highscore.json";
    private int highscore;

    public void loadHighscore() {
        try (FileReader reader = new FileReader(HIGHSCORE_FILE)) {
            Gson gson = new Gson();
            highscore = gson.fromJson(reader, Integer.class);
        } catch (IOException e) {
            highscore = 0;
        }
    }

    public void saveHighscore() {
        try (FileWriter writer = new FileWriter(HIGHSCORE_FILE)) {
            Gson gson = new Gson();
            gson.toJson(highscore, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int score) {
        this.highscore = score;
    }
}
