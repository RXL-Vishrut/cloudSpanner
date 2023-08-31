package com.example.cloudSpanner.service;

import com.example.cloudSpanner.domain.Department;
import com.example.cloudSpanner.dto.DepartmentDto;
import com.example.cloudSpanner.mapper.DomainDtoMapper;
import com.example.cloudSpanner.repository.DepartmentRepository;
import com.google.cloud.spring.data.spanner.core.SpannerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private SpannerTemplate spannerTemplate;

    public void addDepartment(DepartmentDto departmentDto){
        Department dept = DomainDtoMapper.getDepartment(departmentDto);
        spannerTemplate.insert(dept);
    }
}