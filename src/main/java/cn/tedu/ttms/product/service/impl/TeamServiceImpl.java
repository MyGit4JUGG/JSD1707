package cn.tedu.ttms.product.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.web.PageObject;
import cn.tedu.ttms.product.dao.TeamDao;
import cn.tedu.ttms.product.service.TeamService;
@Service
public class TeamServiceImpl implements TeamService{
	@Autowired
	private TeamDao teamDao;

	@Override
	public Map<String, Object> findPageObjects(
		//projectName和pageCurrent为页面返回的数据
			String projectName, Integer pageCurrent) {
		System.out.println("111");
		//验证数据有效性
		if(pageCurrent==null||pageCurrent<=0){
			throw new ServiceException("当前页面值无效");
			
		}
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		//获取当前数据
		List<Map<String,Object>> list=
				//通过Mapper配置中的SQL语句查询结果
				teamDao.findPageObjects(projectName, startIndex, pageSize);
		//获取总记录数，计算封装分页信息
		int rowCount=
				//通过Mapper配置中的SQL语句查询结果
				teamDao.getRowCount(projectName);
		PageObject pageObject=new PageObject();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setStartIndex(startIndex);
		//封装当前数据及分页信息到map集合
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		
		return map;
	}
}













