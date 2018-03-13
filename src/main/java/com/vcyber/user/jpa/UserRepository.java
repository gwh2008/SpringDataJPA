package com.vcyber.user.jpa;

import com.vcyber.user.entity.User;
import com.vcyber.user.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends BaseRepository<User, Long> {

    @Query(value = "select * from user where id = ?1",nativeQuery = true)
    User findById(long id);

    @Query(value = "delete from user where id = ?1",nativeQuery = true)
    Long deleteById(Long id);
}