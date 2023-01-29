package com.github.hanlp.api.controller;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "汉字转拼音")
@RestController
@RequestMapping("/hanlp/api")
public class PinyinController {

    @ApiOperation("汉字转拼音")
    @PostMapping("/convertToPinyin")
    public List<Pinyin> convertToPinyinList(@ApiParam("目标文档") @RequestBody String document) {
        return HanLP.convertToPinyinList(document);
    }

}
