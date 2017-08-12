<@ms.html5>
	<@ms.nav title="自定义页面表管理"></@ms.nav>
	<@ms.searchForm name="searchForm" isvalidation=true>
		<@ms.text label="自定义页面标题" name="pageTitle" value=""  width="240px;" placeholder="请输入自定义页面标题" validation={"required":"true","maxlength":"50","data-bv-stringlength-message":"自定义页面标题长度不能超过五十个字符长度!"}/>
		<@ms.searchFormButton>
			 <@ms.queryButton onclick="search()"/> 
		</@ms.searchFormButton>			
	</@ms.searchForm>
	<@ms.panel>
		<div id="toolbar">
			<@ms.panelNav>
				<@ms.buttonGroup>
					<@ms.addButton id="addPageBtn"/>
					<@ms.delButton id="delPageBtn"/>
				</@ms.buttonGroup>
			</@ms.panelNav>
		</div>
		<table id="pageList" 
			data-show-refresh="true"
			data-show-columns="true"
			data-show-export="true"
			data-method="post" 
			data-pagination="true"
			data-page-size="10"
			data-side-pagination="server">
		</table>
	</@ms.panel>
	<!--添加模块-->    
	<@ms.modal id="addEditModel" title="添加模块">
		<@ms.modalBody>
			<@ms.form isvalidation=true name="addEditForm"  action="" method="post"  >
				<@ms.text id="pageTitle" name="pageTitle" label="标题" labelStyle="width:25%" width="250" title="标题" placeholder="请输入标题" value="" validation={"maxlength":"20","required":"true", "data-bv-notempty-message":"标题不能为空","data-bv-stringlength-message":"标题在20个字符以内!"}/>
				<@ms.text name="pageKey"  label="访问路径" labelStyle="width:25%" width="250" title="访问路径" placeholder="请输入访问路径" value="" validation={"maxlength":"100","required":"true", "data-bv-notempty-message":"访问路径不能为空","data-bv-stringlength-message":"访问路径在100个字符以内!"}/>
				<@ms.formRow label="选择模板" labelStyle="width:25%" width="300" >			    	
				 	<select class="form-control template templateSelect" name="pagePath"></select>
				</@ms.formRow>
			</@ms.form>
		</@ms.modalBody>
		<@ms.modalButton>
			<@ms.button  value=""  id="addEditBtn"/>
		</@ms.modalButton>
	</@ms.modal>	
	<!--删除模块-->	
	<@ms.modal  modalName="delPage" title="删除自定义页面" >
		<@ms.modalBody>删除选中
			<@ms.modalButton>
				<!--模态框按钮组-->
				<@ms.button  value="确认删除？"  id="deletePageBtn"  />
			</@ms.modalButton>
		</@ms.modalBody>
	</@ms.modal>
</@ms.html5>

<script>
	var postUrl;
	$(function(){
		//加载选择模块列表
		$(".templateSelect").request({url:base+"${baseManager}/template/queryTemplateFileForColumn.do",type:"json",method:"post",func:function(msg) {
			if(msg.length != 0 && ($(".template").html() == "" || $(".template").html() == "")){
	   			for(var i=0; i<msg.length; i++){
		   			$(".templateSelect").append($("<option>").val(msg[i]).text(msg[i]));
		   		}
	   		} else {
	   			$(".templateSelect").append("<option>暂无文件</option>");
	   		}
	   		//使用select2插件
			$(".templateSelect").select2({width: "220px"});
		}});
		$("#pageList").bootstrapTable({
			url:"${managerPath}/mdiy/page/list.do",
			contentType : "application/x-www-form-urlencoded",
			queryParamsType : "undefined",
			toolbar: "#toolbar",
	    	columns: [{ checkbox: true},
				    	{
				        	field: 'pageTitle',
				        	title: '自定义页面标题',
				        	formatter:function(value,row,index) {
				        		return "<a style='cursor:pointer' onclick='editPage("+row.pageId+")'>" + value + "</a>";
				        	}
				    	},{
				        	field: 'pagePath',
				        	title: '自定义页面绑定模板的路径'
				    	},{
				        	field: 'pageKey',
				        	title: '访问路径'
				    	}]
	    })
	})
	//增加按钮
	$("#addPageBtn").click(function(){
		postUrl= "${managerPath}/mdiy/page/save.do";
		$("#addEditBtn").text("保存");
		$("#addEditModelTitle").text("添加模块");
		$("#addEditForm")[0].reset();
		$(".addEditModel").modal();
	})
	//点击名称更新
	function editPage(id){
		$.ajax({
			type: "post",
			url: "${managerPath}/mdiy/page/get.do?pageId="+id,
			async: false,
			dataType: "json",
			contentType: "application/json",
			success:function(data) {
				if(data!=null){
					$("#pageTitle").val(data.pageTitle);
					$("input[name='pageKey']").val(data.pageKey); 
					$(".templateSelect").find("option[value='"+data.pagePath+"']").attr("selected",true);
					$("#select2-chosen-2").text($(".templateSelect").find("option[value='"+data.pagePath+"']").text());
					$(".addEditModel").modal();
					postUrl="${managerPath}/mdiy/page/update.do?pageId="+id,
					$("#addEditBtn").text("更新");
					$("#addEditModelTitle").text("编辑模块");
				}	
			}
		})
	}
	//删除按钮
	$("#delPageBtn").click(function(){
		//获取checkbox选中的数据
		var rows = $("#pageList").bootstrapTable("getSelections");
		//没有选中checkbox
		if(rows.length <= 0){
			<@ms.notify msg="请选择需要删除的记录" type="warning"/>
		}else{
			$(".delPage").modal();
		}
	})
	
	$("#deletePageBtn").click(function(){
		var rows = $("#pageList").bootstrapTable("getSelections");
		$(this).text("正在删除...");
		$(this).attr("disabled","true");
		$.ajax({
			type: "post",
			url: "${managerPath}/mdiy/page/delete.do",
			data: JSON.stringify(rows),
			dataType: "json",
			contentType: "application/json",
			success:function(msg) {
				if(msg.result == true) {
					<@ms.notify msg= "删除成功" type= "success" />
				}else {
					<@ms.notify msg= "删除失败" type= "fail" />
				}
				location.reload();
			}
		})
	});
	//查询功能
	function search(){
		var search = $("form[name='searchForm']").serializeJSON();
        var params = $('#pageList').bootstrapTable('getOptions');
        params.queryParams = function(params) {  
        	$.extend(params,search);
	        return params;  
       	}  
   	 	$("#pageList").bootstrapTable('refresh', {query:$("form[name='searchForm']").serializeJSON()});
	}
	//保存或更新
	$("#addEditBtn").on("click",function(){
		var vobj = $("#addEditForm").data('bootstrapValidator').validate();
		if(vobj.isValid()){
			$("#addEditForm").attr("action",postUrl);
			$("#addEditForm").postForm("#addEditForm",{func:function(msg){
		    	if(msg.result == true) {
					if($("#addEditBtn").text()=="保存"){
		     			alert("保存成功");
		     		}else{
		     			alert("更新成功");
		     		}
				}
				location.reload();
			}});
		}
	});
</script>