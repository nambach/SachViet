package io.nambm.sachviet.utils;

import io.nambm.sachviet.xmlchecker.EntitySyntaxChecker;
import io.nambm.sachviet.xmlchecker.XmlSyntaxChecker;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

    public static String refineHtml(String src) {
        src = getBody(src);
        src = removeMiscellaneousTags(src);

        XmlSyntaxChecker xmlSyntaxChecker = new XmlSyntaxChecker();
        EntitySyntaxChecker entitySyntaxChecker = new EntitySyntaxChecker();
        src = xmlSyntaxChecker.check(src);
        src = entitySyntaxChecker.check(src);

        //crop one more time
        src = getBody(src);
        return src;
    }

    public static String getBody(String src) {
        String result = src;

        String expression = "<body.*?<[ ]*/[ ]*body[ ]*>";
        Pattern pattern = Pattern.compile(expression);

        Matcher matcher = pattern.matcher(result);

        if (matcher.find()) {
            result = matcher.group(0);
        }

        return result;
    }

    public static String removeMiscellaneousTags(String src) {
        String result = src;
        List<String> doubleTags = Arrays.asList("script");

        for (String doubleTag : doubleTags) {
            //Match <singleTag> that contains anything but the phrase "/>", with reluctant matches
            String expression = "<tag.*?</tag[ ]*>".replaceAll("tag", doubleTag);
            result = result.replaceAll(expression, "");
        }


        //Remove all comments
        String expression = "<!--.*?-->";
        result = result.replaceAll(expression, "");

        //Remove all whitespace
        expression = "&nbsp;?";
        result = result.replaceAll(expression, "");

        return result;
    }
}

//        String tagRegex = "<\\w+\\s+[^>]*?(?:>|/>)";
//        String attributeRegex = "(\\w+=\"[^/><]*?\")(\\w+=\"[^/><]*?\")";