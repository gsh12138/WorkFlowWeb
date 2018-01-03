package repositorys.mySqlRepositorys;

import entitys.ExceptionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tech on 2017/12/22.
 */
@Repository
public interface ExceptionRepository extends CrudRepository<ExceptionEntity,Integer> {

    ExceptionEntity findByBid(String id);
}
