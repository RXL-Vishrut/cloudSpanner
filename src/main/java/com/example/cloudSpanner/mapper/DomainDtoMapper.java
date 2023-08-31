package com.example.cloudSpanner.mapper;

import com.example.cloudSpanner.domain.Department;
import com.example.cloudSpanner.domain.Employee;
import com.example.cloudSpanner.domain.SalaryGrade;
import com.example.cloudSpanner.dto.DepartmentDto;
import com.example.cloudSpanner.dto.EmployeeDto;
import com.example.cloudSpanner.dto.SalaryGradeDto;
import org.modelmapper.ModelMapper;

public class DomainDtoMapper {

    public static Employee getEmployee(EmployeeDto empDto){
        if(empDto == null){
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(empDto, Employee.class);
    }

    public static EmployeeDto getEmployeeDto(Employee emp){
        if(emp == null){
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(emp, EmployeeDto.class);
    }

    public static Department getDepartment(DepartmentDto deptDto){
        if(deptDto == null){
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(deptDto, Department.class);
    }

    public static DepartmentDto getDepartmentDto(Department dept){
        if(dept == null){
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dept, DepartmentDto.class);
    }

    public static SalaryGrade getSalaryGrade(SalaryGradeDto salGradeDto){
        if(salGradeDto == null){
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(salGradeDto, SalaryGrade.class);
    }

    public static SalaryGradeDto getSalaryGradeDto(SalaryGrade salGrade){
        if(salGrade == null){
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(salGrade, SalaryGradeDto.class);
    }
}
