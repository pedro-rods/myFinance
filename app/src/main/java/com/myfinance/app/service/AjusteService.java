package com.myfinance.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfinance.app.repository.AjusteRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AjusteService {

	@Autowired
	private AjusteRepository repository;

	public void deletar(Long id) {
		repository.deleteById(id);
	}

}