package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {

    }

    @Override
    public void passengers(int numPassengers) {

    }

    @Override
    public int refuel(int litresFuel) {
        return litresFuel * 50;
    }
}
