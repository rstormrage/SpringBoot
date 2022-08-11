package com.blog.service;

import com.blog.vo.params.ArticleParam;
import com.blog.vo.params.Result;
import com.blog.vo.params.PageParam;

public interface ArticleService {
    /**
     * 分页查询实现文章列表
     * @param pageParam
     * @return
     */
    Result listArticle(PageParam pageParam);

    /**
     * 最热文章
     * @param limit
     * @return
     */
    Result hotArticle(int limit);

    /**
     * 最新文章
     * @param limit
     * @return
     */
    Result newArticles(int limit);

    /**
     * 文章归档
     * @return
     */
    Result listArchives();

    /**
     * 查询文章详情
     * @param articleId
     * @return
     */
    Result findArticleById(Long articleId);

    /**
     * 发布文章
     * @param articleParam
     * @return
     */
//    Result publish(ArticleParam articleParam);

}