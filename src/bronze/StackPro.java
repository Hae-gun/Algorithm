package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class StackPro {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		int[] nums = new int[n];
		for(int i=0; i<n; i++) {
			nums[i] = Integer.valueOf(br.readLine());
		}
		
		Stack<Integer> stack = new Stack<>();
		
		int i = 1;
		String s = "";
		int idx = 0;
		stack.add(i++);
		for(int num : nums) {
			
			while(i <= num) {
				stack.add(i++);
				s += "+";
			}
			
			if(stack.peek() == num) {
				stack.pop();
				s += "-";
			}else {
				System.out.println("NO");
				return;
			}
		}
		for(char re : s.toCharArray()) {
			System.out.println(re);
		}
		
		
	}
}
