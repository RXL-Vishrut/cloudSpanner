package com.example.cloudSpanner.controller;


import com.example.cloudSpanner.advSearch.EmpSpecificationBuilder;
import com.example.cloudSpanner.advSearch.SearchOperation;
import com.example.cloudSpanner.domain.Employee;
import com.example.cloudSpanner.advSearch.EmployeeSearchDto;
import com.example.cloudSpanner.advSearch.SearchCriteria;
import com.example.cloudSpanner.service.EmployeeService;
import com.example.cloudSpanner.utils.APIResponse;
import com.google.cloud.spanner.Statement;
import com.google.cloud.spring.data.spanner.core.SpannerQueryOptions;
import com.google.cloud.spring.data.spanner.core.SpannerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class SearchController {

    @Autowired
    private EmployeeService empService;

    @Autowired
    private SpannerTemplate spannerTemplate;

    @GetMapping("/employees")
    public ResponseEntity<APIResponse> getAllEmployees(){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(empService.findAllEmployee());
        apiResponse.setMessage("Employee record retrieved successfully");
        apiResponse.setResponseCode(HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, apiResponse.getResponseCode());
    }

    @PostMapping("/search")
    public ResponseEntity<APIResponse> searchEmployees(@RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
                                                       @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                       @RequestBody EmployeeSearchDto employeeSearchDto){
        System.out.println("employeeSearchDto:" + employeeSearchDto);
        APIResponse apiResponse = new APIResponse();
        EmpSpecificationBuilder builder = new EmpSpecificationBuilder();
        List<SearchCriteria> criteriaList = employeeSearchDto.getSearchCriteriaList();
        if(criteriaList != null){
            criteriaList.forEach(x-> {x.setDataOption(employeeSearchDto.getDataOption());
                builder.with(x);
            });

        }

        Pageable page = PageRequest.of(pageNum, pageSize, Sort.by("empfirstNm")
                .ascending().and(Sort.by("emplastNm"))
                .ascending().and(Sort.by("deptId")).ascending());

        String statement = builder.buildQuery();

        List<Employee> employees = spannerTemplate.query(Employee.class, Statement.of(statement), new SpannerQueryOptions());

//        Page<Employee> employeePage = empService.findBySearchCriteria(builder.build(), page);

        apiResponse.setData(employees);
        apiResponse.setResponseCode(HttpStatus.OK);
        apiResponse.setMessage("Successfully retrieved employee record");

        return new ResponseEntity<>(apiResponse, apiResponse.getResponseCode());
    }
}
