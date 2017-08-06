package com.earthlyfish.althm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class Test {
	
	public static void main(String[] args) {
		HashMap<Integer,Integer> map=new HashMap<>();
		for(int i=0;i<10;i++){
			map.put(i, 10-i);
		}
		Set<Entry<Integer, Integer>> set=map.entrySet();
		for(Entry<Integer, Integer> entry:set){
			System.out.println(entry.getValue());
		}
		System.out.println("==================================");
		List<Entry<Integer,Integer>> list=new ArrayList<Entry<Integer,Integer>>(set);
		Collections.sort(list, new HashMapComp());
		for(Entry<Integer, Integer> entry:list){
			System.out.println(entry.getValue());
		}
	}
	
}

class HashMapComp implements Comparator<Entry<Integer,Integer>>{
	@Override
	public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
		return o1.getValue().compareTo(o2.getValue());
	}
} 
