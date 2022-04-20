package ru.vovac.forms;

import ru.vovac.entity.ProductEntity;
import ru.vovac.manager.ProductEntityManager;
import ru.vovac.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class UpdateForm extends BaseForm {
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
    private JSpinner IDField;

    private ProductEntity productEntity;

    public UpdateForm(ProductEntity entity) {
        super(800, 800);
        setContentPane(mainPanel);
        initButtons();
        productEntity = entity;
        updateFieldValues();
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

    private void updateFieldValues(){
        titleField.setText(productEntity.getTitle());
        productTypeField.setText(productEntity.getProductType());
        articleField.setText(productEntity.getArticleNumber());
        descriptionField.setText(productEntity.getDescription());
        imageField.setText(productEntity.getImagePath());
        personCountField.setValue(productEntity.getPersonCount());
        workshopNumberField.setValue(productEntity.getWorkshopNumber());
        minCostField.setValue(productEntity.getMinCost());
        IDField.setValue(productEntity.getID());
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

        productEntity.setTitle(title);
        productEntity.setProductType(productType);
        productEntity.setArticleNumber(article);
        productEntity.setDescription(description);
        productEntity.setImagePath(image);
        productEntity.setPersonCount(personCount);
        productEntity.setWorkshopNumber(workshopNumber);
        productEntity.setMinCost(minCost);

        try {
            ProductEntityManager.update(productEntity);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
