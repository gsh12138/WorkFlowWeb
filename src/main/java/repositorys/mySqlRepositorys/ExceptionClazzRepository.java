package repositorys.mySqlRepositorys;

import entitys.ExceptionclazzEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExceptionClazzRepository extends CrudRepository<ExceptionclazzEntity,Integer> {
    List<ExceptionclazzEntity> findAll();
    ExceptionclazzEntity findByName(String name);
}
