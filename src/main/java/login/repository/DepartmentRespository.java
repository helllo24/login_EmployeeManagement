package login.repository;


import login.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRespository  extends JpaRepository<DepartmentEntity,Long> {



}
