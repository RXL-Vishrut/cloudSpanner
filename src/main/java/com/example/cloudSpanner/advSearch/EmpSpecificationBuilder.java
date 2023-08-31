package com.example.cloudSpanner.advSearch;

import com.example.cloudSpanner.domain.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EmpSpecificationBuilder {

    private final List<SearchCriteria> params;

    public EmpSpecificationBuilder(){
        this.params = new ArrayList<>();
    }

    public final EmpSpecificationBuilder with(String key,
                                              String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final EmpSpecificationBuilder with(SearchCriteria
                                                      searchCriteria){
        params.add(searchCriteria);
        return this;
    }

//    public Specification<Employee> build(){
//        if(params.size() == 0){
//            return null;
//        }
//
//        Specification<Employee> result = new EmployeeSpecification(params.get(0));
//        for (int idx = 1; idx < params.size(); idx++){
//            SearchCriteria criteria = params.get(idx);
//            result =  SearchOperation.getDataOption(criteria
//                    .getDataOption()) == SearchOperation.ALL
//                    ? Specification.where(result).and(new
//                    EmployeeSpecification(criteria))
//                    : Specification.where(result).or(
//                    new EmployeeSpecification(criteria));
//        }
//        return result;
//    }
    
    

    public String buildQuery() {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Employee");

        if (!params.isEmpty()) {
            queryBuilder.append(" WHERE");

            String conjunction = null;

            for (int i = 0; i < params.size(); i++) {
                SearchCriteria param = params.get(i);
                String column = param.getFilterKey();
                String operation = param.getOperation();
                Object value = param.getValue();
                String dataOption = param.getDataOption();

                if(SearchOperation.getDataOption(dataOption) == SearchOperation.ALL) {
                    conjunction = " AND";
                } else {
                    conjunction = " OR";
                }
                if (i > 0) {
                    queryBuilder.append(conjunction);
                }

                queryBuilder.append(" ").append(column).append(" ");
                queryBuilder.append(getOperatorSymbol(operation)).append(" ");
                queryBuilder.append(value);
            }
        }

        return queryBuilder.toString();
    }

    private String getOperatorSymbol(String operation) {
        // Map operation strings to Cloud Spanner operators
        switch (operation) {
            case "eq":
                return "=";
            case "gt":
                return ">";
            case "ge":
                return ">=";
            case "lt":
                return "<";
            case "le":
                return "<=";
            case "ne":
                return "!=";
            // Add more cases for other operators
            default:
                throw new IllegalArgumentException("Unsupported operation: " + operation);
        }
    }
}
