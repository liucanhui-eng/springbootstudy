package com.model.pro;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private Integer id;

    private String name;

    private Integer age;

    private String sex;

    public void build(Integer id,String name,Integer age,String sex){
        this.setId(id);
        this.setSex(sex);
        this.setAge(age);
        this.setName(name);
    }


}