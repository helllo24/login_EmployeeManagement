package login.emplyeService;


import login.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;


public interface EmployeService {

EmployeeEntity save(EmployeeEntity employeeEntity);

EmployeeEntity findByname(String empName);

List<EmployeeEntity> findAll();


EmployeeEntity findById(Long empid);

String updateEmp(long empid, EmployeeEntity employeeEntity);

String deleteEmp(Long empid);

//Pageable pagenation(Pageable pageable);
Page<EmployeeEntity> getAllemploye(Pageable pageable);


Page<EmployeeEntity>searchEmployeeName(String empName,Pageable pageable);

}

