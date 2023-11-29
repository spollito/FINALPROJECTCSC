package com.example.elevator;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private String type;
    private int id;
    private int currentFloor;
    private int maxCapacity;
    private List<Passenger> passengers;

    public Elevator(String type, int id, int maxCapacity) {
        this.type = type;
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.passengers = new ArrayList<>();
    }

    // ... (other methods remain unchanged)

    public void addPassenger(Passenger passenger) {
        if (passengers.size() < maxCapacity) {
            passengers.add(passenger);
            System.out.println("Passenger " + passenger.getId() + " added to " + getType() + " Elevator " + id);
        } else {
            System.out.println(getType() + " Elevator " + id + " is at maximum capacity. Cannot add more passengers.");
        }
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
        System.out.println("Passenger " + passenger.getId() + " removed from " + getType() + " Elevator " + id);
    }

    public boolean isAtMaxCapacity() {
        return passengers.size() == maxCapacity;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    @Override
    public String toString() {
        return getType() + " Elevator " + getId() + " [Current Floor: " + getCurrentFloor() +
                ", Max Capacity: " + getMaxCapacity() +
                ", Passengers: " + getPassengers().size() +
                "]";
    }
}




