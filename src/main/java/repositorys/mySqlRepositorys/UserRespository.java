package repositorys.mySqlRepositorys;

import entitys.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tech on 2017/12/21.
 */
@Repository
public interface UserRespository extends CrudRepository<UserEntity,Integer> {
    UserEntity findByUserid(String id);
    List<UserEntity> findByUseridOrNameAndDepartment(String userid,String name,String department);
    List<UserEntity> findByUseridOrName(String userid,String name);
    List<UserEntity> findByDepartment(String Department);
}
