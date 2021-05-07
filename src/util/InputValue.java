package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputValue {
	BufferedReader br;
	
	public InputValue() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	// 숫자 1개 받기
	public int NumberReadLine() throws NumberFormatException, IOException {
		return Integer.valueOf(br.readLine());
	}
	
	public String[] readLineStringSet() throws IOException {
		return br.readLine().split(" ");
	}
	public int[] reaLineIntSet() throws IOException {
		String[] tmp = readLineStringSet();
		int[] result = new int[tmp.length];
		for(int i=0; i<result.length; i++) {
			result[i] = Integer.valueOf(tmp[i]);
		}
		return result;
	}
	
	public int[] getRC() throws IOException {
		List<Integer> result = Arrays.asList(br.readLine().split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());
		
		int[] arraySet = new int[2];
		arraySet[0] = result.get(0);
		arraySet[1] = result.get(1);
		return arraySet;
		
	}
	public int[][] makeIntMap(int r,int c) throws IOException{
		int[][] map = new int[r][c];
		for(int i=0; i<r; i++) {
			int[] set = reaLineIntSet();
			for(int j=0; j<c; j++) {
				map[i][j] = set[j];
			}
		}		
		return map;
	}
	
	
	public void closeStream() {
		if(this.br != null)
			try {
				br.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
	}
	
	public int[] getNumSetbyLine(int n) throws NumberFormatException, IOException {
		int set[] = new int[n];
		for(int i=0; i<n; i++) {
			set[i] = this.NumberReadLine();
		}
		return set;
	}
	
	public void showMap(int[][] map) {
		System.out.println("#######################");
		for(int[] line:map) {
			for(int num:line) {
				System.out.print(num+" ");
			}
			System.out.println();
		}
		System.out.println("#######################");
	}
	
}
