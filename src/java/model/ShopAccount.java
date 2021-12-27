/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author son
 */
public class ShopAccount {
    private int shop_id;
    private int account_id;
    private int status;

    
    public ShopAccount(int shop_id, int account_id, int status) {
        this.shop_id = shop_id;
        this.account_id = account_id;
        this.status = status;
    }

    public ShopAccount() {
     
    }

    
    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ShopAccount{" + "shop_id=" + shop_id + ", account_id=" + account_id + ", status=" + status + '}';
    }
     
    
}
