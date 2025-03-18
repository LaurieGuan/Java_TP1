package ca.tierslieux.iou.lib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public enum MODE {
        NUMBER,
        STRING
    }


    public static String attributeMatch(String text, String attributeName, MODE matchMode) {
        String answer = "0";
        String formattedString = String.format("(\"%s\": )((\"[0-9a-zA-ZÀ-ʯ ,.\\\\/:-]+\")?([0-9.]+)?)(,?)", attributeName);
        Pattern p = Pattern.compile(formattedString);
        Matcher match = p.matcher(text);
        if (match.find()) {
            if (matchMode == MODE.STRING) {
                answer = match.group(3);
                answer = answer.substring(1, answer.length() - 1);
            } else if (matchMode == MODE.NUMBER) {
                answer = match.group(4);
            }
        }
        return answer;
    }
}