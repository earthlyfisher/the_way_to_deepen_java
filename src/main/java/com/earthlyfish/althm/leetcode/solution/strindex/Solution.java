package com.earthlyfish.althm.leetcode.solution.strindex;

public class Solution {

	public int strIndex(String src, String dest) {
		if (null == src && null == dest)
			return 0;
		if (null == src)
			return -1;
		if (null == dest)
			return 0;

		for (int index = 0; index < src.length(); index++) {
			int j = 0;
			for (; j < dest.length(); j++) {
				if (src.charAt(index + j) != dest.charAt(j))
					break;
			}
			if (j == dest.length())
				return index;
		}
		return -1;
	}

	public static void main(String[] args) {
		String src = "abcd";
		String dest = "bc";
		System.out.println(src.indexOf(dest));
		System.out.println(new Solution().strIndex(src, dest));
	}
	
}
