package com.example.cofees.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AdminDTO {
    @Id
    private Long id;
    private String userName;
    private String password;
}
