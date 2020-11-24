package com.example.firestore;

public class Productsmodel {
    String name;

    public Productsmodel(String name, String id, String price) {
        this.name = name;
        this.id = id;
        this.price = price;
    }

    public Productsmodel(String name, String price) {

    }

    public String getDocumentID() {
        return id;
    }

    public void setDocumentID(String documentID) {
        this.id = id;
    }

    private String id;


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

    String price;

}
