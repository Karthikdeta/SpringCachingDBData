package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void createBulkInserts() {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            employeeList.add(
                    Employee.builder().name("Test Name" + i).organization("Test Organization " + i)
                            .salary((long) (Math.random() * 100000)).build());
        }
        employeeRepository.saveAll(employeeList);
    }

    public void deleteEmployee(int empId) {
        employeeRepository.deleteById(empId);
    }

    @Transactional
    public void deleteEmployeesById(List<Integer> empIds) {
        employeeRepository.deleteAllById(empIds);
    }

    @Transactional
    public void deleteEmployeesByIdInBatch(List<Integer> empIds) {
        employeeRepository.deleteAllByIdInBatch(empIds);
    }

    @Transactional
    public void deleteByNameIn(List<String> employeeNames) {
        employeeRepository.deleteByNameIn(employeeNames);
    }

    @Transactional
    public void deleteByNameQuery(List<String> employeeNames) {
        employeeRepository.deleteByNameQuery(employeeNames);
    }

    // @Cacheable(value = "employees")
    public Employee findEmployeeById(int empId) {
        // return employeeRepository.findById(empId).get();
        return employeeRepository.findEmployeeById(empId).get();
    }

    public Employee findEmployeeByIdAndSal(int empId, long salary) {
        // return employeeRepository.findById(empId).get();
        return employeeRepository.findEmployeeByIdAndSalary(empId, salary).get();
    }

    @CacheEvict(value = "employeesSalary")
    public String evictEmployeeSalaryCacheByKey(int empId, long salary) {
        return "Employee Salary By Key evicted successfully";
    }

    @CacheEvict(value = "employeesSalary", allEntries = true)
    public String evictEmployeeSalaryCache() {
        return "Employee Salary evicted successfully";
    }

    public String findEmployeeGroupedByName() {
        for (int i = 1; i <= 100; i++) {
            // long sal = employeeRepository.findEmployeeGroupedByName("Test Name" + i);
            long sal =
                    employeeRepository.findAllEmployees().stream()
                            .collect(Collectors.groupingBy(Employee::getName,
                                    Collectors.summingLong(Employee::getSalary)))
                            .get("Test Name" + i);
            System.out.println("Test Name " + i + " - " + sal);
        }
        return "Successfully executed group by name";
    }

}
