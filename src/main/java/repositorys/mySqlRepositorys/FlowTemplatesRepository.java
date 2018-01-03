package repositorys.mySqlRepositorys;

import entitys.FlowTemplatesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tech on 2017/12/25.
 */
@Repository
public interface FlowTemplatesRepository extends CrudRepository<FlowTemplatesEntity,Integer> {

    List<FlowTemplatesEntity> findByFormClazzId(String clazzid);
}
