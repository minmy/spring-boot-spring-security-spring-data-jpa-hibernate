package com.xinmy.springbootbase.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lijianxin
 * @date 2019/9/26 13:24
 * @desc
 */
@Entity
@Table(name = Role.TABLE_NAME)
public class Role extends AbstractEntity {

    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "ROLE";

    /**
     * ID
     */
    @Id
    @TableGenerator(name = TABLE_NAME, table = SEQUENCE_TABLE, pkColumnValue = TABLE_NAME)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
    @Column(name = "ID")
    private Long id;

    /**
     * 名称
     */
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 拥有的权限集合
     */
    @OrderBy("sortWeight,id")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "AUTHORITY_ROLE", joinColumns = @JoinColumn(name = "FK_ROLE"), inverseJoinColumns = @JoinColumn(name = "FK_AUTHORITY"))
    private Set<Authority> authoritys = new HashSet<Authority>();

    /**
     * 拥有该角色的用户集合
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "FK_ROLE"), inverseJoinColumns = @JoinColumn(name = "FK_USER"))
    private Set<User> users = new HashSet<User>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Authority> getAuthoritys() {
        return authoritys;
    }

    public void setAuthoritys(Set<Authority> authoritys) {
        this.authoritys = authoritys;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
