package sandbox;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;

public class URLConnectionTest {
	public static void main(String[] args) {
		String temp = "";
		try {
//		URL url = new URL("https://www.acmicpc.net/problemset");
		URL url = new URL("https://www.acmicpc.net/problemset?sort=no_asc&tier=6%2C7%2C8%2C9%2C10%2C11%2C12%2C13%2C14%2C15&algo=158&algo_if=and");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		URLDecoder decode = new URLDecoder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		FileOutputStream output = new FileOutputStream("D:/board/result.html");
//		String delete = "								<a class=\"link_sub_item\" href=\"/category/";
		while((temp = br.readLine()) != null) {
			temp = temp.replaceAll("><", ">\n<");
			output.write(temp.getBytes());
		}
		}catch (MalformedURLException e) {
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
