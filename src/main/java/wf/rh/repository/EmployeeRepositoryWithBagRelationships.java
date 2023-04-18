package wf.rh.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import wf.rh.domain.Employee;

public interface EmployeeRepositoryWithBagRelationships {
    Optional<Employee> fetchBagRelationships(Optional<Employee> employee);

    List<Employee> fetchBagRelationships(List<Employee> employees);

    Page<Employee> fetchBagRelationships(Page<Employee> employees);
}
