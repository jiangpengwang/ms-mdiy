<@ms.html5>
	 <@ms.nav title="自定义表单表编辑" back=true>
    	<@ms.saveButton  onclick="save()"/>
    </@ms.nav>
    <@ms.panel>
    	<@ms.form name="formForm" isvalidation=true>
    		<@ms.hidden name="formId" value="${(formEntity.formId)?default('')}"/>
    			<@ms.text label="自定义表单提示文字" name="formTipsName" value="${(formEntity.formTipsName)?default('')}"  width="240px;" placeholder="请输入自定义表单提示文字" validation={"required":"false","maxlength":"50","data-bv-stringlength-message":"自定义表单提示文字长度不能超过五十个字符长度!", "data-bv-notempty-message":"必填项目"}/>
    			<@ms.text label="自定义表单表名" name="formTableName" value="${(formEntity.formTableName)?default('')}"  width="240px;" placeholder="请输入自定义表单表名" validation={"required":"false","maxlength":"50","data-bv-stringlength-message":"自定义表单表名长度不能超过五十个字符长度!", "data-bv-notempty-message":"必填项目"}/>
    	</@ms.form>
    </@ms.panel>
</@ms.html5>
<script>
	var url = "${managerPath}/mdiy/form/save.do";
	if($("input[name = 'formId']").val() > 0){
		$("input[name=formTableName]").attr("readonly","true");
		url = "${managerPath}/mdiy/form/update.do";
		$(".btn-success").text("更新");
	}
	//编辑按钮onclick
	function save() {
		$("#formForm").data("bootstrapValidator").validate();
			var isValid = $("#formForm").data("bootstrapValidator").isValid();
			if(!isValid) {
				<@ms.notify msg= "数据提交失败，请检查数据格式！" type= "warning" />
				return;
		}
		var btnWord =$(".btn-success").text();
		$(".btn-success").text(btnWord+"中...");
		$(".btn-success").prop("disabled",true);
		$.ajax({
			type:"post",
			dataType:"json",
			data:$("form[name = 'formForm']").serialize(),
			url:url,
			success: function(status) {
				if(status.result == true) { 
					<@ms.notify msg="保存或更新成功" type= "success" />
					location.href = "${managerPath}/mdiy/form/index.do";
				}
				else{
					<@ms.notify msg= "保存或更新失败！" type= "fail" />
					location.href= "${managerPath}/mdiy/form/index.do";
				}
			}
		})
	}	
</script>
