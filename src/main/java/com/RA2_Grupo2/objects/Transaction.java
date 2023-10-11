package com.RA2_Grupo2.objects;

import java.sql.Date;

public class Transaction {

	private int id, productId, supplierId, quantity;
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Transaction(int productId, int supplierId, int quantity, Date date) {
		super();
		this.productId = productId;
		this.supplierId = supplierId;
		this.quantity = quantity;
		this.date = date;
	}

	public Transaction() {
		super();
	}

}
