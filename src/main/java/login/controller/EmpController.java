package login.controller;


import login.emplyeService.EmployeService;
import login.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController

@RequestMapping("/employee")
public class EmpController {


    private final EmployeService employeService;

    public EmpController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @PostMapping("/saveEmp")
    public ResponseEntity<HttpStatus> save(@RequestBody EmployeeEntity employeeEntity) {

        EmployeeEntity save = employeService.save(employeeEntity);
       return ResponseEntity.ok(HttpStatus.CREATED);

    }

    @GetMapping("/findName")
    public EmployeeEntity findbyname(@RequestParam String empName){

      return  employeService.findByname(empName);
    }
    @GetMapping("/findAll")
    public List<EmployeeEntity> findAll(){

        return  employeService.findAll();
    }

    @GetMapping("/findId/{empid}")

    public EmployeeEntity findbyid(@PathVariable Long empid){
        return employeService.findById(empid);
    }

 @PutMapping("/update/{empid}")
    public String updateEmp(@PathVariable long empid,
                            @RequestBody EmployeeEntity employeeEntity){

        return employeService.updateEmp(empid,employeeEntity);

    }
    @DeleteMapping("/del_emp/{empid}")
    public String deleteEmp(@PathVariable Long empid){
return employeService.deleteEmp(empid);

    }
//page and sorting
    @GetMapping("/page")
    public Page<EmployeeEntity> getallEmp(Pageable pageable){
        return employeService.getAllemploye(pageable);
    }

    @GetMapping("/ms")
    public Page<EmployeeEntity> searchByname(@RequestParam String empName,
                                             Pageable pageable){

        return employeService.searchEmployeeName(empName,pageable);
    }
}
