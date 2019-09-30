package com.xinmy.springbootbase.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lijianxin
 * @date 2019/9/26 14:43
 * @desc
 */
@Entity
@Table(name = Resource.TABLE_NAME)
public class Resource extends AbstractEntity {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "RESOURCE";

    /**
     * ID
     */
    @Id
    @TableGenerator(name = TABLE_NAME, table = SEQUENCE_TABLE, pkColumnValue = TABLE_NAME)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_NAME)
    @Column(name = "ID")
    private Long id;

    /** 资源地址 */
    @Column(name = "URI", unique = true, nullable = false)
    private String uri;

    /** 资源名称 */
    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    /** 需要的接口角色集合 */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ROLE_RESOURCE", joinColumns = @JoinColumn(name = "FK_API_RESOURCE"), inverseJoinColumns = @JoinColumn(name = "FK_API_ROLE"))
    private Set<Role> roles = new HashSet<Role>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
