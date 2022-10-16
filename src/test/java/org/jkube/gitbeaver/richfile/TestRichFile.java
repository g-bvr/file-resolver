package org.jkube.gitbeaver.richfile;

import org.jkube.gitbeaver.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRichFile {

    public static final String DIR = "src/test/resources/richfile";

    @Test
    public void test() {
        doTest("test1.txt", "expected1a.txt", "tag1");
        doTest("test1.txt", "expected1b.txt");
        doTest("test2.txt", "expected2a.txt");
        doTest("test2.txt", "expected2b.txt", "tag1");
        doTest("test2.txt", "expected2c.txt", "tag2");
        doTest("test2.txt", "expected2d.txt", "tag1", "tag2");
        doTest("test3.txt", "expected3a.txt", "tag2");
        doTest("test3.txt", "expected3b.txt", "tag1");
        doTest("test4.txt", "expected4.txt", "tag1");
        doTest("test5.txt", "expected5a.txt", "tag1");
        doTest("test5.txt", "expected5b.txt");
        doTest("test6.txt", "expected6.txt");
        doTest("test7.txt", "expected7.txt");
        doTest("test8.txt", "expected8.txt");
        doTest("test9.txt", "expected9.txt");
        doTest("test10.txt", "expected10.txt");
        doTest("test11.txt", "expected11.txt");
        doTest("test12.txt", "expected12.txt");
        doTest("test13.txt", "expected13.txt");
        doTest("test14.txt", "expected14.txt");
        doTest("test15.txt", "expected15.txt");
        doTest("test16.txt", "expected16.txt");
        doTest("test17.txt", "expected17.txt");
    }

    private void doTest(String test, String expected, String... tags)  {
        RichFile f = new RichFile(Path.of(DIR, test));
        List<String> expectedLines;
        expectedLines = FileUtil.readLines(Path.of(DIR, expected));
        final Map<String, String> settings = new LinkedHashMap<>();
        for (String tag : tags) {
            settings.put(tag, "yes");
        }
        assertEquals(expectedLines, f.resolve(settings));
    }

}
