package com.blog.dao.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    @JsonSerialize(using = ToStringSerializer.class)
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


}
