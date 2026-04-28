package com.klu;

import com.klu.model.Student;
import com.klu.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Adds 4 sample students when app starts
// So your React frontend shows data immediately!
@Component
public class DataLoader implements CommandLineRunner {

    private final StudentRepository repo;

    public DataLoader(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        repo.save(new Student("Rahul Sharma",  "rahul@klu.ac.in",  "CSE",   20));
        repo.save(new Student("Anita Reddy",   "anita@klu.ac.in",  "ECE",   21));
        repo.save(new Student("Arjun Patel",   "arjun@klu.ac.in",  "IT",    19));
        repo.save(new Student("Priya Nair",    "priya@klu.ac.in",  "AI&DS", 22));
        System.out.println("4 sample students loaded!");
    }
}
