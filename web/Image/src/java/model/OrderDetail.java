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
public class OrderDetail {
	private int product_id;
	private int order_id;
	private int quantity;
	private double price;
	private double total;

	public OrderDetail() {
	}

	public OrderDetail(int product_id, int order_id, int quantity, double price, double total) {
		this.product_id = product_id;
		this.order_id = order_id;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
}
