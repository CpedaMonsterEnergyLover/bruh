package ru.vovac.entity;

import ru.vovac.util.BaseForm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ProductEntity {

    private int ID;
    private String title;
    private String productType;
    private String articleNumber;
    private String description;
    private String imagePath;
    private int personCount;
    private int workshopNumber;
    private int minCost;

    private ImageIcon image;

    public ProductEntity(int ID, String title, String productType, String articleNumber, String description, String image, int personCount, int workshopNumber, int minCost) {
        this.ID = ID;
        this.title = title;
        this.productType = productType;
        this.articleNumber = articleNumber;
        this.description = description;
        this.imagePath = image;
        this.personCount = personCount;
        this.workshopNumber = workshopNumber;
        this.minCost = minCost;
        try {
            this.image = new ImageIcon(ImageIO.read(BaseForm.class.getClassLoader().getResource(imagePath)).getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        } catch (Exception e) {
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public int getWorkshopNumber() {
        return workshopNumber;
    }

    public void setWorkshopNumber(int workshopNumber) {
        this.workshopNumber = workshopNumber;
    }

    public int getMinCost() {
        return minCost;
    }

    public void setMinCost(int minCost) {
        this.minCost = minCost;
    }
}
