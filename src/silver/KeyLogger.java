package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class KeyLogger {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		List<String> result = new ArrayList<>(); 
		while(n--!=0) {
			char[] line = br.readLine().toCharArray();
			LinkedList<Character> front = new LinkedList<Character>();
			Stack<Character> tail = new Stack<Character>();
			
			for(char c : line) {
				if(c != '<' && c!= '>' && c!='-') {
					front.add(c);
				}
				
				
				if(c == '>' && !tail.isEmpty()) {
					front.add(tail.pop());
				}else if(c == '<' && !front.isEmpty()) {
					tail.push(front.removeLast());
				}else if(c == '-' && !front.isEmpty()) {
					front.removeLast();
				}
//				System.out.println(front);
//				System.out.println(tail);
//				System.out.println("_______");
				
			}
			String s = "";
			while(!front.isEmpty()) {
				s += front.poll();
			}
			while(!tail.isEmpty()) {
				s += tail.pop();
			}
			System.out.println(s);
		}
		
	}
}
