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
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> rc = Arrays.asList(br.readLine().split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());
		
		int r = rc.get(0);
		int c = rc.get(1);
		
		char[][] map = new char[r][c];
		Queue<Point> water = new LinkedList<Point>();
		Queue<Point> racoon =new LinkedList<Point>();
		Point exit;
		for(int i=0; i<r; i++) {
			char[] line = br.readLine().trim().toCharArray();
			for(int j=0; j<c; j++) {
				map[i][j] = line[j];
				if(map[i][j]=='D') racoon.add(new Point(i, j));
				else if(map[i][j]=='*') water.add(new Point(i,j));
				else if(map[i][j]=='S') exit = new Point(i,j);
			}
		}
		
		
//		for(char[] m : map) {
//			for(int i=0; i<m.length; i++) {
//				System.out.print(m[i] + " ");
//			}
//			System.out.println();
//		}
		
		
	}
}
class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
}