package com.example.cofees.service;

import com.example.cofees.dto.CustomerDTO;
import com.example.cofees.entity.Customer;
import com.example.cofees.repo.CustomerRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.lang.Character.getType;

@Service
@Transactional

public class CustomerService {
@Autowired
private CustomerRepo customerRepo;
@Autowired
private ModelMapper modelMapper;

public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
    customerRepo.save(modelMapper.map(customerDTO, Customer.class));
    return customerDTO;
}

//customer loged in logic
public CustomerDTO customerLogin(String email, String password) {
    Optional<Customer> customer = customerRepo.findByEmail(email);
    if (customer.isPresent()) {
        if (customer.get().getPassword().equals(password)) {
            return modelMapper.map(customer.get(), CustomerDTO.class);
        } else {
            throw new IllegalArgumentException("Incorrect password");
        }
    } else {
        throw new NoSuchElementException("Customer not found with email: " + email);
    }
}

public List<CustomerDTO> getAllCustomers(){
List<Customer>customerList=customerRepo.findAll();
return modelMapper.map(customerList,new TypeToken<List<CustomerDTO>>(){}.getType());
    }

public CustomerDTO updateCustomer(CustomerDTO customerDTO){
    customerRepo.save(modelMapper.map(customerDTO,Customer.class));
    return customerDTO;
}
public Boolean deleteCustomer(CustomerDTO customerDTO){
customerRepo.delete(modelMapper.map(customerDTO,Customer.class));
return true;
}

}

