package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class Pass {
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int n = Integer.valueOf(nm[0]);
		int m = Integer.valueOf(nm[1]);
		
		int[] pass = new int[n];
		
		for(int i=0; i<n; i++) {
			pass[i] = Integer.valueOf(br.readLine());
		}
		
		Arrays.sort(pass);
		
		long left = 1;
		long right = m * pass[n-1];
		
		
		
		
		long result = m * pass[n-1];
		
		while(left<=right) {
			long mid = (left + right)/2;
			
			if(isPossible(mid, pass, m)) {
				result = Math.min(result, mid);
				right = mid-1;
			}else {
				left = mid+1;
			}
		}
		
		System.out.println(result);
		
		
	}
	
	public static boolean isPossible(long mid,int[] pass, int m) {
		
		long tmp = 0;
		
		for(int p: pass) {
			tmp += mid/p;
		}
		return tmp>=m;
	}
	
}
