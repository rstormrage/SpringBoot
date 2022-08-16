package com.blog.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.blog.dao.mapper.ArticleBodyMapper;
import com.blog.dao.mapper.ArticleTagMapper;
import com.blog.dao.pojo.ArticleBody;
import com.blog.dao.pojo.ArticleTag;
import com.blog.dao.pojo.User;
import com.blog.service.*;
import com.blog.dao.dos.Archives;
import com.blog.dao.mapper.ArticleMapper;
import com.blog.dao.pojo.Article;
import com.blog.utils.UserThreadLocal;
import com.blog.vo.ArticleBodyVo;
import com.blog.vo.CategoryVo;
import com.blog.vo.TagVo;
import com.blog.vo.params.ArticleParam;
import com.blog.vo.params.Result;
import com.blog.vo.ArticleVo;


import com.blog.vo.params.PageParam;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @Autowired
    private ThreadService threadService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private ArticleBodyMapper articleBodyMapper;



    @Override
    public Result listArticle(PageParam pageParam) {
        /**
         * 分页查询article数据库表
         */
        Page<Article> page = new Page<>(pageParam.getPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 置顶排序
        // order by create_date desc
        queryWrapper.orderByDesc(Article::getWeight,Article::getCreateDate);
        Page<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        List<Article> records = articlePage.getRecords();
        List<ArticleVo> articleVoList = copyList(records,true,true);
        return Result.success(articleVoList);
    }

    /**
     * 最热文章
     * @param limit
     * @return
     */
    @Override
    public Result hotArticle(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getViewCounts); // 根据浏览量排序
        queryWrapper.select(Article::getId,Article::getTitle);
        queryWrapper.last("limit " + limit);
        // select id,title from article order by view_count desc limit 5
        List<Article> articles = articleMapper.selectList(queryWrapper);
        return Result.success(copyList(articles,false,false));
    }

    /**
     * 最新文章
     * @param limit
     * @return
     */
    @Override
    public Result newArticles(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getCreateDate); // 根据创建时间排序
        queryWrapper.select(Article::getId,Article::getTitle);
        queryWrapper.last("limit " + limit);
        // select id,title from article order by create_date desc limit 5
        List<Article> articles = articleMapper.selectList(queryWrapper);
        return Result.success(copyList(articles,false,false));
    }

    /**
     * 文章归档
     * @return
     */
    @Override
    public Result listArchives() {
        List<Archives> archivesList = articleMapper.listArchives();
        return Result.success(archivesList);
    }

    /**
     * 查询文章详情
     * @param articleId
     * @return
     */
    @Override
    public Result findArticleById(Long articleId) {
        /**
         * 1、根据id查询 文章信息
         * 2、根据bodyId和categoryid 去关联查询
         */
        Article article = articleMapper.selectById(articleId);
        ArticleVo articleVo = copy(article,true,true,true,true);
        // 查看完文章之后，本应该直接返回数据，此时做一个更新操作，更新时加入写锁，阻塞其他读操作，性能会比较低
        // 更新 增加了此次接口的 耗时 */优化* 如果一旦更新出现问题 不能影响查看文章的操作
        // 线程池 可以把更新操作 扔到线程池中去执行 和主线程不相关了
        threadService.updateArticleViewCount(articleMapper,article);
        return Result.success(articleVo);
    }
//
//
//
//    /**
//     * 发布文章
//     * @param articleParam
//     * @return
//     */
//    @Override
//    public Result publish(ArticleParam articleParam) {
//        // 从线程中取到用户
//        User sysUser = UserThreadLocal.get();
//        /**
//         * 1、发布文章 构建Article对象
//         * 2、作者id 当前登录用户
//         * 3、标签 要将标签加入列 关联到表中
//         * 4、body内容存储 article bodyId
//         */
//        Article article = new Article();
//        article.setAuthorId(sysUser.getId());
//        article.setWeight(Article.Article_Common);
//        article.setViewCounts(0);
//        article.setCommentCounts(0);
////        article.setCreateDate(System.currentTimeMillis());
//        article.setTitle(articleParam.getTitle());
//        article.setSummary(articleParam.getSummary());
//        //article.setCategoryId(articleParam.getCategory().getId());
//
//
//        // 插入之后，会生成一个文章id
//        this.articleMapper.insert(article); // insert后主键会自动set到实体id字段
//        // tag 根据标签的内容关联表
//        List<TagVo> tags = articleParam.getTags();
//        if (tags != null) {
//            for (TagVo tag : tags) {
//                Long articleId = article.getId();
//                ArticleTag articleTag = new ArticleTag();
//                articleTag.setTagId(tag.getId());
//                articleTag.setArticleId(articleId);
//                articleTagMapper.insert(articleTag);
//            }
//            // body
//        }
//        ArticleBody articleBody = new ArticleBody();
//        articleBody.setArticleId(article.getId());
//        articleBody.setContent(articleParam.getBody().getContent());
//        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
//        articleBodyMapper.insert(articleBody);  // 插入之后才会有bodyId
//
//        article.setBodyId(articleBody.getId());
//        articleMapper.updateById(article); // 将数据更新
//        // "data": {"id":12232323} 返回类型
//        Map<String, String> map = new HashMap<>();
//        map.put("id",article.getId().toString());
//        return Result.success(map);
//    }
//
//
    /**
     * 通过id查看文章内容详情
     * @param bodyId
     * @return
     */
    private ArticleBodyVo findArticleBodyById(Long bodyId) {
        ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }

    private List<ArticleVo> copyList(List<Article> records,boolean isTag,boolean isAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record,isTag,isAuthor,false,false));
        }
        return articleVoList;
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor,boolean isBody) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record,isTag,isAuthor,isBody,false));
        }
        return articleVoList;
    }

    private List<ArticleVo> copyList(List<Article> records,boolean isTag,boolean isAuthor,boolean isBody,boolean isCategory) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record,isTag,isAuthor,isBody,isCategory));
        }
        return articleVoList;
    }

    private ArticleVo copy(Article article,boolean isTag,boolean isAuthor,boolean isBody,boolean isCategory) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);
        // 将Long类型的时间转化为string类型
        //articleVo.setCreateDate(Timestamp.valueOf(new DateTime(article.getCreateDate()).toString("yyyy-mm-dd HH:mm")));
        if (isTag) {
            Long articleId = article.getId();
            articleVo.setTags(tagService.findTagsByArticleId(articleId));
        }
        if (isAuthor) {
            Long authorId = article.getAuthorId();
            articleVo.setAuthor(userService.findUserById(authorId).getNickname());
        }
        if (isBody) {
            Long bodyId = article.getBodyId();
            articleVo.setBody(findArticleBodyById(bodyId));
        }
        if (isCategory) {
            Long categoryId = article.getCategoryId();
            articleVo.setCategory(categoryService.findCategoryById(categoryId));
        }
        return articleVo;
    }


}
