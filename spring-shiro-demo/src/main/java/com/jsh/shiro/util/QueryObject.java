package com.jsh.shiro.util;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueryObject {
    private int currentPage = 1;
    private int pageSize = 10;

}
