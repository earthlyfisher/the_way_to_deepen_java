package com.earthlyfish.althm.leetcode.solution.MedianOfTwoSortedArrays;

/**
 * 
 * @author earthlyfisher
 *
 */
public class Solution {

	/**
	 * generate new array and get median value,the time complexity is o(m+n)
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public double findMedianSortedArrays_1(int[] nums1, int[] nums2) {
		int[] nums = null;
		if (null == nums1) {
			nums = nums2;
		}
		if (null == nums2) {
			nums = nums1;
		}

		if (nums != null) {
			return getMedianValue(nums);
		}

		int length1 = nums1.length;
		int length2 = nums2.length;
		nums = new int[length1 + length2];
		int index1 = 0, index2 = 0, index = 0;
		while (index1 < length1 && index2 < length2) {
			if (nums1[index1] < nums2[index2]) {
				nums[index++] = nums1[index1];
				index1++;
			} else if (nums1[index1] > nums2[index2]) {
				nums[index++] = nums2[index2];
				index2++;
			} else {
				nums[index++] = nums1[index1];
				nums[index++] = nums2[index2];
				index1++;
				index2++;
			}
		}

		while (index1 < length1) {
			nums[index++] = nums1[index1];
			index1++;
		}

		while (index2 < length2) {
			nums[index++] = nums2[index2];
			index2++;
		}

		return getMedianValue(nums);
	}

	public double findMedianSortedArrays_2(int[] nums1, int[] nums2) {
		int length = nums1.length + nums2.length;
		if (length % 2 != 0) {
			return findIndexkMinValue(nums1, nums2, length / 2 + 1, nums1.length, nums2.length);
		} else {
			int medianValue = findIndexkMinValue(nums1, nums2, length / 2, nums1.length, nums2.length)
					+ findIndexkMinValue(nums1, nums2, length / 2 + 1, nums1.length, nums2.length);
			return (double) medianValue / 2;
		}
	}

	/**
	 * find median value from the param arrays.
	 * 
	 * @param nums
	 * @return
	 */
	private double getMedianValue(int[] nums) {
		if (null == nums || nums.length == 0) {
			throw new RuntimeException("array is null or length is 0!");
		}

		int length = nums.length;
		if (length % 2 == 0) {
			return (double) (nums[length / 2] + nums[length / 2 - 1]) / 2;
		} else {
			return (double) nums[length / 2];
		}
	}

	/**
	 * find the k-th min value in two SortedArrays,the time complexity is
	 * o(log(m+n))
	 * 
	 * @param nums1
	 *            array1
	 * @param nums2
	 *            array2
	 * @param k
	 *            the k-th index
	 * @param m
	 *            array1's length
	 * @param n
	 *            array2's length
	 * @return
	 */
	private int findIndexkMinValue(int[] nums1, int[] nums2, int k, int m, int n) {
		if (k > m + n) {
			throw new RuntimeException("the index is outOfArrays length");
		}

		if (m > n) {
			return findIndexkMinValue(nums2, nums1, k, n, m);
		}

		if (m == 0) {
			return nums2[k-1];
		}
		if (n == 0) {
			return nums1[k-1];
		}
		if (k == 1) {
			return Math.min(nums1[0], nums2[0]);
		}

		int p1 = 0;
		int p2 = 0;
		p1 = Math.min(k / 2, m);
		p2 = k - p1;
		if (nums1[p1 - 1] < nums2[p2 - 1]) {
			int[] newNums1 = new int[m - p1];
			System.arraycopy(nums1, p1, newNums1, 0, m - p1);
			return findIndexkMinValue(newNums1, nums2, k - p1, m - p1, n);
		} else if (nums1[p1 - 1] > nums2[p2 - 1]) {
			int[] newNums2 = new int[n - p2];
			System.arraycopy(nums2, p2, newNums2, 0, n - p2);
			return findIndexkMinValue(nums1, newNums2, k - p2, m, n - p2);
		} else {
			return nums1[p1 - 1];
		}
	}

	public static void main(String[] args) {
		int[] nums1 = new int[] {};
		int[] nums2 = new int[] { 3, 4 };
		System.out.println(new Solution().findMedianSortedArrays_2(nums1, nums2));
	}
}
