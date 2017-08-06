package com.earthlyfish.althm.leetcode.solution.LongestPalindromicSubstring;


/**
 * 时间复杂度有点高哈
 *
 * @author earthlyfisher
 *
 */
public class Solution {

    public String longestPalindrome(String s) {
        if (null == s || s.length() == 0) {
            return "";
        }

        int length = s.length();
        StringBuilder longString = new StringBuilder();
        longString.append(s.charAt(0));

        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {

                // 以下判断子串是不是回文串
                int subLength = j - i + 1;// 子串的长度
                String tmpStr = s.substring(i, j + 1);
                boolean flag = true;
                for (int k = 0; k < subLength / 2; k++) {
                    if (tmpStr.charAt(k) != tmpStr.charAt(subLength - k - 1)) {
                        flag = false;
                        break;
                    }
                }

                // 如果是回文串，判断长度重新赋值
                if (flag) {
                    if (tmpStr.length() > longString.length()) {
                        longString.delete(0, longString.length());
                        longString.append(tmpStr);
                    }
                }
            }
        }
        return longString.toString();
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println(new Solution().longestPalindrome(s));
    }
}
