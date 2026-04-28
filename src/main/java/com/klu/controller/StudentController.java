package com.klu.controller;

import com.klu.model.Student;
import com.klu.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// =====================================================
// @CrossOrigin is VERY IMPORTANT!
// Your React frontend runs on http://localhost:5173
// Your backend runs on http://localhost:8080
// Without @CrossOrigin, the browser BLOCKS the connection
// This line tells Spring: "Allow React to talk to me!"
// =====================================================

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "https://fullstackfront-production.up.railway.app/")
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    // GET http://localhost:8080/students
    // Returns all students as JSON list
    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(repo.findAll());
    }

    // GET http://localhost:8080/students/1
    // Returns one student by ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        Student s = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return ResponseEntity.ok(s);
    }

    // POST http://localhost:8080/students
    // Body: { "name":"Rahul", "email":"rahul@klu.ac.in", "course":"CSE", "age":20 }
    // Adds a new student
    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student) {
        Student saved = repo.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT http://localhost:8080/students/1
    // Body: updated student fields
    // Updates an existing student
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id,
                                           @RequestBody Student updated) {
        Student existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setCourse(updated.getCourse());
        existing.setAge(updated.getAge());
        return ResponseEntity.ok(repo.save(existing));
    }

    // DELETE http://localhost:8080/students/1
    // Removes a student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        repo.deleteById(id);
        return ResponseEntity.ok("Student deleted successfully!");
    }
}
