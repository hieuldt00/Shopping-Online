/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ToneVn
 */
public class Cart {
	private int product_id;
	private int account_id;
	private int quantity;
	private Product product;

	public Cart() {
	}

	public Cart(int product_id, int account_id, int quantity) {
		this.product_id = product_id;
		this.account_id = account_id;
		this.quantity = quantity;
	}

	public Cart(int product_id, int account_id, int quantity, Product product) {
		this.product_id = product_id;
		this.account_id = account_id;
		this.quantity = quantity;
		this.product = product;
	}
	
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public String toString() {
		return "Cart{" + "product_id=" + product_id + ", account_id=" + account_id + ", quantity=" + quantity + '}';
	}
	
}
