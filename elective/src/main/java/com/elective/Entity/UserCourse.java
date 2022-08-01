package com.elective.Entity;

import com.elective.Entity.Base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
@Data
@Entity
@Table(name = "UserCourse")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserCourse extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @Builder.Default
    @Column(name = "grade", columnDefinition = "double not null default 0")
    private Double grade = 0d; // 成绩

    @Builder.Default
    @Column(name = "status", columnDefinition = "tinyint(2) not null default 0")
    private Short status = 0; // 0 未结算 1 通过 2 未通过
}
