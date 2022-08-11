package com.blog.dao.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Article {

    public static final int Article_TOP = 1;

    public static final int Article_Common = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String title;

    private String summary;

    private int commentCounts;

    private int viewCounts;

    /**
     * 作者id
     */
    private Long authorId;
    /**
     * 内容id
     */
    private Long bodyId;
    /**
     *类别id
     */
    private Long categoryId;

    /**
     * 置顶
     */
    private int weight = Article_Common;


    /**
     * 创建时间
     */
    @JsonFormat(locale = "us", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;

    @PrePersist
    protected void prePersist() {
        createDate = new Timestamp(new Date().getTime());
    }
}


