package com.hpkarugendo.hpkrestaurant.management.repositories;

import com.hpkarugendo.hpkrestaurant.management.enums.Department;
import com.hpkarugendo.hpkrestaurant.management.enums.Position;
import com.hpkarugendo.hpkrestaurant.management.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Optional<Employee> findByUsername(String username);
    Optional<Employee> findByCode(int code);
    List<Employee> findByPosition(Position position);
    List<Employee> findByDepartment(Department department);
    List<Employee> findAll();
    Employee findFirstByPositionOrderByCodeDesc(Position position);
    void deleteById(int id);
}
