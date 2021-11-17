package com.ddup.cloud.entity;

import lombok.Data;

@Data
//@Document(indexName = "EsUser")
public class EsUser {
    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private String addr;

    public EsUser() {
    }

    public EsUser(Integer id, String name, String sex, Integer age, String addr) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.addr = addr;
    }
    
    @Override
    public String toString() {
        return "EsUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", addr='" + addr + '\'' +
                '}';
    }
}
