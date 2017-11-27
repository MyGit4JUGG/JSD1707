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
@Service//����û��ָ������Ĭ��idΪprojectServiceImpl
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
		//1.��֤��������Ч��
		if(pageCurrent==null||pageCurrent<1)
			throw new ServiceException("����ֵ��Ч,pageCurrent="+pageCurrent);
		//2.��ȡ��ǰҳ����
		//2.1����startIndex��ֵ
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		//2.2����startIndex��ֵ��ȡ��ǰҳ����
		List<Project> list=
				projectDao.findPageObjects(name,valid,startIndex,pageSize);
		//2.3��ȡ�ܼ�¼�������ݴ�ֵ������ҳ����
		int rowCount=projectDao.getRowCount(name,valid);
		/*int pageCount=rowCount/pageSize;
		if(rowCount=rowCount/pageSize!=0)pageCount++;*/
		PageObject pageObject=new PageObject();
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setStartIndex(startIndex);
		//2.4��װ��ѯ����ͼ�������map����
		/*
		 * 1��HashMap�ײ�ṹ��������+����+�������-->jdk1.8
		 * 2��HashMap�Ƿ��̰߳�ȫ(����ȫ�����̲߳�������)
		 * 3��HashMap�Ƿ��ܱ�֤���Ԫ�ص������ԣ������ܣ�
		 * ����ϣ����֤�����ԣ�����ѡ��LinkedHashMap��
		 * 4��HashMap�ڲ������������ʹ�ã�������ת��Ϊͬ������,
		 * ������Collections.synchronizedMap
		 * ����ֱ��ʹ��ConcurrentHashMap��
		 */
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("list", list);
		map.put("pageObject", pageObject);
		return map;
	}
	
	@Override
	public void validById(String checkedIds, Integer valid) {
		//1.��֤ҵ�����ݵ���Ч��
		if(StringUtils.isEmpty(checkedIds)){
			throw new ServiceException("����ѡ��");
		}
		if(valid!=0&&valid!=1){
			throw new ServiceException("���û���õ�״ֵ̬����ȷ");
		}
		String[] ids=checkedIds.split(",");
		//2.ִ�и��²���
		int rows=projectDao.validById(ids, valid);
		//3.��֤���½��(�ɹ���ʧ��)
		if(rows<=0){
			throw new ServiceException("����ʧ��");
		}	
	}
	@Override
	public void saveObject(Project entity) {
		//1.��֤��������Ч��
		if(entity==null){
			throw new ServiceException("���������Ϊ��");
		}
		//2.ִ�б������
		int rows=
				projectDao.saveObject(entity);
		//3.��֤���
		if(rows<=0){
			throw new ServiceException("����ʧ��");
		}
	}
	@Override
	public Project findObjectById(Integer id) {
		System.out.println("findObjectById()");
		//��֤id����Ч��
		if(id==null||id<1){
			throw new ServiceException("id��ֵ��Ч");
		}
		Project p=
				projectDao.findObjectById(id);
		//��֤���
		if(p==null){
			throw new ServiceException("�˼�¼������");
		}
		return p;
	}
	
	@Override
	public void updateObject(Project entity) {
		System.out.println("updateObject()");
		if(entity==null){
			throw new ServiceException("�������ݲ���Ϊ��");
		}
		int rows=
				projectDao.updateObject(entity);
		if(rows<=0){
			throw new ServiceException("�޸�ʧ��");
		}
	}
	
}


















