package com.xinmy.springbootbase.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lijianxin
 * @date 2019/9/26 13:32
 * @desc
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractEntity.class);
    public static final String SEQUENCE_TABLE = "SEQUENCE";
    public static final String DISCRIMINATOR_COLUMN = "TYPE";

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime = new Date();

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
