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
    @Query(value = "select * from t_user where t_age > ?1",nativeQuery = true)
    public List<UserEntity> nativeQuery(int age);

    //根据用户名、密码删除一条数据
    @Modifying
    @Query(value = "delete from t_user where t_name = ?1 and t_pwd = ?2",nativeQuery = true)
    public void deleteQuery(String name,String pwd);

    @Query(value = "select * from t_user order by t_id  limit  ?1 , ? 2 ",nativeQuery = true)
    public List<UserEntity> pageQuery(int start , int row);


}
