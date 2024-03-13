package com.example.dto;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDTO {

    private String firstName;
    private String lastName;
}
