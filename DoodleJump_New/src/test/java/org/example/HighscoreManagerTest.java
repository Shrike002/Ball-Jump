package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HighscoreManagerTest {

    private HighscoreManager manager;

    @BeforeEach
    void setUp() {
        manager = new HighscoreManager();
    }

    @Test
    void testInitialHighscore() {
        assertEquals(0, manager.getHighscore(), "Initial highscore should be 0");
    }

    @Test
    void testSetHighscore() {
        manager.setHighscore(100);
        assertEquals(100, manager.getHighscore(), "Highscore should be updated correctly");
    }

    @Test
    void testSaveAndLoadHighscore() {
        manager.setHighscore(150);
        manager.saveHighscore();
        HighscoreManager newManager = new HighscoreManager();
        newManager.loadHighscore();
        assertEquals(150, newManager.getHighscore(), "Highscore should persist after saving and loading");
    }
}
