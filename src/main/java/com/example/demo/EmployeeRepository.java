package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    void deleteByNameIn(List<String> empNames);

    @Query("delete from Employee emp where emp.name in (:empNames)")
    @Modifying
    void deleteByNameQuery(@Param("empNames") List<String> empNames);

    @Cacheable(value = "employees")
    @Query("Select emp from Employee emp where id=?1")
    Optional<Employee> findEmployeeById(Integer empId);

    @Cacheable(value = "employeesSalary")
    @Query("Select emp from Employee emp where id=?1 and salary=?2")
    Optional<Employee> findEmployeeByIdAndSalary(Integer empId, Long salary);

    @Cacheable(value = "allEmployees")
    @Query("Select emp from Employee emp")
    List<Employee> findAllEmployees();

    // @Query("Select java.util.Map.of(emp.name, java.util.List.of(emp.name, emp.organization)) from
    // Employee emp group by emp.name")
    // Map<String, List<String>> findEmployeeGroupedByName();
    default Long findEmployeeGroupedByName(String name) {
        return this.findAllEmployees().stream().collect(Collectors.groupingBy(Employee::getName,
                Collectors.summingLong(Employee::getSalary))).get(name);
    }

}
