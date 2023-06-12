package com.vms.app.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import com.vms.app.entity.Visitor;
import com.vms.app.repository.VisitorRepository;
import com.vms.app.service.VisitorService;



@Service
public class VisitorServiceImpl implements VisitorService{

	@Autowired
	private VisitorRepository visitorRepository;
	
	public VisitorServiceImpl(VisitorRepository visitorRepository) {
		super();
		this.visitorRepository = visitorRepository;
	}

	@Override
	public List<Visitor> getAllVisitors() {
		return visitorRepository.findAll();
	}

	@Override
	public Visitor saveVisitor(Visitor visitor) {
		return visitorRepository.save(visitor);
	}

	@Override
	public Visitor getVisitorById(Long id) {
		
		Optional<Visitor> optional = visitorRepository.findById(id);
		Visitor visitor = null;
		if (optional.isPresent()) {
			visitor = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return visitor;
	}

	@Override
	public Visitor updateVisitor(Visitor visitor) {
		return visitorRepository.save(visitor);
	}

	@Override
	public Visitor checkOutVisitorById(Visitor visitor) {
		return visitorRepository.save(visitor);
		}

	@Override
	public Optional<Visitor> findVisitorById(Long id) {
			return visitorRepository.findById(id);
	}

	@Override
	public Page<Visitor> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.visitorRepository.findAll(pageable);
	}
	
	/*
	 * @Query("select * from oasys_vms.visitors where cast(in_time as Date)=CURDATE();"
	 * ) public List<Visitor>getTodayVisitor() { return visitorRepository.findAll();
	 * 
	 * }
	 */

	
	  @Override
	  public List<Visitor> getTodayVisitor() {
		  return visitorRepository.findAll(); 
	 }
	

	

	
}
