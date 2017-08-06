package com.earthlyfish.althm.leetcode.solution.ZigZagConversion;

public class Solutions {

    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder[] zigzagArray = new StringBuilder[numRows];
        int delta = 1;
        int row = 0;
        for (int i = 0; i < s.length(); i++) {
            if (zigzagArray[row] == null) {
                zigzagArray[row] = new StringBuilder();
            }
            zigzagArray[row].append(s.charAt(i));
            row += delta;
            if (row >= numRows) {
                row = row - 2;
                delta = -1;
            }
            if (row < 0) {
                row = 1;
                delta = 1;
            }
        }
        StringBuilder zigzag = zigzagArray[0];
        for (int j = 1; j < numRows; j++) {
            zigzag.append(zigzagArray[j].toString());
        }
        return zigzag.toString();
    }

    /**
     * 第一行和最后一行的index间隔为2*numRows-2;中间行的间隔为2*numRows-2*rowindex,2*rowindex循环
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert2(String s, int numRows) {
        if (s == null || s.equals("")) {
            return "";
        }

        if (numRows == 1) {
            return s;
        }

        int interval = (numRows << 1) - 2;
        StringBuilder zigzag = new StringBuilder();

        // 处理第一行
        int length = s.length();
        for (int i = 0; i < length; i = i + interval) {
            zigzag.append(s.charAt(i));
        }

        // 处理中间各行
        for (int row = 1; row < numRows - 1; row++) {
            int midInterval = row << 1;
            for (int j = row; j < length; j = j + midInterval) {
                zigzag.append(s.charAt(j));
                midInterval = interval - midInterval;
            }
        }

        // 处理最后一行
        for (int i = numRows - 1; i < length; i = i + interval) {
            zigzag.append(s.charAt(i));
        }
        return zigzag.toString();
    }

    public static void main(String[] args) {
        String s = "AB";
        System.out.println(new Solutions().convert2(s, 2));
    }
}
