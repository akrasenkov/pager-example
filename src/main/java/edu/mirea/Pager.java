package edu.mirea;

public class Pager {

    private static final char START_CHAR = '[';
    private static final char END_CHAR = ']';
    private static final char START_SELECTED_CHAR = '(';
    private static final char END_SELECTED_CHAR = ')';
    private static final char SPACING_CHAR = ' ';
    private static final String PLACEHOLDER_STR = "...";

    static String generatePagingStr(int selectedPage, int pagesWidth, int pagesCount) {
        Assert.checkNotEven(pagesWidth, "Width of pages segment can't be even");
        Assert.checkNotZero(selectedPage, "Can't select zero page");

        Assert.checkNotNegative(selectedPage, "Can't select negative page");
        Assert.checkNotNegative(pagesWidth, "Can't select negative pages segment width");

        Assert.checkNotLess(pagesCount, selectedPage, "Unreachable page");
        Assert.checkNotLess(pagesCount, pagesWidth, "Invalid pages segment width");

        StringBuffer buffer = new StringBuffer();
        int onSides = pagesWidth / 2;
        int addOnLeft = 0;
        int addOnRight = 0;
        if ((selectedPage - 1 - onSides) < 0) {
            addOnRight = Math.abs(selectedPage - 1 - onSides);
        }
        if ((selectedPage + 1 + onSides) > pagesCount) {
            addOnLeft = selectedPage + onSides - pagesCount;
        }
        buffer.append(START_CHAR).append(decorate(selectedPage, 1)).append(SPACING_CHAR);
        boolean placeholderFlag = false;
        for (int page = 2; page < pagesCount; page++) {
            if ((page >= (selectedPage - onSides - addOnLeft)) && (page <= (selectedPage + onSides + addOnRight))) {
                buffer.append(decorate(selectedPage, page)).append(SPACING_CHAR);
                placeholderFlag = false;
            } else {
                if (!placeholderFlag) {
                    buffer.append(PLACEHOLDER_STR).append(SPACING_CHAR);
                    placeholderFlag = true;
                }
            }

        }
        buffer.append(decorate(selectedPage, pagesCount)).append(END_CHAR);
        return buffer.toString();
    }

    static String decorate(int selectedPage, int page) {
        if (selectedPage == page) {
            return String.valueOf(START_SELECTED_CHAR) + page + String.valueOf(END_SELECTED_CHAR);
        }
        return String.valueOf(page);
    }
}
