package com.example.elevator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Simulation extends Application {

    private ElevatorController elevatorController;

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
        primaryStage.setTitle("Elevator Simulation");
        Button runSimulationButton = new Button("Run Simulation");
        runSimulationButton.setOnAction(event -> runSimulation());

        VBox vbox = new VBox(runSimulationButton);
        Scene scene = new Scene(vbox, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.show();
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

        // Initialize a specified number of passengers with their types and requests
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

        return passengers; // Add this line
    }

    private int getRandomFloor() {
        // Placeholder logic to generate a random floor number
        // For simplicity, this example returns a random number between 1 and 10
        return (int) (Math.random() * 10) + 1;
    }

    private void runSimulation() {
        // Run simulation logic
        int numIterations = 10;
        for (int i = 1; i <= numIterations; i++) {
            System.out.println("Simulation Iteration: " + i);
            elevatorController.moveElevators();
            elevatorController.processPassengerRequests();
            // Additional simulation logic or metrics tracking can be added here
        }
    }
}




