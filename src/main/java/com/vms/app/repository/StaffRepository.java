package com.vms.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vms.app.entity.Staff;
@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>
{

}
