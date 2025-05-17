//temporary class, will be replaced with DB implementation
package com.bankapp.controller;

import com.bankapp.model.Account;
import java.util.HashMap;

public class AccountManager {
    private static final HashMap<String, Account> accounts = new HashMap<>();

    static {
        accounts.put("testas", new Account("testas", "Testas Testauskas", "1234", 5000));
        accounts.put("testiene", new Account("testiene", "Testiene Testauskine", "abcd", 3000));
    }

    public static Account getAccount(String userId, String password) {
        Account acc = accounts.get(userId);
        return (acc != null && acc.getPassword().equals(password)) ? acc : null;
    }
}
