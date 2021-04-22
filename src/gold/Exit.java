package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Exit {
	static int[] dx = { 0, -1, 1, 0 };
	static int[] dy = { 1, 0, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> rc = Arrays.asList(br.readLine().split(" ")).stream().map(Integer::parseInt)
				.collect(Collectors.toList());

		int r = rc.get(0);
		int c = rc.get(1);

		char[][] map = new char[r][c];
		LinkedList<Point> water = new LinkedList<Point>();
		LinkedList<Point> animal = new LinkedList<Point>();
		Point exit = null;
		for (int i = 0; i < r; i++) {
			char[] line = br.readLine().trim().toCharArray();
			for (int j = 0; j < c; j++) {
				map[i][j] = line[j];
				if (map[i][j] == 'D') {
					animal.add(new Point(i, j));
				}else if (map[i][j] == '*') {
					water.add(new Point(i, j));
				}else if (map[i][j] == 'S') {
					exit = new Point(i, j);
				}
			}
		}
		int count=0;
		while (!animal.isEmpty()) {
			LinkedList<Point> curWaterSet = water;
//			while(!curWaterSet.isEmpty()) {
			for(int i=0; i<curWaterSet.size(); i++) {
				Point curWater = curWaterSet.poll();
				for (int j = 0; j < 4; j++) {
//					showMap(map);
					int nx = curWater.x + dx[j];
					int ny = curWater.y + dy[j];
					if (nx < 0 || nx >= r || ny < 0 || ny >= c)
						continue;
					if (map[nx][ny] == '.') {
						water.add(new Point(nx, ny));
						map[nx][ny] = '*';
					}
				}
				
			}
			Point curAnimal = animal.poll();
			map[curAnimal.x][curAnimal.y] = 'X';
			for(int dir=0; dir<4; dir++) {
				int nx = curAnimal.x + dx[dir];
				int ny = curAnimal.y + dy[dir];
				if (nx < 0 || nx >= r || ny < 0 || ny >= c)
					continue;
				Point next = new Point(nx, ny);
				if(map[nx][ny]=='S') {
					System.out.println(count);
					return;
				}else if(map[nx][ny]=='.') {
					map[nx][ny] = 'D';
					animal.add(next);
				}
			}
			
			count++;
			if(animal.isEmpty()) System.out.println(-1);
		}


	}

	private static void showMap(char[][] map) {
		for(char[] m : map) {
			for(int k=0; k<m.length; k++) {
				System.out.print(m[k] + " ");
			}
			System.out.println();
		}
		System.out.println("**********");
	}
}

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

}