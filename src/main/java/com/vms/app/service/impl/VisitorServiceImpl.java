package com.vms.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		return visitorRepository.findById(id).get();
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

	
}
