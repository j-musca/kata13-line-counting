package org.example;

import java.util.Arrays;

public class RuleLineCounter implements LineCounter {

    private final TextReplacementRule textReplacementRule;
    private final TextReplacementRule multiLineCommentReplacementRule;
    private final TextReplacementRule singleLineCommentReplacementRule;

    public RuleLineCounter() {
        textReplacementRule = new TextReplacementRule("\"", "\"", "TEXT");
        multiLineCommentReplacementRule = new TextReplacementRule("/*", "*/", "");
        singleLineCommentReplacementRule = new TextReplacementRule("//", "\n", "\n");
    }

    @Override
    public int countCodeLines(final String codeText) {
        String textWithoutStrings = textReplacementRule.getFilteredText(codeText);
        String textWithoutMultiLineComments = multiLineCommentReplacementRule.getFilteredText(textWithoutStrings);
        String textWithoutSingleLineComments = singleLineCommentReplacementRule.getFilteredText(textWithoutMultiLineComments);

        return (int) Arrays.stream(textWithoutSingleLineComments.split("\n")).filter( (String line) -> line.trim().length() > 0 ).count();
    }
}
