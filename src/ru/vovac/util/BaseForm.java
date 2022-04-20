package ru.vovac.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public abstract class BaseForm extends JFrame {

    private static final String WINDOW_TITLE = "Product manager";
    private static Image WINDOW_ICON;

    static {
        try {
            WINDOW_ICON = ImageIO.read(BaseForm.class.getClassLoader().getResource("icon.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BaseForm (int formWidth, int formHeight){
        setSize(new Dimension(formWidth, formHeight));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - formWidth) / 2, (screenSize.height - formHeight) / 2);
        setTitle(WINDOW_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(WINDOW_ICON);
        setResizable(false);
    }

}
