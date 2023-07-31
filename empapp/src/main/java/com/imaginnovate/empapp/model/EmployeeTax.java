package com.imaginnovate.empapp.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="emptaxdetails")
public class EmployeeTax {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeID;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private List<String> phoneNumber;
	@Column(nullable = false)
	private LocalDate dOJ;
	@Column(nullable = false)
	private Double salary;
	@Column(nullable = false)
	private Double totalTexAmount;
	@Column(nullable = false)
	private Double totalCessAmount ;
	public Long getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(Long employeeID) {
		this.employeeID = employeeID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(List<String> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public LocalDate getdOJ() {
		return dOJ;
	}
	public void setdOJ(LocalDate dOJ) {
		this.dOJ = dOJ;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Double getTotalTexAmount() {
		return totalTexAmount;
	}
	public void setTotalTexAmount(Double totalTexAmount) {
		this.totalTexAmount = totalTexAmount;
	}
	public Double getTotalCessAmount() {
		return totalCessAmount;
	}
	public void setTotalCessAmount(Double totalCessAmount) {
		this.totalCessAmount = totalCessAmount;
	}
	public EmployeeTax(Long employeeID, String firstName, String lastName, String email, List<String> phoneNumber,
			LocalDate dOJ, Double salary, Double totalTexAmount, Double totalCessAmount) {
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.dOJ = dOJ;
		this.salary = salary;
		this.totalTexAmount = totalTexAmount;
		this.totalCessAmount = totalCessAmount;
	}
	public EmployeeTax() {
		super();
	}
	@Override
	public String toString() {
		return "EmployeeTax [employeeID=" + employeeID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", dOJ=" + dOJ + ", salary=" + salary
				+ ", totalTexAmount=" + totalTexAmount + ", totalCessAmount=" + totalCessAmount + "]";
	}
	
	
	
	
}
