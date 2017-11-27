package cn.tedu.ttms.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TeamDao {
	/**
	 * 分页获取团目信息
	 * @param projectName 页面上的查询条件,根据项目进行查询
	 * @param startIndex 查询当前页数据的起始位置
	 * @param pageSize	当前页面的大小，即每页最多显示多少条数据
	 * @return
	 */
	List<Map<String,Object>> findPageObjects(
			@Param("projectName")String projectName,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	int getRowCount(
			@Param("projectName")String projectName);
}















