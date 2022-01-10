package ru.job4j.oop;

public class Error {
    boolean active;
    int status;
    String message;

    public Error() {

    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Ошибка активна: " + this.active);
        System.out.println("Номер ошибки: " + this.status);
        System.out.println("Сообщение: " + this.message);
        System.out.println("--------------------------");
    }

    public static void main(String[] args) {
        Error num1Error = new Error();
        Error num2Error = new Error(true, 555, "Overheating");
        Error num3Error = new Error(false, 232, "System damaged");
        num1Error.printInfo();
        num2Error.printInfo();
        num3Error.printInfo();
    }
}
