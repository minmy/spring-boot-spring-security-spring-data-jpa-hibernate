package com.xinmy.springbootbase.service;

import com.xinmy.springbootbase.context.Context;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


/**
 * @desc
 */
@Transactional(readOnly = true)
public interface CommonService<T, V, ID extends Serializable> {
    @Transactional
    T save(Context context, T entity);

    T findOne(ID id);

    @Transactional
    T delete(Context context, ID id);

    Page<T> findByPage(Context context, V example, Pageable pageable);

    List<T> findAll();

}
