package rpc.test;

import java.io.UnsupportedEncodingException;

public class T3 {
	public static void main(String[] args) {
		
		String s1 = "你好"; 
		try {
			String s2 = new String(s1.getBytes(),"UTF-8");
			System.out.println(s2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			A a = new A();
//			a.name="123";
//			A b = (A) a.clone();
//			System.out.println(b.name);
//			b.name="234";
//			System.out.println(a.name);
//			System.out.println(b.name);
//		} catch (Exception e) {
//			
//		}
	}

	
}

class A implements Cloneable{
	String name ;
	String age;
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
