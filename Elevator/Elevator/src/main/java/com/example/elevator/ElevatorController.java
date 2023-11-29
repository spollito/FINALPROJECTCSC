package com.example.elevator;

import java.util.List;

public class ElevatorController {
    private List<Elevator> elevators;
    private List<Passenger> passengers;

    public ElevatorController(List<Elevator> elevators, List<Passenger> passengers) {
        this.elevators = elevators;
        this.passengers = passengers;
    }

    public void moveElevators() {
        for (Elevator elevator : elevators) {
            // Placeholder logic to simulate elevator movement
            int currentFloor = elevator.getCurrentFloor();
            int destinationFloor = calculateNextDestination(currentFloor);

            // Simulate moving the elevator to the next floor
            elevator.setCurrentFloor(destinationFloor);
            System.out.println("Elevator " + elevator.getId() + " moved to floor " + destinationFloor);
        }
    }

    private int calculateNextDestination(int currentFloor) {
        // Placeholder logic to calculate the next destination floor
        // For simplicity, this example just increments the floor by 1
        return currentFloor + 1;
    }

    public void processPassengerRequests() {
        for (Passenger passenger : passengers) {
            // Placeholder logic to find the best elevator for the passenger
            Elevator selectedElevator = findBestElevator(passenger);

            if (selectedElevator != null) {
                selectedElevator.addPassenger(passenger);
                System.out.println("Passenger " + passenger.getId() + " assigned to Elevator " + selectedElevator.getId());
            } else {
                System.out.println("No available elevator for Passenger " + passenger.getId());
            }
        }
    }

    private Elevator findBestElevator(Passenger passenger) {
        // Placeholder logic to find the best elevator for the passenger
        // For simplicity, this example returns the first available elevator
        return elevators.stream()
                .filter(elevator -> !elevator.isAtMaxCapacity())
                .findFirst()
                .orElse(null);
    }
}


