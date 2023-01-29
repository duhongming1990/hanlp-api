package com.github.hanlp.api.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TokenizerCountVO {
    private String word;
    private String nature;
    private Integer count;
}
