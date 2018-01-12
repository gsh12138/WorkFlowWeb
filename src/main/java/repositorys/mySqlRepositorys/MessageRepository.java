package repositorys.mySqlRepositorys;

import entitys.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<MessageEntity,Integer> {
    List<MessageEntity> findAllByReceiver(String receiver);

    MessageEntity findById(int id);
}
