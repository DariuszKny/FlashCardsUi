package com.jpro.flashCardsUi.validator;

import com.jpro.flashCardsUi.client.UserClient;
import com.jpro.flashCardsUi.domain.FetchedUser;

import java.util.List;
import java.util.stream.Collectors;

public class LoginValidator {
    private String name, password, confirmPassword;
    private FetchedUser fetchedUser = null;

    public LoginValidator(final String name, final String password, final String confirmPassword) {
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public FetchedUser validatedUser() {
        if (!validateName() || !validatePassword()) {
            return null;
        } else {
            UserClient userClient = new UserClient();
            List<FetchedUser> users = userClient.getUsers();

            List<FetchedUser> validatedUsers = users.stream().filter(fetchedUser1 -> fetchedUser1.getName().equals(name))
                    .collect(Collectors.toList());

            if (validatedUsers.size() == 0) {
                return null;
            } else {
                return validatedUsers.get(0);
            }
        }
    }


    public boolean validateName() {
        if (name.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validatePassword() {
        if (password.isEmpty()) {
            return false;
        }
        return password.equals(confirmPassword);
    }
}
