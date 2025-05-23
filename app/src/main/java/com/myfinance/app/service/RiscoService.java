package com.myfinance.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfinance.app.repository.RiscoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class RiscoService {

	@Autowired
	private RiscoRepository repository;

	public void deletar(Long id) {
		repository.deleteById(id);
	}

}