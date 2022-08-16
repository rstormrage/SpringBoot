package com.blog.service;

import com.blog.vo.params.CommentParam;
import com.blog.vo.params.Result;

public interface CommentsService {


    Result commentsByArticleId(Long articleId);

    Result comment(CommentParam commentParam);
}