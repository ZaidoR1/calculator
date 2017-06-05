package com.example.calc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {

  @Autowired
  private CalcRepository repo;

  @GetMapping("histories")
  public ResponseEntity<?> getAll() {
    return ResponseEntity.ok(repo.findAllByOrderByIdDesc());
  }

  @GetMapping("history/{id}")
  public ResponseEntity<?> get(@PathVariable Long id) {
    final CalcEntry entry = repo.findOne(id);
    if (entry != null) {
      return ResponseEntity.ok(entry);
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("calculate")
  public ResponseEntity<?> add(@RequestBody CalcEntry entry) {
    return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(entry));
  }
}
