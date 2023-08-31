package com.example.cloudSpanner.service;

import com.example.cloudSpanner.domain.Employee;
import com.example.cloudSpanner.dto.EmployeeDto;
import com.example.cloudSpanner.mapper.DomainDtoMapper;
import com.example.cloudSpanner.repository.EmployeeRepository;
import com.google.cloud.spring.data.spanner.core.SpannerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepository;

    @Autowired
    private SpannerTemplate spannerTemplate;

    public void addEmployee(EmployeeDto employeeDto){
        Employee emp = DomainDtoMapper.getEmployee(employeeDto);
        spannerTemplate.insert(emp);
    }

    public List<Employee> findAllEmployee(){
        return (List<Employee>) empRepository.findAll();
    }

    public Page<Employee> findBySearchCriteria(Specification<Employee> spec, Pageable page){
        Page<Employee> searchResult = empRepository.findAll(page);
        return searchResult;
    }
}