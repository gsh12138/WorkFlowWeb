package repositorys.mySqlRepositorys;

import entitys.MessageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity,Integer> {

    @Query(value = "select  m from MessageEntity m where m.receiver=?1 and m.isnew=true ")
    List<MessageEntity> findAllByReceiver(String receiver);

    MessageEntity findById(int id);
}
