package jartest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IterTest {
	public static void main(String[] args) {
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.addLast(1);
		list.addLast(2);
		list.addLast(3);
		list.addLast(4);
		Iterator<Integer> it = list.iterator();
		List<Integer> arryas = new ArrayList<>();
		while(it.hasNext()) {
			int num = it.next();
			arryas.add(num++);
			arryas.add(num);
		}
		
		System.out.println(arryas);
		
	}
}
