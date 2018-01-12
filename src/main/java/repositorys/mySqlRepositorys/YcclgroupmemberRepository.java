package repositorys.mySqlRepositorys;

import entitys.YcclgroupmemberEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface YcclgroupmemberRepository extends CrudRepository<YcclgroupmemberEntity,Integer> {

    List<YcclgroupmemberEntity> findAllByMemberid(String memberid);

    List<YcclgroupmemberEntity> findAllByGroupid(String groupid);


    void deleteByGroupidAndMemberid(String groupid,String memberid);

    void deleteAllByGroupid(String groupid);
}
