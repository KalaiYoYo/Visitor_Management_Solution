package com.vms.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vms.app.entity.Visitor;
@Service
public interface VisitorService {
	
	List<Visitor> getAllVisitors();
	
	Visitor saveVisitor(Visitor visitor);
	
	Visitor getVisitorById(Long id);
	
	Visitor updateVisitor(Visitor visitor);
	
	Visitor checkOutVisitorById(Visitor visitor);
	
	Optional<Visitor>findVisitorById(Long id);
	
	
	
}
