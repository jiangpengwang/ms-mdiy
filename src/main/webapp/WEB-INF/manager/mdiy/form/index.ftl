<@ms.html5>
	<@ms.nav title="自定义表单表管理"></@ms.nav>
	<@ms.searchForm name="searchForm" isvalidation=true>
			<@ms.searchFormButton>
				<@ms.text label="名称" name="formTipsName" value=""  width="240px;" placeholder="请输入自定义表单名称" validation={"maxlength":"50","data-bv-stringlength-message":"自定义表单提示文字长度不能超过五十个字符长度!"}/>
				 <@ms.queryButton onclick="search()"/> 
			</@ms.searchFormButton>			
	</@ms.searchForm>
	<@ms.panel>
		<div id="toolbar">
			<@ms.panelNav>
				<@ms.buttonGroup>
					<@ms.addButton id="addFormBtn"/>
					<@ms.delButton id="delFormBtn"/>
				</@ms.buttonGroup>
			</@ms.panelNav>
		</div>
		<table id="formList" 
			data-show-refresh="true"
			data-show-columns="true"
			data-show-export="true"
			data-method="post" 
			data-pagination="true"
			data-page-size="10"
			data-side-pagination="server">
		</table>
	</@ms.panel>
	
	<@ms.modal  modalName="delForm" title="授权数据删除" >
		<@ms.modalBody>删除此授权
			<@ms.modalButton>
				<!--模态框按钮组-->
				<@ms.button  value="确认删除？"  id="deleteFormBtn"  />
			</@ms.modalButton>
		</@ms.modalBody>
	</@ms.modal>
</@ms.html5>

<script>
	$(function(){
		$("#formList").bootstrapTable({
			url:"${managerPath}/mdiy/form/list.do",
			contentType : "application/x-www-form-urlencoded",
			queryParamsType : "undefined",
			toolbar: "#toolbar",
	    	columns: [{ checkbox: true},
				    	{
				        	field: 'formId',
				        	title: '编号'
				        	
				    	},{
				        	field: 'formTipsName',
				        	title: '名称',
				        	formatter:function(value,row,index) {
				        		var url = "${managerPath}/mdiy/form/form.do?formId="+row.formId;
				        		return "<a href=" +url+ " target='_self'>" + value + "</a>";
				        	}
				    	},{
				        	field: 'formTipsName',
				        	title: '表单提交地址',
				        	formatter:function(value,row,index) {
				        		return "/mdiy/diyForm/"+row.formUrl+".do";
				        	}
				    	},{
				        	field: 'formTipsName',
				        	title: '请求数据地址',
				        	formatter:function(value,row,index) {
				        		return "/mdiy/diyForm/"+row.formUrl+"/queryData.do";
				        	}
				    	},{
				        	title: '提交记录',
				        	align: 'center',
				        	formatter:function(value,row,index) {
				        		var url="${managerPath}/mdiy/form/querydata.do?formId="+row.formId+"&formTipsName="+row.formTipsName
				        		return "<a href=" +url+ " target='_self'> 查看 </a>";
				        	}
				    	}]
	    })
	})
	//增加按钮
	$("#addFormBtn").click(function(){
		location.href ="${managerPath}/mdiy/form/form.do"; 
	})
	//删除按钮
	$("#delFormBtn").click(function(){
		//获取checkbox选中的数据
		var rows = $("#formList").bootstrapTable("getSelections");
		//没有选中checkbox
		if(rows.length <= 0){
			<@ms.notify msg="请选择需要删除的记录" type="warning"/>
		}else{
			$(".delForm").modal();
		}
	})
	
	$("#deleteFormBtn").click(function(){
		var rows = $("#formList").bootstrapTable("getSelections");
		$(this).text("正在删除...");
		$(this).attr("disabled","true");
		$.ajax({
			type: "post",
			url: "${managerPath}/mdiy/form/delete.do",
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
        var params = $('#formList').bootstrapTable('getOptions');
        params.queryParams = function(params) {  
        	$.extend(params,search);
	        return params;  
       	}  
   	 	$("#formList").bootstrapTable('refresh', {query:$("form[name='searchForm']").serializeJSON()});
	}
</script>