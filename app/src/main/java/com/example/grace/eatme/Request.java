package com.example.grace.eatme;

import java.util.List;

/**
 * Created by Grace on 12/4/2017.
 */

public class Request {

    private String phone;
    private String address;
    private String name;
    private String total;
    private  String status;



    private List<Order> foods;
    Request()
    {

    }

    public Request(String phone, String name, String s, List<Order> cart)
    {

    }

    public Request(String phone, String address, String name, String total, List<Order> foods) {
        this.phone = phone;
        this.address = address;
        this.name = name;
        this.total = total;
        this.foods = foods;
        this.status = "0";

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }
}
