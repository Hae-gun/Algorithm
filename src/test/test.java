package test;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class test {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
		
		String a = "a^b^c^";
		
		String[] set = a.split("\\^", 4);
		List<String> list = Arrays.asList(set);	
		System.out.println(list);	
		}
	public static void throwException() throws IOException{
		throw new SocketTimeoutException();
	}

}
class test2{
	VO vo = new VO();
	public test2() {}
	
	int getInteger(String num) throws Exception {
		int re = vo.toInteger(num);
		return re;
	}
}

class VO{
	int[] set;
	public VO() {}
	public VO(int[] set) {
		this.set = set;
	}
	int[] getSet() {
		return this.set.clone();
	}
	int toInteger(String number) throws Exception {
		int result=0;
		try {
			result =Integer.parseInt(number); 
		}catch(NumberFormatException e) {
//			System.out.println(e);
//			throw e;
		}
		return result;
	}
	
}