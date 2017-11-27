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
public class DateUtil {
	private static SimpleDateFormat sdf=new SimpleDateFormat();
	
	public static synchronized String format(Date date){
		return sdf.format(date);
		
	}
	
	public static synchronized Date parse(String str)
			throws ParseException{
		return sdf.parse(str) ;
		
	}

}
