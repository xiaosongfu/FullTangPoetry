package com.fuxiaosong.fengya.common.model;

/**
 * Created by fuxiaosong on 16/8/30.
 */
public class PoetryVO {

//    `id` int(11) NOT NULL AUTO_INCREMENT,
//    `poet_id` int(11) DEFAULT NULL,
//    `content` text COLLATE utf8_unicode_ci,
//    `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
//    `created_at` datetime DEFAULT NULL,
//    `updated_at` datetime DEFAULT NULL,


    //id
    public String id;

    //诗人 ID
    public String poetId;

    //内容
    public String content;

    //诗的标题
    public String title;

    //创建时间
    public String createdAt;

    //最后更新时间
    public String updatedAt;


    public PoetryVO(){}

    public PoetryVO(String id , String title){
        this.id = id;
        this.title = title;
    }

    public PoetryVO(String id , String title , String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public PoetryVO(String id , String title , String content , String createdAt , String updatedAt){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
