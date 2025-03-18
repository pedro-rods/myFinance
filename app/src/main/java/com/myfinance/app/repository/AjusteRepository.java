package com.myfinance.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myfinance.app.entitiy.Ajuste;

@Repository
public interface AjusteRepository extends JpaRepository<Ajuste, Long> {

}
