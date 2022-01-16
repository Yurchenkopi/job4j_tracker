package ru.job4j.io;

import java.util.Scanner;

public class Matches {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Игра 11.");
        boolean turn = true;
        int count = 11;
        while (count > 0) {
            String player = turn ? "Первый игрок" : "Второй игрок";
            System.out.println(player + " введите число от 1 до 3:");
            while (!input.hasNextInt()) {
                System.out.println("Введенный символ не является числом");
                input.nextLine();
                System.out.println(player + " введите число от 1 до 3:");
            }
            int matches = Integer.parseInt(input.nextLine());
            if ((matches <= 3) && (matches >= 1)) {
                turn = !turn;
                count = count - matches;
                if (count > 0) {
                    System.out.println("Осталось спичек: " + count + " шт.");
                }

            } else {
                System.out.println("Можно убрать не больше 3 спичек, повторите попытку");
            }
        }
        if (!turn) {
            System.out.println("Выиграл первый игрок");
        } else {
            System.out.println("Выиграл второй игрок");
        }
    }
}