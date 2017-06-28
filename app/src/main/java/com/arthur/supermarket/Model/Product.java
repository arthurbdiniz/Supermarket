package com.arthur.supermarket.Model;


public class Product {
     public Product(String code, String name, String price, int photo){
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
    private String price;
    private int photo;




}
