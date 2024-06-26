package com.example.cofees.repo;

import com.example.cofees.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface AdminRepo extends JpaRepository<Admin,Long> {
    Optional<Admin> findByUserName(String userName);
}
