package repositorys.mySqlRepositorys;


import entitys.KnowledgeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface KnowledgeRepository extends CrudRepository<KnowledgeEntity,Integer> {

    @Query(value = "SELECT k from KnowledgeEntity k where k.submitdate between ?1 and ?1 order by k.bid desc ")
    List<KnowledgeEntity> findMax(Date date);

    KnowledgeEntity findByBid(String bid);

    @Query(value = "SELECT k from KnowledgeEntity k ORDER BY k.id desc ")
    List<KnowledgeEntity> findAllDec(Pageable pageable);


}
