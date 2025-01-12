package org.example;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighscoreManager implements IHighscoreManager {
    private static final String HIGHSCORE_FILE = "highscore.json";
    private int highscore;

    @Override
    public void loadHighscore() {
        try (FileReader reader = new FileReader(HIGHSCORE_FILE)) {
            Gson gson = new Gson();
            highscore = gson.fromJson(reader, Integer.class);
        } catch (IOException e) {
            highscore = 0;
        }
    }

    @Override
    public void saveHighscore() {
        try (FileWriter writer = new FileWriter(HIGHSCORE_FILE)) {
            Gson gson = new Gson();
            gson.toJson(highscore, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getHighscore() {
        return highscore;
    }

    @Override
    public void setHighscore(int score) {
        this.highscore = score;
    }
}
