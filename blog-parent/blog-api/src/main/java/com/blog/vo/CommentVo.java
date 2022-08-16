package com.blog.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class CommentVo  {

    private Long id;

    private UserVo author;

    private String content;

    private List<CommentVo> childrens;

    private Timestamp createDate;

    private Integer level;

    private UserVo toUser;
}