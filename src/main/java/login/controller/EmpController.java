package login.controller;


import login.dto.EmpDto;
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
@CrossOrigin(origins = "*")
public class EmpController {


    private final EmployeService employeService;

    public EmpController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @PostMapping("/saveEmp")
    public ResponseEntity<HttpStatus> save(@RequestBody EmpDto empDto) {

        EmployeeEntity save = employeService.save(empDto);
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
    public EmployeeEntity updateEmp(@PathVariable long empid,
                            @RequestBody EmpDto empDto ){

        return employeService.updateEmp(empid,empDto);

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
    @GetMapping("/mail")
    public Page<EmployeeEntity> searchByMailid(@RequestParam String empMailid, Pageable pageable){
        return employeService.searchEmpmailid(empMailid,pageable);
    }

    @PostMapping("/askai")
    public String askAi(@RequestBody String question){

        return employeService.askempploye(question);


    }
}
