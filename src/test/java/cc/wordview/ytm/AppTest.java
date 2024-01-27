package cc.wordview.ytm;

import java.io.IOException;
import java.util.List;

import cc.wordview.ytm.response.SearchResult;
import cc.wordview.ytm.response.Snippet;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
    public static YoutubeApi api = new YoutubeApi();
    String apiKey = ApiKeyLoader.getApiKey();

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testSearch() throws IOException {
        List<SearchResult> result = api.search("ano-yume-wo-nazotte", 2, apiKey);

        Snippet snippet = result.get(0).getSnippet();

        assertEquals(snippet.getChannelId(), "UCvpredjG93ifbCP1Y77JyFA");
        assertEquals(snippet.getChannelTitle(), "Ayase / YOASOBI");
        assertEquals(snippet.getTitle(), "YOASOBI「あの夢をなぞって」 Official Music Video");
    }
}
