package com.hpkarugendo.hpkrestaurant.security.components;

import com.hpkarugendo.hpkrestaurant.management.enums.Department;
import com.hpkarugendo.hpkrestaurant.management.enums.Position;
import com.hpkarugendo.hpkrestaurant.management.models.Employee;
import com.hpkarugendo.hpkrestaurant.management.models.EmployeeDTO;
import com.hpkarugendo.hpkrestaurant.management.services.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;

@Component
public class DataComponent implements CommandLineRunner {
    private EmployeeService uService;

    public DataComponent(EmployeeService uService) {
        this.uService = uService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(uService.listAll().isEmpty()){
            EmployeeDTO e = new EmployeeDTO();
            e.setDept("KITCHEN");
            e.setPassword("pass");
            e.setUsername("John");
            e.setPosition("MANAGER");
            System.out.println(uService.saveEmployee(e).toString());

            e = new EmployeeDTO();
            e.setDept("BAR");
            e.setPassword("pass");
            e.setUsername("Marta");
            e.setPosition("EMPLOYEE");
            System.out.println(uService.saveEmployee(e).toString());

            e = new EmployeeDTO();
            e.setDept("RESTAURANT");
            e.setPassword("pass");
            e.setUsername("Beata");
            e.setPosition("SUPERVISOR");
            System.out.println(uService.saveEmployee(e).toString());
        }

    }
}
