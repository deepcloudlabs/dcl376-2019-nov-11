package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Employee;

/**
 *
 *  @author Binnur Kurt <binnur.kurt@gmail.com>
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
