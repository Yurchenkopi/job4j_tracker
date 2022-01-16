package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Тррррр");
    }

    @Override
    public void passengers(int numPassengers) {
        System.out.println("Количество пассажиров: " + numPassengers);
    }

    @Override
    public int refuel(int litresFuel) {
        return litresFuel * 50;
    }
}
