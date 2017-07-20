<@ms.html5>
	<@ms.nav title="自定义搜索表管理"></@ms.nav>
	<@ms.panel>
		<div id="toolbar">
			<@ms.panelNav>
				<@ms.buttonGroup>
					<@ms.addButton openModal="searchModal"/>
					<@ms.delButton id="delSearchBtn"/>
				</@ms.buttonGroup>
				<@ms.button id="setUp" value="高级设置"/>
			</@ms.panelNav>
		</div>
		<table id="searchList" 
			data-show-refresh="true"
			data-show-columns="true"
			data-show-export="true"
			data-method="post" 
			data-pagination="true"
			data-page-size="10"
			data-side-pagination="server">
		</table>
	</@ms.panel>
	
	<@ms.modal  modalName="delSearch" title="搜索数据删除" >
		<@ms.modalBody>删除选中记录
			<@ms.modalButton>
				<!--模态框按钮组-->
				<@ms.button  value="确认删除？"  id="deleteSearchBtn"  />
			</@ms.modalButton>
		</@ms.modalBody>
	</@ms.modal>
</@ms.html5>
<@ms.modal modalName="searchModal" title="搜索设置">
	 <@ms.modalBody>
	 	<@ms.form isvalidation=true name="searchForm" action="${managerPath}/mdiy/search/save.do" redirect="${managerPath}/mdiy/search/index.do">
				<@ms.hidden name="searchId" value="0"/>
				<@ms.text label="搜索名称"  id="searchName" title="搜索名称"  placeholder="请输入搜索名称" name="searchName" 
					 validation={"minlength":"1","maxlength":"10","required":"true","data-bv-notempty-message":"必填项目", "data-bv-stringlength-message":"长度在1到10个字符以内!"} />
				<!--搜索结果模板-->
				<@ms.select  name="searchTemplets" label="结果模版"/>	
				<@ms.select  name="searchType" label="所属模块" list=searchType  value="" listKey="key" listValue="Value"/>			 	
		</@ms.form>
     </@ms.modalBody>
     <@ms.modalButton>
		<@ms.saveButton postForm="searchForm"/>  
	 </@ms.modalButton>
</@ms.modal>
		
<!--=================模态框部分结束=================-->
<script>
	$(function(){
		$("#searchList").bootstrapTable({
			url:"${managerPath}/mdiy/search/list.do",
			contentType : "application/x-www-form-urlencoded",
			queryParamsType : "undefined",
			toolbar: "#toolbar",
	    	columns: [{ checkbox: true},
		    	{
		        	field: 'searchId',
		        	title: '编号',
		        	width:'10',
		        	align: 'center'
		    	},{
		        	field: 'searchName',
		        	title: '搜索名称',
		        	width:'20',
		        	formatter:function(value,row,index) {
		        		return "<a onclick='updateSearch("+row.searchId+")' style='cursor: pointer;' >" + value + "</a>";
		        	}
		    	},{
		        	field: 'searchTemplets',
		        	title: '搜索结果模版',
		        	width:'50'
		    	},{
		        	field: 'searchType',
		        	title: '搜索类型',
		        	width:'255',
		        	align: 'center',
		        	formatter:function(value,row,index) {
		        		if(value =="cms"){
		        			return "商城";
		        		}else{
		        			return "文章";
		        		}
		        	}
		    	}]
	    })
	})
	$.ajax({
		type: "post",
		url:"${managerPath}/template/queryTemplateFileForColumn.do",
		dataType:"json",
		success:function(msg){
			if(msg.length==0){
				$("select[name='searchTemplets']").append("<option value=''>暂无模板文件</option>")
			}
			for(var i = 0;i<msg.length;i++) {
				$("select[name='searchTemplets']").append("<option value="+msg[i]+">"+msg[i]+"</option>");
			}							
		}
	});
	
	//修改搜索器
	$(".updateSearch").delegate("click",function(){
		alert("a");
		$("#searchForm").attr("action","${managerPath}/mdiy/search/update.do");
		$("#searchForm input[name='searchId']").val($(this).attr("data-id"));
		$("#searchForm input[name='searchName']").val($(this).text());
		$("#searchForm select[name='searchTemplets']").val($(this).attr("searchTemplets"));
		$("#searchModal").modal();
	})
	
	//增加按钮
	$("#setUp").click(function(){
		var rows = $("#searchList").bootstrapTable("getSelections");
		//没有选中checkbox
		if(rows.length < 1){
			<@ms.notify msg="请选择需要设置的记录" type="warning"/>
		}else if(rows.length > 1){
			<@ms.notify msg="只能选中一条数据" type="warning"/>
		}else{
			location.href ="${managerPath}/mdiy/search/"+rows[0].searchId+"/searchCode.do"; 
		}
		
	})
	//删除按钮
	$("#delSearchBtn").click(function(){
		//获取checkbox选中的数据
		var rows = $("#searchList").bootstrapTable("getSelections");
		//没有选中checkbox
		if(rows.length <= 0){
			<@ms.notify msg="请选择需要删除的记录" type="warning"/>
		}else{
			$(".delSearch").modal();
		}
	})
	
	$("#deleteSearchBtn").click(function(){
		var rows = $("#searchList").bootstrapTable("getSelections");
		$(this).text("正在删除...");
		$(this).attr("disabled","true");
		$.ajax({
			type: "post",
			url: "${managerPath}/mdiy/search/delete.do",
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
	function updateSearch(searchId){
		$(this).request({url:"${managerPath}/mdiy/search/form.do?searchId="+searchId,func:function(search) {
			if (search.searchId > 0) {
				$("#searchForm").attr("action","${managerPath}/mdiy/search/update.do");
				$("#searchForm input[name='searchId']").val(search.searchId);
				$("#searchForm input[name='searchName']").val(search.searchName);
				$("#searchForm select[name='searchTemplets']").val(search.searchTemplets);
				$("#searchModal").modal();
			}					
		}});
	}
</script>