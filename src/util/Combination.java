package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Combination {
	static int[] com ;
	
	static List<List<Integer>> result = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("1~ N까지 조합 구하기.");
		System.out.print(" N : ");
		int n = Integer.valueOf(br.readLine());
		System.out.print(" 뽑을 원소수 : ");
		int m = Integer.valueOf(br.readLine());
		com = new int[n];
		for(int i=0 ; i<n; i++) {
			com[i] = i+1;
		}
		
		List<Integer> set = new ArrayList<>();
		combi(0,m,0,set);
		System.out.println("총 조합 갯수 : " + result.size());
	}
	
	
	
	static void combi(int dept,int target,int position,List<Integer> list){
		if(dept == target) {
			List<Integer> tmp = new ArrayList<>();
			for(int i:list) {
				tmp.add(i);
			}
			result.add(tmp);
			return;
//			result.add(list);
//			System.out.println(list);
		}
		if(position >= com.length) {
			return;
		}
			list.add(com[position]);
//			System.out.println(list);
			combi(dept+1, target, position+1, list);
			list.remove(list.size()-1);
//			System.out.println(list);
			combi(dept,target,position+1,list);
		
	}
}
