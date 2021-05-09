package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Combination2 {
	public static void main(String[] args) throws NumberFormatException, IOException {

		int idxs[];

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());

		idxs = new int[n];

		for (int i = 0; i < n; i++) {
			idxs[i] = i;
		}

		int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		while (idxs[0] != nums.length - n+1) {
			int[] tmp = idxs;
			
			for (int j = idxs[idxs.length-1]; j < nums.length; j++) {
//				System.out.println(Arrays.toString(tmp));
//				for(int idx : idxs) {
//					System.out.print(nums[idx]+" ");
//				}
//				System.out.println();
				tmp[tmp.length-1] += 1;
			}
//				System.out.println();
//				for(int ik=idxs.length-1; ik>0; ik--) {
//					if(idxs[ik] == nums.length ) {
//						tmp[ik-1] +=1;
//						tmp[ik] = tmp[ik-1]+1;
//					}
//				}
			int len = idxs.length;
			System.out.println(Arrays.toString(idxs));
			for (int k = 0; k < len; k++) {
				System.out.printf(tmp[k]+" ");
				if (idxs[len - 1 - k] == nums.length -1  - k) {
					tmp[len - k - 2] += 1;
					tmp[len - k -1] = tmp[len - k - 2] + 1;
				}
			}
			System.out.println();
//			idxs = Arrays.copyOf(tmp, idxs.length);
//			System.out.println(Arrays.toString(idxs));
		}

	}
}
