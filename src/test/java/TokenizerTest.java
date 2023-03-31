import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;

/**
 * @author duhongming
 * @email duhongming@lixiang.com
 * @date 2023/2/13 13:44
 * @description HanLP.segment其实是对StandardTokenizer.segment的包装
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TokenizerTest {
    @Test
    @Order(1)
    @DisplayName("1.第一个Demo")
    public void testSegment() {
        final List<Term> terms = HanLP.segment("你好，欢迎使用HanLP汉语处理包！");
        System.out.println(terms);
    }

    @Test
    @Order(2)
    @DisplayName("2.标准分词")
    public void testStandardTokenizer() {
        List<Term> termList = StandardTokenizer.segment("商品和服务");
        System.out.println(termList);
    }

    @Test
    @Order(3)
    @DisplayName("3.NLP分词(建议)")
    public void testNLPTokenizer() {
        System.out.println(NLPTokenizer.segment("我新造一个词叫幻想乡你能识别并标注正确词性吗？"));
        // 注意观察下面两个“希望”的词性、两个“晚霞”的词性
        System.out.println(NLPTokenizer.analyze("我的希望是希望张晚霞的背影被晚霞映红").translateLabels());
        System.out.println(NLPTokenizer.analyze("支援臺灣正體香港繁體：微软公司於1975年由比爾·蓋茲和保羅·艾倫創立。"));
    }

    @Test
    @Order(4)
    @DisplayName("4.索引分词")
    public void testIndexTokenizer() {
        List<Term> termList = IndexTokenizer.segment("主副食品");
        for (Term term : termList) {
            System.out.println(term + " [" + term.offset + ":" + (term.offset + term.word.length()) + "]");
        }
    }

    @Test
    @Order(5)
    @DisplayName("5.N-最短路径分词")
    public void testNShortSegment() {
        Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        String[] testCase = new String[]{
                "今天，刘志军案的关键人物,山西女商人丁书苗在市二中院出庭受审。",
                "刘喜杰石国祥会见吴亚琴先进事迹报告团成员",
        };
        for (String sentence : testCase) {
            System.out.println("N-最短分词：" + nShortSegment.seg(sentence) + "\n最短路分词：" + shortestSegment.seg(sentence));
        }
    }

    @Test
    @Order(6)
    @DisplayName("6.CRF分词")
    public void testCRFLexicalAnalyzer() throws IOException {
        CRFLexicalAnalyzer analyzer = new CRFLexicalAnalyzer();
        String[] tests = new String[]{
                "商品和服务",
                "上海华安工业（集团）公司董事长谭旭光和秘书胡花蕊来到美国纽约现代艺术博物馆参观",
                "微软公司於1975年由比爾·蓋茲和保羅·艾倫創立，18年啟動以智慧雲端、前端為導向的大改組。" // 支持繁体中文
        };
        for (String sentence : tests) {
            System.out.println(analyzer.analyze(sentence));
        }
    }

    @Test
    @Order(7)
    @DisplayName("7.极速词典分词")
    public void testSpeedTokenizer() {
        String text = "江西鄱阳湖干枯，中国最大淡水湖变成大草原";
        System.out.println(SpeedTokenizer.segment(text));
        long start = System.currentTimeMillis();
        int pressure = 1000000;
        for (int i = 0; i < pressure; ++i) {
            SpeedTokenizer.segment(text);
        }
        double costTime = (System.currentTimeMillis() - start) / (double) 1000;
        System.out.printf("分词速度：%.2f字每秒", text.length() * pressure / costTime);
    }

    @Test
    @DisplayName("")
    public void test() {
        String text = "我的爱就是爱自然语言处理";
        Segment segment = HanLP.newSegment();

        System.out.println("未标注：" + segment.seg(text));
        segment.enablePartOfSpeechTagging(true);
        System.out.println("标注后：" + segment.seg(text));
    }
}
