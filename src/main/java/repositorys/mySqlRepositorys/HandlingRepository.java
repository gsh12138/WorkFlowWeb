package repositorys.mySqlRepositorys;

import entitys.HandlingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tech on 2017/12/22.
 */
@Repository
public interface HandlingRepository extends CrudRepository<HandlingEntity,Integer> {
    List<HandlingEntity> findByBidOrderById(String bid);
}
