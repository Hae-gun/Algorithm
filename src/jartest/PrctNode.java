package jartest;

public class PrctNode {
	public static void main(String[] args) {
		Node head = new Node("head");
		Node n1 = new Node("1");
		Node n2 = new Node("2");
		Node n3 = new Node("3");
		Node n4 = new Node("4");
		Node tail = new Node("tail");
		setNode(head,n1);
		setNode(n1,n2);
		setNode(n2,n3);
		setNode(n3,n4);
		setNode(n4,tail);
	
		Node temp = head;
		
		while(temp!=null) {
			System.out.println(temp);
			temp = temp.next;
		}
		// 노드 뒤집기.
		
		
	}
	
	public static void setNode(Node prev, Node next) {
		prev.next = next;
		next.prev = prev;
	}
	
	public static void reverseNode() {
		
	}
}

