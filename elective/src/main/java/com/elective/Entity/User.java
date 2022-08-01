package com.elective.Entity;

import com.elective.Entity.Base.BaseEntity;
import lombok.Builder;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Data
@Entity
@Table(name = "User")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(name = "name", columnDefinition = "varchar(25) not null")
    private String name;

    @Column(name = "number", columnDefinition = "varchar(11) not null")
    private String number;

    @Column(name = "password", columnDefinition = "varchar(255) not null")
    private String password;

    @Builder.Default
    @Column(name = "role", columnDefinition = "tinyint(2) not null default 1")
    private Short role = 1; // 0 教师 1 学生

    @Builder.Default
    @Column(name = "credit", columnDefinition = "int(11) not null default 0")
    private Integer credit = 0;

    @Builder.Default
    @Column(name = "status", columnDefinition = "tinyint(2) not null default 0")
    private Short status = 1; // 0 不可用 1 可用

}
