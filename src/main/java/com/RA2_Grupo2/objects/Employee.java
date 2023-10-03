package com.RA2_Grupo2.objects;

public class Employee {

	private String NIF, name, surname, address, email, password;

	public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee(String nIF, String name, String surname, String address, String email) {
		super();
		NIF = nIF;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.email = email;
	}

	public Employee() {
		super();
	}

}
