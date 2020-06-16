package com.example.dailygrocer;

public class SignUpData {
    String firstName,lastName,phoneNumber,address;
    String userType;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserType() {
        return userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public SignUpData() {
    }

    public SignUpData(String firstName, String lastName, String userType, String phoneNumber,String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
        this.address=address;
    }
}
