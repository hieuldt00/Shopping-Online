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
public class Category {
	private int category_id;
	private String category_name;
	private String category_image;
	private int status;

	public Category() {
		status = 1;
	}

	public Category(int category_id, String category_name, String category_image, int status) {
		this.category_id = category_id;
		this.category_name = category_name;
		this.category_image = category_image;
		this.status = status;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_image() {
		return category_image;
	}

	public void setCategory_image(String category_image) {
		this.category_image = category_image;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Category{" + "category_id=" + category_id + ", category_name=" + category_name + ", category_image=" + category_image + ", status=" + status + '}';
	}
	
}
