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

            int currentFloor = elevator.getCurrentFloor();
            int destinationFloor = calculateNextDestination(currentFloor);

            // Simulate moving the elevator to the next floor
            elevator.setCurrentFloor(destinationFloor);
            System.out.println("Elevator " + elevator.getId() + " moved to floor " + destinationFloor);
        }
    }

    private int calculateNextDestination(int currentFloor) {

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

        return elevators.stream()
                .filter(elevator -> !elevator.isAtMaxCapacity())
                .findFirst()
                .orElse(null);
    }

    public Elevator getElevator(int i) {
        return elevators.get(i);
    }

    public List<Elevator> getElevators() {
        return null;
    }
}






