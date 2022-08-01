package com.elective.Entity.dto;

import com.elective.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class UserDTOFactory {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserDTO {
        private Integer id;
        private String name;
        private String number;
        @Builder.Default
        private Short role = 1; // 0 教师 1 学生
        @Builder.Default
        private Integer credit = 0;
        @Builder.Default
        private Short status = 1; // 0 不可用 1 可用
    }

    public UserDTO pojoToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setNumber(user.getNumber());
        userDTO.setRole(user.getRole());
        userDTO.setCredit(user.getCredit());
        userDTO.setStatus(user.getStatus());
        return userDTO;
    }
}
