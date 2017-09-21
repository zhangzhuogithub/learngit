//新增按钮
$(".addrole").click(function(){
	$("#addRoleDiv").modal('show');
	
});
//点击关闭清空内容
$("#addRoleCancel").click(function(){
	$("#roleCode").html('');
	$("#roleName").html('');
	$("#formtip").html('');
	window.location.href = "queryRoles.action";
});
$("#addRoleClose").click(function(){
	$("#roleCode").html('');
	$("#roleName").html('');
	$("#formtip").html('');
	window.location.href = "queryRoles.action";
});

//新增保存按钮
$("#addRoleBtn").click(function(){
	var rolecode = $("#roleCode").val();
	var rolename = $("#roleName").val();
	if (rolecode == null || rolecode == "") {
		$("#formtip").css("color","red");
		$('#formtip').html('角色编码不能为空！');
	}else if(rolename == null || rolename == ""){
		$("#formtip").css("color","red");
		$('#formtip').html('角色名称不能为空！');
	}else{
		$.ajax({
			url:'addRole.action',
			type:'POST',
			data:{'rolecode':rolecode,'rolename':rolename},
			timeout: 6000,
			error:function(){
				alert("error");
			},
			success:function(result){
				if("failed" == result){
					alert("操作超时！");
				}else if("nodata" == result){
					alert("没有数据！");
				}else if("norolecode"== result){
					$("#formtip").css("color","red");
					$('#formtip').html('角色编码不能重复！');
				}else if("norolename"== result){
					$("#formtip").css("color","red");
					$('#formtip').html('角色名称不能重复！');
				}else if("success"== result){
					$("#formtip").css("color","green");
					$('#formtip').html('角色添加成功 ^_^ 继续添加请填写。');
					$("#roleCode").val('');
					$("#roleName").val('');
				}
			}
		});
	}
});

//删除按钮
$(".delrole").click(function(){
	var roleid = $(this).attr("roleId");
	var rolename = $(this).attr("roleName");
	if (confirm("确定要删除该角色么？")) {
		$.ajax({
			url:'deleteRole.action',
			data:{'roleid':roleid,'rolename':rolename},
			timeout:6000,
			type:'POST',
			dataType:'html',
			timeout: 6000,
			
			success:function(result){
				alert(result);
				
				if("failed" == result){
					alert("操作超时！");
				}else if("nodata" == result){
					alert("没有数据！");
				}else if("success" == result){
					window.location.href = "queryRoles.action";
					alert("删除成功！");
					
				}else{
					var resultJson = $.parseJSON(result);
					var r = '';
					for (var i = 0; i < resultJson.length; i++) {
						r=r+"["+resultJson[i].logincode+"]";
					}
					alert("系统中有用户被授权该角色，不能删除！用户帐号："+r);
				}
			}
		});
	}
	
});

//是否启用角色
$(".modifyIsStart").change(function(){
	var isstart = $(this).is(':checked');
	var roleid = $(this).attr('roleid');
	$.ajax({
		url:'updateIsstart.action',
		data:{'isstart':isstart,'roleid':roleid},
		type:'POST',
		dataType:'html',
		timeout: 6000,
		error:function(){
			alert('error');
		},
		success:function(result){
			if ("success" == result) {
				alert('success');
				//window.location.href = "queryRoles.action";	
			}else if("failed" == result){
				alert("操作超时！");
			}else if("nodata" == result){
				alert("没有数据！");
			}else{
				var resultJson = $.parseJSON(result);
				var r = '';
				for (var i = 0; i < resultJson.length; i++) {
					r=r+"["+resultJson[i].logincode+"]";
				}
				window.location.href = "queryRoles.action";
				//$('.modifyIsStart').attr('checked','checked');
				alert("系统中有用户被授权该角色，请先修改用户角色在设置启用状态！用户帐号："+r);
			}
		}
	});
	
});


//修改信息
$(".modifyrole").click(function(){
	var roleid = $(this).attr("roleId");
	var rolename = $("#roleName"+roleid).val();
	var rolecode = $("#roleCode"+roleid).val();
	if (confirm("确定要修改角色信息么？")) {
		$.ajax({
			url:'updateRole.action',
			data:{'roleid':roleid,'rolename':rolename,'rolecode':rolecode},
			timeout:6000,
			type:'POST',
			dataType:'html',
			timeout: 6000,
			
			success:function(result){
				alert(result);
				
				if("failed" == result){
					alert("操作超时！");
				}else if("nodata" == result){
					alert("没有数据！");
				}else if("success" == result){
					window.location.href = "queryRoles.action";
					alert("修改成功！");
				}
			}
		});
	}
});;
