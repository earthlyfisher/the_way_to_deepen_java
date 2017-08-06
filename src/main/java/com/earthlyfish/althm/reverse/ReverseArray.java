package com.earthlyfish.althm.reverse;

import java.util.ArrayList;
import java.util.List;

public class ReverseArray {

    /**
     * 倒序排列
     *
     * @param list
     * @return
     */
    public static List<Integer> reverseArray(final List<Integer> list) {
        int length = list.size();
        int midIndex = length / 2;
        for (int i = 0; i < midIndex; i++) {
            Integer temp = list.get(i);
            list.set(i, list.get(length - 1 - i));
            list.set(length - 1 - i, temp);
        }
        return list;
    }

    /**
     * 指定位置倒序排列
     *
     * @param list
     * @return
     */
    public static List<Integer> reverseArray4Index(List<Integer> list, int index) {
        if (index > list.size()) {
            throw new RuntimeException("index is too large");
        }

        if(index<=0){
            throw new RuntimeException("index is too small");
        }

        final List<Integer> leftLst = new ArrayList<>(list.subList(0, index));
        final List<Integer> rightLst = new ArrayList<>(list.subList(index, list.size()));
        list=new ArrayList<>();
        //反手方式
        list.addAll(reverseArray(leftLst));
        list.addAll(reverseArray(rightLst));
        return reverseArray(list);
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println(reverseArray4Index(list, 3));
        System.out.println(reverseArray(list));
    }
}
