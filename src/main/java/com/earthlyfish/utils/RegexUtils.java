package com.earthlyfish.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by earthlyfisher on 2017/5/25.
 */
public class RegexUtils {

    /**
     * 匹配正则串
     *
     * @param name
     * @param patternParam
     * @return
     */
    public static boolean regexMatch(String name, String patternParam) {
        Pattern pattern = Pattern.compile(patternParam);
        Matcher m = pattern.matcher(name);
        return m.matches();
    }

    /**
     * 返回匹配的正则分组
     *
     * @param name
     * @param patternParam
     * @return
     */
    public static String[] getGroup(String name, String patternParam) {
        Pattern pattern = Pattern.compile(patternParam);
        Matcher m = pattern.matcher(name);
        String[] groups = null;
        if (m.find()) {
            int groupCount = m.groupCount();
            if (groupCount > 1) {
                groups = new String[m.groupCount() - 1];
                for (int i = 0; i < groupCount - 1; i++) {
                    groups[i] = m.group(i + 1);
                }
            }
        }
        return groups;
    }

    /**
     * 找出所有匹配的串.
     *
     * @param name
     * @param patternParam
     * @return
     */
    public static int foundRegex(String name, String patternParam) {
        Pattern pattern = Pattern.compile(patternParam);
        Matcher m = pattern.matcher(name);
        int count = 0;
        while (m.find()) {
            count++;
            System.out.println(m.group(1) + ":" + m.group(2));
        }
        return count;
    }

    public static void main(String[] args) {
        String patternParam = "Hello[ \\t]*(.*)world";
        String name = "Hello \t\tPython world";
        Pattern pattern = Pattern.compile(patternParam);
        Matcher m = pattern.matcher(name);
        while (m.find()) {
            System.out.println(m.group(0) + ":" + m.group(1));
        }
    }
}
