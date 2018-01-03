package repositorys.oracleRepositorys;

import entitys.GemFileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tech on 2017/12/21.
 */
@Repository
public interface GemRepository extends CrudRepository<GemFileEntity,String> {


}
