package jartest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class DpPractice {
	static int idx =0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
//		List<Integer> list = readLine(br);
		
//		System.out.println(list);
		
		Node head = new Node("head");
		Node tail = new Node("tail");
		Node node1 = new Node(head,null,idx++);
		head.next = node1;

		Node temp = head;
		while(true) {
			System.out.println(temp.num);
			temp = temp.next;
			if(temp == null) break;
		}
		
	}

	private static List<Integer> readLine(BufferedReader br) throws IOException {
		return Arrays.asList(br.readLine().split(" ")).stream()
														.map(Integer::parseInt)
														.collect(Collectors.toList());
	}
	
	
}
