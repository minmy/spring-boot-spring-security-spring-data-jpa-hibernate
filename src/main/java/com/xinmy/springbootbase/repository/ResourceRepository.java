package com.xinmy.springbootbase.repository;

import com.xinmy.springbootbase.entity.Resource;

/**
 * @author lijianxin
 * @date 2019/9/26 14:56
 * @desc
 */
public interface ResourceRepository extends BaseRepository<Resource, Long> {
    Resource findByUri(String url);
}
