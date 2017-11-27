var zTree;
var setting = {
		data : {   
			simpleData : {
				enable : true,
				idKey : "id",  //节点数据中保存唯一标识的属性名称
				pIdKey : "parentId",  //节点数据中保存其父节点唯一标识的属性名称
				rootPId : null  //根节点id
			}
		}
}
$(function(){
	$("#editTypeForm")
	.on("click",".load-product-type",loadZtreeNodes)
	.on("click","#btn-save",saveOrUpdate)
	
	$("#typeLayer")
	.on("click",".btn-cancle",hideZtree)
	.on("click",".btn-confirm",setSelectedNode)
})
function saveOrUpdate(){
	//验证表单数据是否为空
	if(!$("#editTypeForm").valid())return;
	//获取表单数据
	var params=getEditFormData();
	//异步提交表单数据
	var url="type/doSaveObject.do";
	$.post(url,params,function(result){
		if(result.state==1){
			alert(result.message);
			//保存成功后显示页面
			$(".content").load("type/listUI.do");
		}else{
			alert(result.message);
		}
	})
}
////添加完成后返回首页
//function returnIndexUI(){
//	
//}
function getEditFormData(){
	//debugger
	var params={
			"name":$("#typeNameId").val(),
			"parentId":$("#editTypeForm").data("parentId"),
			"note":$("#typeNoteId").val(),
			"sort":$("#typeSortId").val()
	}
	return params;
}

function setSelectedNode(){
	//获得选中的节点对象(数组)
	var nodes=
		zTree.getSelectedNodes();
	console.log(nodes[0]);
		if(nodes.length>0){
			//debugger;
			//获得具体节点对象
			var node=nodes[0];
			//将id和name设置到对应的对象上
			$("#editTypeForm").data("parentId",node.id);
			
			$("#parentNameId").val(node.name);
		}
		//隐藏zTree对象
		hideZtree();
}
function hideZtree(){
	$("#typeLayer").css("display","none");
}

//点击上级分类时异步加载树结构信息
function loadZtreeNodes(){
	//显示树结构
	$("#typeLayer").css("display","block");
	//异步加载数据
	debugger
	var url="type/doFindZtreeNodes.do";
	$.getJSON(url,function(result){
		console.log(result.data);
		if(result.state==1){
			zTree=$.fn.zTree.init(
					$("#typeTree"),setting,result.data);
			//console.log(zTree);
		}else{
			alert(result.message);
		}
	})
}

