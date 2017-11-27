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
		//���ص����view�ύ��springMVC����ͼ���������н���
		return "product/project_list";
	}//WEB-INF/pages/product/project_list.jsp
	
	@RequestMapping("doFindObjects")//@RequestMapping�����Ƕ���������Դӳ��
	
	@ResponseBody//���ã�������������API���������ص�����ת��ΪJson��ʽ���ַ���
	
	/*spring���ַ������д�ע��(@ResponseBody)����ʱ���ײ������������API
	 * ���������ص�����ת��ΪJson��ʽ���ַ���
	 * �������jackson����API�ײ����ö����getxxx������ȡ���ݣ�
	 * "[
	 * 		{"id":1,"name":��ŷ��}��
	 * 		{"id":2,"name":������},
	 * ]"
	 * ����http://localhost:8089/ttms1.01/project/doFindObjects.do��
	 * ���û�и�ע��ᱨ404����
	 * */
	public List<Project> doFindObjects(){//ModelAndView
		
		List<Project> list=
				projectService.findObjects();
		return list;
		
	}//400���ͻ��˴��䵽�����ʱ��ʽ����,406
	//Springͨ�����ϵ�������API������jackson��ʵ����JSON���ݵ�ת��
	
	/**
	 * ���������ֱ���������µ�ַ���з���
	 * http://localhost:8089/ttms1.01/project/doFindPageObjects.do?pageCurrent=1
	 * @param pageCurrent
	 * @return
	 * spring mvc ���Զ��������еĲ������ݰ���ָ�����ͣ�
	 * ��������ת����Ȼ��ֵ����Ӧ����(������ͬ�Ĳ�����һ����ͨ�������ȡ������Ϣ)
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
			 * ��ִ��������ݱ���ʱ�������쳣��spring-mvc��δ���
			 * 1.�ȼ�⵱ǰ���Ʋ���������Ƿ���������쳣�Ĵ���
			 * 2.�����˾�ֱ�ӵ����쳣����������
			 * 3.û�ж���Ҫ����Ƿ���ȫ���쳣������
			 * Ȼ����ȫ���쳣�������в��Ҷ�Ӧ���쳣������������
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















