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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String account;

    private Integer admin;

    private String avatar;

    @JsonFormat(locale = "us", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createDate;

    private Boolean deleted;

    private String email;

    @JsonFormat(locale = "us", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private String password;

    private String salt;

    private String status;

    @PrePersist
    protected void prePersist() {
        this.deleted = false; // 0 false 1 true 被删除了
        if (createDate == null) {
            createDate = new Timestamp(new Date().getTime());
        }

        if (lastLogin == null) {
            lastLogin = new Timestamp(new Date().getTime());
        }
    }

}
