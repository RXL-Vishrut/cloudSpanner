package com.example.cloudSpanner.domain;

import com.google.cloud.spring.data.spanner.core.mapping.Column;
import com.google.cloud.spring.data.spanner.core.mapping.Interleaved;
import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYEE")
public class Employee {

    @PrimaryKey(keyOrder = 2)
    @Column(name = "EMP_ID")
    private Long empId;

    @Column(name = "EMP_LASTNM")
    private String emplastNm;

    @Column(name = "EMP_FIRSTNM")
    private String  empfirstNm;

    @Column(name = "JOB_NM")
    private String jobNm;

    @Column(name = "MGR_ID", nullable = true)
    private Long managerId;

    @Column(name = "HIREDT")
    private Date hireDt;

    @Column(name = "SALARY")
    private double salary;

    @Column(name = "COMMISSION", nullable = true)
    private double commission;

    @PrimaryKey
    @Column(name = "DEPT_ID")
    private Long deptId;
}
