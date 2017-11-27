package cn.tedu.ttms.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.product.entity.Project;
/**持久层对象：负责数据的持久化操作*/
public interface ProjectDAO {
	/**
	 * 从数据库查询表中所有数据（项目信息）
	 * 1）一行记录封装一个project对象
	 * 2）多行记录对应的多个project对象在封装list集合
	 * @return
	 */
	/**
	 * @Param startIndex 对象limit语句中offset，表示从哪开始
	 * @Param pageSize 每页最多显示多少条记录
	 * @param 是MyBatis中的一个用于定义参数的注解，一般dao方法中的
	 * 参数个数多于一个时，建议使用此注解进行声明。
	 * @return
	 */
	List<Project> findObjects();
	
	List<Project> findPageObjects(
			@Param("name")String name,
			@Param("valid")Integer valid,
			@Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize);
	//获取表中记录的总行数，我们要根据这个结果计算总页数
	int getRowCount(
			@Param("name")String name,
			@Param("valid")Integer valid
			);
	/**
	 * 执行禁用和启用操作时都执行此函数
	 * @param valid 要禁用和启动
	 * @param ids
	 * @return
	 */
	int validById(
			@Param("ids")String[] ids,
			@Param("valid")Integer valid
			);
	Project findObjectById(Integer id);
	
	int saveObject(Project entity);
	
	int updateObject(Project entity);
}



















