package com.example.cloudSpanner.advSearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSearchDto {

    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;

    //frontend payload

//        {
//        "dataOption":"all",
//            "searchCriteriaList":[
//        {
//            "filterKey":"deptName",
//                "operation":"eq",
//                "value":"MARKETING"
//        },
//        {
//            "filterKey":"salary",
//                "operation":"lt",
//                "value":2957
//        },
//    }
}
