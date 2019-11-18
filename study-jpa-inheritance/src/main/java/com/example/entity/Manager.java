package com.example.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 *  @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@Entity
@DiscriminatorValue("manager")
public class Manager extends Employee {

	private String department;

	public Manager() {
	}

	public Manager(String department) {
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Manager [department=" + department + ", getDepartment()=" + getDepartment() + "]";
	}

}
