package com.example.calc;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface CalcRepository extends CrudRepository<CalcEntry, Long> {
	
	// TODO: eigene Query implementieren, sh. 
	// https://github.com/netgloo/spring-boot-samples/blob/master/spring-boot-mysql-springdatajpa-hibernate/src/main/java/netgloo/models/UserDao.java
	
}
