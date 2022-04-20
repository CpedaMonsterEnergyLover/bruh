package ru.vovac.util;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;

public class AlertManager {

    public static void ShowErrorDialog(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    public static void ShowConfirmDialog(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message, "Оповещение", JOptionPane.INFORMATION_MESSAGE);
    }

    public static Integer IntegerInputDialog(Component parent, String message){
        String value = JOptionPane.showInputDialog(parent, message);

        if(value != null){
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e){
                ShowErrorDialog(parent, "Введено некорректное значение");
            }
        }

        return null;
    }

}
