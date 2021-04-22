package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class VoteRep {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int size = Integer.valueOf(br.readLine());
		int pplCount = Integer.valueOf(br.readLine());
		
		List<Integer> votes = Arrays.asList(br.readLine().split(" ")).stream()
									.map(Integer::parseInt).collect(Collectors.toList());
		
		
	}
	static void fail(BufferedReader br) throws NumberFormatException, IOException  {
		int size = Integer.valueOf(br.readLine());
		int pplCount = Integer.valueOf(br.readLine());
		
		List<Integer> votes = Arrays.asList(br.readLine().split(" ")).stream()
									.map(Integer::parseInt).collect(Collectors.toList());
		int getVotes[] = new int[101];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		for(int i=0; i<pplCount; i++) {
			int candi = votes.get(i);
			System.out.println(queue);
			if(queue.contains(candi)) {
				getVotes[candi]++;
			}else {
				if(queue.size()>=size) {
					int out = queue.removeFirst();
					getVotes[out]=0;
				}
				queue.addLast(candi);
				getVotes[candi]++;
			}
		}
			
		Object[] result = queue.toArray();
		Arrays.sort(result);
		StringBuilder s = new StringBuilder();
		for(Object o : result) {
			s.append(o+" ");
		}
		s.deleteCharAt(s.length()-1);
		System.out.println(s.toString());
	}
}


