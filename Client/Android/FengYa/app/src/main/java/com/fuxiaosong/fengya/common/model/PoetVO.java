package com.fuxiaosong.fengya.common.model;

/**
 * Created by fuxiaosong on 16/8/30.
 */
public class PoetVO {

//    `id` int(11) NOT NULL AUTO_INCREMENT,
//    `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
//    `created_at` datetime DEFAULT NULL,
//    `updated_at` datetime DEFAULT NULL,

    public String id;
    public String name;
    public String createdAt;
    public String updatedAt;

    public PoetVO(){}
    public PoetVO(String id , String name){
        this.id = id;
        this.name = name;
    }
    public PoetVO(String id , String name , String createdAt , String updatedAt){
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
