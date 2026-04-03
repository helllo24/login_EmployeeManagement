package login.emplyeService;


import login.dto.DepartmentDto;
import login.entity.DepartmentEntity;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {


    DepartmentEntity saveDepartment(DepartmentDto departmentDto);

    List<DepartmentEntity> getall();




}
