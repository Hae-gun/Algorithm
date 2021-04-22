package jartest;

public class Node {
	Node prev;
	Node current;
	Node next;
	String name;
	
	public Node(String name) {
		this.current = this;
		this.name = name;
	}

	public Node(Node prev, Node next,String name) {
		this.prev = prev;
		this.current = this;
		this.next = next;

		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	boolean isHead() {
		if(prev == null) return true;
		else return false;
	}
	
	boolean isTail() {
		if(next == null) return true;
		else return false;
	}
}
