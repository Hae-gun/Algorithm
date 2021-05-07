package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class ExitRenew {
	static int dx[] = { 1, 0, -1, 0 };
	static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] rc = br.readLine().split(" ");

		int r = Integer.valueOf(rc[0]);
		int c = Integer.valueOf(rc[1]);

		char map[][] = new char[r][c];
		int waterMap[][] = new int[r][c];
		int timeCheck[][] = new int[r][c];

		Queue<Point> waterPoint = new LinkedList<>();
		Queue<Point> animalPosition = new LinkedList<>();

		Point exit = new Point();

		for (int i = 0; i < r; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				map[i][j] = line[j];
				if (map[i][j] == 'S')
					animalPosition.add(new Point(i, j));
				else if (map[i][j] == '*')
					waterPoint.add(new Point(i, j));
				else if (map[i][j] == 'D') {
					exit.x = i;
					exit.y = j;
				}
			}
		}

		waterMap = water_bfs(map, waterPoint);
//		showMap(waterMap);
//		System.out.println("################################");
		timeCheck = bfs(map, animalPosition, waterMap);
//		showMap(timeCheck);
		
		if(timeCheck[exit.x][exit.y]==0) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(timeCheck[exit.x][exit.y]);
		}
	}

	private static void showMap(int[][] map) {
		for (int[] line : map) {
			for (int t : line) {
				System.out.print(t + " ");
			}
			System.out.println();
		}
	}

	private static int[][] bfs(char[][] map, Queue<Point> animalPosition, int[][] waterMap) {
		int r = map.length;
		int c = map[0].length;
		int result[][] = new int[r][c];
		
		while(!animalPosition.isEmpty()) {
			Point cur = animalPosition.poll();
			
			for(int dir=0; dir<4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if(nx>=0 && nx<r && ny >=0 && ny < c) {
					if(result[nx][ny]==0 && (map[nx][ny]=='.' || map[nx][ny]=='D') ) {
						if(waterMap[nx][ny]==0) {
							result[nx][ny] = result[cur.x][cur.y] + 1;
							animalPosition.add(new Point(nx,ny));
						}else {
							if(waterMap[nx][ny] > result[cur.x][cur.y] + 1) {
								result[nx][ny] = result[cur.x][cur.y] + 1;
								animalPosition.add(new Point(nx,ny));
							}
						}
					}
				}
			}
		}
		
		
		
		return result;
	}

	private static int[][] water_bfs(char[][] map, Queue<Point> waterPoint) {
		int r = map.length;
		int c = map[0].length;
		int result[][] = new int[r][c];
		while (!waterPoint.isEmpty()) {
			Point cur = waterPoint.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (nx >= 0 && nx < r && ny >= 0 && ny < c && map[nx][ny] == '.' && result[nx][ny] == 0) {
					result[nx][ny] = result[cur.x][cur.y] + 1;
					waterPoint.add(new Point(nx, ny));
				}

			}

		}

		return result;
	}

}
