package cn.tedu.ttms.product.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.ttms.common.web.JsonResult;
import cn.tedu.ttms.product.entity.ProductType;
import cn.tedu.ttms.product.service.ProductTypeService;

@RequestMapping("/type/")
@Controller
public class ProductTypeController {
	@Autowired
	private ProductTypeService productTypeService;
	
	@RequestMapping("listUI")
	public String listUI(){
		return "product/type_list";
	}
	@RequestMapping("editUI")
	public String editUI(){
		return "product/type_edit";
	}
	
	@RequestMapping("doFindTreeGridNodes")
	@ResponseBody
	public JsonResult doFindTreeGridNodes(){
		List<Map<String,Object>> list=
				productTypeService.findTreeGridNodes();
		//System.out.println(list);
		return new JsonResult(list);	
	}
	
	@RequestMapping("doDeleteById")
	@ResponseBody
	public JsonResult doDeleteById(Integer id){
		
		productTypeService.deleteById(id);
		
		return new JsonResult();
	}
	
	@RequestMapping("doFindZtreeNodes")
	@ResponseBody
	public JsonResult doFindZtreeNodes(){
	System.out.println(productTypeService.findZtreeNodes());	
		return new JsonResult(
				productTypeService.findZtreeNodes());
	}	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(ProductType productType){
		
		productTypeService.saveObject(productType);
		
		return new JsonResult();
	}
}














