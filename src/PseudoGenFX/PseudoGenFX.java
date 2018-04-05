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
import javafx.stage.Stage;
import javafx.util.Duration;

public class PseudoGenFX extends Application {

    static final int WIDTH = 800;
    static final int HEIGHT = 500;
    static final int size = 50;
    static final int squareX = 10;
    static final int squareY = 6;
    boolean Start = true;

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> start(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
        primaryStage.setTitle("PseudoGen");
        canvas.setOnMouseClicked(e -> Start = true);
        primaryStage.setScene(new Scene(new StackPane(canvas)));
        primaryStage.show();

    }

    private void start(GraphicsContext gc) {

        if (Start == true) {
            Random rand = new Random();
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, WIDTH, HEIGHT);
            gc.setFill(Color.WHITE);
            int grass = 20;
            int mud = 20;
            int rock = 20;
            int lava = 20;
            int water = 20;
            String tmp;
            int x = 0, y = 50;
            int[] stats = new int[5];
            String[] pole = new String[100];

            for (int i = 0; i < 100; i++) {

                if (grass > 0) {
                    pole[i] = "G";
                    grass--;
                    continue;
                } else if (mud > 0) {
                    pole[i] = "M";
                    mud--;
                    continue;
                } else if (rock > 0) {
                    pole[i] = "R";
                    rock--;
                    continue;
                } else if (lava > 0) {
                    pole[i] = "L";
                    lava--;
                    continue;
                } else if (water > 0) {
                    pole[i] = "W";
                    water--;
                    continue;
                }
            }

            for (int i = 0; i < squareY; i++) {
                x = 40;
                for (int k = 0; k < squareX; k++) {
                    tmp = pole[rand.nextInt(100) + 0];
                    if (tmp == "G") {
                        gc.setFill(Color.GREEN);
                        stats[0]++;
                    } else if (tmp == "M") {
                        gc.setFill(Color.rgb(102, 51, 0));
                        stats[1]++;
                    } else if (tmp == "R") {
                        gc.setFill(Color.GRAY);
                        stats[2]++;
                    } else if (tmp == "L") {
                        gc.setFill(Color.RED);
                        stats[3]++;
                    } else if (tmp == "W") {
                        gc.setFill(Color.BLUE);
                        stats[4]++;
                    }

                    gc.fillRect(x, y, size, size);
                    x += 75;
                }
                y += 75;

            }

            double percent = ((double) squareX * (double) squareY) / 100;
            System.out.println("Statistiky generování:");
            System.out.println("Tráva: " + stats[0] + " Procenta: " + (double) stats[0] / percent + "%");
            System.out.println("Bahno: " + stats[1] + " Procenta: " + (double) stats[1] / percent + "%");
            System.out.println("Kámen: " + stats[2] + " Procenta: " + (double) stats[2] / percent + "%");
            System.out.println("Láva: " + stats[3] + " Procenta: " + (double) stats[3] / percent + "%");
            System.out.println("Voda: " + stats[4] + " Procenta: " + (double) stats[4] / percent + "%");
            System.out.println("------------------------------------------------");

            Start = false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
