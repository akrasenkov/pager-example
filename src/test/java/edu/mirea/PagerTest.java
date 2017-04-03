package edu.mirea;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class PagerTest extends org.junit.Assert {

    private final int selectedPage;
    private final int pagesWidth;
    private final int pagesCount;
    private final String expectedStr;

    public PagerTest(TestParam param) {
        this.selectedPage = param.selectedPage;
        this.pagesWidth = param.pagesWidth;
        this.pagesCount = param.pagesCount;
        this.expectedStr = param.expectedStr;
    }

    @Test
    public void testGeneratePagingStr() throws Exception {
        String actualStr = Pager.generatePagingStr(selectedPage, pagesWidth, pagesCount);
        assertEquals("Invalid output", expectedStr, actualStr);
        System.out.println(
                String.format("Output for (%d, %d, %d): %s", selectedPage, pagesWidth, pagesCount, actualStr)
        );
        System.out.println(
                String.format("Expected for (%d, %d, %d): %s", selectedPage, pagesWidth, pagesCount, expectedStr)
        );
    }

    @Parameterized.Parameters
    public static List<TestParam> parameters() {
        return Arrays.asList(
                new TestParam(1, 5, 5, "[(1) 2 3 4 5]"),
                new TestParam(2, 5, 5, "[1 (2) 3 4 5]"),
                new TestParam(2, 1, 4, "[1 (2) ... 4]"),
                new TestParam(5, 9, 10, "[1 2 3 4 (5) 6 7 8 9 10]"),
                new TestParam(88, 7, 150, "[1 ... 85 86 87 (88) 89 90 91 ... 150]")
        );
    }

    public static class TestParam {
        final int selectedPage;
        final int pagesWidth;
        final int pagesCount;
        final String expectedStr;

        public TestParam(int selectedPage, int pagesWidth, int pagesCount, String expectedStr) {
            this.selectedPage = selectedPage;
            this.pagesWidth = pagesWidth;
            this.pagesCount = pagesCount;
            this.expectedStr = expectedStr;
        }
    }
}
