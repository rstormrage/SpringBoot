package com.blog.dao.dos;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Archives {
    private Integer year;

    private Integer month;

    private Long count;
}
