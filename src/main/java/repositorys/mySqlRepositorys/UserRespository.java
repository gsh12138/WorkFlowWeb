package repositorys.mySqlRepositorys;

import entitys.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tech on 2017/12/21.
 */
@Repository
public interface UserRespository extends CrudRepository<UserEntity,Integer> {
    UserEntity findByUserid(String id);
}
