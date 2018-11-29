package io.github.raduorleanu.sep4.models;

import java.io.Serializable;
import java.util.Random;

public class User implements Serializable {

    private String name;
    private String address;
    private String email;
    private String password;
    private String picture;
    private long _id;

    public User(){}

    public User(String name, String address, String email, String password, String picture) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.picture = picture;
        _id = new Random().nextLong();
    }

    public User(String name) {
        this.name = name;
        _id = new Random().nextLong();
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
