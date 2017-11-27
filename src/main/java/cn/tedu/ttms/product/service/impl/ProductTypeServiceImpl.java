package cn.tedu.ttms.product.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.ttms.common.exception.ServiceException;
import cn.tedu.ttms.common.vo.Node;
import cn.tedu.ttms.product.dao.ProductTypeDao;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;
@Service
public class ProductTypeServiceImpl implements ProductTypeService{
	@Autowired
	private ProductTypeDao  productTypeDao;
	@Override
	public List<Map<String, Object>> findTreeGridNodes() {
		
		return productTypeDao.findTreeGridNodes();
	}
	@Override
	public void deleteById(Integer id) {
		//1.判断id有效性
		if(id==null||id<1)throw new ServiceException("id值无效");
		//2.查询此id对应的记录有没有子元素
		int count=productTypeDao.hasChilds(id);
		if(count>0){
			throw new ServiceException("此记录有子元素不允许删除");
		}
		//3.删除此元素
		int rows=productTypeDao.deleteById(id);
		if(rows<=0){
			throw new ServiceException("删除失败，可能此元素已经不存在");
		}
	}
	@Override
	public List<Node> findZtreeNodes() {
		
		return productTypeDao.findZtreeNodes();
	}
	@Override
	public void saveObject(ProductType productType) {
			//验证获得的数据
			if(productType==null)
				throw new ServiceException("保存的数据不能为空");
			//保存到数据库
			int rows=
					productTypeDao.saveObject(productType);
			if(rows<=0){
				throw new ServiceException("保存失败");
			}
	}
}

















