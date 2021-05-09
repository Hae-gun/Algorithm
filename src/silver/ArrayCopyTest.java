package silver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayCopyTest {
	public static void main(String[] args) {
		
		int k = 3;	
		int[] num = {12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1};	
		int[][] links = {{-1, -1}, {-1, -1}, {-1, -1}, {-1, -1}, {8, 5}, {2, 10}, {3, 0}, {6, 1}, {11, -1}, {7, 4}, {-1, -1}, {-1, -1}};
		
		Solution33 solution = new Solution33();
		solution.solution(k, num, links);
	}
}

class Solution33 {
    int max = 0;
    public int solution(int k, int[] num, int[][] links) {
        int answer = 0;
        Node[] nodes = new Node[num.length];
        for(int i=0; i<num.length; i++){
            nodes[i] = new Node();
            nodes[i].value = num[i];
            nodes[i].idx = i;
        }
        
        for(int i=0; i<links.length; i++){
            if(links[i][0] != -1){
                nodes[i].left = nodes[links[i][0]];
                nodes[links[i][0]].head = nodes[i];
            }
            if(links[i][1] != -1){
                nodes[i].right = nodes[links[i][1]];
                nodes[links[i][1]].head = nodes[i];
            }
        }
        
       /* for(Node n : nodes){
            System.out.println("NODE"+n.idx+":: "+ n +" HEAD >"+n.head);
            String left = n.left != null ? "LFET > " + n.left : "";
            String right = n.right != null ? "RIGHT > " + n.right : "";
            String child = left + " " + right;
            if(!" ".equals(child)){
                System.out.println(child);
            }
        }*/
        dfs(k,0,nodes);
        return answer;
    }
    
    public void dfs(int target,int k,Node[] nodeMap){
        if(k==target){
            List<Node> head = new ArrayList<>();
            for(Node n : nodeMap){
            System.out.println("NODE"+n.idx+":: "+ n +" HEAD >"+n.head);
            String left = n.left != null ? "LFET > " + n.left : "";
            String right = n.right != null ? "RIGHT > " + n.right : "";
            String child = left + " " + right;
            if(!" ".equals(child)){
                System.out.println(child);
                }
            }
            
        }
        for(int i=0; i<nodeMap.length; i++){
            Node[] nextMap = Arrays.copyOf(nodeMap,nodeMap.length);
            Node tmp = nextMap[i].cutLine("L");
            dfs(target,k+1,nextMap);
            nextMap[i].left = tmp;
            tmp = nextMap[i].cutLine("R");
            dfs(target,k+1,nextMap);
            nextMap[i].right = tmp;
            dfs(target,k,nextMap);
        }
        
    }
}



class Node{
    Node head;
    Node left;
    Node right;
    int idx;
    int value;
     @Override
    public String toString() {
    	return "value >> "+value;
    }
    
    public Node cutLine(String LorR){
        Node result = null;
        if("L".equals(LorR)){
            result = this.left;
            if(result != null) {
            	this.left.head = null;
            	this.left = null;
            }
        }else if("R".equals(LorR)){
            result = this.right;
            if(result != null) {
	            this.right.head = null;
	            this.right = null;
            }
        }
        return result;
    }
    
}