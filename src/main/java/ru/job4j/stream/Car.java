package ru.job4j.stream;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

public class Car {
    private String brand;

    private String model;

    private LocalDate created;

    private double volume;

    private String color;

    static class Builder {
        private String brand;
        private String model;
        private LocalDate created;
        private double volume;
        private String color;

        Builder buildBrand(String brand) {
            this.brand = brand;
            return this;
        }

        Builder buildModel(String model) {
            this.model = model;
            return this;
        }

        Builder buildCreated(LocalDate created) {
            this.created = created;
            return this;
        }

        Builder buildVolume(double volume) {
            this.volume = volume;
            return this;
        }

        Builder buildColor(String color) {
            this.color = color;
            return this;
        }

        Car build() {
            Car car = new Car();
            car.brand = brand;
            car.model = model;
            car.created = created;
            car.volume = volume;
            car.color = color;
            return car;
        }
    }

    @Override
    public String toString() {
        return "Car{"
                + "brand='" + brand + '\''
                + ", model='" + model + '\''
                + ", created=" + created
                + ", volume=" + volume
                + ", color='" + color + '\''
                + '}';
    }

    public static void main(String[] args) {
        Car car1 = new Builder()
                .buildBrand("Toyota")
                .buildModel("Camry")
                .buildCreated(LocalDate.of(2021, 6, 1))
                .buildVolume(2.5)
                .buildColor("Red")
                .build();
        Car car2 = new Builder()
                .buildBrand("VAZ2107")
                .buildModel("Zhiguli")
                .buildCreated(LocalDate.of(1987, 5, 19))
                .buildColor("Green")
                .build();
        Car car3 = new Builder()
                .buildBrand("Toyota")
                .buildModel("RAV4")
                .buildVolume(2.0)
                .buildColor("Silver")
                .build();
        Car car4 = new Builder()
                .buildBrand("Saab")
                .buildModel("95")
                .buildCreated(LocalDate.of(1995, 11, 11))
                .buildVolume(2.0)
                .buildColor("White")
                .build();
        Car[] cars = {car1, car2, car3, car4};
        Stream.of(cars)
                .forEach(System.out::println);
    }
}