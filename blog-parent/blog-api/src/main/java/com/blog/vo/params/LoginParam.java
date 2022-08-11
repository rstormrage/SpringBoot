package com.blog.vo.params;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginParam {

    private String account;

    private String password;

    private String nickname;
}
