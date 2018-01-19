package repositorys.mySqlRepositorys;

import entitys.YcclgroupEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public interface YcclgroupRepository extends CrudRepository<YcclgroupEntity,Integer> {

    @Query(value = "select en from YcclgroupEntity en order by en.id DESC ")
    List<YcclgroupEntity> findmaxid();

    @Transactional
    YcclgroupEntity findByGroupid(String groupid);

    @Transactional
    void deleteByGroupid(String groupid);

    List<YcclgroupEntity> findAll();

    YcclgroupEntity findByGroupname(String groupname);
}
