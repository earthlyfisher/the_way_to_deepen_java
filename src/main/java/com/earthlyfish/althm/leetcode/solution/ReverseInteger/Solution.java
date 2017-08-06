package com.earthlyfish.althm.leetcode.solution.ReverseInteger;

public class Solution {
	public int reverse(int x) {
		long result = 0;
		while (x != 0) {
			result = result * 10 + x % 10;
			/*if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) {
				return x == Integer.MAX_VALUE ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}*/
			x = x / 10;
		}
		return (int)result;
	}

	public static void main(String[] args) {
		int a=1534236469;
		System.out.println(new Solution().reverse(a));
	}
}
