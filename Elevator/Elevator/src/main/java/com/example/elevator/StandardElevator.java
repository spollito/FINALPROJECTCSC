package com.example.elevator;

public class StandardElevator extends Elevator {
    public StandardElevator(int id) {
        super("Standard", id , 10); // Standard elevators have a max capacity of 10 passengers
    }
}

