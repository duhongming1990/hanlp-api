package com.github.hanlp.api.controller;

import com.hankcs.hanlp.HanLP;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@Api(tags = "自动摘要")
@RestController
@RequestMapping("/hanlp/api")
public class SummaryController {

    @ApiOperation("获取摘要")
    @PostMapping("/getSummary")
    public String getSummary(@ApiParam("目标文档") @RequestBody String document,
                             @ApiParam("需要摘要的长度") @RequestParam(defaultValue = "100") Integer maxLength) {
        return HanLP.getSummary(document, maxLength);
    }

}
