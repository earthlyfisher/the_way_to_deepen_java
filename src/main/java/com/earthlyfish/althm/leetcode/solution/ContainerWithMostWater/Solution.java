package com.earthlyfish.althm.leetcode.solution.ContainerWithMostWater;

public class Solution {
    public int maxArea1(int[] height) {

        // 如果只有一条线
        if (height.length < 2) {
            return 0;
        }

        int sum = 0;
        int size = height.length;

        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                int tmpSum = (j - i) * Math.min(height[i], height[j]);
                if (sum < tmpSum) {
                    sum = tmpSum;
                }
            }
        }

        return sum;
    }

    public int maxArea2(int[] height) {

        // 如果只有一条线
        if (height.length < 2) {
            return 0;
        }

        int area = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int tmpArea = Math.min(height[left], height[right]) * (right - left);

            // 比较大小,并替换
            if (tmpArea > area) {
                area = tmpArea;
            }

            // 那条线短，就平移那条
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return area;
    }
}
