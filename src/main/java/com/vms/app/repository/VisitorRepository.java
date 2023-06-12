package com.vms.app.repository;



import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vms.app.entity.Visitor;
@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long>

{
//	@Transactional
//	@Modifying
	
//	@Query(nativeQuery=true, value="select * from oasys_vms.visitors where cast(DATE_FORMAT(in_time as Date,'%d-%m-%Y %T'))=curdate() order by in_time desc")
//	List<Visitor> getTodayVisitor();
	
	@Query(nativeQuery=true, value="SELECT date_format(in_time,'%d-%m-%Y %T'),v.* FROM oasys_vms.visitors AS v WHERE DATE(in_time) = CURDATE() order by in_time desc ")
	List<Visitor> getTodayVisitor();
	
 @Query(nativeQuery=true, value="select * from oasys_vms.visitors where in_time between:fdate and :enddate")
	 List<Visitor> reportList(Date fdate, Date enddate);
//	String exitVisitor(String out_time, String phone_number);
 
 @Query(nativeQuery=true, value="SELECT * FROM oasys_vms.visitors where phone_number= :phoneNumber limit 1")
 Optional<Visitor>findVisitorBymobile(String phoneNumber);
	

}
