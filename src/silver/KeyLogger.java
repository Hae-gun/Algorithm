package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class KeyLogger {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		List<String> result = new ArrayList<>(); 
		while(n--!=0) {
			char[] line = br.readLine().toCharArray();
			int logIdx = 0;
			LinkedList<Character> words = new LinkedList<>();
			for(char word : line) {
//				System.out.println(words);
				if(word == '<' && logIdx>0) {
					logIdx--;
				}else if(word=='>' && logIdx <words.size()) {
					logIdx++;
				}else if(word != '<' && word != '>' && word !='-'){
					words.add(logIdx++,word);
				}else if(word == '-' && logIdx>0 && logIdx <= words.size()) {
					words.remove(--logIdx);
				}
			}
			String tmp = "";
			for(char c : words) {
				tmp += c;
			}
			result.add(tmp);
//			System.out.println(words);
		}
		for(String s : result) {
			System.out.println(s);
		}
		
	}
}
