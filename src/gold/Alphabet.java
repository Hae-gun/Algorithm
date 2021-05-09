package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class Alphabet {
	static char[][] map;
	
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> nm =Arrays.asList(br.readLine().split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());
		int r = nm.get(0);
		int c = nm.get(1);
		
		map = new char[r][c];
		boolean[][] chk = new boolean[r][c];
		Map<Character,Boolean> chkMap = new HashMap<>();
		for(int i=0; i<r; i++) {
			String line = br.readLine();
			for(int j=0; j<line.length(); j++) {
				map[i][j] = line.charAt(j);
				chkMap.put(map[i][j],false);
			}
		}
		
		Pare first = new Pare(0,0);
		first.pull += map[0][0];
		chkMap.put(map[0][0],true);
		Stack<Pare> stack = new Stack<Pare>();
		stack.add(first);
		String result = "";
		result = first.pull;
		while(!stack.isEmpty()) {
			Pare cur = stack.pop();
			for(int dir=0; dir<4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if(nx<0 || nx>=r || ny<0 || ny >=c) {
					continue;
				}
				if(!cur.pull.contains(""+map[nx][ny])) {
					Pare next = new Pare(nx,ny);
					next.pull = cur.pull+map[nx][ny];
					stack.add(next);
				}
				if(result.length()<cur.pull.length()) {
					result = cur.pull;
				}
			}
		}
		
		System.out.println(result.length());
		
	}
}

class Pare{
	int x;
	int y;
	String pull;
	public Pare(int x, int y) {
		this.x = x;
		this.y = y;
		this.pull = "";
	}
}
