package com.blog.vo.params;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Getter
@Setter
@Component
public class PageParam {

    private Integer page = 1;

    private Integer pageSize = 10;
}
