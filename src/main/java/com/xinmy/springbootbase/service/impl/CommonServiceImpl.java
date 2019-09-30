package com.xinmy.springbootbase.service.impl;

import com.xinmy.springbootbase.context.Context;
import com.xinmy.springbootbase.repository.BaseRepository;
import com.xinmy.springbootbase.service.CommonService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @desc
 */
public abstract class CommonServiceImpl<T, V, ID extends Serializable> implements CommonService<T, V, ID> {
    @Override
    public T save(final Context context, final T entity) {
        return this.currentJpaRepository().save(entity);
    }

    protected abstract BaseRepository<T, ID> currentJpaRepository();

    @Override
    public T findOne(final ID id) {
        return this.currentJpaRepository().findOne(id);
    }

    @Override
    public T delete(final Context context, final ID id) {
        T entity = this.findOne(id);
        if (null != entity) {
            this.currentJpaRepository().delete(entity);
        }
        return entity;
    }

    @Override
    public List<T> findAll() {
        List<T> list = currentJpaRepository().findAll();
        if (null == list) {
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public Page<T> findByPage(Context context, V example, Pageable pageable) {
        return currentJpaRepository().findAll(pageable);
    }
}
