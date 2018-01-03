package repositorys.splserverRepositorys;

import entitys.GenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tech on 2017/12/19.
 */
@Repository
public interface GenRepository extends JpaRepository<GenEntity,String> {
    GenEntity findByUsername(String username);
}
