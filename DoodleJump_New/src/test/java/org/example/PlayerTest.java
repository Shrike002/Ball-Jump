package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(200, 500);
    }

    @Test
    void testInitialPosition() {
        assertEquals(200, player.getX());
        assertEquals(500, player.getY());
    }

    @Test
    void update() {
        player.setVelocityY(5);
        player.update();
        assertTrue(player.getVelocityY() > 5, "VelocityY should increase due to gravity");
    }

    @Test
    void testJump() {
        player.landOnGround();
        player.jump();
        assertEquals(-7, player.getVelocityY(), "Jump should set velocityY to -7");
    }

    @Test
    void testLandOnGround() {
        player.landOnGround();
        assertTrue(player.isOnGround(), "Player should be marked as on ground after landing");
    }

}