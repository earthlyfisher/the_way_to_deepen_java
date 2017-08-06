package com.earthlyfish.althm.leetcode.solution.AddTwoNumbers;

/**
 * 
 * You are given two linked lists representing two non-negative numbers. The
 * digits are stored in reverse order and each of their nodes contain a single
 * digit. Add the two numbers and return it as a linked list.
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 
 * Output: 7 -> 0 -> 8
 * 
 * @author earthlyfisher
 * @see https://leetcode.com/problems/add-two-numbers/
 *
 */
public class Solution {

	private int addFactor = 0;

	/**
	 * the solution for add two numbers.
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode headNode = null;
		ListNode sumNode = null;
		while (null != l1 && null != l2) {
			int sumValue = l1.val + l2.val + addFactor;
			ListNode sumNextNode = setNextNode(sumValue);
			if (null == sumNode) {
				sumNode = sumNextNode;
				headNode = sumNode;
			} else {
				sumNode.next = sumNextNode;
				sumNode = sumNode.next;
			}
			l1 = l1.next;
			l2 = l2.next;
		}

		while (null != l1) {
			int sumValue = l1.val + addFactor;
			ListNode sumNextNode = setNextNode(sumValue);
			if (null == sumNode) {
				sumNode = sumNextNode;
				headNode = sumNode;
			} else {
				sumNode.next = sumNextNode;
				sumNode = sumNode.next;
			}
			l1 = l1.next;
		}

		while (null != l2) {
			int sumValue = l2.val + addFactor;
			ListNode sumNextNode = setNextNode(sumValue);
			if (null == sumNode) {
				sumNode = sumNextNode;
				headNode = sumNode;
			} else {
				sumNode.next = sumNextNode;
				sumNode = sumNode.next;
			}
			l2 = l2.next;
		}

		if (addFactor != 0) {
			ListNode node = new ListNode(addFactor);
			node.next = null;
			sumNode.next = node;
		}
		return headNode;
	}

	/**
	 * set listNode's nextNode by add two numbers sum.
	 * 
	 * @param sumValue
	 * @return
	 */
	private ListNode setNextNode(int sumValue) {
		ListNode sumNextNode = new ListNode(0);
		sumNextNode.next = null;
		if (sumValue >= 10) {
			sumNextNode.val = sumValue % 10;
			addFactor = sumValue / 10;
		} else {
			addFactor = 0;
			sumNextNode.val = sumValue;
		}
		return sumNextNode;
	}

	public static void main(String[] args) {

		ListNode l10 = new ListNode(2);
		ListNode l11 = new ListNode(4);
		l10.next = l11;
		ListNode l12 = new ListNode(3);
		l11.next = l12;

		ListNode l20 = new ListNode(5);
		ListNode l21 = new ListNode(6);
		l20.next = l21;
		ListNode l22 = new ListNode(4);
		l21.next = l22;

		ListNode result = new Solution().addTwoNumbers(l10, l20);
		for (; result != null; result = result.next) {
			System.out.println(result.val);
		}

		System.gc();
	}

	/**
	 * Definition for singly-linked list.
	 */
	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

}
