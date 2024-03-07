package com.example.cofees.controller;

import com.example.cofees.dto.AdminDTO;
import com.example.cofees.dto.CustomerDTO;
import com.example.cofees.entity.Customer;
import com.example.cofees.repo.CustomerRepo;
import com.example.cofees.service.AdminService;
import com.example.cofees.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequestMapping(value = "/api/v1/admin")
@RestController
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepo customerRepo;

    //Admin login Logic
    @PostMapping("/login")
    public ResponseEntity<AdminDTO> adminLogin(@RequestBody AdminDTO adminDTO) {
        String userName = adminDTO.getUserName();
        String password = adminDTO.getPassword();
        AdminDTO loggedInAdmin = adminService.adminLogin(userName, password);
        return ResponseEntity.ok(loggedInAdmin);
    }

//Admin do admins operations
    @PostMapping("/saveAdmins")
    public AdminDTO saveAdmin(@RequestBody AdminDTO adminDTO){
        return adminService.saveAdmin(adminDTO);
    }
    @GetMapping("/getAdmins")
    public List<AdminDTO> getAdmin(){
        return adminService.getAllAdmins();
    }

    @PutMapping("/updateAdmins")
    public AdminDTO updateAdmin(@RequestBody AdminDTO adminDTO){
        return adminService.updateAdmin(adminDTO);
    }

    @DeleteMapping("/deleteAdmins")
    public Boolean deleteAdmin(@RequestBody AdminDTO adminDTO){
        return true;
    }



    //Admin do customer operations
    @GetMapping("/getCustomers")
    public List<CustomerDTO> getCustomer(){
        return adminService.getAllCustomers();
    }
    @PostMapping("/saveCustomers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return adminService.saveCustomer(customerDTO);
    }
    @PutMapping("/updateCustomers")
    public CustomerDTO updateCustomers(@RequestBody CustomerDTO customerDTO){
        return adminService.updateCustomer(customerDTO);
    }
    @DeleteMapping("/deleteCustomers")
    public Boolean deleteCustomer(@RequestBody CustomerDTO customerDTO){
        return true;
    }

    //get single customer data
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        CustomerDTO customerDTO = adminService.getCustomerById(id);
        return ResponseEntity.ok(customerDTO);
    }

    //delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDTO> deleteCustomerById(@PathVariable Long id) {
        try {
            CustomerDTO customerDTO = adminService.deleteCustomerById(id);
            return ResponseEntity.ok(customerDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomerById(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        try {
            CustomerDTO updatedCustomerDTO = adminService.updateCustomerById(id, customerDTO);
            return ResponseEntity.ok(updatedCustomerDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }



}

