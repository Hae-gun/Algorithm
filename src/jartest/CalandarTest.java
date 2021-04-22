package jartest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalandarTest {
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		long date = System.currentTimeMillis();
		System.out.println(getDate(date, "yyyy/MM/dd", 'D', 15) );
		System.out.println(getDate(date, 'D', 15));
		
	}
	public static String getDate(long date, String format, char c, int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		switch (c) {
			case 'D' :
				calendar.add(5, i);
				break;
			case 'H' :
				calendar.add(10, i);
				break;
			case 'I' :
				calendar.add(12, i);
				break;
			case 'M' :
				calendar.add(2, i);
				break;
			case 'S' :
				calendar.add(13, i);
				break;
			case 'W' :
				calendar.add(4, i);
				break;
			case 'Y' :
				calendar.add(1, i);
		}
		DateFormat df = new SimpleDateFormat(format);
		
		String result = df.format(calendar.getTime());
		
		return result;
	}
	
	public static long getDate(long date, char c, int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		switch (c) {
			case 'D' :
				calendar.add(5, i);
				break;
			case 'H' :
				calendar.add(10, i);
				break;
			case 'I' :
				calendar.add(12, i);
				break;
			case 'M' :
				calendar.add(2, i);
				break;
			case 'S' :
				calendar.add(13, i);
				break;
			case 'W' :
				calendar.add(4, i);
				break;
			case 'Y' :
				calendar.add(1, i);
		}

		return calendar.getTime().getTime();
	}
	
}
