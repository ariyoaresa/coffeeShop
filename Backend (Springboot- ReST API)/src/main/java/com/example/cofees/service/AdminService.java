package com.example.cofees.service;

import com.example.cofees.dto.AdminDTO;
import com.example.cofees.dto.CustomerDTO;
import com.example.cofees.entity.Admin;
import com.example.cofees.entity.Customer;
import com.example.cofees.repo.AdminRepo;
import com.example.cofees.repo.CustomerRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional

public class AdminService {
   @Autowired
    private AdminRepo adminRepo;
   @Autowired
    private ModelMapper modelMapper;
   @Autowired
   private CustomerService customerService;
   @Autowired
   private CustomerRepo customerRepo;



   //Admin login logic

    public AdminDTO adminLogin(String userName, String password) {
        Optional<Admin> admin = adminRepo.findByUserName(userName);
        if (admin.isPresent()) {
            if (admin.get().getPassword().equals(password)) {
                return modelMapper.map(admin.get(), AdminDTO.class);
            } else {
                throw new IllegalArgumentException("Incorrect password");
            }
        } else {
            throw new NoSuchElementException("Admin not found with username: " + userName);
        }
    }



   //Admin logic
    public AdminDTO saveAdmin(AdminDTO adminDTO) {
        Admin admin = modelMapper.map(adminDTO, Admin.class);
        adminRepo.save(admin);
        return adminDTO;
    }

    public List<AdminDTO> getAllAdmins() {
        List<Admin> admins = adminRepo.findAll();
        return modelMapper.map(admins, new TypeToken<List<AdminDTO>>(){}.getType());
    }
    public AdminDTO updateAdmin(AdminDTO adminDTO){
        adminRepo.save(modelMapper.map(adminDTO, Admin.class));
        return adminDTO;
    }
    public Boolean deleteAdmin(AdminDTO adminDTO){
        adminRepo.delete(modelMapper.map(adminDTO,Admin.class));
        return true;
    }









    //Admin do customer logic

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        return modelMapper.map(customers, new TypeToken<List<CustomerDTO>>(){}.getType());
    }
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        customerRepo.save(modelMapper.map(customerDTO, Customer.class));
        return customerDTO;
    }
    public CustomerDTO updateCustomer(CustomerDTO customerDTO){
        customerRepo.save(modelMapper.map(customerDTO,Customer.class));
        return customerDTO;
    }
    public Boolean deleteCustomer(CustomerDTO customerDTO){
        customerRepo.delete(modelMapper.map(customerDTO,Customer.class));
        return true;
    }

    public CustomerDTO getCustomerById(Long customerId) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if (customer.isPresent()) {
            return modelMapper.map(customer.get(), CustomerDTO.class);
        } else {
            throw new NoSuchElementException("Customer not found with id: " + customerId);
        }
    }
    public CustomerDTO deleteCustomerById(Long customerId) {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if (customer.isPresent()) {
            customerRepo.deleteById(customerId);
            return modelMapper.map(customer.get(), CustomerDTO.class);
        } else {
            throw new NoSuchElementException("Customer not found with id: " + customerId);
        }
    }

    public CustomerDTO updateCustomerById(Long customerId, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            // Update the properties of the existing customer with the values from the customerDTO
            existingCustomer.setEmail(customerDTO.getEmail());
            existingCustomer.setPassword(customerDTO.getPassword());
            existingCustomer.setFirstName(customerDTO.getFirstName());
            existingCustomer.setLastName(customerDTO.getLastName());
            existingCustomer.setAddress(customerDTO.getAddress());
            existingCustomer.setTelephone(customerDTO.getTelephone());

            // Save the updated customer back to the database
            Customer updatedCustomer = customerRepo.save(existingCustomer);

            // Return the updated customer as CustomerDTO
            return modelMapper.map(updatedCustomer, CustomerDTO.class);
        } else {
            throw new NoSuchElementException("Customer not found with id: " + customerId);
        }
    }






}
