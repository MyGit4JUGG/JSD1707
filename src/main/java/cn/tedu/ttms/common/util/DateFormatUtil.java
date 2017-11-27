package cn.tedu.ttms.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * ThreadLocal是Java中的一个API，此对象提供了
 * 这样的一种机制，能够将某个对象绑定到当前线程
 * 也可以从当前线程获取某个对象，目的是保证线程
 * 内部单例（某个类的额实例在当前线程中只有一份）
 * @author Administrator
 *
 */
public class DateFormatUtil {
	/*private static SimpleDateFormat sdf=new SimpleDateFormat();*/
	private static ThreadLocal<SimpleDateFormat> td=
			new ThreadLocal<SimpleDateFormat>();
	private static SimpleDateFormat getInstance(){
		//1.获取当前线程中format对象
		SimpleDateFormat sdf=td.get();
		//2.当前线程加入没有format对象，则先创建后绑定
		if(sdf==null){
			sdf=new SimpleDateFormat("yyyy/MM/dd");
			td.set(sdf);//绑定put(key,value)
		}
		return sdf;
	}
	
	
	
	//静态同步方法默认使用的对象锁为（类名.class）
	public static synchronized String format(Date date){
		return getInstance().format(date);
		
	}
	
	public static synchronized Date parse(String str)
			throws ParseException{
		return getInstance().parse(str) ;
		
	}
	static SimpleDateFormat sdf1;
	static SimpleDateFormat sdf3;
	public static void main(String[] args) {
		
		
		
		Thread t1=new Thread(){
			
			@Override
			public void run() {
				sdf1 = getInstance();
				SimpleDateFormat sdf2=getInstance();
				System.out.println(sdf1==sdf2);
				
				
			}
		
		};
		Thread t2=new Thread(){
			
			@Override
			public void run() {
				sdf3 = getInstance();
				SimpleDateFormat sdf4=getInstance();
				System.out.println(sdf3==sdf4);
			}
		
		};
		System.out.println(sdf1==sdf3);
		
	}


}
