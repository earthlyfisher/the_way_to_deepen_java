package com.earthlyfish.althm.leetcode.solution.twosum;

/**
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target.
 * 
 * You may assume that each input would have exactly one solution.
 * 
 * Example:
 * 
 * Given nums = [2, 7, 11, 15], target = 9,
 * Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
 * 
 * @author earthlyfisher
 * @see https://leetcode.com/problems/two-sum/
 *
 */

public class Solution {

	public int[] twoSum(int[] nums, int target) {
		int index1 = 0, index2 = 0;
		int length = nums.length;

		ok: for (int i = 0; i < length - 1; i++) {
			index1 = i;
			if (nums[index1] >= target) {
				continue;
			}

			for (index2 = i + 1; index2 < length; index2++) {
				if (nums[index1] + nums[index2] == target) {
					break ok;
				}
			}

		}

		if (index2 >= length) {
			throw new RuntimeException("not have two index num sum for target");
		}

		return new int[] { index1, index2 };
	}

	public static void main(String[] args) {
		int[] nums = new int[] { 2, 7, 11, 15 };
		int target = 9;
		int[] indexs = new Solution().twoSum(nums, target);
		System.out.printf("%s,%s", indexs[0], indexs[1]);
	}

}
