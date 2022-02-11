package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель данных аккаунт Account пользователя.
 */

public class Account {
    private String requisite;
    private double balance;

    /**
     * Конструктор инициализирует поля аккаунта по умолчанию
     * @param requisite содержит номер счета
     * @param balance содержит количество денежных средств на счете
     */

    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    public String getRequisite() {
        return requisite;
    }

    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
