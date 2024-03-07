package com.example.cofees.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerDTO {
    @Id
    private Long id;
    private  String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private int telephone;
}
