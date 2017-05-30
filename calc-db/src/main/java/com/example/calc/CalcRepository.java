package com.example.calc;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface CalcRepository extends CrudRepository<CalcEntry, Long> {
	
}
