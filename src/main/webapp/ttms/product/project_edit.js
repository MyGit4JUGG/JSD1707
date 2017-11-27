$(function(){
	//1.模态框上绑定click事件
	$("#modal-dialog").on("click",".ok",doSaveOrUpdate);
	//2.模态框隐藏时解除click事件
	$("#modal-dialog").on("hidden.bs.modal",function(){
		$("#modal-dialog").off("click",".ok").removeData("id");
	});
	//3.获取模态框上绑定的id值(可能有值,也可能没有)
	var id=$("#modal-dialog").data("id");
	//假如id有值,一定是修改,然后根据id查记录,将记录信息初始化到表单中
	if(id){
		doFindObjectById(id);
	}
})
function doFindObjectById(id){
	var url="project/doFindObjectById.do"
	var params={"id":id};
	$.getJSON(url,params,function(result){
		if(result.state==1){
			setEditFormData(result.data)
		}else{
			alert(result.message);
		}
	});
}
function setEditFormData(result){
	//赋值,val().括号里面没参数就是取值，有参数就是给里面的对象赋值
	$("#nameId").val(result.name);
	$("#codeId").val(result.code);
	$("#beginDateId").val(result.beginDate);
	$("#endDateId").val(result.endDate);
	$("#noteId").html(result.note);
	$("#editFormId input[name='valid']")//radio
	//迭代input标签中name为valid的dom对象
	.each(function(){
		//假如这个对象的值等于result.valid的值,则让其选中.
		if($(this).val()==result.valid){
		   //设置radio对象的checked属性为true,prop相当于attr
		   $(this).prop("checked",true)
		}
	});
}

function doSaveOrUpdate(){
	//对表单进行非空验证
	if(!$("#editFormId").valid())return;
	//1.获取表单数据
	var params=getEditFormData();
	//2.异步提交数据
	var url;
	var id=$("#modal-dialog").data("id");
	console.log(id);
	if(id){
		//动态把id加进params
		params.id=id;//当K不是一个单词时:params['stu-id']=id;
		url="project/doUpdateObjects.do"
	}else{
		url="project/doSaveObjects.do"
	}
	console.log(params);
	$.post(url,params,function(result){
		//console.log(result.state);
		if(result.state==1){
			alert("操作成功");
			doGetObjects();
			$("#modal-dialog").modal('hide');
		}else{
			alert("操作失败");
		}
	});
}
//获取表单数据
function getEditFormData(){
	var params={
			name:$("#nameId").val(),
			code:$("#codeId").val(),
			beginDate:$("#beginDateId").val(),
			endDate:$("#endDateId").val(),
			valid:$("#editFormId input[name='valid']:checked").val(),
			note:$("#noteId").val()
	}
	console.log(JSON.stringify(params));
	return params;
}