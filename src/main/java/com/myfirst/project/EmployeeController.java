package com.myfirst.project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE, RequestMethod.OPTIONS })
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepo;

    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepo.save(employee);
    }

    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @PutMapping("/updateEmployee")
    public String updateEmployee(@RequestParam String email, @RequestBody Employee updatedEmployee) {
        List<Employee> employees = employeeRepo.findAll();
        for (Employee employee : employees) {
            if (employee.getEmail().equals(email)) {
                employee.setName(updatedEmployee.getName());
                employee.setPosition(updatedEmployee.getPosition());
                employee.setDepartment(updatedEmployee.getDepartment());
                employeeRepo.save(employee);
                return "Employee updated successfully";
            }
        }
        return "Employee not found with email " + email;
    }

    @DeleteMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam String email) {
        List<Employee> employees = employeeRepo.findAll();
        for (Employee employee : employees) {
            if (employee.getEmail().equals(email)) {
                employeeRepo.delete(employee);
                return "Employee with email " + email + " deleted successfully";
            }
        }
        return "Employee not found with email " + email;
    }
}
