package com.example.cloudSpanner.domain;

import com.google.cloud.spring.data.spanner.core.mapping.Column;
import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SALARY_GRADE")
public class SalaryGrade {

    @PrimaryKey
    private Long grade;
    private double minSalary;
    private double maxSalary;
}
