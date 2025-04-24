package com.myfinance.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myfinance.app.entitiy.Risco;

@Repository
public interface RiscoRepository extends JpaRepository<Risco, Long> {

}
