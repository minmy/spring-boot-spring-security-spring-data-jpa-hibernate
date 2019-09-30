package com.xinmy.springbootbase.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lijianxin
 * @date 2019/9/26 13:36
 * @desc
 */
@Entity
@Table(name = Authority.TABLE_NAME)
public class Authority extends AbstractEntity implements GrantedAuthority {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AUTHORITY";
    public static final String MUST_LOGIN = "MUST_LOGIN";

    /**
     * ID
     */
    @Id
    @TableGenerator(name = TABLE_NAME, table = SEQUENCE_TABLE, pkColumnValue = TABLE_NAME)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
    @Column(name = "ID")
    private Long id;

    /**
     * 权限
     */
    @Column(name = "AUTHORITY", unique = true, nullable = false)
    private String authority;

    /**
     * 中文名称
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 排序权重
     */
    @Column(name = "SORT_WEIGHT", nullable = false)
    private Long sortWeight;

    /**
     * 所属上一级权限
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_SUPER_AUTHORITY")
    private Authority superAuthority;

    /**
     * 子权限集合
     */
    @OneToMany(mappedBy = "superAuthority", fetch = FetchType.LAZY)
    private Set<Authority> subAuthoritys = new HashSet<Authority>();

    /**
     * 拥有该权限的角色集合
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "AUTHORITY_ROLE", joinColumns = @JoinColumn(name = "FK_AUTHORITY"), inverseJoinColumns = @JoinColumn(name = "FK_ROLE"))
    private Set<Role> roles = new HashSet<Role>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getSortWeight() {
        return sortWeight;
    }

    public void setSortWeight(Long sortWeight) {
        this.sortWeight = sortWeight;
    }

    public Authority getSuperAuthority() {
        return superAuthority;
    }

    public void setSuperAuthority(Authority superAuthority) {
        this.superAuthority = superAuthority;
    }

    public Set<Authority> getSubAuthoritys() {
        return subAuthoritys;
    }

    public void setSubAuthoritys(Set<Authority> subAuthoritys) {
        this.subAuthoritys = subAuthoritys;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
