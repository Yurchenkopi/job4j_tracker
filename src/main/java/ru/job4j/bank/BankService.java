package ru.job4j.bank;

import java.util.*;

/**
 * Класс условно описывает работу простейшего банковского сервиса.
 * Основным элементом класса является справочник, хранящий информацию
 * по спискам счетов и их балансом для каждого пользователя.
 * @author Pavel Yurchenko
 * @version 1.0
 */

public class BankService {
    /**
     * Хранение данных пользователей по счетам с балансом осуществляется
     * в ассоциативной карте
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод позволяет добавить в справочник нового пользователя, если такого пользователя ранее
     * не было в справочнике
     * @param user - пользователь, который должен быть добавлен в систему
     */

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод позволяет присвоить пользователю новый аккаунт
     * Если пользователь не найден по номеру паспорта или номер счета пользователя
     * уже существует, то аккаунт не создастся
     * @param passport используется для поиска пользователя User в справочнике
     * @param account содержит информацию с номером счета и балансом средств
     */

    public void addAccount(String passport, Account account) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent() && !users.get(user.get()).contains(account)) {
            users.get(user.get()).add(account);
        }
    }

    /**
     * Метод позволяет найти пользователя типа User по номеру паспорта
     * @param passport передаётся для поиска пользователя в справочнике
     * @return возвращает найденного пользователя
     */

    public Optional<User> findByPassport(String passport) {
        return users.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
    }

    /**
     * Метод позволяет найти по номеру паспорта пользователя и
     * номеру счета аккаунт пользователя
     * @param passport передаваемый номер папорта
     * @param requisite передаваемый номер счета
     * @return возвращает аккаунт
     */

    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> user = findByPassport(passport);
        Optional<Account> rsl = Optional.empty();
        if (user.isPresent()) {
            rsl = users.get(user.get()).stream()
                    .filter(account -> account.getRequisite().equals(requisite))
                    .findFirst();
        }
        return rsl;
    }

    /**
     * Метод позволяет перевести денежные средства с одного счета на другой.
     * Если аккаунт, которому принадлежит счет, с которого осуществляется перевод null или
     * счет этого аккаунта null или баланс этого счета меньше переводимой суммы, перевод
     * осуществлен не будет и метод завершится со значением false.
     * @param srcPassport номер паспорта пользователя, со счета которго осуществляется перевод
     * @param srcRequisite номер счета, с которого осуществляется перевод
     * @param destPassport номер паспорта пользователя, являющегося получателем перевода
     * @param destRequisite номер счета получателя
     * @param amount количество переводимых денежных средств
     * @return в случае, если перевод выполнен возвращает true, если перевод отклонен - false
     */

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite).get();
        Account destAccount = findByRequisite(destPassport, destRequisite).get();
        if (srcRequisite != null && srcAccount != null && srcAccount.getBalance() >= amount) {
            destAccount.setBalance(destAccount.getBalance() + amount);
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            rsl = true;
        }
        return rsl;
    }
}
