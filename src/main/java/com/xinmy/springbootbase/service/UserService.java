package com.xinmy.springbootbase.service;

import com.xinmy.springbootbase.act.vo.UserVo;

import com.xinmy.springbootbase.context.Context;
import com.xinmy.springbootbase.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author lijianxin
 * @date 2019/9/26 13:46
 * @desc
 */

public interface UserService extends UserDetailsService, CommonService<User, UserVo, Long> {
    @Override
    Page<User> findByPage(Context context, UserVo example, Pageable pageable);
}
