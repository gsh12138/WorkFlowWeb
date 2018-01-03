package repositorys.splserverRepositorys;

import entitys.GtestbiaodanEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tech on 2017/12/21.
 */
@Repository
public interface GtestbiaodanRepository extends CrudRepository<GtestbiaodanEntity,String> {

    @Query(value = "select entity from GtestbiaodanEntity entity where entity.id=?1")
    GtestbiaodanEntity findByUserId(String id);
}
