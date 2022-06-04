package com.aws.demo.model;

import com.aws.demo.controller.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Account {
    private String account;
    private String password;

    public void setAccount(String account) {
        this.account = account;
    }
    public String getAccount() {
        return account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    static ArrayList<String> account_db;
    static ArrayList<String> password_db;
    static DBTitle t = new DBTitle();

    public static void GetAccountInfo(DBTitle t) throws SQLException, ClassNotFoundException {
        account_db = new ArrayList<>();
        password_db = new ArrayList<>();
        ResultSet rs = DatabaseManager.view_table("user_information");
        while (rs.next() == true) {
            account_db.add(rs.getString(t.a1));
            password_db.add(rs.getString(t.a2));
        }
    }

    public static Boolean login(Account acc) throws SQLException, ClassNotFoundException {
        GetAccountInfo(t);
        for (int i = 0; i < account_db.size(); i++) {
            if (acc.getAccount().equals(account_db.get(i)) && acc.getPassword().equals(password_db.get(i))) {
                return true;
            }
        }
        return false;
    }
    public static void change_password(Account acc) {
        String np = "input";
        acc.password = np;
    }

}
