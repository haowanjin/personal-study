package com.ddup.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc:
 * @author: wty
 * @create: 2020-09-29 14:09
 */
public class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long id;

    protected String code;

    protected Integer version;

    protected Date createTime;

    protected Date updateTime;

    protected String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
