package com.earthlyfish.althm.leetcode.solution.LongestSubstring;

public class Solution {

    /**
     * 通过利用StringBuilder对字符串的增删改来实现.
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if ("".equals(s)) {
            return 0;
        }

        String sb = s.charAt(0) + "";
        StringBuilder sbTmp = new StringBuilder();
        sbTmp.append(s.charAt(0));
        String indexStr;
        for (int i = 1; i < s.length(); i++) {
            indexStr = s.charAt(i) + "";
            int index = sbTmp.indexOf(indexStr);
            if (index >= 0) {
                sbTmp.delete(0, index + 1);
            }
            sbTmp.append(indexStr);
            if (sbTmp.length() >= sb.length()) {
                sb = sbTmp.toString();
            }
        }
        return sb.length();
    }

    /**
     * 通过利用子串在源字符串的启始和终止index来实现.
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringByIndex(String s) {
        if ("".equals(s)) {
            return 0;
        }

        int begin = 0, beginTmp = 0;
        int end = 1, endTmp = 1;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = beginTmp; j < endTmp; j++) {
                if (c == s.charAt(j)) {
                    beginTmp = j + 1;
                    break;
                }
            }
            endTmp++;
            if (end - begin <= endTmp - beginTmp) {
                end = endTmp;
                begin = beginTmp;
            }
        }
        return end - begin;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(new Solution().lengthOfLongestSubstringByIndex(s));
    }
}
