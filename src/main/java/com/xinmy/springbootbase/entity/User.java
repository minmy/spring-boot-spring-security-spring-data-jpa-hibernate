package com.xinmy.springbootbase.entity;

import com.xinmy.springbootbase.helper.DateUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lijianxin
 * @date 2019/9/26 13:23
 * @desc
 */
@Entity
@Table(name = User.TABLE_NAME)
public class User extends AbstractEntity implements UserDetails {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "USER";
    public static final Integer ALLOW_LOGIN_ERROR_COUNT = 5;

    /**
     * ID
     */
    @Id
    @TableGenerator(name = TABLE_NAME, table = SEQUENCE_TABLE, pkColumnValue = TABLE_NAME)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
    private Long id;

    private String username;

    private String password;

    /**
     * 帐号禁用
     */
    @Column(name = "DISABLE", nullable = false)
    private boolean disable = false;

    /**
     * 登录错误次数
     */
    @Column(name = "LOGIN_ERROR_COUNT")
    private Integer loginErrorCount;

    /**
     * 登录错误时间
     */
    @Column(name = "LOGIN_ERROR_TIME")
    private Date loginErrorTime;

    /**
     * 拥有的角色集合
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "FK_USER"), inverseJoinColumns = @JoinColumn(name = "FK_ROLE"))
    private Set<Role> roles = new HashSet<Role>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> authoritys = new HashSet<Authority>();
        for (Role role : this.roles) {
            authoritys.addAll(role.getAuthoritys());
        }
        return authoritys;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        int errorCount = this.loginErrorCount == null ? 0 : this.loginErrorCount;
        return !(errorCount >= ALLOW_LOGIN_ERROR_COUNT && DateUtils.isSameDay(this.loginErrorTime, new Date()));
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !this.disable;
    }
}
