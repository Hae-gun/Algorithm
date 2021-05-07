package test;

import java.util.ArrayList;
import java.util.Arrays;

public class BIgDe {
	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<>();
		
		String[] num = new String[4];
		num[0] = "b";
		num[1] = "c";
		num[2] = "a";
		num[3] = "t";
		
		for(int i=0; i<num.length; i++) {
			for(int j=i; j<num.length-1; j++) {
				if(num[j].compareTo(num[i])>0) {
					String tmp = num[j];
					num[j] = num[i];
					num[i] = tmp;
				}
			}
		}
		System.out.println(Arrays.toString(num));
	}
}
