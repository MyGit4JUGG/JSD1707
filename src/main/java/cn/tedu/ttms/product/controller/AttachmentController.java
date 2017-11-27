package cn.tedu.ttms.product.controller;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.ttms.common.web.JsonResult;

@Controller
@RequestMapping("/attach/")
public class AttachmentController {
	private Logger log;
	@RequestMapping("attachUI")
	public String attachUI(){
		
		return "attachment/attachment"; 
	}
	@RequestMapping("doUpload")
	@ResponseBody
	public JsonResult doUpload(String title,MultipartFile mfile) throws IOException{
		
		return new JsonResult();
	}
}
