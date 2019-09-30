package com.xinmy.springbootbase.act.vo;

import com.xinmy.springbootbase.context.IUser;
import com.xinmy.springbootbase.entity.User;
import com.xinmy.springbootbase.helper.BeanUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("系统用户")
public class UserVo implements IUser {

    @ApiModelProperty(value = "ID")
    private Long id;

    @ApiModelProperty(value = "username")
    private String username;

    @ApiModelProperty(value = "password")
    private String password;

    public UserVo() {
    }

    public UserVo(User user) {
        if (null != user) {
            BeanUtils.copyProperties(user, this);
        }
    }

    public User toUser() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        return user;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public void setToken(String token) {

    }

    @Override
    public Long getLogsId() {
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
