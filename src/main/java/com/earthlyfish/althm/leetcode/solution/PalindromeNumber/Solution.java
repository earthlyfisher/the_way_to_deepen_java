package com.earthlyfish.althm.leetcode.solution.PalindromeNumber;

public class Solution {

	public boolean isPalindrome1(int x) {
		String reverse = new StringBuilder(x + "").reverse().toString();
		if (Integer.valueOf(reverse).equals(x)) {
			return true;
		}
		return false;
	}

	public boolean isPalindrome2(int x) {
		if (x < 0) {
			return false;
		}

		if (x < 10) {
			return true;
		}

		int length = 0;
		int digit = x;

		while (digit != 0) {
			digit = digit / 10;
			length++;
		}

		digit = x;
		int index = 1;
		boolean palindrome = true;
		int reverseDevide = 1;
		
		while (index < length) {
			reverseDevide = (int) Math.pow(10, length - 1);
			if (digit / reverseDevide == x % 10) {
				digit = digit % reverseDevide;
				x = x / 10;
				length--;
				index++;
			} else {
				palindrome = false;
				break;
			}
		}
		
		return palindrome;
	}

	public static void main(String[] args) {
		int x = 12221;
		System.out.println(new Solution().isPalindrome2(x));
	}
}
