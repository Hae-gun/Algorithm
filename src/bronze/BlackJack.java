package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJack {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> nm = Arrays.asList(br.readLine().split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());
		int n = nm.get(0);
		int m = nm.get(1);
		
		List<Integer> cards = Arrays.asList(br.readLine().split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());
		
		int result = 0;
		
		for(int i=0; i<cards.size()-2; i++) {
			for(int j=i+1; j<cards.size()-1; j++) {
				for(int k=j+1; k<cards.size(); k++) {
					int tmp = cards.get(i) + cards.get(j) + cards.get(k);
					if(tmp <= m) {
						result = Math.max(tmp, result);
					}
				}
			}
		}
		
		System.out.println(result);
		
		
	}
}
