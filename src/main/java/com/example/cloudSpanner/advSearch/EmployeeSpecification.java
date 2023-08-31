package com.example.cloudSpanner.advSearch;

import com.example.cloudSpanner.domain.Department;
import com.example.cloudSpanner.domain.Employee;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class EmployeeSpecification implements Specification<Employee> {

    private final SearchCriteria searchCriteria;

    public EmployeeSpecification(final SearchCriteria
                                         searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }


    /*
    Inside this method, we will implement different reusable predicates depending on the
     search operation (i.e. CONTAINS, DOES_NOT_CONTAIN, EQUAL, NOT_EQUAL).
     */
    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        String strToSearch = searchCriteria.getValue().toString().toLowerCase();

        switch(Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))){
            case CONTAINS:
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return cb.like(cb.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

            case DOES_NOT_CONTAIN:
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return cb.notLike(cb.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");

            case BEGINS_WITH:
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return cb.like(cb.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

            case DOES_NOT_BEGIN_WITH:
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return cb.notLike(cb.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), strToSearch + "%");
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), strToSearch + "%");

            case ENDS_WITH:
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return cb.like(cb.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case DOES_NOT_END_WITH:
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return cb.notLike(cb.lower(departmentJoin(root).<String>get(searchCriteria.getFilterKey())), "%" + strToSearch);
                }
                return cb.notLike(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch);

            case EQUAL:
                if(searchCriteria.getFilterKey().equals("deptName")){
                    System.out.println(searchCriteria.getValue());
                    return cb.equal(departmentJoin(root).<String>get(searchCriteria.getFilterKey()), searchCriteria.getValue());
                }
                return cb.equal(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

            case NOT_EQUAL:
                if(searchCriteria.getFilterKey().equals("deptName")){
                    return cb.notEqual(departmentJoin(root).<String>get(searchCriteria.getFilterKey()), searchCriteria.getValue() );
                }
                return cb.notEqual(root.get(searchCriteria.getFilterKey()), searchCriteria.getValue());

            case NUL:
                return cb.isNull(root.get(searchCriteria.getFilterKey()));

            case NOT_NULL:
                return cb.isNotNull(root.get(searchCriteria.getFilterKey()));

            case GREATER_THAN:
                return cb.greaterThan(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case GREATER_THAN_EQUAL:
                return cb.greaterThanOrEqualTo(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case LESS_THAN:
                return cb.lessThan(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());

            case LESS_THAN_EQUAL:
                return cb.lessThanOrEqualTo(root.<String> get(searchCriteria.getFilterKey()), searchCriteria.getValue().toString());
        }
        return null;
    }

    private Join<Employee, Department> departmentJoin(Root<Employee> root){
        return root.join("department");

    }
}
