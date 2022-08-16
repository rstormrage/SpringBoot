package com.blog.dao.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleBody {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String content;

    private String contentHtml;

    private Long articleId;
}