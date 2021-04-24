package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;

public class Matches {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.valueOf(br.readLine());
		
		for(int i=0; i<n; i++) {
			int m = Integer.valueOf(br.readLine());
			BigDecimal min = minDp(m);
			BigDecimal max = maxDp(m);
			
			
			System.out.println(min+" "+max);
			
		}
		
		
	}
	private static BigDecimal maxDp(int m) {
		String maxDp[] = new String[101];
		Arrays.fill(maxDp, "0");
		String[] add = {"0","0","1","7","11","17","111"};
		for(int i=2; i<add.length; i++) {
			maxDp[i] = add[i];
		}
		for(int i=2; i<=m; i++) {
			for(int j=i; j>2; j--) {
//				System.out.println(maxDp[i]);
				BigDecimal a= new BigDecimal(maxDp[i]);
				BigDecimal b = new BigDecimal(maxDp[i-j] + maxDp[j]);
				maxDp[i] = "" + a.max(b);
//				maxDp[i] = "" + Math.max( Long.valueOf(maxDp[i]), Long.valueOf(maxDp[i-j] + maxDp[j]));
				
//				System.out.println(Arrays.toString(maxDp));
				
			}
			
		}
		
		
		return new BigDecimal(maxDp[m]) ;
				//.valueOf(maxDp[m]);
	}

	private static BigDecimal minDp(int m) {
		String minDp[] = new String[101];
		String INF = "" + new BigDecimal(Long.MAX_VALUE);
		Arrays.fill(minDp, INF);
		String[] add = {INF,INF,"1","7","4","2","6","8","10"};
		for(int i=2; i<add.length; i++) {
			minDp[i] = add[i];
		}
		for(int i=2; i<=m; i++) {
			for(int j=i; j>2; j--) {
//				System.out.println(maxDp[i]);
//				minDp[i] = "" + Math.min(Long.valueOf(minDp[i]), Long.valueOf(minDp[i-j] + minDp[j]));
				BigDecimal a= new BigDecimal(minDp[i]);
				BigDecimal b = new BigDecimal(minDp[i-j] + minDp[j]);
				minDp[i] = "" + a.min(b);
//				System.out.println(Arrays.toString(minDp));
				
			}
			
		}
		return new BigDecimal(minDp[m]) ;
//		return Long.valueOf(minDp[m]);
	}

}
