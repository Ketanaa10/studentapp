package com.klu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentAppApplication.class, args);
        System.out.println("=====================================");
        System.out.println("  Backend is RUNNING!");
        System.out.println("  API: https://fullstackfront-production.up.railway.app/students");
        System.out.println("=====================================");
    }
}
