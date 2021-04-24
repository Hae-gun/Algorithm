package bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		System.out.println(bytesToHex1(sha256(s)));
	}
	
	public static byte[] sha256(String msg) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(msg.getBytes());
		
		return md.digest();
	}
	public static String bytesToHex1(byte[] bytes) {
	    StringBuilder builder = new StringBuilder();
	    for (byte b: bytes) {
	      builder.append(String.format("%02x", b));
	    }
	    return builder.toString();
	}


}

