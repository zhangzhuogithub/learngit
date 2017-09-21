$(".roleNameAuthority").click(function(){
	var authority = $(this);
	var roleid = authority.attr("roleid");
	$("#selectrole").html("当前配置角色为："+authority.attr("rolename"));
	$("#roleidhide").val(roleid);
	//获取FunctionList
	$.ajax({
		url:'queryAuthorityByRoleid.action',
		data:{'rid':roleid},
		Type:'POST',
		dataType:'json',
		timeout:6000,
		error:function(){
			alert('加载功能列表失败');
		},
		success:function(result){
			if (result=='nodata') {
				alert('功能列表获取失败，无数据');
			}else{
				
				listr = "";
				for (var i = 0; i < result.length; i++) {
					listr += "<li>";
					listr += "<ul id=\"subfuncul"+result[i].mainFunction.id+"\" class=\"subfuncul\">";	
					listr += "<li class=\"functiontitle\"> <input id='functiontitle"+result[i].mainFunction.id+"' onchange='mainFunctionSelectChange(this,"+result[i].mainFunction.id+")' funcid=\""+result[i].mainFunction.id+"\" type='checkbox' />"+result[i].mainFunction.functionname+"</li>";
					for (var j = 0; j < result[i].subFunction.length; j++) {
						
						listr += "<li><input onchange='subFunctionSelectChange(this,"+result[i].mainFunction.id+");' funcid=\""+result[i].subFunction[j].id+"\" type='checkbox' />"+result[i].subFunction[j].functionname+"</li>";
					}
					listr += "</ul></li>";
				}
				$("#functionList").html(listr);
				//通过roleid回显checkbox框（循环
				$("#functionList :checkbox").each(function(){
					var checkbox = $(this);
					$.ajax({
						url:'queryAuthorityDefault.action',
						type:'POST',
						data:{rid:$("#roleidhide").val(),fid:$(this).attr("funcid")},
						dataType:'html',
						timeout:6000,
						error:function(){
							alert("回显失败");
						},
						success:function(result){
							if ("success" == result) {
								
								checkbox.attr("checked",true);
							}else{
								checkbox.attr("checked",false);
							}
							
						}
					});
					
				});
			}
		}
	})
	
});

//点击子功能菜单
function subFunctionSelectChange(obj,id){
	if (obj.checked) {
		$("#functiontitle"+id).attr("checked",true);
	}
}
//点击主功能菜单
function mainFunctionSelectChange(obj,id){
	
	if(obj.checked){
		$("#subfuncul"+id+" :checkbox").attr("checked", true);  
	}else{
		$("#subfuncul"+id+" :checkbox").attr("checked", false);  
	}
}

//提交授权修改
$("#confirmsave").click(function(){
	if (confirm("确认修改当前角色的权限么？")) {
		//角色id roleid runctionids
		var ids = $("#roleidhide").val()+"-";
		$("#functionList :checkbox").each(function(){
			if ($(this).attr("checked")=="checked") {
				ids += $(this).attr("funcid")+"-";
			}
			
		});
		alert(ids);
		//ajax提交
		$.ajax({
			url:'updateAuthority.action',
			type:'POST',
			data:{ids:ids},
			dataType:'html',
			timeout:6000,
			error:function(){
				alert("修改权限失败");
			},
			success:function(result){
				if (result=="nodata") {
					alert('功能列表获取失败');
				}else{
					alert("修改权限成功！");
				}
			}
		})
		
	}
});


$("#selectAll").click(function () {//全选  
    $("#functionList :checkbox").attr("checked", true);  
});  

$("#unSelect").click(function () {//全不选  
    $("#functionList :checkbox").attr("checked", false);  
});  

$("#reverse").click(function () {//反选  
    $("#functionList :checkbox").each(function () {  
        $(this).attr("checked", !$(this).attr("checked"));  
    });  
});  
