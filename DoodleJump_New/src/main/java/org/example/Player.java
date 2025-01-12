package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player {
    private double x, y, velocityX = 0, velocityY = 0;
    private final double width = 40, height = 40;
    private boolean onGround = false;

    public Player(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        if (!onGround) {
            velocityY += 0.1;
        }

        y += velocityY;
        x += velocityX;

        if (x < -width) x = 400;
        if (x > 400) x = -width;
    }

    public void jump() {
        if (onGround) {
            velocityY = -7;
            onGround = false;
        }
    }

    public void landOnGround() {
        velocityY = 0;
        onGround = true;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillOval(x, y, width, height);
    }
}
