package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s  = "abc";
		String s1 = "";
		
		String date1 ="20201212";
		String date2 = "20200121";
		
		System.out.println(date2.compareTo(date1));
		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("ISSUE_DT", "123-456-78900");
		System.out.println(removeHyphen(map));
		
		System.out.println("s".compareTo("s"));
		
		
	}

	
	protected static String removeHyphen(Map<?,?> resItem) {
		
		
		
		String result = (String) resItem.get("ISSUE_DT");
		if(!"".equals(result)) {
			int idx = result.indexOf("-");
			if(idx > -1) {
				result = result.replaceAll("-", "");
			}
		}
		return result;
	}
	
}
