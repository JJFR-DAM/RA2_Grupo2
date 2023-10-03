package com.RA2_Grupo2.objects;

public class Product {

	private String name, description, price, category, image;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Product(String name, String description, String price, String category, String image) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.image = image;
	}

	public Product() {
		super();
	}

}
