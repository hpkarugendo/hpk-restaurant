package com.hpkarugendo.hpkrestaurant.management.services;

import com.hpkarugendo.hpkrestaurant.management.enums.Department;
import com.hpkarugendo.hpkrestaurant.management.enums.Position;
import com.hpkarugendo.hpkrestaurant.management.models.Employee;
import com.hpkarugendo.hpkrestaurant.management.models.EmployeeDTO;
import com.hpkarugendo.hpkrestaurant.management.repositories.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService implements UserDetailsService {
    private final EmployeeRepository eRepo;

    public EmployeeService(EmployeeRepository uRepo) {
        this.eRepo = uRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return eRepo.findByUsername(username).get();
    }

    public Employee saveEmployee(EmployeeDTO employeeDTO){
        Employee e;

        if(employeeDTO.getId() <1){
            e = new Employee();

            switch (employeeDTO.getPosition()){
                case "MANAGER":
                    if(eRepo.findFirstByPositionOrderByCodeDesc(Position.MANAGER) == null){
                        e.setCode(1001);
                    } else {
                        e.setCode(eRepo.findFirstByPositionOrderByCodeDesc(Position.MANAGER).getCode() + 1);
                    }
                    break;
                case "SUPERVISOR":
                    if(eRepo.findFirstByPositionOrderByCodeDesc(Position.SUPERVISOR) == null){
                        e.setCode(2001);
                    } else {
                        e.setCode(eRepo.findFirstByPositionOrderByCodeDesc(Position.SUPERVISOR).getCode() + 1);
                    }
                    break;
                case "EMPLOYEE":
                    if(eRepo.findFirstByPositionOrderByCodeDesc(Position.EMPLOYEE) == null){
                        e.setCode(3001);
                    } else {
                        e.setCode(eRepo.findFirstByPositionOrderByCodeDesc(Position.EMPLOYEE).getCode() + 1);
                    }
                    break;
            }
        } else {
            e = eRepo.findById(employeeDTO.getId()).get();
        }
        e.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(employeeDTO.getPassword()));
        e.setFirstName(employeeDTO.getFirstName());
        e.setLastName(employeeDTO.getLastName());
        e.setUsername(employeeDTO.getUsername());
        e.setEmail(employeeDTO.getEmail());
        e.setPhone(employeeDTO.getPhone());

        switch (employeeDTO.getDept()){
            case "BAR":
                e.setDepartment(Department.BAR);
                break;
            case "KITCHEN":
                e.setDepartment(Department.KITCHEN);
                break;
            case "RESTAURANT":
                e.setDepartment(Department.RESTAURANT);
                break;
        }

        switch (employeeDTO.getPosition()){
            case "MANAGER":
                e.setPosition(Position.MANAGER);
                break;
            case "SUPERVISOR":
                e.setPosition(Position.SUPERVISOR);
                break;
            case "EMPLOYEE":
                e.setPosition(Position.EMPLOYEE);
                break;
        }

        return eRepo.save(e);
    }

    public List<Employee> listAll(){
        return eRepo.findAll();
    }

    public EmployeeDTO getDTO(int id){
        EmployeeDTO ans = new EmployeeDTO();
        Employee toEdit = null;
        if(eRepo.findById(id).isEmpty()){
            return null;
        } else {
            toEdit = eRepo.findById(id).get();

            ans.setId(toEdit.getId());
            ans.setUsername(toEdit.getUsername());
            ans.setPosition(toEdit.getPosition().name());
            ans.setEmail(toEdit.getEmail());
            ans.setPhone(toEdit.getPhone());
            ans.setDept(toEdit.getDepartment().name());
            ans.setFirstName(toEdit.getFirstName());
            ans.setLastName(toEdit.getLastName());

            return ans;
        }
    }

    public void deleteEmp(int id){
        try {
            eRepo.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Error Deleting Employee With ID: " + id + "!");
        }
    }
}
