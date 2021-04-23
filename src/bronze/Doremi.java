package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Doremi {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] t = br.readLine().split(" ");
		int[] set = new int[t.length];
		for(int i=0; i<t.length; i++) {
			set[i] = Integer.valueOf(t[i]);
		}
		
		int sub = set[0]-set[1];
		for(int i=1; i<t.length-1; i++) {
			if(sub != set[i]-set[i+1]) {
				System.out.println("mixed");
				return;
			}
		}
		
		if(sub==-1) {
			System.out.println("ascending");
		}else {
			System.out.println("descending");
		}
	}
}
