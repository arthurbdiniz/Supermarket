package com.arthur.supermarket.Model;


import java.util.ArrayList;

public class Product {

    ArrayList<Product> products = new ArrayList<>();
     public Product(String code, String name, double price, int photo){
         setCode(code);
         setName(name);
         setPrice(price);
         setPhoto(photo);
     }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    private String code;
    private String name;
    private double price;
    private int photo;




}
