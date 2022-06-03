package ru.vovac;

import ru.vovac.forms.AuthForm;
import ru.vovac.forms.ProductsTableForm;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

    public enum UserRole {
        GUEST,
        CUSTOMER,
        SELLER,
        ADMIN,
        NONE
    }

    public static UserRole AUTH_ROLE;

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        AUTH_ROLE = UserRole.NONE;
//        new ProductsTableForm();
        new AuthForm();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/tryexam02", "root", "1111");
    }

}
