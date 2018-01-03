package repositorys.mySqlRepositorys;

import entitys.FlowDetailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tech on 2017/12/25.
 */
@Repository
public interface FlowDetailRepository extends CrudRepository<FlowDetailEntity,Integer> {

    List<FlowDetailEntity> findByFlowId(String id);
}
