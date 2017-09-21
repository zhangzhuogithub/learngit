$("#loginbtn").click(function(){
	
	//获取当前路径
	function getRootPath() {
	    //获取当前网址，如： http://localhost:9527/zdss-web/login/login.do
	    var curWwwPath = window.document.location.href;
	 //   console.log("当前网址：" + curWwwPath);
	    
	    //获取主机地址之后的目录，如：zdss-web/login/login.do
	    var pathName = window.document.location.pathname;
	  //  console.log("当前路径：" + pathName);
	    
	    var pos = curWwwPath.indexOf(pathName);
	 //   console.log("路径位置：" + pos);
	    
	    //获取主机地址，如： http://localhost:9527
	    var localhostPath = curWwwPath.substring(0, pos);
	    console.log("当前主机地址：" + localhostPath);
	    
	    //获取带"/"的项目名，如：/zdss-web
	    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	    console.log("当前项目名称：" + projectName);
	    
	    return localhostPath+projectName;
	}
	
	var user = new Object();
	user.logincode = $.trim($("#logincode").val());
	user.password = $.trim($("#password").val());
	user.isstart = 1;
	
	if(user.logincode == "" || user.logincode == null){
		
		$("#formtip").css("color","red");
		$("#formtip").html("登陆账号不能为空！");
		$("#logincode").focus;
	}else if(user.password == "" || user.password == null){
		$("#password").focus;
		$("#formtip").css("color","red");
		$("#formtip").html("登陆密码不能为空！");
		
	}else{
		$("#formtip").html("");
		//ajax验证
		$.ajax({
			type:'POST',
			url:getRootPath()+'/user/login.action',
			data:{user:JSON.stringify(user)},
			dataType:'html',
			timeout:10000,
			error:function(){
				$("#formtip").css("color","red");
				$("#formtip").html("登录失败，请重试！");
			},
			success:function(result){
				//若登陆成功，跳转到main.jsp
				if(result!="" && result=="success"){
					window.location.href=getRootPath()+"/user/main.action";
				}else if(result=="failed"){
					$("#formtip").css("color","red");
					$("#formtip").html("登录失败，请重试！");
					$("#logincode").val("");
					$("#password").val("");
				}else if(result=="nologincode"){
					$("#formtip").css("color","red");
					$("#formtip").html("登录账号不存在，请重试！");
				}else if(result=="nopassword"){
					$("#formtip").css("color","red");
					$("#formtip").html("登录密码不存在，请重试！");
				}else if("nodata"==result){
					$("#formtip").css("color","red");
					$("#formtip").html("没有数据需要处理,请重试！");
				}
			}
		});
	}
});


