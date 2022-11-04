package com.example.demo;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employee/bulkinsert")
    public String createBulkInserts() {
        employeeService.createBulkInserts();
        return "Bulk Insert Successful";
    }

    @DeleteMapping("/employee/deleteById")
    public String deleteEmployee(@RequestParam int empId) {
        employeeService.deleteEmployee(empId);
        return "Delete Employee Successful";
    }

    @DeleteMapping("/employee/deleteByIds")
    public String deleteEmployeeByIds(@RequestParam List<Integer> empIds) {
        employeeService.deleteEmployeesById(empIds);
        return "Delete Employee By Ids is Successful";
    }

    @DeleteMapping("/employee/deleteByIdsInBatch")
    public String deleteEmployeeByIdsInBatch(@RequestParam List<Integer> empIds) {
        employeeService.deleteEmployeesByIdInBatch(empIds);
        return "Delete Employee By Ids using Batch is Successful";
    }

    @DeleteMapping("/employee/deleteByNameIn")
    public String deleteEmployeeByNameIn(@RequestParam List<String> empNames) {
        employeeService.deleteByNameIn(empNames);
        return "Delete Employee By Name In Successful";
    }

    @DeleteMapping("/employee/deleteByNameQuery")
    public String deleteEmployeeByNameQuery(@RequestParam List<String> empNames) {
        employeeService.deleteByNameQuery(empNames);
        return "Delete Employee By Name using Query Successful";
    }

    @GetMapping("/employee/getEmployeeById")
    public Employee getEmployeeById(@RequestParam int empId) {
        return employeeService.findEmployeeById(empId);
    }

    @GetMapping("/employee/getEmployeeByIdandSalary")
    public Employee getEmployeeByIdAndSalary(@RequestParam int empId, @RequestParam long salary) {
        return employeeService.findEmployeeByIdAndSal(empId, salary);
    }

    @DeleteMapping("/employee/evictEmployeeSalaryCacheByKey")
    public String evictEmployeeSalaryCacheByKey(@RequestParam int empId,
            @RequestParam long salary) {
        return employeeService.evictEmployeeSalaryCacheByKey(empId, salary);
    }

    @DeleteMapping("/employee/evictEmployeeSalaryCache")
    public String evictEmployeeSalaryCache() {
        return employeeService.evictEmployeeSalaryCache();
    }

    @GetMapping("/employee/getEmployeeGroupedByName")
    public String findEmployeeGroupedByName() {
        return employeeService.findEmployeeGroupedByName();
    }
}
