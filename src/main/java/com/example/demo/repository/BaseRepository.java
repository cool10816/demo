package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.demo.model.BaseBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseBean> extends JpaSpecificationExecutor<T> {

}
