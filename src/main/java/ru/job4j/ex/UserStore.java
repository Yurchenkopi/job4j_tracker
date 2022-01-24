package ru.job4j.ex;

public class UserStore {

    public static User findUser(User[] users, String login) throws UserNotFoundException {
        for (User user : users) {
            if (login.equals(user.getUsername())) {
                return user;
            }
        }
        throw new UserNotFoundException("User not found");
    }

    public static boolean validate(User user) throws UserInvalidException {
        if (!user.isValid() || user.getUsername().length() <= 3) {
            throw new UserInvalidException("Invalid user");
        }
        return user.isValid() || user.getUsername().length() > 3;
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Petr Arsentiev", true)
        };
        try {
            User user = findUser(users, "Petr Arsentiev");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
        } catch (UserInvalidException in) {
            in.printStackTrace();
        } catch (UserNotFoundException fo) {
            fo.printStackTrace();
        }
    }
}
