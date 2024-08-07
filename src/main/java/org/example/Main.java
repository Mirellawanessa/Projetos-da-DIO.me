package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
package com.example.santanderdevweek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SantanderDevWeekApplication {

    public static void main(String[] args) {
        SpringApplication.run(SantanderDevWeekApplication.class, args);
    }
}
package com.example.santanderdevweek.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    // Getters and Setters
}
package com.example.santanderdevweek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.santanderdevweek.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
package com.example.santanderdevweek.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.santanderdevweek.domain.Customer;
import com.example.santanderdevweek.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Customer findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
package com.example.santanderdevweek.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

        import com.example.santanderdevweek.domain.Customer;
import com.example.santanderdevweek.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = service.findById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Validated @RequestBody Customer customer) {
        Customer savedCustomer = service.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Validated @RequestBody Customer customer) {
        if (service.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        customer.setId(id);
        Customer updatedCustomer = service.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (service.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
