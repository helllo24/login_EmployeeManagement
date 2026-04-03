package login.emplyeService;

import login.Aiservice.AIservice;
import login.dto.EmpDto;
import login.entity.DepartmentEntity;
import login.entity.EmployeeEntity;
import login.repository.DepartmentRespository;
import login.repository.EmployeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EmployeServiceImple implements EmployeService{

    private final AIservice aIservice;
    private  final EmployeRepository empRepo;
  private final DepartmentRespository departmentRespository;

    public EmployeServiceImple(AIservice aIservice, EmployeRepository empRepo, DepartmentRespository departmentRespository){
        this.aIservice = aIservice;
        this.empRepo=empRepo;
        this.departmentRespository = departmentRespository;

    }



  
 
    @Override
    public EmployeeEntity save(EmpDto empDto) {

        EmployeeEntity emp = new EmployeeEntity();
        emp.setEmpName(empDto.getEmpName());
        emp.setEmpMailid(empDto.getEmpMailid());
        emp.setRole(empDto.getRole());

        DepartmentEntity dept = departmentRespository.findById(empDto.getDepartmentId())
                        .orElseThrow(()->new RuntimeException("department is not found"));


        emp.setDepartment(dept);
                return empRepo.save(emp);
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
    public String askempploye(String question) {
        List<EmployeeEntity> employee = empRepo.findAll();

        StringBuilder box = new StringBuilder();

        //convert java object - > normal text  why noraml text becoz ai read only text
        for (EmployeeEntity emp : employee) {
            box.append("name: ").append(emp.getEmpName())
                    .append(", mail: ").append(emp.getEmpMailid())
                    .append(", role:").append(emp.getRole())
                    .append(", department: ").append(emp.getDepartment().getDepname())
                    .append("\n");
        }
        String prompt = "Ask any question in employe data\n"
                + box + "\nquestion" + question;
        return aIservice.AiService(prompt);

    }

    @Override
    public EmployeeEntity findById(Long empid) {
        
        return empRepo.findByEmpid(empid);
    }

    @Override
    public EmployeeEntity updateEmp(long empid, EmpDto empDto) {
        EmployeeEntity emp = empRepo.findByEmpid(empid);
        if(emp==null){
            throw  new RuntimeException("Emp is not in DB");
            
        }
        

        emp.setEmpName(empDto.getEmpName());
        emp.setEmpMailid(empDto.getEmpMailid());
        emp.setRole(empDto.getRole());

        //here we joins
        DepartmentEntity dept = departmentRespository.findById(empDto.getDepartmentId())
                .orElseThrow(()->new RuntimeException("department is not found"));


        emp.setDepartment(dept);
        return empRepo.save(emp);



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
