package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ContainNumbers {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n =  Integer.valueOf(br.readLine());
		
		Set<Integer> nSet = Arrays.asList((br.readLine().split(" "))).stream().map(Integer::parseInt).collect(Collectors.toSet());
		
		int m =  Integer.valueOf(br.readLine());
		
		List<Integer> myList = Arrays.asList((br.readLine().split(" "))).stream().map(Integer::parseInt).collect(Collectors.toList());
		
		for(int i : myList) {
			int result = nSet.contains(i) ? 1:0;
			System.out.println(result);
		}
		
		
	}
}
