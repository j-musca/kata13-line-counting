package org.example;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class LineCounterTests {

    private final String sourceCode = "// This file contains 3 lines of code\n"+
            "public interface Dave {\n"+
            "/**\n"+
            " * count the number of lines in a file\n"+
            " */\n"+
            "int countLines(File inFile); // not the real signature!\n"+
            "}\n";

    private final String sourceCode2 = "/*****\n" +
            " * This is a test program with 5 lines of code\n" +
            " *  \\/* no nesting allowed!\n" +
            " //*****//***/// Slightly pathological comment ending...\n" +
            "\n" +
            "public class Hello {\n" +
            "\tpublic static final void main(String [] args) { // gotta love Java\n" +
            "\t\t// Say hello\n" +
            "\t\tSystem./*wait*/out./*for*/println/*it*/(\"Hello/*\");\n" +
            "\t}\n" +
            "\n" +
            "}";

    @Test
    public void testLineCounting() {
        LineCounter ruleLineCounter = new RuleLineCounter();
        assertThat(ruleLineCounter.countCodeLines(sourceCode)).isEqualTo(3);
    }

    @Test
    public void testLineCounting2() {
        LineCounter ruleLineCounter = new RuleLineCounter();
        assertThat(ruleLineCounter.countCodeLines(sourceCode2)).isEqualTo(5);
    }
}
