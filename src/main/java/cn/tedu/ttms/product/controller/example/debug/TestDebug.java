package cn.tedu.ttms.product.controller.example.debug;
/*
 * Debug模式
 * f5(进入方法内部)
 * f6(单步执行)
 * f7(从方法中退出)
 * f8(运行结束)
 */
public class TestDebug {
	static int get(){
		int a=10;
		try{
			return a;
		}finally{
			a++;
		/*	return a;*/
		}
	}
	public static void main(String[] args) {
		System.out.println("main");
		int s=get();
		System.out.println(s);
	}
}
