package com.example.cofees.repo;

import com.example.cofees.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
    Optional<Customer> findByEmail(String email);
    void deleteById(Long customerId);
}
