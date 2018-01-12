package repositorys.mySqlRepositorys;

import entitys.UserdetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserdetailRepository extends CrudRepository<UserdetailEntity,Integer> {

    UserdetailEntity findByUserid(String userid);
    List<UserdetailEntity> findByUseridOrUsernameAndDepartment(String userid,String username,String department);
    List<UserdetailEntity> findByUseridOrUsername(String userid,String username);
    List<UserdetailEntity> findByDepartment(String department);
}
