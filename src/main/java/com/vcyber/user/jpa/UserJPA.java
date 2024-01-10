package com.vcyber.user.jpa;

import com.vcyber.user.base.BaseRepository;
import com.vcyber.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ========================
 * Created with IntelliJ IDEA.
 *
 * Date：2018/2/2
 * Time：23:08
 *
 * ========================
 */
@Transactional
public interface UserJPA extends BaseRepository<UserEntity,Long>
{
    //查询大于20岁的用户
    @Query(value = "select * from t_user where t_age > :age",nativeQuery = true)
    public List<UserEntity> nativeQuery(int age);

    //根据用户名、密码删除一条数据
    @Modifying
    @Query(value = "delete from t_user where t_name = :name and t_pwd = :pwd",nativeQuery = true)
    public void deleteQuery(String name,String pwd);

    @Query(value = "select * from t_user order by t_id  limit  :start , :row ",nativeQuery = true)
    public List<UserEntity> pageQuery(int start , int row);

    @Query(value = "select * from user where id = :id",nativeQuery = true)
    UserEntity findOne(Long id);

    @Query(value = "delete from user where id = :userId",nativeQuery = true)
    void delete(Long userId);
}
