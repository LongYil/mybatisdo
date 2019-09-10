package com.longdatech.mybatisdo.po;

import lombok.Data;

import java.util.Date;

@Data
public class Blog {
    private String id;
    private String title;
    private String content;
    private Date createTime;

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        System.out.println("对id执行了赋值");
//        this.id = id;
//    }
//
//    public String getTitle() {
//        System.out.println("获取id的值");
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
}
