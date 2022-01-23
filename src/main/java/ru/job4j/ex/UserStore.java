package ru.job4j.ex;

public class UserStore {

    public static User findUser(User[] users, String login) throws UserNotFoundException {
        int rsl = -1;
        for (int i = 0; i < users.length; i++) {
            if (login.equals(users[i].getUsername())) {
                rsl = i;
                break;
            }
        }
        if (rsl == -1) {
            throw new UserNotFoundException("User not found");
        }
        return rsl == -1 ? null : users[rsl];
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
