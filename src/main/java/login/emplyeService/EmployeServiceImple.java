package login.emplyeService;

import login.entity.EmployeeEntity;
import login.repository.EmployeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EmployeServiceImple implements EmployeService{

    private  final EmployeRepository empRepo;

    public EmployeServiceImple(EmployeRepository empRepo){
        this.empRepo=empRepo;
    }



    @Override
    public EmployeeEntity save(EmployeeEntity employeeEntity) {

      return  empRepo.save(employeeEntity);
//       EmployeeEntity emp = new EmployeeEntity();
//       emp.setEmpName(empName);
//       emp.setEmpMailid(empMailId);
//       emp.setRole(role);
//       emp.setDepartment(department);
//
//        return  empRepo.save(emp);



    }

    @Override
    public EmployeeEntity findByname(String name) {
        return empRepo.findByEmpName(name);
    }

    @Override
    public List<EmployeeEntity> findAll() {
        return empRepo.findAll();
    }

    @Override
    public EmployeeEntity findById(Long empid) {
        
        return empRepo.findByEmpid(empid);
    }

    @Override
    public String updateEmp(long empid, EmployeeEntity employeeEntity) {


        EmployeeEntity emp = empRepo.findByEmpid(empid);
if(emp==null){
    return "emp is not in db";
}

        emp.setEmpName(employeeEntity.getEmpName());
        emp.setEmpMailid(employeeEntity.getEmpMailid());
        emp.setDepartment(employeeEntity.getDepartment());
        emp.setRole(employeeEntity.getRole());

         empRepo.save(emp);

         return "Update success";
    }

    @Override
    public String deleteEmp(Long empid) {

        EmployeeEntity emp = empRepo.findByEmpid(empid);
        if(emp == null){
            return "empId is not stored";
        }

        empRepo.deleteById(empid);


        return "Emp deleted Successfully";
    }

    @Override
    public Page<EmployeeEntity> getAllemploye(Pageable pageable) {

        return empRepo.findAll(pageable);
    }

@Override
    public Page<EmployeeEntity> searchEmployeeName(String empName, Pageable pageable) {
        return empRepo.findByEmpNameContaining(empName,pageable);
    }
}
