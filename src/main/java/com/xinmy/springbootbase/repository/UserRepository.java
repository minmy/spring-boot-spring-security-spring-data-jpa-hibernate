package com.xinmy.springbootbase.repository;

import com.xinmy.springbootbase.entity.User;

/**
 * @author lijianxin
 * @date 2019/9/26 13:48
 * @desc
 */
public interface UserRepository extends BaseRepository<User, Long> {
    User findByUsername(String userName);
}
