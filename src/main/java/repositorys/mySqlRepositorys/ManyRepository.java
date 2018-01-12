package repositorys.mySqlRepositorys;

import entitys.TestonetomanymanyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManyRepository extends CrudRepository<TestonetomanymanyEntity,Integer> {
}
