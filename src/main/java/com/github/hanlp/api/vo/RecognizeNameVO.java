package com.github.hanlp.api.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecognizeNameVO implements Serializable {
    Set<String> names;
    Set<String> places;
    Set<String> organizations;
}
