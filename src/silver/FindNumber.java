package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FindNumber {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		Set<Integer> list1 = Arrays.asList(br.readLine().split(" ")).stream().map(Integer::parseInt).collect(Collectors.toSet());
		int m = Integer.valueOf(br.readLine());
		List<Integer> list2 = Arrays.asList(br.readLine().split(" ")).stream().map(Integer::parseInt).collect(Collectors.toList());
		
		for(Integer num : list2) {
			if(list1.contains(num)) {
				System.out.println(1);
			}else {
				System.out.println(0);
			}
			
		}
		
		
	}

}
