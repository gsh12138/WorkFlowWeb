package repositorys.mySqlRepositorys;

import entitys.MainFlowEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tech on 2017/12/25.
 * 主流程数据仓库
 */
@Repository
public interface MainFlowRepository extends CrudRepository<MainFlowEntity,Integer> {

    MainFlowEntity findByFlowId(String id);

    MainFlowEntity findByBid(String id);

    @Query(value = "SELECT m from MainFlowEntity m where m.flowId like ?1 order by m.flowId desc ")
    List<MainFlowEntity> findMaxid(String did);
}
