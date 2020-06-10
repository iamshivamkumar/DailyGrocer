package com.example.dailygrocer;

public class Notify {
    String id;
    String username;
    String cropname;
    String quantities;
    String phoneNumber;

    public Notify(String id, String username , String cropname, String quantities, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.cropname = cropname;
        this.quantities = quantities;
        this.phoneNumber = phoneNumber;
    }

    public Notify(){}

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
        return phoneNumber;
    }
}
