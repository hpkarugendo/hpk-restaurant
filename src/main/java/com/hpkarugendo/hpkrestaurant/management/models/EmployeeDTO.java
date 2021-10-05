package com.hpkarugendo.hpkrestaurant.management.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private int id;
    private String username, password, firstName, lastName, dept, position, email, phone;
}
