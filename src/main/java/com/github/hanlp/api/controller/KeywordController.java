package com.github.hanlp.api.controller;

import com.hankcs.hanlp.HanLP;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "关键词提取")
@RestController
@RequestMapping("/hanlp/api")
public class KeywordController {

    @ApiOperation("关键词提取")
    @PostMapping("/extractKeyword")
    public List<String> extractKeyword(@ApiParam("目标文档") @RequestBody String document,
                                       @ApiParam("希望提取几个关键词") @RequestParam(defaultValue = "5") Integer size) {
        return HanLP.extractKeyword(document, size);
    }

}
