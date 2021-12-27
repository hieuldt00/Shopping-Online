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
public class Product {
	private int product_id;
	private String product_name;
	private int quantity;
	private double price;
	private String description;	
	private String detail;
	private int status;
	private int category_id;
	private int shop_id;
	private String image_thumbnail;

	public Product() {
		status = 1;
	}

	public Product(int product_id, String product_name, int quantity, double price, String description, String detail, int status, int category_id, int shop_id, String image_thumbnail) {
		this.product_id = product_id;
		this.product_name = product_name;
		this.quantity = quantity;
		this.price = price;
		this.description = description;
		this.detail = detail;
		this.status = status;
		this.category_id = category_id;
		this.shop_id = shop_id;
		this.image_thumbnail = image_thumbnail;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public int getShop_id() {
		return shop_id;
	}

	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}

	public String getImage_thumbnail() {
		return image_thumbnail;
	}

	public void setImage_thumbnail(String image_thumbnail) {
		this.image_thumbnail = image_thumbnail;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	@Override
	public String toString() {
		return "Product{" + "product_id=" + product_id + ", product_name=" + product_name + ", quantity=" + quantity + ", price=" + price + ", description=" + description + ", status=" + status + ", category_id=" + category_id + ", shop_id=" + shop_id + '}';
	}
	
}
