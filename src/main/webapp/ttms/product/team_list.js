$(document).ready(function(){
	doGetObjects();
	$("#queryFormId").on("click",".btn-search",doQueryObjects)
});
function doQueryObjects(){
	//初始化首页
	$('#pageId').data("pageCurrent",1);
	//查询
	console.log("111111");
	doGetObjects();
	console.log("1112221");
}
function doGetObjects(){
	var url="team/doFindPageObjects.do";
	var params=getQueryFormData();
	console.log("params="+params);
	//获取绑定在pageId上的当前页面的值
	var pageCurrent=$("#pageId").data("pageCurrent");
	if(!pageCurrent)pageCurrent=1;
	//将当前页面的值以K，V的形式加进params
	params.pageCurrent=pageCurrent;
	$.getJSON(url,params,function(result){
		if(result.state==1){
			//把数据显示到页面
			setTableBodyRows(result.data.list);
			//设置分页
			setPagination(result.data.pageObject);
		}else{
			alert(result.message);
		}
	})
}
//获取表单的数据(输入框的值)
function getQueryFormData(){
	var params={
			"projectName":$("#searchPrjId").val()
	}
	return params;
}
//把数据显示到页面
function setTableBodyRows(list){
	var tBody=$("#tbodyId");
	tBody.empty();
	var tds=
	"<td><input type='checkbox' name='checkItem' " +
	"value='[id]'></td>"+
	"<td>[name]</td>"+
	"<td>[projectName]</td>"+
	"<td>[state]</td>"+
	"<td>修改</td>"
	for(var i in list){
		var tr=$("<tr></tr>");
		tBody.append(tr.append(tds.replace('[id]',list[i].id)
		   .replace('[name]',list[i].name)
		   .replace('[projectName]',list[i].projectName)
		   .replace('[state]',list[i].valid?"启用":"禁用")))
	}
}







