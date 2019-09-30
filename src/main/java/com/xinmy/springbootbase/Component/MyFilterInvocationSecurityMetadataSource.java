package com.xinmy.springbootbase.Component;

import com.xinmy.springbootbase.entity.Authority;
import com.xinmy.springbootbase.entity.Resource;
import com.xinmy.springbootbase.entity.Role;
import com.xinmy.springbootbase.service.ResourceService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lijianxin
 * @date 2019/9/26 14:31
 * @desc
 */
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ResourceService resourceService;

    @Override
    //接收用户请求的地址，返回访问该地址需要的所有权限
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //得到用户的请求地址,控制台输出一下
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        System.out.println("用户请求的地址是：" + requestUrl);

        //如果登录页面就不需要权限
        if ("/login".equals(requestUrl)) {
            return null;
        }

        Resource resource = resourceService.findByUri(requestUrl);

        //如果没有匹配的url则说明大家都可以访问
        if (resource == null) {
            return SecurityConfig.createList(Authority.MUST_LOGIN);
        }
        Set<String> authoritys = new HashSet<>();
        resource.getRoles().forEach(r ->
                authoritys.addAll(r.getAuthoritys().stream().map(Authority::getAuthority).collect(Collectors.toSet())));
        if (CollectionUtils.isEmpty(authoritys)) {
            authoritys.add(Authority.MUST_LOGIN);
        }
        //将resource所需要到的roles按框架要求封装返回
        return SecurityConfig.createList(authoritys.toArray(new String[authoritys.size()]));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}