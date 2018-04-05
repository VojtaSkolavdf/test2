package PseudoGenFX;  //prejmenuj na jmeno sveho projektu

import java.util.Random; //inputy
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

public class PinBall extends Application { //prejmenuj na jmeno sveho projektu

    Random rand = new Random(); //objekt na random generovani cisel
    char vygenerovano = 0;  //(boolean) jestli jsou vygenerovany castice, aby se negenerovali furt znova resp. jejich pocatecni x/y
    int osaX[] = new int[1000]; //pole kde jsou ulozeny vsechny pozice castic na ose X
    int osaY[] = new int[1000]; //pole kde jsou ulozeny vsechny pozice castic na ose Y

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(600, 400); //utvoreni canvasu /platna
        GraphicsContext gc = canvas.getGraphicsContext2D(); // tvorba gc objektu 
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(100), e -> start(gc))); //timeline.. nastaveni fps
        tl.setCycleCount(Timeline.INDEFINITE); //nekonecna timelina
        primaryStage.setScene(new Scene(new StackPane(canvas)));
        primaryStage.show();
        primaryStage.setTitle("Prach"); //nastaveni titlu
        tl.play(); // timeline se spusti
    }

    private void start(GraphicsContext gc) { // trida ktera se zavola pro beh programu.. vola se na radku 25

        gc.setFill(Color.WHITE); //nastaveni barvy pozadi
        gc.fillRect(0, 0, 600, 400);  // vykresleni pozadi/ obdelni s delkou a sirkou jako okno
        gc.setFill(Color.BLACK); //nastaveni ze castice budou cerne

        for (int i = 0; i < 1000; i++) { //cyklus pro generovani
            if (vygenerovano == 0) { // podminka, pokud neni vygenerovano tak se vygeneruje 1000 pozic pro castice.. x a y
                osaX[i] = rand.nextInt(600) + 0; //generace X pozice
                osaY[i] = rand.nextInt(400) + 0; //generace Y pozice
            }
            gc.fillRect(osaX[i], osaY[i], 3, 3); //vykresleni castice. jedna po druhe
        }

        vygenerovano = 1; //nastaveni ze je vygenerovano, takze v dalsim cyklu se nebudou generovat pozice

        for (int i = 0; i < 1000; i++) { //cyklus pro pohyb

            osaX[i] = osaX[i] + (- 1 + rand.nextInt(3) + 0); //kazda pozice se bud pricte ci odecte .. to je reseno tim -1 + random number...
            osaY[i] = osaY[i] + (- 1 + rand.nextInt(3) + 0); //kazda pozice se bud pricte ci odecte .. to je reseno tim -1 + random number...
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
