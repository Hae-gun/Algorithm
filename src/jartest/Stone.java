package jartest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Stone {
	
	static int N;
	static int[][] dp = new int[21][2]; // dp[n][0] -> n번 돌까지 큰점프 않했을때 최소 에너지 소모값 dp[n][1] 큰점프 한번 썻을때최소값
	static int[][] jump = new int[21][2];
	static int k;
	public static void main(String[] args) throws NumberFormatException, IOException {
		input();
		solve();
	}

	private static void solve() {
		for(int i=3; i<=N; i++) {
			for(int j=0; j<2; j++) {
				dp[i][j] = Math.min(dp[i-1][j]+jump[i-1][0], dp[i-2][j]+jump[i-2][1]);
			}
			if(i>=3) {
				dp[i][1] = Math.min(dp[i][1], dp[i-3][0]+k);
			}
			System.out.println(Math.min(dp[i][0], dp[i][1]) );
		}
		System.out.println(dp[N][0] + ":"+dp[N][1]);
		
	}

	private static void input() throws NumberFormatException, IOException  {
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		N = Integer.valueOf(br.readLine());
		for(int i=1; i<=N-1; i++) {
			String[] jumpS = br.readLine().split(" ");
			jump[i][0] = Integer.valueOf(jumpS[0]);
			jump[i][1] = Integer.valueOf(jumpS[1]);
		}
		k = Integer.valueOf(br.readLine());
		
	}
	
	
	
}
