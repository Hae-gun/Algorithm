package sandbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

public class URLConnectionTest {
	public static void main(String[] args) {
		try {
		URL url = new URL("https://goddaehee.tistory.com/161");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		URLDecoder decode = new URLDecoder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String temp = "";
		String delete = "								<a class=\"link_sub_item\" href=\"/category/";
		while((temp = br.readLine()) != null) {
			if(temp.contains("link_sub_item"))
				System.out.println(decode.decode(temp, "utf-8").replaceAll(delete, "").replaceAll("\">", "").replaceAll(" ","_"));
		}
		}catch (MalformedURLException e) {
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
