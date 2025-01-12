package org.example;

public interface IHighscoreManager {
    void loadHighscore();

    void saveHighscore();

    int getHighscore();

    void setHighscore(int score);
}
