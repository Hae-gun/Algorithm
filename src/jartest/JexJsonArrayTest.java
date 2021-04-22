package jartest;

import java.util.Arrays;

public class JexJsonArrayTest {
	public static void main(String[] args) {
		
		
		String set = "01,02,03,04";
		String[] aSet = {"01","04","05"};
		
		MyClass[] mySet = new MyClass[3];
		for(int i=0; i<mySet.length; i++) {
			mySet[i] = new MyClass();
		}
		int idx = 0;
		for(MyClass c : mySet) {
			c.setName(""+idx++);
			
		}
		
//		System.out.println(Arrays.toString(mySet));
		String test = "abc";
		for(MyEnum e : MyEnum.values()) {
			System.out.println(e.getValue());
		}
	}
}
enum MyEnum{
	ENUM1("123"), ENUM2("456");
	
	final private String value;
	
	private MyEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
class MyClass {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MyClass [name=" + name + "]";
	}
}

interface Supplier<T>{
	void get(String s);	
}

