package com.example.elevator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Simulation extends Application {

    private ElevatorController elevatorController;
    private int currentIteration = 0;
    private int totalIterations = 10;  // Set the desired number of iterations

    private static final int FLOOR_HEIGHT = 50;
    private static final int NUM_FLOORS = 5;
    private static final int ELEVATOR_WIDTH = 50;
    private static final int ELEVATOR_HEIGHT = 30;

    private Text iterationCounterText;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Initialize elevators and passengers with specified percentages
        List<Elevator> elevators = initializeElevators();
        List<Passenger> passengers = initializePassengers();

        // Create ElevatorController instance
        elevatorController = new ElevatorController(elevators, passengers);

        // Set up GUI
        primaryStage.setTitle("Elevator Simulator");

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: #F4F4F4;");

        // Create ElevatorAnimation instances for each elevator
        ElevatorAnimation elevator1 = new ElevatorAnimation(Color.BLUE);
        ElevatorAnimation elevator2 = new ElevatorAnimation(Color.GREEN);
        ElevatorAnimation elevator3 = new ElevatorAnimation(Color.RED);
        ElevatorAnimation elevator4 = new ElevatorAnimation(Color.YELLOW);

        // Add elevators to the vbox
        vbox.getChildren().addAll(
                elevator1,
                elevator2,
                elevator3,
                elevator4
        );

        Text titleText = new Text("Elevator Simulator");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleText.setFill(Color.DARKBLUE);

        iterationCounterText = new Text("Iteration: " + currentIteration);
        iterationCounterText.setFont(Font.font("Arial", 16));

        Text projectInfoText = new Text("Project by Steven Buruca and Marvin Cabrera");
        projectInfoText.setFont(Font.font("Arial", 12));
        projectInfoText.setFill(Color.GRAY);

        Button runSimulationButton = new Button("Run Simulation");
        runSimulationButton.setStyle("-fx-font-size: 16; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        runSimulationButton.setOnAction(event -> runSimulation(elevator1, elevator2, elevator3, elevator4));

        vbox.getChildren().addAll(titleText, iterationCounterText, createFloorInfoGrid(), runSimulationButton, projectInfoText);

        Scene scene = new Scene(vbox, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public class ElevatorAnimation extends StackPane {

        private Rectangle elevator;
        private TranslateTransition elevatorTransition;

        public ElevatorAnimation(Color color) {
            elevator = createElevator(color);
            getChildren().add(elevator);

            elevatorTransition = new TranslateTransition(Duration.seconds(1), elevator);
            elevatorTransition.setOnFinished(event -> {

            });
        }

        private Rectangle createElevator(Color color) {
            return new Rectangle(ELEVATOR_WIDTH, ELEVATOR_HEIGHT, color);
        }

        public void animateElevator(int targetFloor) {
            int targetY = targetFloor * FLOOR_HEIGHT;
            elevatorTransition.setToY(targetY);
            elevatorTransition.play();
        }
    }

    private void runSimulation(ElevatorAnimation elevator1, ElevatorAnimation elevator2, ElevatorAnimation elevator3, ElevatorAnimation elevator4) {
        // Run simulation logic for one iteration
        System.out.println("Simulation Iteration: " + ++currentIteration);
        elevatorController.moveElevators();
        elevatorController.processPassengerRequests();
        iterationCounterText.setText("Iteration: " + currentIteration);

        // Animate elevators to their target floors
        elevator1.animateElevator(elevatorController.getElevator(0).getCurrentFloor());
        elevator2.animateElevator(elevatorController.getElevator(1).getCurrentFloor());
        elevator3.animateElevator(elevatorController.getElevator(2).getCurrentFloor());
        elevator4.animateElevator(elevatorController.getElevator(3).getCurrentFloor());

        // Check if all iterations are completed
        if (currentIteration == totalIterations) {
            System.out.println("Simulation completed.");

        }
    }

    private GridPane createFloorInfoGrid() {
        // Create a grid for floor information display
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);


        for (int floor = 1; floor <= 10; floor++) {
            Text floorLabel = new Text("Floor " + floor + ": ");
            grid.add(floorLabel, 0, floor - 1);
        }

        return grid;
    }

    private List<Elevator> initializeElevators() {
        List<Elevator> elevators = new ArrayList<>();

        int numStandardElevators = 4;
        int numExpressElevators = 2;
        int numFreightElevators = 1;
        int numGlassElevators = 1;

        for (int i = 1; i <= numStandardElevators; i++) {
            elevators.add(new Elevator("Standard", i, 10)); // Use "Standard" as the type
        }

        for (int i = 1; i <= numExpressElevators; i++) {
            elevators.add(new Elevator("Express", i + numStandardElevators, 8)); // Use "Express" as the type
        }

        for (int i = 1; i <= numFreightElevators; i++) {
            elevators.add(new Elevator("Freight", i + numStandardElevators + numExpressElevators, 5)); // Use "Freight" as the type
        }

        for (int i = 1; i <= numGlassElevators; i++) {
            elevators.add(new Elevator("Glass", i + numStandardElevators + numExpressElevators + numFreightElevators, 6)); // Use "Glass" as the type
        }

        return elevators;
    }

    private List<Passenger> initializePassengers() {
        List<Passenger> passengers = new ArrayList<>();

        int numStandardPassengers = 30;
        int numVIPPassengers = 5;
        int numFreightPassengers = 7;
        int numGlassPassengers = 3;

        for (int i = 1; i <= numStandardPassengers; i++) {
            passengers.add(new StandardPassenger(i, getRandomFloor(), getRandomFloor()));
        }

        for (int i = 1; i <= numVIPPassengers; i++) {
            passengers.add(new VIPPassenger(i + numStandardPassengers, getRandomFloor(), getRandomFloor()));
        }

        for (int i = 1; i <= numFreightPassengers; i++) {
            passengers.add(new FreightPassenger(i + numStandardPassengers + numVIPPassengers, getRandomFloor(), getRandomFloor()));
        }

        for (int i = 1; i <= numGlassPassengers; i++) {
            passengers.add(new GlassPassenger(i + numStandardPassengers + numVIPPassengers + numFreightPassengers, getRandomFloor(), getRandomFloor()));
        }

        return passengers;
    }


    private void runSimulation() {
        // Run simulation logic for one iteration
        System.out.println("Simulation Iteration: " + ++currentIteration);
        elevatorController.moveElevators();
        elevatorController.processPassengerRequests();
        iterationCounterText.setText("Iteration: " + currentIteration);

        // Animate elevators to their target floors
        List<Elevator> elevators = getElevatorsFromController();
        for (int i = 0; i < elevators.size(); i++) {
            ElevatorAnimation elevatorAnimation = (ElevatorAnimation) ((VBox) ((VBox) iterationCounterText.getParent()).getChildren().get(i + 1)).getChildren().get(0);
            elevatorAnimation.animateElevator(elevators.get(i).getCurrentFloor());
        }

        // Check if all iterations are completed
        if (currentIteration == totalIterations) {
            System.out.println("Simulation completed.");
            // You can disable the button or handle it based on your requirements
        }
    }


    private List<Elevator> getElevatorsFromController() {
        return elevatorController.getElevators();
    }




    private int getRandomFloor() {

        return (int) (Math.random() * 10) + 1;
    }
}




