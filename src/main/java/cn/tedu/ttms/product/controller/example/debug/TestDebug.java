package cn.tedu.ttms.product.controller.example.debug;
/*
 * Debugģʽ
 * f5(���뷽���ڲ�)
 * f6(����ִ��)
 * f7(�ӷ������˳�)
 * f8(���н���)
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
