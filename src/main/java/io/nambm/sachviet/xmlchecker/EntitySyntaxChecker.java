package io.nambm.sachviet.xmlchecker;

import java.util.Arrays;
import java.util.List;

public class EntitySyntaxChecker {

    public static final char _AMP = '&';
    public static final char _SEMICOLON = ';';
    public static final char _SHARP = '#';
    public static final char _X = 'x';

    public static final String AMPERSAND = "ampersand";
    public static final String SHARP = "sharp";
    public static final String X = "x";
    public static final String DECIMAL = "decimal";
    public static final String HEXADECIMAL = "hexadecimal";
    public static final String SEMICOLON = "semicolon";
    public static final String CONTENT = "content";
    public static final String LETTER = "letter";

    public static final List<String> LETTER_ENTITIES = Arrays.asList(
            "quot", "amp", "apos", "lt", "gt");

    private boolean isHexadecimal(char c) {
        return Character.isDigit(c) || ('a' <= c && c <= 'f') || ('A' <= c && c <= 'F');
    }

    public String check(String src) {
        char[] reader = (src + " ").toCharArray();

        StringBuilder writer = new StringBuilder();
        StringBuilder entityName = new StringBuilder();

        boolean isLetter = false;

        int maxLetterLength = LETTER_ENTITIES.stream().map(String::length).max(Integer::compareTo).get();

        String state = CONTENT;

        for (int i = 0; i < reader.length; i++) {
            char c = reader[i];

            switch (state) {
                case CONTENT:
                    if (c == _AMP) {
                        state = AMPERSAND;
                        entityName.setLength(0);
                    } else {
                        writer.append(c);
                    }
                    break;

                case AMPERSAND:
                    if (Character.isLetter(c)) { //e.g: &quot;
                        state = LETTER;
                        isLetter = true;

                        entityName.append(c);
                    } else if (c == _SHARP) { // e.g: &#123;
                        state = SHARP;
                        entityName.append(c);

                    } else { //Exception ...& param=

                        writer.append("&amp;");
                        if (c == _AMP) {
                            state = AMPERSAND;
                            entityName.setLength(0);
                        } else {
                            state = CONTENT;
                            writer.append(c);
                        }
                    }
                    break;

                case SHARP:
                    if (Character.isDigit(c)) {
                        state = DECIMAL;

                        entityName.append(c);
                    } else if (c == _X) {
                        state = HEXADECIMAL;

                        entityName.append(c);

                    } else { //Exception

                        writer.append("&amp;")
                                .append(entityName.toString());

                        if (c == _AMP) {
                            state = AMPERSAND;
                            entityName.setLength(0);
                        } else {
                            state = CONTENT;
                            writer.append(c);
                        }
                    }
                    break;

                case LETTER:
                    if (Character.isLetter(c)) {
                        entityName.append(c);

                        if (reader[i + 1] != _SEMICOLON) { //peek the next char if it is not a semicolon
                            if (LETTER_ENTITIES.contains(entityName.toString())) {
                                state = SEMICOLON;
                            } else if (entityName.length() == maxLetterLength) {
                                writer.append("&amp;")
                                        .append(entityName.toString());

                                state = CONTENT;

                                isLetter = false; //turn of flag
                            }
                        }

                    } else if (c == _SEMICOLON) {
                        state = SEMICOLON;

                    } else { //Exception

                        writer.append("&amp;")
                                .append(entityName.toString());

                        isLetter = false; //turn of flag

                        if (c == _AMP) {
                            state = AMPERSAND;
                            entityName.setLength(0);
                        } else {
                            state = CONTENT;
                            writer.append(c);
                        }
                    }
                    break;

                case DECIMAL:
                    if (Character.isDigit(c)) {
                        entityName.append(c);
                    } else if (c == _SEMICOLON) {
                        state = SEMICOLON;
                    }
                    break;

                case HEXADECIMAL:
                    if (isHexadecimal(c)) {
                        entityName.append(c);
                    } else if (c == _SEMICOLON) {
                        state = SEMICOLON;
                    }
                    break;

                case SEMICOLON:
                    if (isLetter && !LETTER_ENTITIES.contains(entityName.toString())) {
                        //a letter entity that is not allowed
                        writer.append("&amp;")
                                .append(entityName.toString())
                                .append(_SEMICOLON);
                    } else {
                        writer.append(_AMP)
                                .append(entityName.toString())
                                .append(_SEMICOLON);
                    }

                    isLetter = false; //turn of flag

                    if (c == _AMP) {
                        state = AMPERSAND;
                        entityName.setLength(0);
                    } else {
                        state = CONTENT;
                        writer.append(c);
                    }
                    break;
            }
        }

        return writer.toString().trim();
    }
}
