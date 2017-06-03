package com.example.calc;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface CalcRepository extends CrudRepository<CalcEntry, Long> {
  /**
   * Returns all instances of the type in descend order (Spring Magic!)
   * 
   * @return the list in descend order
   */
  public Iterable<CalcEntry> findAllByOrderByIdDesc();
}
