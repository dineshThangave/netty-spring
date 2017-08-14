package com.example.httpserver.controller.request;

public class Employee {

	String	firstname;
	String	lastName;
	Integer	id;
	Double salary;
	public Employee(String firstname, String lastName, Integer id, Double salary) {
		super();
		this.firstname = firstname;
		this.lastName = lastName;
		this.id = id;
		this.salary = salary;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	

}
