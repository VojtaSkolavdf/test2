package PseudoGenFX;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PongFXSingle extends Application {

    int tmp;
    static final int SPEED = 3;
    static final int WIDTH = 800;
    static final int HEIGHT = 500;
    static final int PLAYER_HEIGHT = 20;
    static final int PLAYER_WIDTH = 80;
    double Radius = 50;
    double speedY = SPEED;
    double speedX = SPEED;
    Random rand = new Random();
    int score = 0;
    int x;
    double BallPosX = rand.nextInt(WIDTH - 200);
    double BallPosY = rand.nextInt(HEIGHT - 200);
    boolean Start;
    double PlayerPosX = 0;
    double PlayerPosY = HEIGHT - 20;
    int games = 0;
    final int BLOCK_WIDTH = 60;
    final int BLOCK_HEIGHT = 20;

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> start(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);

        canvas.setOnMouseMoved(e -> PlayerPosX = e.getX());
        canvas.setOnMouseClicked(e -> Start = true);

        primaryStage.setScene(new Scene(new StackPane(canvas)));
        primaryStage.show();
        primaryStage.setTitle("Ping-Pong");
        tl.play();

    }

    private void start(GraphicsContext gc) {

        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
        gc.setFill(Color.WHITE);

        if (Start) {
            BallPosY = BallPosY - speedY;
            BallPosX = BallPosX - speedX;

            gc.fillOval(BallPosX, BallPosY, Radius, Radius);
            gc.fillRect(PlayerPosX, PlayerPosY, PLAYER_WIDTH, PLAYER_HEIGHT);

            for (int x = 0; x <= 70; x += 10) {

                gc.fillOval(PlayerPosX + x, PlayerPosY - 5, Radius / 5, Radius / 5);
            }

            gc.setStroke(Color.LIME);
            gc.setFont(Font.font("Ariel", 20));
            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText(score + "", WIDTH / 2, 100);

            if (BallPosY <= 0) {
                speedY *= -1;
            }

            if (BallPosX > WIDTH - Radius || BallPosX < 0) {

                speedX *= -1;
            }

            if ((BallPosX < PlayerPosX + PLAYER_WIDTH && BallPosX > PlayerPosX - PLAYER_WIDTH / 2) && BallPosY >= HEIGHT - PLAYER_HEIGHT - Radius) {

                speedY *= -1 * 1.05;
                x = rand.nextInt(100);
                if (x >= 50) {
                    speedX *= - 1;
                } else {
                    speedX *= 1;
                }
                score++;
                Radius -= 1;

            } else if (BallPosY > HEIGHT) {
                tmp = score;
                reset();

            }

        } else {

            gc.setStroke(Color.YELLOW);
            gc.setFont(Font.font("Ariel", 20));
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("Click to Start", WIDTH / 2, HEIGHT / 2);
            gc.setFont(Font.font("Ariel", 10));
            gc.fillText("Vojtech Zahradnik", 755, 494);

            if (games >= 1) {
                gc.setFont(Font.font("Ariel", 20));
                gc.strokeText("Your score is " + tmp + ". Lets continue!", WIDTH / 2, HEIGHT - 200);
                gc.fillText("Is it easy ? Now it will be harder!", WIDTH / 2, HEIGHT - 180);
                gc.fillText("Game number: " + games, WIDTH / 2, HEIGHT - 220);
                gc.fillText("Record is 42", WIDTH / 2, HEIGHT - 160);

            }

        }

    }

    public void reset() {

        Start = false;
        BallPosX = rand.nextInt(WIDTH - 200);
        BallPosY = rand.nextInt(100);
        speedX = SPEED;
        speedY = SPEED;
        games++;
        Radius = 50;
        score = 0;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
