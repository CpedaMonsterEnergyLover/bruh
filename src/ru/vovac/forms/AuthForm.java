package ru.vovac.forms;

import ru.vovac.App;
import ru.vovac.manager.AuthManager;
import ru.vovac.util.AlertManager;
import ru.vovac.util.BaseForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AuthForm extends BaseForm {
    private JPanel mainPanel;
    private JTextField loginField;
    private JPasswordField passwordField1;
    private JButton guestButton;
    private JButton authButton;

    public AuthForm() {
        super(600, 600);
        initButtons();
        setContentPane(mainPanel);
        setVisible(true);
    }

    private void initButtons(){
        guestButton.addActionListener(e -> {
            App.AUTH_ROLE = App.UserRole.GUEST;
            dispose();
            new ProductsTableForm();
        });
        authButton.addActionListener(e -> {
            String login = loginField.getText();
            String password = passwordField1.getText();
            try {
                System.out.println(login + " " + password);
                App.AUTH_ROLE = AuthManager.Login(login, password);
            } catch (SQLException exception) {
                AlertManager.ShowErrorDialog(this, exception.getMessage());
            }
            if(App.AUTH_ROLE == App.UserRole.NONE){
                AlertManager.ShowErrorDialog(this, "Неправильные логин или пароль");
            } else {
                AlertManager.ShowConfirmDialog(this, "Вы успешно вошли с правами " + App.AUTH_ROLE);
                dispose();
                new ProductsTableForm();
            }
        });
    }
}
