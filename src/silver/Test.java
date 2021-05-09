package silver;

import java.util.LinkedList;

public class Test {
	public static void main(String[] args) {
		
		
		LinkedList<Integer> lls = new LinkedList<Integer>();
		lls.add(1);
		lls.add(2);
		lls.add(3);
		
		int k = 2;
		
		LinkedList<Integer> other = (LinkedList<Integer>) lls.clone();
		lls.remove(k);
		if(k >= lls.size() ) k = lls.size()-1;
		System.out.println(lls);
		
		System.out.println(other);
		
	}
}
