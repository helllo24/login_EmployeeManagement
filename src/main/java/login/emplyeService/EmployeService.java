package login.emplyeService;


import login.dto.EmpDto;
import login.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;


public interface EmployeService {

EmployeeEntity save(EmpDto empDto);

EmployeeEntity findByname(String empName);

List<EmployeeEntity> findAll();

    String askempploye(String question);

EmployeeEntity findById(Long empid);

EmployeeEntity updateEmp(long empid, EmpDto  empDto);

String deleteEmp(Long empid);

//Pageable pagenation(Pageable pageable);
Page<EmployeeEntity> getAllemploye(Pageable pageable);


Page<EmployeeEntity>searchEmployeeName(String empName,Pageable pageable);



}

