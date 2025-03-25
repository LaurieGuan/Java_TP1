package ca.tierslieux.iou.lib.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public enum ATTRIBUTE_MODE {
        NUMBER,
        STRING
    }

    public static String attributeMatch(String text, String attributeName, ATTRIBUTE_MODE matchMode) {
        String answer = "";
        String formattedString = String.format("(\"%s\": )((\"[\\wÀ-ʯ& ,.\\\\/:-]+\")?([\\d.]+)?)(,?)", attributeName);
        Pattern p = Pattern.compile(formattedString);
        Matcher match = p.matcher(text);
        if (match.find()) {
            if (matchMode == ATTRIBUTE_MODE.STRING) {
                answer = match.group(3);
                answer = answer.substring(1, answer.length() - 1);
            } else if (matchMode == ATTRIBUTE_MODE.NUMBER) {
                answer = match.group(4);
            }
        }
        return answer;
    }


    public static String listNameMatch(String text) {
        String answer = "";
        String listPattern = "\\\"(\\w+)\\\": \\[[\\t\\n\\r [\\w+À-ʯ\\\\\\-.\\\":{},\\n\\t\\r ]+]";
        Pattern p = Pattern.compile(listPattern);
        Matcher match = p.matcher(text);

        if (match.find()) {
            answer = match.group(1);
        }

        return answer;
    }

    public static String[] objectMatches(String text) {
        String objectPattern = "[\\t ]+\\\"(Tool|Game|Book)\\\": \\{([\\n\\t\\r \\\"\\wÀ-ʯ&:,.\\-\\\\]+)}";
        Pattern p = Pattern.compile(objectPattern);
        Matcher match = p.matcher(text);

        int numberOfMatches = 0;
        while (match.find()) {
            numberOfMatches++;
        }

        String[] answer = new String[numberOfMatches];
        for (int i = 0; i < numberOfMatches; i++) {
            if (i == 0) {
                match.find(0);
            } else {
                match.find();
            }
            answer[i] = match.group();
        }

        return answer;
    }

    public static String objectTypeMatch(String text) {
        String answer = "";
        String objectPattern = "^[\t ]+\"(Tool|Game|Book)\": \\{([\n\t\r \\\"\\wÀ-ʯ&:,.\\-\\\\]+)}";
        Pattern p = Pattern.compile(objectPattern);
        Matcher match = p.matcher(text);

        if (match.find()) {
            answer = match.group(1);
        }

        return answer;
    }

    public static boolean inputValid(String text, String keyword) {
        Pattern p = Pattern.compile(keyword);
        Matcher match = p.matcher(text);
        return match.find();
    }
}