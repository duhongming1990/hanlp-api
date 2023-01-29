package com.github.hanlp.api.controller;

import com.github.hanlp.api.vo.RecognizeNameVO;
import com.github.hanlp.api.vo.TokenizerCountVO;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

import static java.util.stream.Collectors.*;

@Api(tags = "分词")
@RestController
@RequestMapping("/hanlp/api")
public class TokenizerController {
    @Resource
    private Segment segment;

    @ApiOperation("命名实体识别")
    @PostMapping("/tokenizer/name")
    public RecognizeNameVO tokenizerName(@ApiParam("目标文档") @RequestBody String document) {
        List<Term> terms = segment.seg(document);
        terms = terms.stream().filter(term -> !CoreStopWordDictionary.shouldInclude(term)).collect(toList());
        RecognizeNameVO nameEntity = new RecognizeNameVO();
        Set<String> names = terms.stream().filter(term -> term.nature.startsWith("nr")).map(term -> term.word).collect(toSet());
        nameEntity.setNames(names);
        Set<String> places = terms.stream().filter(term -> term.nature.startsWith("ns")).map(term -> term.word).collect(toSet());
        nameEntity.setPlaces(places);
        Set<String> organizations = terms.stream().filter(term -> term.nature.startsWith("nt")).map(term -> term.word).collect(toSet());
        nameEntity.setOrganizations(organizations);
        return nameEntity;
    }

    @ApiOperation("分词计数")
    @PostMapping("/tokenizer/counts")
    public List<TokenizerCountVO> tokenizerCounts(@ApiParam("目标文档") @RequestBody String document) {
        final List<Term> terms = NotionalTokenizer.segment(document);
        final Map<String, Integer> wordCountMap = terms.stream().collect(groupingBy(term -> term.word + "|" + term.nature, collectingAndThen(toList(), ts -> ts.size())));
        List<TokenizerCountVO> tokenizerCountVOS = new LinkedList<>();
        wordCountMap.forEach((word, count) -> {
            TokenizerCountVO tokenizerCountVO = new TokenizerCountVO();
            tokenizerCountVO.setWord(word.split("\\|")[0]);
            tokenizerCountVO.setNature(word.split("\\|")[1]);
            tokenizerCountVO.setCount(count);
            tokenizerCountVOS.add(tokenizerCountVO);
        });
        return tokenizerCountVOS.stream().sorted(Comparator.comparing(TokenizerCountVO::getCount).reversed()).collect(toList());
    }
}
