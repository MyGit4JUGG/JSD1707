package cn.tedu.ttms.product.service;

import java.util.List;
import java.util.Map;

import cn.tedu.ttms.product.entity.Project;

/**ҵ���ӿڶ��󣨸���ҵ��Ĵ���
 * 1��ҵ���߼�����֤
 * 2��������
 * 3����־����
 * 4�����洦��
 * 5��Ȩ�޴���
 * 6��...
 * */
public interface ProjectService {
	List<Project> findObjects();
	/**
	 * 
	 * @param pageCurrent
	 * @return ���ص�ǰҳ���ݼ���ҳ��Ϣ
	 */
	Map<String,Object> findPageObjects(String name,
			Integer valid,
			Integer pageCurrent); 
	
	/**
	 * ����������Ŀ��Ϣ
	 * @param ckeckedIds
	 * @param valid
	 */
	void validById(String checkedIds,Integer valid);
	
	Project findObjectById(Integer id);
	
	void saveObject(Project entity);
	
	void updateObject(Project entity);
}




