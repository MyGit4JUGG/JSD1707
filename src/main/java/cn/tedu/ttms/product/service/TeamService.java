package cn.tedu.ttms.product.service;

import java.util.Map;

public interface TeamService {
	//分页查询团目信息
	Map<String,Object> findPageObjects(
			String projectName,
			Integer pageCurrent);
}
