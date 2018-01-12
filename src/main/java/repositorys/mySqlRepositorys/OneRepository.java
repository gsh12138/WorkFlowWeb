package repositorys.mySqlRepositorys;

import entitys.TestonetomanyoneEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OneRepository extends CrudRepository<TestonetomanyoneEntity,Integer> {

    @Transactional
    TestonetomanyoneEntity findById(int id);
}
