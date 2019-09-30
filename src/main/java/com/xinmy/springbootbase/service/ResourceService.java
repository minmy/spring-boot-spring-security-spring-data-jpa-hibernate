package com.xinmy.springbootbase.service;

import com.xinmy.springbootbase.act.vo.ResourceVo;
import com.xinmy.springbootbase.entity.Resource;

/**
 * @author lijianxin
 * @date 2019/9/26 14:45
 * @desc
 */
public interface ResourceService extends CommonService<Resource, ResourceVo, Long> {
    Resource findByUri(String uri);
}
