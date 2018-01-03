package repositorys.mySqlRepositorys;

import entitys.UserdetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserdetailRepository extends CrudRepository<UserdetailEntity,Integer> {

    UserdetailEntity findByUserid(String userid);
}
