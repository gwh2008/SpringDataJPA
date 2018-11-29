package com.vcyber.user.service;

import com.vcyber.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {

    public List<User> getUserList();

    //分页查询
    Page<User> findAll(Pageable pageable);

    public User findUserById(long id);

    public void save(User user);

    public void edit(User user);

    public void delete(long id);


}
