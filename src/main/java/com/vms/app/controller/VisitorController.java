package com.vms.app.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.vms.app.entity.Department;
import com.vms.app.entity.Staff;
import com.vms.app.entity.Visitor;
import com.vms.app.repository.DepartmentRepository;
import com.vms.app.repository.StaffRepository;

import com.vms.app.service.VisitorService;

@Controller
public class VisitorController {

	private static final Object checkout = null;

	@Autowired
	VisitorService visitorService;

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private DepartmentRepository deptRepository;
	
	@GetMapping("/home")
	public String visitors(Model model) {
		
		model.addAttribute("successMsg", "Details saved successfully!!");
				return "home";
	}

	// handler method to handle list visitors and return mode and view
	@GetMapping("/visitors")
	public String listvisitors(Model model) {
		model.addAttribute("visitors", visitorService.getAllVisitors());
		return "visitors";
	}

	@GetMapping("/visitors/new")
	public String createVisitorForm(Model model) {

		// Staff staf=new Staff();

		List<Staff> toMeet = staffRepository.findAll();
		List<Department> department = deptRepository.findAll();

		// create visitor object to hold visitor form data

		// List<Staff> staffs=StService.getAllStaff(staff);
		model.addAttribute("visitor", new Visitor());
		model.addAttribute("department", department);
		model.addAttribute("toMeet", toMeet);

		// List<String>toMeet=Arrays.asList("Tony","Ramesh","Karthick","Abdul","Sivakumar","Yaswanth","Abdul","Dass","Durai");
		// model.addAttribute("toMeet", toMeet);
		return "create_visitor";
	}

	
	/*
	 * @PostMapping("/visitors") public String
	 * saveVisitor(@ModelAttribute("visitor") Visitor visitor) {
	 * //visitor.setInTime(now());
	 * 
	 * if (visitor.getInTime() == null) { visitor.setInTime(LocalDateTime.now()); }
	 * visitorService.saveVisitor(visitor); return "redirect:/visitors"; }
	 */

	
	 @PostMapping("/visitors/save") 
	 public String successVisitor(@ModelAttribute("visitor") Visitor visitor, Errors errors,Model model) 
	 {
	 //visitor.setInTime(now()); 
		if (visitor.getInTime() == null) {
	 visitor.setInTime(LocalDateTime.now()); }
		visitorService.saveVisitor(visitor);
	model.addAttribute("successMsg", "Details saved successfully!!");
//		if (null != errors && errors.getErrorCount() > 0) {
//            return "create_visitor";
//        } else {
//            //model.addAttribute("successMsg", "Details saved successfully!!");
//           
//        }
		 return "redirect:/home";
	 
	 }
	

	@GetMapping("/visitors/edit/{id}")
	public String editVisitorForm(@PathVariable Long id, Model model) {
		model.addAttribute("visitor", visitorService.getVisitorById(id));
		return "edit_visitor";
	}

	@GetMapping("/visitors/show/{id}")
	public String showVisitorForm(@PathVariable Long id, Model model) {
		model.addAttribute("visitor", visitorService.getVisitorById(id));
		return "show";
	}

	@PostMapping("/visitors/{id}")
	public String updateVisitor(@PathVariable Long id, @ModelAttribute("visitor") Visitor visitor, Model model)
			throws IOException {// get visitor from database by id

		Visitor existingVisitor = visitorService.getVisitorById(id);
		existingVisitor.setId(id);
		existingVisitor.setEmail(visitor.getEmail());
		//existingVisitor.setVisitorCount(visitor.getVisitorCount());
		existingVisitor.setPhoneNumber(visitor.getPhoneNumber());
		existingVisitor.setToMeet(visitor.getToMeet());
		//existingVisitor.setDepartment(visitor.getDepartment());
		existingVisitor.setAddress(visitor.getAddress());
		existingVisitor.setPurpose(visitor.getPurpose());
		//existingVisitor.setVehicleNumber(visitor.getVehicleNumber());
		//existingVisitor.setVehicleType(visitor.getVehicleType());
		existingVisitor.setInTime(visitor.getInTime());
		existingVisitor.setoutTime(visitor.getoutTime());
		//existingVisitor.setGovtId(visitor.getGovtId());
		//existingVisitor.setGender(visitor.getGender());
		existingVisitor.setDescription(visitor.getDescription());
		// save updated visitor object
		visitorService.updateVisitor(existingVisitor);
		return "redirect:/visitors";
	}

	// handler method to handle checkout visitor request

	@GetMapping("/visitors/{id}")
	public String checkOutVisitor(@PathVariable Long id, Model model) {
		Visitor existingVisitor = visitorService.getVisitorById(id);
		if (existingVisitor.getoutTime() == null) {
			existingVisitor.setoutTime(LocalDateTime.now());
		}
		visitorService.updateVisitor(existingVisitor);
		model.addAttribute("visitors", visitorService.getAllVisitors());
		return "redirect:/visitors";
	}

	/*
	 * @GetMapping("/visitors/{id}") public String CheckOutVisitorById(@PathVariable
	 * Long id,@ModelAttribute("visitor") Visitor visitor, Model model) {
	 * //model.addAttribute("LocalDateTime", LocalDateTime.now()); Visitor
	 * existingVisitor = visitorService.getVisitorById(id);
	 * //visitor.setInTime(now()); if (existingVisitor.getoutTime() == null) {
	 * existingVisitor.setoutTime(LocalDateTime.now()); }
	 * existingVisitor.setoutTime(visitor.getoutTime());
	 * visitorService.updateVisitor(existingVisitor); return "redirect:/visitors"; }
	 */

}
