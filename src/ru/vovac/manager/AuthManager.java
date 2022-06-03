package ru.vovac.manager;

import ru.vovac.App;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthManager {

    public static App.UserRole Login(String login, String password) throws SQLException {

        try (Connection c = App.getConnection()){

            String sql = "select role from users where login =? and password=?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            App.UserRole role = App.UserRole.NONE;



            if(rs.next()) {
                System.out.println(rs.getString("role"));
                switch (rs.getString("role")) {
                    case "Администратор" -> role = App.UserRole.ADMIN;
                    case "Продавец" -> role = App.UserRole.SELLER;
                    case "Покупатель" -> role = App.UserRole.CUSTOMER;
                }
            }

            return role;
        }
    }

}
