package com.elective.Entity;

import com.elective.Entity.Base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "Course")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Course extends BaseEntity {

    @Column(name = "name", columnDefinition = "varchar(25) not null")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @Builder.Default
    @Column(name = "credit", columnDefinition = "int(11) not null default 0")
    private Integer credit = 0;

    @JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "begin_time")
    private Timestamp beginTime;

    @JsonFormat(locale="zh", pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "end_time")
    private Timestamp endTime;

    @Builder.Default
    @Column(name = "limit_num", columnDefinition = "int(11) not null default 0")
    private Integer limitNum = 0;

    @Builder.Default
    @Column(name = "selected", columnDefinition = "int(11) not null default 0")
    private Integer selected = 0; // 已选的人数

}
