package com.elective.Entity.rpo;


import lombok.*;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class LoginRPO implements Serializable {
    @NotBlank(message = "Number can not be Null")
    private String number;
    @NotBlank(message = "Password can not be Null")
    private String password;

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
