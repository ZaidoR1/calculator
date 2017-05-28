package com.example.calc;

import org.springframework.data.repository.CrudRepository;

public interface CalcRepository extends CrudRepository<CalcEntry, Long> {
	
}
