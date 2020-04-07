package com.jpro.flashCardsUi.validator;

import com.jpro.flashCardsUi.domain.FetchedUser;
import com.jpro.flashCardsUi.client.UserClient;

import java.util.List;

public class NewUserValidator {
    private String name, mail, password, confirmPassword;

    public NewUserValidator(final String name, final String mail, final String password, final String confirmPassword) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public boolean validateName() {
        UserClient userClient = new UserClient();
        List<FetchedUser> users = userClient.getUsers();
        if (name.isEmpty()) {
            return false;
        }
        for (FetchedUser user : users) {
            if (user.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public boolean validateEmail() {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(mail);
        return m.matches();
    }

    public boolean validatePassword() {
        if (password.isEmpty()) {
            return false;
        }
        return password.equals(confirmPassword);
    }

}
