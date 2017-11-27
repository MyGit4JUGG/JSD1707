package beans.product;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.ttms.product.dao.ProjectDAO;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;


public class TestProjectService {
	ClassPathXmlApplicationContext ctx;
	
	@Before
	public void init(){
		ctx=new ClassPathXmlApplicationContext("spring-mvc.xml","spring-mybatis.xml");
		
	}
	@Test
	public void testFindObjects(){
		//1.��ȡҵ�����
		ProjectService projectService=ctx.getBean(
				"projectServiceImpl",ProjectService.class);

		
		//2.����ҵ����󷽷�
		List<Project> list=projectService.findObjects();
		System.out.println(list);
		//3.���Խ���Ƿ���ȷ
		Assert.assertNotEquals(null, list);
		//��ǰ���������ܼ���3�������������Ƿ�������
		Assert.assertEquals(3, list.size());
		System.out.println(list);
	}
	@Test
	public void testFindPageObjects(){
		/*ProjectService projectService=ctx.getBean(
				"projectServiceImpl",ProjectService.class);
		Map<String, Object> map=projectService.findPageObjects(1);
		List<Project> list=(List<Project>)map.get("list");
		Assert.assertEquals(2, list.size());
		System.out.println("list:"+list);*/
	}
	@Test
	public void testSaveObject(){
		ProjectService projectService=ctx.getBean(
				"projectServiceImpl",ProjectService.class);
		Project p=new Project();
		p.setName("��ŷ��");
		p.setCode("tt-20171116-CN-BJ-001");
		p.setBeginDate(new Date());
		p.setEndDate(new Date());
		p.setValid(1);
		p.setNote("ȥ�����˸���");
		projectService.saveObject(p);
		System.out.println("insert ok");
	}
	
	@After
	public void destroy(){
		ctx.close();
	}
	

}























