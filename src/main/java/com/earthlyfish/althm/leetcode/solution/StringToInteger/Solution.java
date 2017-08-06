package com.earthlyfish.althm.leetcode.solution.StringToInteger;

public class Solution {

	public int myAtoi(String str) {

		if (null == str) {
			return 0;
		}

		str = str.trim();
		int length = str.length();
		long result = 0L;

		if (length > 0) {
			boolean negative = false;
			int c = str.charAt(0);
			negative = c == '-' ? true : false;
			int index = 0;
			if (c == '+' || c == '-') {
				index = 1;
			}

			while (index < length) {
				c = str.charAt(index);
				if (c >= 48 && c <= 57) {
					result = result * 10 + (c - 48);
				} else {
					break;
				}

				if (result > (Integer.MAX_VALUE)) {
					break;
				}
				index++;
			}

			result = negative ? -result : result;
			if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
				result = result > Integer.MAX_VALUE ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
		} else {
			result = 0;
		}
		return (int) result;
	}

	public static void main(String[] args) {
		String str = "9223372036854775809";
		//String str = "123";
		System.out.println(new Solution().myAtoi(str));
	}
}
