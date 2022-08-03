package com.vms.app.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vms.app.entity.Visitor;
@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long>{
	

//	@Transactional
//	@Modifying
//	@Query("select out_time as out_time, name,phone_number, address,to_meet, department, purpose,in_time from vms_college.visitors  where phone_number=:phone_number and out_time=out_time;")
//	String exitVisitor(String out_time, String phone_number);
}
