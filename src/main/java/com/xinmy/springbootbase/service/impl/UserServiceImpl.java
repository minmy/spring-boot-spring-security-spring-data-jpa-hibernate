package com.xinmy.springbootbase.service.impl;

import com.xinmy.springbootbase.act.vo.UserVo;
import com.xinmy.springbootbase.context.Context;
import com.xinmy.springbootbase.entity.User;
import com.xinmy.springbootbase.helper.StringUtils;
import com.xinmy.springbootbase.repository.BaseRepository;
import com.xinmy.springbootbase.repository.UserRepository;
import com.xinmy.springbootbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lijianxin
 * @date 2019/9/26 14:46
 * @desc
 */
@Service
public class UserServiceImpl extends CommonServiceImpl<User, UserVo, Long> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected BaseRepository<User, Long> currentJpaRepository() {
        return userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user == null) {
            throw new RuntimeException("找不到用户");
        }
        return user;
    }

    /**
     * 分页查询
     */
    @Override
    public Page<User> findByPage(Context context, UserVo example, Pageable pageable) {
        return userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                String name = example.getName();
                if (StringUtils.isNotEmpty(name)) {
                    predicates.add(cb.like(root.get("username"), StringUtils.wrapByPercent(name.trim())));
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        }, pageable);
    }

}
