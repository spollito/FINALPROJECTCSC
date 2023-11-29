package com.example.elevator;

public class FreightElevator extends Elevator {
    public FreightElevator(int id) {
        super("Freight",id, 5); // Freight elevators have a max capacity of 5 passengers
    }
}

