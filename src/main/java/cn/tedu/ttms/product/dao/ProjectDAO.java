package cn.tedu.ttms.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.ttms.product.entity.Project;
/**�־ò���󣺸������ݵĳ־û�����*/
public interface ProjectDAO {
	/**
	 * �����ݿ��ѯ�����������ݣ���Ŀ��Ϣ��
	 * 1��һ�м�¼��װһ��project����
	 * 2�����м�¼��Ӧ�Ķ��project�����ڷ�װlist����
	 * @return
	 */
	/**
	 * @Param startIndex ����limit�����offset����ʾ���Ŀ�ʼ
	 * @Param pageSize ÿҳ�����ʾ��������¼
	 * @param ��MyBatis�е�һ�����ڶ��������ע�⣬һ��dao�����е�
	 * ������������һ��ʱ������ʹ�ô�ע�����������
	 * @return
	 */
	List<Project> findObjects();
	
	List<Project> findPageObjects(
			@Param("name")String name,
			@Param("valid")Integer valid,
			@Param("startIndex")int startIndex,
			@Param("pageSize")int pageSize);
	//��ȡ���м�¼��������������Ҫ����������������ҳ��
	int getRowCount(
			@Param("name")String name,
			@Param("valid")Integer valid
			);
	/**
	 * ִ�н��ú����ò���ʱ��ִ�д˺���
	 * @param valid Ҫ���ú�����
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



















