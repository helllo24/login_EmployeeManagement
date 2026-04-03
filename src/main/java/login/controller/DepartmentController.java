package login.controller;


import login.apiResponce.Responce;
import login.dto.DepartmentDto;
import login.emplyeService.DepartmentService;
import login.entity.DepartmentEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/depart")
public class DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/savedepart")
    public ResponseEntity<Responce<DepartmentEntity>> saveDepart(@RequestBody DepartmentDto departmentDto){

        DepartmentEntity savedepartment = departmentService.saveDepartment(departmentDto);

        Responce<DepartmentEntity> responce = new Responce<>();
        responce.setSuccess(true);
        responce.setMessage("Department Saved ");
        responce.setData(savedepartment);

        return ResponseEntity.ok(responce);

    }

    @GetMapping("/getDepart")
    public ResponseEntity<Responce<List<DepartmentEntity>>> getDepart(){


        List<DepartmentEntity> getall = departmentService.getall();

        Responce<List<DepartmentEntity>> responce = new Responce<>();
        responce.setSuccess(true);
        responce.setMessage("Department Details");
        responce.setData(getall);

        return  ResponseEntity.ok(responce);
    }


}
