package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendNet {

	static int[] parent;
	static int[] count;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		List<Integer> list = new ArrayList<>();
		while(n--!=0) {
			int F = Integer.valueOf(br.readLine());
			parent = new int[F*2];
			count = new int[F*2];
			for(int i=0; i<F*2; i++) {
				parent[i] = i;
			}
			Arrays.fill(count, 1);
			Map<String, Integer> map = new HashMap<>();
			int index = 0;
			for(int j=0; j<F; j++) {
				String[] mem = br.readLine().split(" ");
				if(!map.containsKey(mem[0])) {
					map.put(mem[0],index++);
				}
				if(!map.containsKey(mem[1])) {
					map.put(mem[1],index++);
				}
				System.out.println(union(map.get(mem[0]),map.get(mem[1])));
				
			}
			
			
		}
		
	}
	private static int union(Integer a, Integer b) {
		a = find(a);
		b = find(b);
		if(a!=b) {
			parent[b] = a;
			count[a] += count[b];
			return count[a];
		}else {
			parent[a] = b;
			count[b] += count[a];
			return count[b];
		}
	}
	static int find(int a) {
		if(parent[a] == a) return a;
		else return parent[a] = find(parent[a]);
	}
	
	

}
