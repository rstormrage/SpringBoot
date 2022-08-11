package com.blog.controller;


import com.blog.service.ArticleService;
import com.blog.vo.params.ArticleParam;
import com.blog.vo.params.Result;
import com.blog.vo.params.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/articles")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    /**
     * index List of article
     * @param //pageParam
     */
//    @GetMapping("{currentPage}/{pageSize}")
//    public Result listArticle(@PathVariable int currentPage, @PathVariable int pageSize){
//
//      return articleService.listArticle(currentPage, pageSize);
//    }


    /**
     * 首页 文章列表
     * @param pageParam
     * @return
     */
    @PostMapping
    public Result listArticle(@RequestBody PageParam pageParam){
        return articleService.listArticle(pageParam);
    }

    /**
     * 首页 最热文章
     * @return
     */
    @GetMapping("/hot")
    public Result hostArticle(){
        int limit = 5;
        return articleService.hotArticle(limit);
    }

    /**
     * 首页 最新文章
     * @return
     */
    @GetMapping("/new")
    public Result newArticle(){
        int limit = 5;
        return articleService.newArticles(limit);
    }

    /**
     * 首页 文章归档
     * @return
     */
    @GetMapping("/listArchives")
    public Result listArchives(){
        return articleService.listArchives();
    }

    @GetMapping("/view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId) {
        return articleService.findArticleById(articleId);
    }

    /**
     * 发布文章
     */
//    @PostMapping("/publish")
//    public Result publish(@RequestBody ArticleParam articleParam) {
//        return articleService.publish(articleParam);
//    }
}
