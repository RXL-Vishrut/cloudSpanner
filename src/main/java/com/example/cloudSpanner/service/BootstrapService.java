package com.example.cloudSpanner.service;

import com.example.cloudSpanner.domain.*;
import com.example.cloudSpanner.dto.DepartmentDto;
import com.example.cloudSpanner.dto.EmployeeDto;
import com.example.cloudSpanner.dto.SalaryGradeDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spanner.KeySet;
import com.google.cloud.spring.data.spanner.core.SpannerOperations;
import com.google.cloud.spring.data.spanner.core.SpannerTemplate;
import com.google.cloud.spring.data.spanner.core.admin.SpannerDatabaseAdminTemplate;
import com.google.cloud.spring.data.spanner.core.admin.SpannerSchemaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class BootstrapService implements CommandLineRunner {

    @Autowired
    private SpannerOperations spannerOperations;

    @Autowired
    private SpannerSchemaUtils spannerSchemaUtils;

    @Autowired
    private SpannerDatabaseAdminTemplate spannerDatabaseAdminTemplate;

    @Autowired
    private SpannerTemplate spannerTemplate;


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SalaryGradeService salaryGradeService;

    @Override
    public void run(String... args) throws Exception {
        if (!this.spannerDatabaseAdminTemplate.tableExists("DEPARTMENT")) {
            this.spannerDatabaseAdminTemplate.executeDdlStrings(Arrays.asList(
                    this.spannerSchemaUtils.getCreateTableDdlString(Department.class)), false);
        }

        if (!this.spannerDatabaseAdminTemplate.tableExists("SALARY_GRADE")) {
            this.spannerDatabaseAdminTemplate.executeDdlStrings(Arrays.asList(
                    this.spannerSchemaUtils.getCreateTableDdlString(SalaryGrade.class)), false);
        }

        if (!this.spannerDatabaseAdminTemplate.tableExists("EMPLOYEE")) {
            this.spannerDatabaseAdminTemplate.executeDdlStrings(Arrays.asList(
                    this.spannerSchemaUtils.getCreateTableDdlString(Employee.class)), false);
        }

        spannerTemplate.delete(Employee.class, KeySet.all());
        spannerTemplate.delete(Department.class, KeySet.all());
        spannerTemplate.delete(SalaryGrade.class, KeySet.all());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/department.json");
            log.info("Saving Department data");
            List<DepartmentDto> dept = objectMapper.readValue(inputStream, new TypeReference<List<DepartmentDto>>() {
            });
            dept.stream().forEach(x -> departmentService.addDepartment(x));
            log.info("Successfully save");

            inputStream = TypeReference.class.getResourceAsStream("/json/employee.json");
            log.info("Saving Employee data");
            List<EmployeeDto> empList = objectMapper.readValue(inputStream, new TypeReference<List<EmployeeDto>>() {
            });
            empList.stream().forEach(x -> employeeService.addEmployee(x));
            log.info("Successfully save");

            inputStream = TypeReference.class.getResourceAsStream("/json/salarygrade.json");
            log.info("Saving Salary grade data");
            List<SalaryGradeDto> salaryGrade = objectMapper.readValue(inputStream, new TypeReference<List<SalaryGradeDto>>() {
            });
            salaryGrade.stream().forEach(x -> salaryGradeService.addSalaryGrade(x));
            log.info("Successfully save");
        } catch (IOException e) {
            log.error("Unable to save data" + e.getMessage());
        }
    };

//    public void createAlbumsTable() {
//        if (!this.spannerDatabaseAdminTemplate.tableExists("albums")) {
//            this.spannerDatabaseAdminTemplate.executeDdlStrings(Arrays.asList(
//                    this.spannerSchemaUtils.getCreateTableDdlString(Album.class)), false);
//        }
//    }
//
//
//    public void createSingersTable() {
//        if (!this.spannerDatabaseAdminTemplate.tableExists("singers")) {
//            this.spannerDatabaseAdminTemplate.executeDdlStrings(
//                    Arrays.asList(
//                            this.spannerSchemaUtils.getCreateTableDdlString(Singer.class)),
//                    false);
//        }
//    }

//    public void insertDataOnStartUp() {

//        spannerTemplate.delete(Singer.class, KeySet.all());
//        spannerTemplate.delete(Album.class, KeySet.all());
//
//        Singer singer = new Singer(1L, "Vishrut" , "Sacheti", 40, new ArrayList<>());
//        Singer singer1 = new Singer(2L, "Sarthak" , "Gupta", 50, new ArrayList<>());
////
//        Album album = new Album(1L, 1, "Swedish Mafia", 9);
//        Album album1 = new Album(1L, 2, "ColdPlay", 8);
//        Album album2 = new Album(2L, 3, "Swedish Mafiass", 10);
//
//        singer.setAlbums(List.of(album, album1));
//        singer1.setAlbums(List.of(album2));
//
//        spannerTemplate.insert(singer);
//        spannerTemplate.insert(singer1);
////        spannerTemplate.insert(album);
////        spannerTemplate.insert(album1);
//
//        List<Singer> singerList = spannerTemplate.readAll(Singer.class);
//        List<Album> albumList = spannerTemplate.readAll(Album.class);
//
//        log.info("Data inserted in tables");
//        for (Singer singers : singerList) {
//            log.info(String.valueOf(singers));
//        }
//
//        for (Album albums : albumList) {
//            log.info(String.valueOf(albums));
//        }
//    }
}