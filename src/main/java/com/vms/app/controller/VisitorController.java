  package com.vms.app.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vms.app.entity.Department;
import com.vms.app.entity.Staff;
import com.vms.app.entity.Visitor;
import com.vms.app.repository.DepartmentRepository;
import com.vms.app.repository.StaffRepository;
import com.vms.app.repository.VisitorRepository;
import com.vms.app.service.VisitorService;



@Controller
public class VisitorController {

	private static final Object checkout = null;

	@Autowired
	VisitorService visitorService;
	
	@Autowired
	VisitorRepository visitorRepo;

	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private DepartmentRepository deptRepository;
	
	@GetMapping("/visitors")
	public String viewHomePage(Model model) {
		return findPaginated(1, "inTime", "desc", model);		
	}
	
	@GetMapping("/home")
	public String visitors(Model model) {
		model.addAttribute("successMsg", "Details saved successfully!!");
				return "home";
	}


	@GetMapping("/visitors/new")
	public String indexvms(Model model) {
	return "index";
	}
	
	@GetMapping("/oasysCybernetics/info")
	public String oasysinfo(Model model)
	{
		return "oasys_info";
		
	}
	
	@GetMapping("/visitors/welcome")
	public String createVisitorForm(Model model) {
		List<Staff> toMeet = staffRepository.findAll();
		List<Department> department = deptRepository.findAll();
		// create visitor object to hold visitor form data
		// List<Staff> staffs=StService.getAllStaff(staff);
		model.addAttribute("visitor", new Visitor());
		model.addAttribute("department", department);
		model.addAttribute("toMeet", toMeet);
		// model.addAttribute("toMeet", toMeet);
		return "create_visitor";
	}

	@GetMapping("/visitors/reg")
	public String getVisitorForm(Model model) {
		return "regd_visitor";
	}

	@PostMapping("/visitors/resubmit")
	public String editRegisterdVisitor(@Param ("phoneNumber") String phoneNumber, Model model) 
	{
		Optional<Visitor> getvisitor=visitorRepo.findVisitorBymobile(phoneNumber);
		if(getvisitor.isPresent())
		{
			model.addAttribute("visitor", visitorRepo.findVisitorBymobile(phoneNumber));
			return "edit_visitor1";
		}
		else
		{
			return "no_data_found";
		}
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
		existingVisitor.setDepartment(visitor.getDepartment());
		existingVisitor.setAddress(visitor.getAddress());
		existingVisitor.setPurpose(visitor.getPurpose());
		//existingVisitor.setVehicleNumber(visitor.getVehicleNumber());
		//existingVisitor.setVehicleType(visitor.getVehicleType());
		existingVisitor.setInTime(visitor.getInTime());
		existingVisitor.setoutTime(visitor.getoutTime());
		//existingVisitor.setExittime(visitor.getExittime());
		
//		String startDateString = existingVisitor.setoutTime(visitor.getoutTime());
//		DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
//		Date startDate;
//		try {
//		    startDate = df.parse(startDateString);
//		    String newDateString = df.format(startDate);
//		    System.out.println(newDateString);
//		} catch (ParseException e) {
//		    e.printStackTrace();
//		}
//		
//		
		//LocalDateTime date = new LocalDateTime(existingVisitor.setExittime(visitor.getExittime()), DateTimeFormatter.BASIC_ISO_DATE);
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
		return "redirect:/visitors/time";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Visitor> page = visitorService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Visitor> visitors = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("visitors",  visitors);
		return "visitors";
	}
	
	
	@GetMapping("visitors/time")
	public String getTodayVisitor(Model model)
	{
		List<Visitor>visitorNow=visitorRepo.getTodayVisitor();
		
		//model.addAttribute("visitorNow",visitorService.getTodayVisitor(inTime));
		model.addAttribute("visitorNow", visitorNow);
		return "visitor_now";
		
		}
	
	@GetMapping("visitors/report")
public String dateWiseReport(Model model)
{
//		Date sDate=new SimpleDateFormat("YYYY-MM-DD").parse(sdate);
//		Date tDate=new SimpleDateFormat("YYYY-MM-DD").parse(enddate);
//		List<Visitor>vistorReportList=visitorRepo.reportList(sDate, tDate);
//		model.addAttribute("visitorReportList", vistorReportList);
		return "report_list";
}
	@PostMapping("visitors/dwr")
public String dateWiseReport(Model model, @Param ("fdate") String fdate, @Param("enddate") String enddate) throws ParseException 
{
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
		 String fromDate = myFormat.format(fromUser.parse(fdate));
		 String toDate = myFormat.format(fromUser.parse(enddate));
// String fdate1 = dateFormat.format(fdate);
   // String enddate1 = dateFormat.format(enddate);
   // model.addAttribute("myDate", dateString);
	    
		Date stDate=new SimpleDateFormat("dd/MM/yyyy").parse(fromDate);
		Date endDate=new SimpleDateFormat("dd/MM/yyyy").parse(toDate);
		List<Visitor> vistorReportList= visitorRepo.reportList(stDate, endDate);
	
		model.addAttribute("visitorReportList", vistorReportList);
		model.addAttribute("fromdate", fromDate);
		model.addAttribute("tdate", toDate);
//		return "redirect:/visitors/report";
		return "report_list";
}


	
	
	@GetMapping("visitorreport")
	public String report(Model model)
	{
		List<Visitor>visitorNowReport=visitorRepo.getTodayVisitor();
		model.addAttribute("visitorReport", visitorNowReport);
		return "report";
		
		}
	
		 
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
//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

