package repositorys.mySqlRepositorys;

import entitys.ExceptionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by tech on 2017/12/22.
 */
@Repository
public interface ExceptionRepository extends CrudRepository<ExceptionEntity,Integer> {

    ExceptionEntity findByBid(String id);

    @Query(value = "SELECT e from ExceptionEntity e where e.creatdate between ?1 and ?1 order by e.bid desc ")
    List<ExceptionEntity> findMax(Date date);
}
