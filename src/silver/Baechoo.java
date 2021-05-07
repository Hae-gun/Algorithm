package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Baechoo {
	static int[][] map;
	static boolean[][] mapB;
	static int r;
	static int c;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.valueOf(br.readLine());
		
		while(n--!=0) {
			List<Integer> rcCount =  Arrays.asList(br.readLine().split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());
			r = rcCount.get(0);
			c = rcCount.get(1);
			int count = rcCount.get(2);
			map = new int[r][c];
			mapB = new boolean[r][c];
			for(int i=0; i<count; i++) {
				List<Integer> point =  Arrays.asList(br.readLine().split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());
				map[point.get(0)][point.get(1)] = 1;
			}
			int result = 0;
			for(int i=0; i<r; i++) {
				for(int j=0; j<c; j++) {
					
					if(map[i][j]==1 && !mapB[i][j]) {
						dfs(i,j);
						result++;
					}
					
				}
			}
			System.out.println(result);
		}
		
		
		
	}
	
	static int[] dx = {0,-1,0,1};
	static int[] dy = {1,0,-1,0};
	private static void dfs(int i, int j) {
		
		mapB[i][j] = true;
		for(int dir = 0; dir<4; dir++) {
			int nx = i + dx[dir];
			int ny = j + dy[dir];
			if(nx < 0 || nx >= r || ny < 0 || ny >= c) {
				continue;
			}
			if(map[nx][ny]==1 && !mapB[nx][ny]) {
				dfs(nx,ny);
			}
		}
		
	}

}
