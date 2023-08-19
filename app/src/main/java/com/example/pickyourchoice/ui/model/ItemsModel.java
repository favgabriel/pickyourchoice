package com.example.pickyourchoice.ui.model;

public class ItemsModel {
    String id;
    String image;
    String item_name;
    String item_price;
    String item_description;
    String category;

    public ItemsModel() {
    }

    public ItemsModel(String id, String image, String item_name, String item_price, String item_description, String category) {
        this.id = id;
        this.image = image;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_description = item_description;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
