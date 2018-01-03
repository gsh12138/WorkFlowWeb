package repositorys.splserverRepositorys;

import entitys.GtestUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tech on 2017/12/21.
 */
@Repository
public interface GtestUserRepository extends CrudRepository<GtestUserEntity,Integer> {
}
