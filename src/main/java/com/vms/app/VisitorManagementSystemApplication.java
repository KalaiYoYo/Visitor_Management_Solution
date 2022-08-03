package com.vms.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vms.app.entity.Staff;
import com.vms.app.entity.Visitor;
import com.vms.app.repository.StaffRepository;
import com.vms.app.repository.VisitorRepository;

@SpringBootApplication
public class VisitorManagementSystemApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(VisitorManagementSystemApplication.class, args);
	}

	@Autowired
	private VisitorRepository visitorRepository;
	
	@Autowired
	private StaffRepository staffRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		
//	  Visitor visitor1 = new Visitor("Ramesh", "12345", "ramesh@gmail.com", null);
//	  visitorRepository.save(visitor1);
//		
		/* 
		 * Visitor visitor2 = new Visitor("Sanjay", "Jadhav", "sanjay@gmail.com");
		 * visitorRepository.save(visitor2);
		 * 
		 * Visitor visitor3 = new Visitor("tony", "stark", "tony@gmail.com");
		 * visitorRepository.save(visitor3);
		 */
		
		
//		 Staff staff=new Staff((long) 1001,"AbdulSalam", "Mechanical");
//		  staffRepo.save(staff); 
		 
		 /*Staff staff1=new Staff("1002","Aravind","Electrical");
		 * 
		 * staffRepo.save(staff1); Staff staff2=new Staff("1003","Karthick","Civil");
		 * staffRepo.save(staff2); Staff staff3=new Staff("1004","Anbu","ECE");
		 * staffRepo.save(staff3); Staff staff4=new Staff("1005","Siva","IT");
		 * staffRepo.save(staff4);
		 */
		
	}

}
