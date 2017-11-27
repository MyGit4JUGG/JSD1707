package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.ProjectDAO;
import cn.tedu.ttms.product.entity.Project;
import cn.tedu.ttms.product.service.ProjectService;
@Service//加入没有指定名字默认id为projectServiceImpl
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectDAO projectDao;
	@Override
	public List<Project> findObjects() {
		// TODO Auto-generated method stub
		return projectDao.findObjects();
	}
	@Override
	public Map<String, Object> findPageObjects(
			String name,
			Integer valid,
			Integer pageCurrent) {
		//1.验证参数的有效性
		if(pageCurrent==null||pageCurrent<1)
			throw new ServiceException("参数值无效,pageCurrent="+pageCurrent);
		//2.获取当前页数据
		//2.1计算startIndex的值
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		//2.2根据startIndex的值获取当前页数据
		List<Project> list=
				projectDao.findPageObjects(name,valid,startIndex,pageSize);
		//2.3获取总记录数（根据此值计算总页数）
		int rowCount=projectDao.getRowCount(name,valid);
		/*int pageCount=rowCount/pageSize;
		if(rowCount=rowCount/pageSize!=0)pageCount++;*/
		PageObject pageObject=new PageObject();
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setStartIndex(startIndex);
		//2.4封装查询结果和计算结果到map对象
		/*
		 * 1）HashMap底层结构？（数组+链表+红黑树）-->jdk1.8
		 * 2）HashMap是否线程安全(不安全，多线程并发访问)
		 * 3）HashMap是佛能保证添加元素的有序性？（不能，
		 * 假如希望保证有序性，可以选择LinkedHashMap）
		 * 4）HashMap在并发场景下如何使用？（将其转化为同步集合,
		 * 借助于Collections.synchronizedMap
		 * 或者直接使用ConcurrentHashMap）
		 */
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		return map;
	}
	
	@Override
	public void validById(String checkedIds, Integer valid) {
		//1.验证业务数据的有效性
		if(StringUtils.isEmpty(checkedIds)){
			throw new ServiceException("请先选择");
		}
		if(valid!=0&&valid!=1){
			throw new ServiceException("启用或禁用的状态值不正确");
		}
		String[] ids=checkedIds.split(",");
		//2.执行更新操作
		int rows=projectDao.validById(ids, valid);
		//3.验证更新结果(成功，失败)
		if(rows<=0){
			throw new ServiceException("更新失败");
		}	
	}
	@Override
	public void saveObject(Project entity) {
		//1.验证参数的有效性
		if(entity==null){
			throw new ServiceException("保存对象不能为空");
		}
		//2.执行保存操作
		int rows=
				projectDao.saveObject(entity);
		//3.验证结果
		if(rows<=0){
			throw new ServiceException("保存失败");
		}
	}
	@Override
	public Project findObjectById(Integer id) {
		System.out.println("findObjectById()");
		//验证id的有效性
		if(id==null||id<1){
			throw new ServiceException("id的值无效");
		}
		Project p=
				projectDao.findObjectById(id);
		//验证结果
		if(p==null){
			throw new ServiceException("此记录不存在");
		}
		return p;
	}
	
	@Override
	public void updateObject(Project entity) {
		System.out.println("updateObject()");
		if(entity==null){
			throw new ServiceException("更新数据不能为空");
		}
		int rows=
				projectDao.updateObject(entity);
		if(rows<=0){
			throw new ServiceException("修改失败");
		}
	}
	
}


















