package com.example.calc;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	private CalcRepository repo;

	@GetMapping("histories")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(repo.findAll());
	}
	
	@GetMapping("history/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		CalcEntry entry = repo.findOne(id);
		if (entry != null) {
			return ResponseEntity.ok(entry);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("history")
	public ResponseEntity<?> add(@RequestBody CalcEntry entry) {
		return ResponseEntity.ok(repo.save(entry));
	}
	
	@PutMapping("history/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CalcEntry entry) {
		CalcEntry oldEntry = repo.findOne(id);
		if (oldEntry != null) {
			oldEntry.setOperation(entry.getOperation());
			oldEntry.setResult(entry.getResult());
			oldEntry.setDate(new Date());
			return ResponseEntity.ok(repo.save(oldEntry));
		}
		
		LOGGER.info("Entry with id {} not found for updating", id);
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("history/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			repo.delete(repo.findOne(id));
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			LOGGER.info("Entry with id {} not found for deleting", id);
			return ResponseEntity.notFound().build();
		}
	}
}
