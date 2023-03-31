package com.github.hanlp.api.controller;

import com.github.hanlp.api.vo.RecognizeNameVO;
import com.github.hanlp.api.vo.TokenizerCountVO;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
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

    @ApiOperation("命名实体识别 NamedEntityRecognition ner")
    @PostMapping("/tokenizer/name")
    public RecognizeNameVO tokenizerName(@ApiParam("目标文档") @RequestBody String document) {
        final List<String> sentences = splitSentence(document);
        RecognizeNameVO nameEntity = new RecognizeNameVO();
        for (String sentence : sentences) {
//            List<Term> terms = segment.seg(sentence);
            List<Term> terms = NLPTokenizer.segment(sentence);
            terms = terms.stream().filter(CoreStopWordDictionary::shouldInclude).collect(toList());
            Set<String> names = terms.stream().filter(term -> term.nature.startsWith("nr")).map(term -> term.word).collect(toSet());
            nameEntity.addAllNames(names);
            Set<String> places = terms.stream().filter(term -> term.nature.startsWith("ns")).map(term -> term.word).collect(toSet());
            nameEntity.addAllPlaces(places);
            Set<String> organizations = terms.stream().filter(term -> term.nature.startsWith("nt")).map(term -> term.word).collect(toSet());
            nameEntity.addAllOrganizations(organizations);
        }
        return nameEntity;
    }

    @ApiOperation("分词计数 词性标注 Part-of-speech Tagging pos")
    @PostMapping("/tokenizer/counts")
    public List<TokenizerCountVO> tokenizerCounts(@ApiParam("目标文档") @RequestBody String document) {
        List<Term> terms = NLPTokenizer.segment(document);
        terms = terms.stream().filter(CoreStopWordDictionary::shouldInclude).collect(toList());
        final Map<String, Integer> wordCountMap = terms.stream().collect(groupingBy(term -> term.word, collectingAndThen(toList(), List::size)));
        List<TokenizerCountVO> tokenizerCountVOS = new LinkedList<>();
        wordCountMap.forEach((word, count) -> {
            TokenizerCountVO tokenizerCountVO = new TokenizerCountVO();
            tokenizerCountVO.setWord(word);
            tokenizerCountVO.setCount(count);
            tokenizerCountVOS.add(tokenizerCountVO);
        });
        return tokenizerCountVOS.stream().sorted(Comparator.comparing(TokenizerCountVO::getCount).reversed()).collect(toList());
    }

    @ApiOperation("分词计数 词性标注 Part-of-speech Tagging pos")
    @PostMapping("/tokenizer/nature/counts")
    public List<TokenizerCountVO> tokenizerNatureCounts(@ApiParam("目标文档") @RequestBody String document) {
        List<Term> terms = NLPTokenizer.segment(document);
        terms = terms.stream().filter(CoreStopWordDictionary::shouldInclude).collect(toList());
        final Map<String, Integer> wordCountMap = terms.stream().collect(groupingBy(term -> term.word + "|" + term.nature, collectingAndThen(toList(), List::size)));
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

    /**
     * 将文章分割为句子
     *
     * @param document           待分割的文档
     * @param sentence_separator 句子分隔符，正则表达式，如：   [。:？?！!；;]
     * @return
     */
    static List<String> splitSentence(String document, String sentence_separator) {
        List<String> sentences = new ArrayList<String>();
        for (String line : document.split("[\r\n]")) {
            line = line.trim();
            if (line.length() == 0) continue;
            for (String sent : line.split(sentence_separator))        // [，,。:：“”？?！!；;]
            {
                sent = sent.trim();
                if (sent.length() == 0) continue;
                sentences.add(sent);
            }
        }

        return sentences;
    }

    /**
     * 将文章分割为句子
     * 默认句子分隔符为：[，,。:：“”？?！!；;]
     *
     * @param document
     * @return
     */
    final static String default_sentence_separator = "[，,。:：“”？?！!；;]";

    static List<String> splitSentence(String document) {
        return splitSentence(document, default_sentence_separator);
    }
}
