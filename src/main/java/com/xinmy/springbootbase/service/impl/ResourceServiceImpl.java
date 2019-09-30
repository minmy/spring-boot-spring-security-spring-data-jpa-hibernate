package com.xinmy.springbootbase.service.impl;

import com.xinmy.springbootbase.act.vo.ResourceVo;
import com.xinmy.springbootbase.entity.Resource;
import com.xinmy.springbootbase.repository.BaseRepository;
import com.xinmy.springbootbase.repository.ResourceRepository;
import com.xinmy.springbootbase.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lijianxin
 * @date 2019/9/26 14:55
 * @desc
 */
@Service
public class ResourceServiceImpl extends CommonServiceImpl<Resource, ResourceVo, Long> implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Override
    protected BaseRepository<Resource, Long> currentJpaRepository() {
        return resourceRepository;
    }

    @Override
    public Resource findByUri(String uri) {
        return resourceRepository.findByUri(uri);
    }
}
