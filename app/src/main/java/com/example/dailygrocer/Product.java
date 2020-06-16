package com.example.dailygrocer;

public class Product {
    String id;
    String cropname;
    String quantities;
    String detail;


    public Product(String id , String cropname, String quantities, String detail) {
        this.id = id;
        this.cropname = cropname;
        this.quantities = quantities;
        this.detail = detail;
    }

    public Product(){}


    public String getId() {
        return id;
    }

    public String getCropname() {
        return cropname;
    }

    public String getQuantities() {
        return quantities;
    }

    public String getDetail() {
        return detail;
    }

}
