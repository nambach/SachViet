package io.nambm.sachviet.xmlchecker;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import static io.nambm.sachviet.xmlchecker.SyntaxState.*;

public class XmlSyntaxChecker {

    public String check(String src) {
        src = src + " ";
        char[] reader = src.toCharArray();
        StringBuilder writer = new StringBuilder();

        StringBuilder openTag = new StringBuilder();
        boolean isEmptyTag = false, isOpenTag = false, isCloseTag = false;
        StringBuilder closeTag = new StringBuilder();
        StringBuilder attrName = new StringBuilder();
        StringBuilder attrValue = new StringBuilder();
        Map<String, String> attributes = new HashMap<>();

        StringBuilder content = new StringBuilder();

        Stack<String> stack = new Stack<>();

        String state = CONTENT;

        for (int i = 0; i < reader.length; i++) {
            char c = reader[i];

            switch (state) {
                case CONTENT:
                    if (c == LT) {
                        state = OPEN_BRACKET;
                        writer.append(content.toString().trim());
                    } else {
                        content.append(c);
                    }
                    break;

                case OPEN_BRACKET:
                    if (isStartTagChars(c)) {
                        state = OPEN_TAG_NAME;

                        isOpenTag = true;
                        isCloseTag = false;
                        isEmptyTag = false;

                        openTag.setLength(0);
                        openTag.append(c);
                    } else if (c == SLASH) {
                        state = CLOSE_TAG_SLASH;

                        isOpenTag = false;
                        isCloseTag = true;
                        isEmptyTag = false;
                    }
                    break;

                case OPEN_TAG_NAME:
                    if (isTagChars(c)) { //loop
                        openTag.append(c);
                    } else if (isSpace(c)){
                        state = TAG_INNER;

                        attributes.clear();
                    } else if (c == GT) {
                        state = CLOSE_BRACKET;
                    } else if (c == SLASH) {
                        state = EMPTY_SLASH;
                    }
                    break;

                case TAG_INNER:
                    if (isSpace(c)) { //loop

                    } else if (isStartAttrChars(c)) {
                        state = ATTR_NAME;

                        attrName.setLength(0);
                        attrName.append(c);
                    } else if (c == GT) {
                        state = CLOSE_BRACKET;
                    } else if (c == SLASH) {
                        state = EMPTY_SLASH;
                    }
                    break;

                case ATTR_NAME:
                    if (isAttrChars(c)) { //loop
                        attrName.append(c);
                    } else if (c == EQ) {
                        state = EQUAL;
                    } else if (isSpace(c)) {
                        state = EQUAL_WAIT;

                    } else { //Exception
                        if (c == SLASH) {
                            attributes.put(attrName.toString(), "true");
                            state = EMPTY_SLASH;
                        } else if (c == GT) {
                            attributes.put(attrName.toString(), "true");
                            state = CLOSE_BRACKET;
                        }
                    }
                    break;

                case EQUAL_WAIT:
                    if (isSpace(c)) { //loop

                    } else if (c == EQ) {
                        state = EQUAL;

                    } else { //Exception
                        if (isStartAttrChars(c)) {
                            attributes.put(attrName.toString(), "true");
                            state = ATTR_NAME;

                            attrName.setLength(0);
                            attrName.append(c);
                        }
                    }
                    break;

                case EQUAL:
                    if (isSpace(c)) { //loop

                    } else if (c == D_QUOT || c == S_QUOT) {
                        quote = c;
                        state = ATTR_VALUE_Q;

                        attrValue.setLength(0);
                    } else if (!isSpace(c) && c != GT) {
                        state = ATTR_VALUE_NQ;

                        attrValue.setLength(0);
                        attrValue.append(c);
                    }
                    break;

                case ATTR_VALUE_Q:
                    if (c != quote) { //loop
                        attrValue.append(c);
                    } else if (c == quote) {
                        state = TAG_INNER;
                        attributes.put(attrName.toString(), attrValue.toString());
                    }
                    break;

                case ATTR_VALUE_NQ:
                    if (!isSpace(c) && c != GT) { //loop
                        attrValue.append(c);
                    } else if (isSpace(c)) {
                        state = TAG_INNER;
                        attributes.put(attrName.toString(), attrValue.toString());
                    } else if (c == GT) {
                        state = CLOSE_BRACKET;
                        attributes.put(attrName.toString(), attrValue.toString());
                    }
                    break;

                case EMPTY_SLASH:
                    if (c == GT) {
                        state = CLOSE_BRACKET;
                        isEmptyTag = true;
                    }
                    break;

                case CLOSE_BRACKET:
                    if (isOpenTag) {
                        if (INLINE_TAGS.contains(openTag.toString())) {
                            isEmptyTag = true;
                        }
                        writer.append(LT)
                                .append(openTag.toString())
                                .append(convert(attributes))
                                .append((isEmptyTag ? "/" : ""))
                                .append(GT);

                        attributes.clear();

                        //STACK HERE: push open-tag
                        if (!INLINE_TAGS.contains(openTag.toString())) {
                            stack.push(openTag.toString());
                        }

                    } else if (isCloseTag) {
//                        writer.append(LT)
//                                .append(SLASH)
//                                .append(closeTag.toString())
//                                .append(GT);

                        //STACK HERE: pop out open-tag having the same name
                        String closeTagName = closeTag.toString();
                        //An open-tag is missing: <a><b><c>...</d>
                        if (!stack.isEmpty() && !stack.contains(closeTagName)) {
                            writer.append(LT)
                                    .append(SLASH)
                                    .append(stack.pop())
                                    .append(GT);

                        //A close-tag is missing: <a><b><c>...</a>
                        } else if (!stack.isEmpty() && stack.contains(closeTagName)) {
                            while (!stack.isEmpty() && !stack.peek().equals(closeTagName)) {
                                writer.append(LT)
                                        .append(SLASH)
                                        .append(stack.pop())
                                        .append(GT);
                            }
                            if (!stack.isEmpty() && stack.peek().equals(closeTagName)) {
                                writer.append(LT)
                                        .append(SLASH)
                                        .append(stack.pop())
                                        .append(GT);
                            }
                        }
                    }

                    if (c == LT) {
                        state = OPEN_BRACKET;
                    } else {
                        state = CONTENT;

                        content.setLength(0);
                        content.append(c);
                    }
                    break;

                case CLOSE_TAG_SLASH:
                    if (isStartTagChars(c)) {
                        state = CLOSE_TAG_NAME;

                        closeTag.setLength(0);
                        closeTag.append(c);
                    }
                    break;

                case CLOSE_TAG_NAME:
                    if (isTagChars(c)) { //loop
                        closeTag.append(c);
                    } else if (isSpace(c)) {
                        state = WAIT_END_TAG_CLOSE;
                    } else if (c == GT) {
                        state = CLOSE_BRACKET;
                    }
                    break;

                case WAIT_END_TAG_CLOSE:
                    if (isSpace(c)) { //loop

                    } else if (c == GT) {
                        state = CLOSE_BRACKET;
                    }
                    break;
            }
        }

        if (CONTENT.equals(state)) {
            writer.append(content.toString().trim());
        }

        //pop out all left tags
        while (!stack.isEmpty()) {
            writer.append(LT)
                    .append(SLASH)
                    .append(stack.pop())
                    .append(GT);
        }
        return writer.toString();
    }

    private Character quote;

    private String convert(Map<String, String> attributes) {
        if (attributes.isEmpty()) {
            return "";
        }
        return attributes.entrySet().stream().map(entry -> {
            String value = entry.getValue()
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&apos;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;");

            return entry.getKey() + "=" + "\"" + value + "\"";
        }).collect(Collectors.joining(" ", " ", ""));
    }
}
