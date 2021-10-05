package com.hpkarugendo.hpkrestaurant.management.controllers;

import com.hpkarugendo.hpkrestaurant.kitchen.models.Allergy;
import com.hpkarugendo.hpkrestaurant.kitchen.services.AllergyService;
import com.hpkarugendo.hpkrestaurant.kitchen.services.FoodService;
import com.hpkarugendo.hpkrestaurant.management.models.Employee;
import com.hpkarugendo.hpkrestaurant.management.models.EmployeeDTO;
import com.hpkarugendo.hpkrestaurant.management.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ManagementController {
    private final FoodService fService;
    private final AllergyService aService;
    private final EmployeeService eService;

    public ManagementController(FoodService fService, AllergyService aService, EmployeeService eService) {
        this.fService = fService;
        this.aService = aService;
        this.eService = eService;
    }

    @GetMapping("manage/home")
    public String manageHome(){
        return "home_management";
    }

    @GetMapping("manage/employees")
    public String manageEmployees(Model m){
        m.addAttribute("employees", eService.listAll());
        return "manage_employees";
    }

    @GetMapping("manage/employees/new")
    public String newEmp(Model m){
        if(!m.containsAttribute("employeeDTO")){
            m.addAttribute("employeeDTO", new EmployeeDTO());
        }
        return "manage_employee_form";
    }

    @PostMapping("manage/employees/add")
    public String addEmp(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO, RedirectAttributes ra){
        try {
            eService.saveEmployee(employeeDTO);
            ra.addFlashAttribute("mYes", employeeDTO.getFirstName().toUpperCase() + " " + employeeDTO.getLastName().toUpperCase() + " has been saved successfully!");
            return "redirect:/manage/employees";
        } catch (Exception e){
            ra.addFlashAttribute("mNo", "Error Saving User. Username Might Be Taken!");
            ra.addFlashAttribute("employeeDTO", employeeDTO);
            System.out.println(e);
            return "redirect:/manage/employees/new";
        }

    }

    @GetMapping("manage/employees/edit/{id}")
    public String editEmp(@PathVariable("id") int id, RedirectAttributes ra){
        EmployeeDTO toEdit = eService.getDTO(id);
        if(toEdit == null){
            ra.addFlashAttribute("mNo", "Could not find employee with ID: " + id);
            return "redirect:/manage/employees";
        } else {
            ra.addFlashAttribute("employeeDTO", toEdit);
            return "redirect:/manage/employees/new";
        }
    }

    @GetMapping("manage/employees/delete/{id}")
    public String deleteEmp(@PathVariable("id") int id, RedirectAttributes ra){
        try {
            eService.deleteEmp(id);
            ra.addFlashAttribute("mYes", "Employee with ID: " + id + " deleted successfully!");
            return "redirect:/manage/employees";
        } catch (Exception e){
            ra.addFlashAttribute("mNo", "Error deleting employee with ID: " + id);
            return "redirect:/manage/employees";
        }
    }
}

