package org.example;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class TextReplacementRule {

    private final String startToken;
    private final String endToken;
    private final String replacementToken;

    public TextReplacementRule(String startToken, String endToken, String replacementToken) {
        this.startToken = startToken;
        this.endToken = endToken;
        this.replacementToken = replacementToken;
    }

    public String getFilteredText(String text) {
        final char[] characters = text.toCharArray();
        final LinkedList<Character> tokenSpace = new LinkedList<>();
        final StringBuilder codeCharacters = new StringBuilder();

        boolean isNotReplacementMode = true;

        for (char character : characters) {
            final String token = pushCharacter(tokenSpace, character);

            if (isNotReplacementMode) {
                if (token.endsWith(startToken)) {
                    isNotReplacementMode = false;
                    removeAddedStartTokenCharacters(codeCharacters);
                } else {
                    codeCharacters.append(character);
                }
            } else {
                if (token.endsWith(endToken)) {
                    isNotReplacementMode = true;
                    codeCharacters.append(replacementToken);
                }
            }
        }

        return codeCharacters.toString();
    }

    private void removeAddedStartTokenCharacters(StringBuilder codeCharacters) {
        int counter = startToken.length() - 1;

        while (counter > 0) {
            codeCharacters.deleteCharAt(codeCharacters.length() - 1);
            counter--;
        }
    }

    private String pushCharacter(LinkedList<Character> tokenSpace, char character) {
        final int maxSize = Math.max(startToken.length(), endToken.length());

        if (tokenSpace.size() == maxSize) {
            tokenSpace.removeFirst();
        }

        tokenSpace.addLast(character);

        return tokenSpace.stream().map(String::valueOf).collect(Collectors.joining(""));
    }
}
