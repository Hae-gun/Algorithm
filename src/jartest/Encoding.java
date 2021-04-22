package jartest;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Encoding {
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String s = new String("abcdefghijklmnop".getBytes(),"8859_1");
		
		String temp = subStrByte(s,  30, "euc-kr", "utf-8");
		System.out.println(temp);
		
		
		
		
		
	}
	private static String setCharEncoding(String str ) throws UnsupportedEncodingException {

		String rtrStr = "";

		byte [] tmpBytes = str.getBytes("KSC5601");

		rtrStr = new String(tmpBytes, "8859_1");
		return rtrStr;
	}
	
	
	private static String subStrByte(String str, int cutlen, String fromSet, String toSet) throws UnsupportedEncodingException {

		if(!str.isEmpty()) {
			str = str.trim();
			if(str.getBytes(fromSet).length <= cutlen) { //euc-kr
				return str;
			}else {
				StringBuffer sbStr = new StringBuffer(cutlen);
				int nCnt = 0;
				for (char ch : str.toCharArray()) {
					nCnt += String.valueOf(ch).getBytes(fromSet).length; //euc-kr
					if(nCnt > cutlen)break;
					sbStr.append(ch);
				}
				String rtrStr = new String(sbStr.toString().getBytes(), toSet); //utf-8
				return rtrStr;
			}
		}else {
			return "";
		}
	}
	
}
