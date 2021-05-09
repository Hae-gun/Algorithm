package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.spi.DirStateFactory.Result;

public class SolveEasily {
	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		List<Integer> line = Arrays.asList(br.readLine().split(" ")).stream().
//								map(Integer::parseInt).collect(Collectors.toList());
//		
//		int srt = line.get(0);
//		int end = line.get(1);
//		
//		List<Integer> list = new ArrayList<Integer>();
//		int num = 1;
//		while(list.size()<end) {
//			for(int i=0; i<num; i++) {
//				list.add(num);
//			}
//			num++;
//		}
////		System.out.println(list);
//		List<Integer> result = list.subList(srt-1, end);
////		System.out.println(result);
//		int sum = result.stream().reduce(0,Integer::sum);
//		System.out.println(sum);
//		System.out.println(Arrays.toString(idx));
		String s = "one2";
		String[] numWords = {"zero","one","two","three","four","five","six","seven","eight","nine"};
		for(int i=0; i<numWords.length; i++) {
			s = s.replaceAll(numWords[i], ""+i);
		}
		
		LinkedList<List<Integer>> pPosition = new LinkedList<List<Integer>>();
		List<Integer> tmp = new ArrayList<Integer>();
        tmp.add(1);
        tmp.add(2);
        pPosition.push(tmp);
		System.out.println(s);
		LinkedList<Integer> lls = new LinkedList<Integer>();
		lls.remove
	}
}
