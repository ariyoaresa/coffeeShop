package com.example.cofees.controller;

import com.example.cofees.dto.CustomerDTO;
import com.example.cofees.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value="/api/v1/users")
@RestController
@CrossOrigin

public class CustomerController {
@Autowired
    private CustomerService customerService;

@PostMapping("/saveCustomers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
return customerService.saveCustomer(customerDTO);
}

//login page
@PostMapping("/login")
public ResponseEntity<CustomerDTO> customerLogin(@RequestBody CustomerDTO customerDTO) {
    String email = customerDTO.getEmail();
    String password = customerDTO.getPassword();
    CustomerDTO loggedInCustomer = customerService.customerLogin(email, password);
    if (loggedInCustomer != null) {
        return ResponseEntity.ok(loggedInCustomer);
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
//get all customer data
@GetMapping("/getCustomers")
    public List<CustomerDTO> getCustomer(){
return customerService.getAllCustomers();
}

@PutMapping("/updateCustomers")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO){
    return customerService.updateCustomer(customerDTO);
}

@DeleteMapping("/deleteCustomers")
    public Boolean deleteCustomer(@RequestBody CustomerDTO customerDTO){
return customerService.deleteCustomer(customerDTO);
}

}
