package com.vms.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vms.app.entity.Department;

public interface DepartmentRepository  extends JpaRepository<Department, String>{

}
