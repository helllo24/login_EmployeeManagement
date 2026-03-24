
package login.repository;

import login.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity,Long>
{
    Optional<LoginEntity> findByusername(String username);

  Optional<LoginEntity> findByResetToken(String resetToken);


}
