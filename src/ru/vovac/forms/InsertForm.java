package ru.vovac.forms;

import ru.vovac.entity.ProductEntity;
import ru.vovac.manager.ProductEntityManager;
import ru.vovac.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class InsertForm extends BaseForm {
    private JTextField titleField;
    private JTextField productTypeField;
    private JTextField articleField;
    private JTextField descriptionField;
    private JTextField imageField;
    private JSpinner personCountField;
    private JSpinner workshopNumberField;
    private JSpinner minCostField;
    private JButton backButton;
    private JButton confirmButton;
    private JPanel mainPanel;

    public InsertForm() {
        super(800, 800);
        setContentPane(mainPanel);
        initButtons();
        setVisible(true);
    }

    private void initButtons() {
        backButton.addActionListener(e -> onBackButtonPressed());
        confirmButton.addActionListener(e -> onConfirmButtonPressed());
    }

    private void onBackButtonPressed(){
        dispose();
        new ProductsTableForm();
    }

    private void onConfirmButtonPressed(){
        String title = titleField.getText();
        String productType = productTypeField.getText();
        String article = articleField.getText();
        String description = descriptionField.getText();
        String image = imageField.getText();
        int personCount = Integer.parseInt(personCountField.getValue().toString());
        int workshopNumber = Integer.parseInt(workshopNumberField.getValue().toString());
        int minCost = Integer.parseInt(minCostField.getValue().toString());

        ProductEntity productEntity = new ProductEntity(
                -1,
                title,
                productType,
                article,
                description,
                image,
                personCount,
                workshopNumber,
                minCost
        );

        try {
            ProductEntityManager.insert(productEntity);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
