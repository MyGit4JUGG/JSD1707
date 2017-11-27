$(document).ready(function(){
	doGetObjects();
	$("#queryFormId")
	.on("click",".btn-search",doQueryObjects)
	.on("click",".btn-valid,.btn-invalid",doValidById)
	.on("click",".btn-add,.btn-update",doShowEditDialog)
});
function doShowEditDialog(){
	var title;
	if($(this).hasClass("btn-add")){
		title="添加项目信息";
	}
	if($(this).hasClass("btn-update")){
		//获取button所在行的id值
		var id=$(this).parent().parent().data("id");
		//将id值绑定到模态框上(根据此id判定要执行添加还是修改)
		$("#modal-dialog").data("id",id);
		title="修改项目信息,id="+id;
	}
	
	var url="project/editUI.do"
	$("#modal-dialog .modal-body").load(url,function(){
		$("#modal-dialog").modal('show');
		$(".modal-title").html(title);
	});
	
}
/*禁用或启用项目信息*/
function doValidById(){
	//console.log("doValidById");
	//1.根据操作(禁用，启动)设置状态信息
	var valid;
	if($(this).hasClass("btn-valid")){
		valid=1;
	}
	if($(this).hasClass("btn-invalid")){
		valid=0;
	}
	//console.log("valid="+valid)
	//2.获取选中(checkbox)的项目id值
	var checkedIds=getCheckedIds();
	if(checkedIds==""){
		alert("请至少选择一个");
		return;
	}
	//3.发起异步请求，禁用或启用项目信息
	var url="project/doValidById.do";
	var params={"checkedIds":checkedIds,"valid":valid};
	$.post(url,params,function(result){
		//console.log(result.state);
		if(result.state==1){
			alert("update ok");
			doGetObjects();	
		}else{
			alert(result.message);
		}
		
	});
}
function getCheckedIds(){
	var checkedIds="";
	//each遍历tbody下名为checkItem的input
	$("#tbodyId input[name=checkItem]")
	.each(function(){
		if($(this).prop("checked")){//checked等于true表示选中
			if(checkedIds==""){//1,2,3,4
				checkedIds+=$(this).val();
			}else{
				checkedIds+=","+$(this).val();
			}
		}
	});
	//console.log("checkedIds="+checkedIds);
	return checkedIds;
}
function doQueryObjects() {
	//1.初始化当前页码信息
	$('#pageId').data("pageCurrent",1);
	//2.执行查询操作
	//2.1获得表单数据
	
	//2.2提交表单数据执行查询
	doGetObjects();
}
//获取表单数据
function getQueryFormData() {
	var params={
			"name":$("#searchNameId").val(),
			"valid":$("#searchValidId").val()
	}
	return params;
}
function doGetObjects() {
	var url="project/doFindPageObjects.do";
	//2.获得表单数据（查询时用）
	var params=getQueryFormData();
	//3.设置动态分页页码数据
	var pageCurrent=$('#pageId').data("pageCurrent");
	if(!pageCurrent){
		
		pageCurrent=1;
	}
	//动态添加K，V,.后面是K，=后面是V
	params.pageCurrent=pageCurrent;
	/*var params={"pageCurrent":pageCurrent}*/
	//console.log();
	/*$.ajax({
		//1.定义访问项目信息的url
		url:url,
		//2.发起异步ajax请求
		type:"get",
		success:function(result){
			console.log(result);
		}
	});*/
	
	/*
	 * 服务端:list<project>--->json串
	 * 客户端：Json串-->Json对象：Array
	 */
	//4.发起异步ajax请求{name:"",valid:"",pageCurrent:1}
	$.getJSON(url,params,function(result){//JsonResult对象
		//console.log(result);
		if(result.state==1){
		//设置当前页数据
		setTableBodyRows(result.data.list);
		//设置分页信息
		setPagination(result.data.pageObject);
		}
		else{
			alert(result.message);
		}
	});
}

//将服务端返回的Json对象数据显示在页面上
function setTableBodyRows(result) {
	//1.获取具体dom对象（显示数据位置的那个dom对象）
	var tBody=$("#tbodyId");//jQuery的函数
	tBody.empty();
	//2.迭代result对象
	for(var i in result){//for(var i=0;i<result.length;i++)
		//2.1构建tr对象
		var tr=$("<tr></tr>")
		//将id的值绑定在tr对象上
		tr.data('id',result[i].id);
		//2.2构建td对象
		var tds=
			"<td><input type='checkbox' name='checkItem' value='"+result[i].id+"'></td>"+
			"<td>"+result[i].code+"</td>"+
			"<td>"+result[i].name+"</td>"+
			"<td>"+result[i].beginDate+"</td>"+
			"<td>"+result[i].endDate+"</td>"+
			"<td>"+(result[i].valid ?"启用":"禁止")+"</td>"+
			"<td><button type='button' class='btn btn-default btn-update'>修改</button></td>";
		//2.3将td对象添加到tr中
			tr.append(tds);
		//2.4将tr对象添加到tbody中
			tBody.append(tr);		
	}
}
