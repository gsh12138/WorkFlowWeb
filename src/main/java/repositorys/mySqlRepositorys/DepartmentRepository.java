package repositorys.mySqlRepositorys;

import entitys.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tech on 2017/12/21.
 */
@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity,Integer> {

    DepartmentEntity findByDepartmentid(String id);

    DepartmentEntity findByDepartmentname(String name);

}
