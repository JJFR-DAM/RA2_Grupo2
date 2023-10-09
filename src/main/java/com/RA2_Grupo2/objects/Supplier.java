package com.RA2_Grupo2.objects;

public class Supplier {

	private String name, address, phone;
	private int id, deleted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Supplier(String name, String address, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.deleted = 0;
	}

	public Supplier() {
		super();
	}

}
