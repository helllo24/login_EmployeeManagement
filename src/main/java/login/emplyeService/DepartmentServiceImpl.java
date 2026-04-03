package login.emplyeService;


import login.dto.DepartmentDto;
import login.entity.DepartmentEntity;
import login.repository.DepartmentRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl  implements DepartmentService{

    private final DepartmentRespository departmentRespository;

    public DepartmentServiceImpl(DepartmentRespository departmentRespository) {
        this.departmentRespository = departmentRespository;
    }


    @Override
    public DepartmentEntity saveDepartment(DepartmentDto departmentDto) {
        DepartmentEntity department = new DepartmentEntity();
        department.setDepname(departmentDto.getDepartmentname());
        return departmentRespository.save(department);
    }

    @Override
    public List<DepartmentEntity> getall() {
        return  departmentRespository.findAll();
    }
}
