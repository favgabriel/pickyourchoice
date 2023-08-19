package com.example.pickyourchoice.ui.model;

public class CartModel {
    String id;
    String itemimage;
    String itemname;
    String itemprice;
    String itemquantity;

    public CartModel() {
    }

    public CartModel(String id, String itemimage, String itemname, String itemprice, String itemquantity) {
        this.id = id;
        this.itemimage = itemimage;
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.itemquantity = itemquantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemimage() {
        return itemimage;
    }

    public void setItemimage(String itemimage) {
        this.itemimage = itemimage;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getItemquantity() {
        return itemquantity;
    }

    public void setItemquantity(String itemquantity) {
        this.itemquantity = itemquantity;
    }
}
