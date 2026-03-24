package login.repository;

import login.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeRepository  extends JpaRepository<EmployeeEntity,Long> {

    EmployeeEntity findByEmpName(String empName);

    EmployeeEntity findByEmpid(Long empid);


    Page<EmployeeEntity> findByEmpNameContaining(String empName, Pageable pageable);


}
