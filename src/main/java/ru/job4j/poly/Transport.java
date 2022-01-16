package ru.job4j.poly;

public interface Transport {
    void drive();

    void passengers(int numPassengers);

    int refuel(int litresFuel);
}
