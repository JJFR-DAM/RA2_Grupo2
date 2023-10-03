package com.RA2_Grupo2.objects;

public class Employee {

	private int id;

	private String NIF, name, surname, email, password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Employee(String nIF, String name, String surname, String email) {
		super();
		NIF = nIF;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	public Employee() {
		super();
	}

}
