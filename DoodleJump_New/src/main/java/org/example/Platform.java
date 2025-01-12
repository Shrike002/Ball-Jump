package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Platform {
    private double x, y, width, height;
    private boolean outOfScreen = false;
    private boolean active = true;
    private PlatformType type;

    public Platform(double x, double y, double width, double height, PlatformType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public void update(double offsetY) {
        y += offsetY;
        if (y > 600) {
            outOfScreen = true;
        }
    }

    public void render(GraphicsContext gc) {
        if (type == PlatformType.NORMAL) {
            gc.setFill(Color.GREEN);
        } else if (type == PlatformType.PURPLE) {
            gc.setFill(active ? Color.PURPLE : Color.GRAY);
        } else if (type == PlatformType.YELLOW) {
            gc.setFill(Color.YELLOW);
        }
        gc.fillRect(x, y, width, height);
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

    public boolean isOutOfScreen() {
        return outOfScreen;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }

    public PlatformType getType() {
        return type;
    }
}
