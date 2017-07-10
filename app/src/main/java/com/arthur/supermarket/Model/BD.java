package com.arthur.supermarket.Model;


import java.util.ArrayList;

public class BD {

    private ArrayList<Product> products;

    public BD(){
        products = new ArrayList<>();
    }

    public void postProduct(Product product){
        products.add(product);
    }
    public void deleteProduct(Product product){
        products.remove(product);
    }
    public Product getProductByCode(String code){
        for(Product product : products){
            if(product.getCode().equals(code)){
                return product;
            }
        }

        return null;
    }

}
