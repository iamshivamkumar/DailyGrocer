package com.example.dailygrocer;

public class Notify {
    String id;
    String username;
    String cropname;
    String quantities;
    String phone;
    String address;

    public Notify(String id, String username , String cropname, String quantities, String phone,String address) {
        this.id = id;
        this.username = username;
        this.cropname = cropname;
        this.quantities = quantities;
        this.phone = phone;
        this.address = address;
    }

    public Notify(){}

    public String getAddress() {
        return address;
    }

    public String getUsername() { return username; }

    public String getId() {
        return id;
    }

    public String getCropname() {
        return cropname;
    }

    public String getQuantities() {
        return quantities;
    }

    public String getPhone() {
        return phone;
    }
}
