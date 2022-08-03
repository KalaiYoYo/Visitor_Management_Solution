package com.vms.app.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "visitors")
public class Visitor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	/*
	 * @Column(name = "gender") private String gender;
	 */
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "to_meet")
	private String toMeet;
	
	/*
	 * @Column(name="govt_id") private String govtId;
	 */
	
//	@Column(name = "department")
//	private String department;
	
	@Column(name = "purpose")
	private String purpose;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "in_time")
	private LocalDateTime inTime;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Column(name = "out_time")
	private LocalDateTime  outTime;
	
//	@Column(name="visitor_count")
//	private String visitorCount;
//	
//	@Column(name="vehicle_no")
//	private String vehicleNumber;
//	
//	@Column(name="vehicle_type")
//	private String vehicleType;
	
	@Column(name="description")
	private String description;

	public Visitor() {
		
	}

	public Visitor(String name, String phoneNumber, String email, String toMeet) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.toMeet = toMeet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
//	public String getGender() {
//		return gender;
//	}
//
//	public void setGender(String gender) {
//		this.gender = gender;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getToMeet() {
		return toMeet;
	}

	public void setToMeet(String toMeet) {
		this.toMeet = toMeet;
	}

//	public String getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(String department) {
//		this.department = department;
//	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public LocalDateTime getInTime() {
		return inTime;
	}

	public void setInTime(LocalDateTime localDateTime) {
		this.inTime = localDateTime;
	}

	public LocalDateTime getoutTime() {
		return outTime;
	}

	public void setoutTime(LocalDateTime localDateTime) {
		this.outTime = localDateTime;
	}

//	public String getGovtId() {
//		return govtId;
//	}
//
//	public void setGovtId(String govtId) {
//		this.govtId = govtId;
//	}
//
//	
//
//	public String getVisitorCount() {
//		return visitorCount;
//	}
//
//	public void setVisitorCount(String visitorCount) {
//		this.visitorCount = visitorCount;
//	}
//
//	public String getVehicleNumber() {
//		return vehicleNumber;
//	}
//
//	public void setVehicleNumber(String vehicleNumber) {
//		this.vehicleNumber = vehicleNumber;
//	}
//
//	public String getVehicleType() {
//		return vehicleType;
//	}
//
//	public void setVehicleType(String vehicleType) {
//		this.vehicleType = vehicleType;
//	}
//	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		if(outTime==null)
		{
			return "Visitor inside";
		}
		return   outTime.toString()  ;
	}

	
}
