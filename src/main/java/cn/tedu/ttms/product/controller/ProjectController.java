package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;
@RequestMapping("/project/")
@Controller
public class ProjectController {
	
	@Autowired
	@Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@RequestMapping("listUI")
	public String listUI(){
		//返回的这个view会交给springMVC的视图解析器进行解析
		return "product/project_list";
	}//WEB-INF/pages/product/project_list.jsp
	
	@RequestMapping("doFindObjects")//@RequestMapping作用是定义请求资源映射
	
	@ResponseBody//作用：会启动第三方API将方法返回的数据转换为Json格式的字符串
	
	/*spring发现方法上有此注解(@ResponseBody)修饰时，底层会启动第三方API
	 * 将方法返回的数据转换为Json格式的字符串
	 * 列如借助jackson（此API底层会调用对象的getxxx方法获取数据）
	 * "[
	 * 		{"id":1,"name":北欧游}，
	 * 		{"id":2,"name":月球游},
	 * ]"
	 * 访问http://localhost:8089/ttms1.01/project/doFindObjects.do，
	 * 如果没有该注解会报404错误。
	 * */
	public List<Project> doFindObjects(){//ModelAndView
		
		List<Project> list=
				projectService.findObjects();
		return list;
		
	}//400（客户端传输到服务端时格式出错）,406
	//Spring通过整合第三方的API（列如jackson）实现了JSON数据的转换
	
	/**
	 * 在浏览器中直接输入如下地址进行访问
	 * http://localhost:8089/ttms1.01/project/doFindPageObjects.do?pageCurrent=1
	 * @param pageCurrent
	 * @return
	 * spring mvc 会自动将请求中的参数数据按照指定类型，
	 * 进行类型转换，然后赋值给对应参数(名字相同的参数，一般是通过反射获取参数信息)
	 */
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
			String name,
			Integer valid, 
			Integer pageCurrent){
			Map<String,Object> map=
					projectService.findPageObjects(name,valid,pageCurrent);
			return new JsonResult(map);
	}
	
	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(String checkedIds,Integer valid){
		
		projectService.validById(checkedIds, valid);
		
		System.out.println("11");
		return new JsonResult();
	}
	
	@RequestMapping("editUI")
	public String editUI(){
		
		return "product/project_edit";
	}
	@RequestMapping("doSaveObjects")
	@ResponseBody
	public JsonResult doSaveObjects(Project entity){
			/*
			 * 当执行这个数据保存时出现了异常，spring-mvc如何处理？
			 * 1.先检测当前控制层对象内容是否定义了这个异常的处理
			 * 2.定义了就直接调用异常处理喊出处理
			 * 3.没有定义要检测是否有全局异常处理类
			 * 然后在全局异常处理类中查找对应的异常处理函数来处理
			 */
			projectService.saveObject(entity);
			return new JsonResult();//message:ok,state:1
	}
	@RequestMapping("doUpdateObjects")
	@ResponseBody
	public JsonResult doUpdateObjects(Project entity){
			
			projectService.updateObject(entity);
			return new JsonResult();//message:ok,state:1
	}
	
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		Project entity=
				projectService.findObjectById(id);
		return new JsonResult(entity);
	}
	
}















