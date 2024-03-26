package entities;

import java.time.LocalDate;

public class Seller {
	private Integer id;
	private String name;
	private String email;
	private LocalDate date;
	private Double baseSalary;
	
	private Department department;
	
	public Seller() {

	}

	public Seller(Integer id, String name, String email, LocalDate date, Double baseSalary, Department department) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.date = date;
		this.baseSalary = baseSalary;
		this.department = department;
	}
	
	public Integer getId() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	public Department getDepartment() {
		return department;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}

	
	
}
