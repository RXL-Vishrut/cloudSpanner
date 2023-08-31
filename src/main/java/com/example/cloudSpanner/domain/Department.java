package com.example.cloudSpanner.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.cloud.spring.data.spanner.core.mapping.Column;
import com.google.cloud.spring.data.spanner.core.mapping.Interleaved;
import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DEPARTMENT")
public class Department {

    @PrimaryKey
    @Column(name = "DEPT_ID")
    private Long deptId;

    @Column(name = "DEPT_NAME")
    private String deptName;

    @JsonIgnore
    @Interleaved
    private List<Employee> emps;
}
