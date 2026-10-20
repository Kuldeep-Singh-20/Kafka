package com.producer.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmployeeDto {

    private Long id;
    private String name;
    private String email;
    private String company;

    Address address;
}
