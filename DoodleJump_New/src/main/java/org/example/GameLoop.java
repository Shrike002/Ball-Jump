package org.example;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


public class GameLoop extends AnimationTimer {
    private final GraphicsContext gc;
    private final Scene scene;
    private final Player player;
    private final List<Platform> platforms = new ArrayList<>();
    private final Random random = new Random();
    private double offsetY = 0;
    private boolean leftPressed = false, rightPressed = false;
    private final HighscoreManager highscoreManager;
    private int maxHeight = 0;
    private int score = 0;

    public GameLoop(GraphicsContext gc, Scene scene, HighscoreManager highscoreManager) {
        this.gc = gc;
        this.scene = scene;
        this.highscoreManager = highscoreManager;
        this.player = new Player(200, 500);

        Platform ground = new Platform(0, 590,600,10,PlatformType.NORMAL);
        platforms.add(ground);
        
        for (int i = 0; i < 5; i++) {
            platforms.add(new Platform(random.nextInt(340), 600 - i * 120,60,10,PlatformType.NORMAL));
        }

        setupInput();
    }

    private void setupInput() {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) leftPressed = true;
            if (event.getCode() == KeyCode.RIGHT) rightPressed = true;
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT) leftPressed = false;
            if (event.getCode() == KeyCode.RIGHT) rightPressed = false;
        });
    }

    @Override
    public void handle(long now) {
        if (leftPressed) player.setVelocityX(-5);
        else if (rightPressed) player.setVelocityX(5);
        else player.setVelocityX(0);

        player.update();

        boolean playerLanded = false;

        for (int i = 0; i < platforms.size(); i++) {
            Platform platform = platforms.get(i);

            if (player.getVelocityY() > 0 &&
                    player.getY() + player.getHeight() >= platform.getY() &&
                    player.getY() + player.getHeight() <= platform.getY() + platform.getHeight() &&
                    player.getX() + player.getWidth() > platform.getX() &&
                    player.getX() < platform.getX() + platform.getWidth()) {

                if (platform.isActive()) {
                    playerLanded = true;

                    if (platform.getType() == PlatformType.NORMAL) {
                        player.landOnGround();
                        player.jump();

                    } else if (platform.getType() == PlatformType.PURPLE) {
                        player.landOnGround();
                        player.jump();
                        platform.deactivate();

                    } else if (platform.getType() == PlatformType.YELLOW) {
                        player.setVelocityY(-15);
                    }
                }
                break;
            }
        }

        if (!playerLanded) {
            player.setVelocityY(player.getVelocityY());
            player.setOnGround(false);
        }


        if (player.getY() < 300) {
            offsetY = 300 - player.getY();
            platforms.forEach(p -> p.update(offsetY));
            player.setY(300);
            maxHeight += offsetY;
            score = maxHeight / 10;
        }


        platforms.removeIf(Platform::isOutOfScreen);


        if (platforms.size() < 5) {
            PlatformType randomType = random.nextInt(10) < 8 ? PlatformType.NORMAL :
                    random.nextInt(2) == 0 ? PlatformType.PURPLE : PlatformType.YELLOW;
            platforms.add(new Platform(random.nextInt(340), 0, 60, 10, randomType));
        }
       
        if (score > highscoreManager.getHighscore()) {
            highscoreManager.setHighscore(score);
            highscoreManager.saveHighscore();
        }

        if (player.getY() > 600) {
            stop();
            showGameOverPopup();
        }


        gc.clearRect(0, 0, 400, 600);
        gc.setFill(Color.GREEN);
        gc.fillText("Score: " + score, 10, 20);
        gc.fillText("Highscore: " + highscoreManager.getHighscore(), 10, 40);
        player.render(gc);
        platforms.forEach(p -> p.render(gc));
    }
    private void showGameOverPopup() {

        stop();


        javafx.application.Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText("You lost!");
            alert.setContentText("Your score was: " + score);
            ButtonType replayButton = new ButtonType("Restart Game");
            ButtonType quitButton = new ButtonType("Exit");

            alert.getButtonTypes().setAll(replayButton, quitButton);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == replayButton) {
                restartGame();
            } else if (result.isPresent() && result.get() == quitButton) {
                System.exit(0);
            }
        });
    }

    private void restartGame() {
        GameLoop newGameLoop = new GameLoop(gc, scene, highscoreManager);
        newGameLoop.start();
    }
}
