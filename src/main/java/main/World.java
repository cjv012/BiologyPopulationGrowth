/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2022
 * Instructor: Prof. Brian King
 *
 * Name: Connor Vucovich
 * Section: 01 - 10a
 * Date: 4/13/22
 * Time: 10:21 AM
 *
 * Project: csci205_final_project
 * Package: main
 * Class: World
 *
 * Description:
 *
 * ****************************************
 */
package main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import main.model.ParticleSystemModel;

public class World {

    /**
     * A reference to the model this controller must work with
     */
    private WorldModel theModel;

    /**
     * The Graphics Context of the canvas
     */
    private GraphicsContext gc;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnStart;

    @FXML
    private Canvas canvas;

    @FXML
    void initialize() {
        assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'world.fxml'.";
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'world.fxml'.";

        this.gc = canvas.getGraphicsContext2D();

    }

    public void setModel(WorldModel theModel) {
        this.theModel = theModel;


        this.btnStart.setOnAction(event -> {
            System.out.println("Press Button");

            /*for (int i = 0; i < 3; i++) {
                WorldThread WorldT = new WorldThread();
                Thread thread = new Thread((Runnable) WorldT);
                thread.start();
            }*/
            Animal animal = this.theModel.generateAnimal((int) this.canvas.getWidth(), (int)this.canvas.getHeight());
            System.out.println(animal.getAnimalLocX() + ", " + animal.getAnimalLocY());
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



                //gc.clearRect(0, 0, canvas.getWidth(),canvas.getHeight() );
                animal.Move();
                System.out.println(animal.getAnimalLocX() + ", " + animal.getAnimalLocY());
                gc.fillOval(animal.getAnimalLocX(), animal.getAnimalLocY(), 10, 10);



            }

            //Animal animal = this.theModel.generateAnimal((int)this.canvas.getWidth(),(int)this.canvas.getHeight());
            //System.out.println(animal.getAnimalLocX() + ", " + animal.getAnimalLocY());
            //while (animal.getEnergy() != 0)
            //gc.fillOval(animal.getAnimalLocX(), animal.getAnimalLocY(), 30, 30);
            //this.theModel.run();
        });

    }

    private class WorldThread implements Runnable {
        @Override
        public void run() {
            try {
                Animal threadAnimal = theModel.generateAnimal((int) canvas.getWidth(), (int) canvas.getHeight());
                System.out.println(threadAnimal.getEnergy());
                //while (threadAnimal.getEnergy() > 0) {
                    gc.fillOval(threadAnimal.getAnimalLocX(), threadAnimal.getAnimalLocY(), 30, 30);
                    threadAnimal.Move();
                    //System.out.println(threadAnimal.getEnergy());
                    Thread.sleep(50);
                    //gc.fillOval(threadAnimal.getAnimalLocX(), threadAnimal.getAnimalLocY(), 30, 30);
                //}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


