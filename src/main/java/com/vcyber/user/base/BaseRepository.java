package com.vcyber.user.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * ========================
 * Created with IntelliJ IDEA.
 *
 * Date：2018/2/2
 * Time：9:40
 *
 * ========================
 */
@NoRepositoryBean
public interface BaseRepository<T,PK extends Serializable> extends JpaRepository<T,PK>, JpaSpecificationExecutor<T>,
        Serializable
{
}
