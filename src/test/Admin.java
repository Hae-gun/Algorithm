package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Admin {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.valueOf(br.readLine());
		Long[] A = new Long[N];
		
		String[] countA = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			A[i] = Long.valueOf(countA[i]);
		}
		
		String[] countBC = br.readLine().split(" ");
		
		Long B = Long.valueOf(countBC[0]);
		Long C = Long.valueOf(countBC[1]);
		
		Long Nb = Long.valueOf(N);
		Long Nc = 0L;
		
		for(Long a : A) {
			Long temp;
			if (a>=B) {
				temp = (a-B) % C;
				if(temp == 0) Nc += (a-B)/C;
				else Nc += (a-B)/C + 1;
			}
		}
		System.out.println(Nb + Nc);
	}
}
