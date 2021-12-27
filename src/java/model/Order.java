/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author ToneVn
 */
public class Order {
    private int order_id;
    private Date dateCreate;
    private String order_name;
    private String order_phone;
    private String order_address;
    private double total;
    private int status;
    private int account_id;
    private int shop_id;
    private Shop shop;

    public Order() {

    }

    public Order(int order_id, Date dateCreate, String order_name, String order_phone, String order_address,
            double total, int status, int account_id, int shop_id, Shop shop) {
        this.order_id = order_id;
        this.dateCreate = dateCreate;
        this.order_name = order_name;
        this.order_phone = order_phone;
        this.order_address = order_address;
        this.total = total;
        this.status = status;
        this.account_id = account_id;
        this.shop_id = shop_id;
        this.shop = shop;
    }

    public Order(int order_id, Date dateCreate, String order_name, String order_phone, String order_address,
            double total, int status, int account_id, int shop_id) {
        this.order_id = order_id;
        this.dateCreate = dateCreate;
        this.order_name = order_name;
        this.order_phone = order_phone;
        this.order_address = order_address;
        this.total = total;
        this.status = status;
        this.account_id = account_id;
        this.shop_id = shop_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public String getOrder_phone() {
        return order_phone;
    }

    public void setOrder_phone(String order_phone) {
        this.order_phone = order_phone;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "Order{" + "order_id=" + order_id + ", dateCreate=" + dateCreate + ", order_name=" + order_name
                + ", order_phone=" + order_phone + ", order_address=" + order_address + ", total=" + total + ", status="
                + status + ", account_id=" + account_id + ", shop_id=" + shop_id + '}';
    }
}
