package repositorys.mySqlRepositorys;

import entitys.KnowledgehandingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowledgeHandingRepository extends CrudRepository<KnowledgehandingEntity,Integer> {

    List<KnowledgehandingEntity> findAllByBid(String bid);
}
