package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.product.entity.Project;

/**业务层接口对象（负责业务的处理）
 * 1）业务逻辑的验证
 * 2）事务处理
 * 3）日志处理
 * 4）缓存处理
 * 5）权限处理
 * 6）...
 * */
public interface ProjectService {
	List<Project> findObjects();
	/**
	 * 
	 * @param pageCurrent
	 * @return 返回当前页数据及分页信息
	 */
	Map<String,Object> findPageObjects(String name,
			Integer valid,
			Integer pageCurrent); 
	
	/**
	 * 禁用启用项目信息
	 * @param ckeckedIds
	 * @param valid
	 */
	void validById(String checkedIds,Integer valid);
	
	Project findObjectById(Integer id);
	
	void saveObject(Project entity);
	
	void updateObject(Project entity);
}




