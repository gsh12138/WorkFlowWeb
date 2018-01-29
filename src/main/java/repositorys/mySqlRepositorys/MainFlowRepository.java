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

    @Query(value = "SELECT m from MainFlowEntity m where m.flowClazzId=?1 and m.starter=?2 order by m.startDate")
    List<MainFlowEntity> findFlowByClazzIdAndStarter(String clazzid,String starter);

    @Query(value = "SELECT m from MainFlowEntity m where m.flowClazzId=?1 and m.handing=?2 and m.state<>'CLOSE' order by  m.startDate")
    List<MainFlowEntity> findHandling(String clazzid,String handing);
}
