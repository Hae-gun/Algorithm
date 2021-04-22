package test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;

public class DateFormat {
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		
		String s = new String("안녕하세요감사합니다.".getBytes(),"euc-kr");
		int cutlen = 10;
		StringBuffer sb = new StringBuffer(cutlen);
		
		String fromSet = "euc-kr"; 
		String toSet = "utf-8";
		int nCnt = 0;
		for(char ch : s.toCharArray()) {
			nCnt += String.valueOf(ch).getBytes(fromSet).length;
			if(nCnt > cutlen) break;
			sb.append(ch);
		}
		
		String result = new String(sb.toString().getBytes(),toSet);
		
		
		String uri = "./a/b/c/d/abc.txt";
		String fileName = FilenameUtils.getName(uri);
		String path = Paths.get(uri).toString();
		String getParent = Paths.get(uri).getParent().toString();
		String getRoot = Paths.get(uri).getRoot() + fileName;
		System.out.println(fileName);
		System.out.println(getParent);
		System.out.println(getRoot + fileName);
		
		System.out.println("TEST>>" + path);
	}
}

class FilenameUtils {
	private static final char UNIX_SEPARATOR = '/';
	private static final char WINDOWS_SEPARATOR = '\\';

	private static void failIfNullBytePresent(String path) {
		int len = path.length();

		for (int i = 0; i < len; ++i) {
			if (path.charAt(i) == 0) {
				throw new IllegalArgumentException(
						"Null byte present in file/path name. There are no known legitimate use cases for such data, but several injection attacks may use it");
			}
		}

	}

	private static int indexOfLastSeparator(String fileName) {
		if (fileName == null) {
			return -1;
		} else {
			int lastUnixPos = fileName.lastIndexOf(47);
			int lastWindowsPos = fileName.lastIndexOf(92);
			return Math.max(lastUnixPos, lastWindowsPos);
		}
	}

	public static String getName(String fileName) {
		if (fileName == null) {
			return null;
		} else {
			failIfNullBytePresent(fileName);
			int index = indexOfLastSeparator(fileName);
			return fileName.substring(index + 1);
		}
	}
}
