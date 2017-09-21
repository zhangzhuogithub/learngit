//查看
$(".viewgoodsinfo").click(function(){
	var m_id = $(this).attr('id');
	//ajax
	$.ajax({
		url:'queryGoodsInfoById.action',
		type:'POST',
		data:{id:m_id},
		dataType:'json',
		timeout:6000,
		error:function(){
			alert("error");
		},
		success:function(result){
			if ("failed" == result) {
				alert("操作超时");
			}else if("nodata" == result){
				alert("没有数据");
			}else{
				//$('#v_id').val(result.id);
				$('#v_goodsName').val(result.goodsname);
				$('#v_goodsSN').val(result.goodssn);
				$('#v_marketPrice').val(result.marketprice);
				$('#v_realPrice').val(result.realprice);
				$('#v_num').val(result.num);
				$('#v_unit').val(result.unit);
				$('#v_goodsFormat').html(result.goodsformat);
				$('#v_note').html(result.note);
				
				
				$('#v_state').val(result.state);
				var state = result.state;
				if (state == '1') {
					$('#v_state').html("已上架");
				}else{
					$('#v_state').html("未上架");
				}
				
				$("#viewGoodsInfoDiv").modal('show');
			}
			
		}
	});
	
});


//修改上架状态

$(".modifystate").change(function(){
	var state = $(this).is(':checked');
	var goodsinfoid = $(this).attr('goodsinfoid');
	$.ajax({
		url:'updateState.action',
		data:{'state':state,'goodsinfoid':goodsinfoid},
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
				alert(result);
				var resultJson = $.parseJSON(result);
				alert(result);
				var r = '';
				for (var i = 0; i < resultJson.length; i++) {
					r=r+"["+resultJson[i].goodspackname+"]";
				}
				window.location.href = "queryGoodsInfo.action";
				//$('.modifyIsStart').attr('checked','checked');
				alert("系统中有商品套餐存在该商品，无法修改上架状态！套餐名称："+r);
			}
		}
	});
	
});

//删除按钮
$(".delgoodsinfo").click(function(){
	var goodinfoid = $(this).attr("id");
	var goodsName = $(this).attr("goodsName");
	if (confirm("确定要删除该角色么？")) {
		$.ajax({
			url:'deleteGoodInfo.action',
			data:{'goodinfoid':goodinfoid,'goodsName':goodsName},
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
					window.location.href = "queryGoodsInfo.action";
					alert("删除成功！");
					
				}else{
					var resultJson = $.parseJSON(result);
					var r = '';
					for (var i = 0; i < resultJson.length; i++) {
						r=r+"["+resultJson[i].goodspackname+"]";
					}
					alert("系统中有商品套餐存在该商品，无法删除！套餐名称："+r);
				}
			}
		});
	}
	
});

//新增验证商品编码唯一
$("#a_goodsSN").blur(function(){
	var a_goodssn = $("#a_goodsSN").val();
	
	if (a_goodssn != "") {
		//异步判断
		$.post("goodsSNIsExist.action",{'goodssn':a_goodssn,'id':'-1'},function(result){
			if (result == "repeat") {
				$("#add_formtip").css("color","red");
				$("#add_formtip").html("该编码已存在!");
				
			}else if(result == "failed"){
				alert("操作超时");
				
			}else if(result == 'only'){
				$("#add_formtip").css("color","green");
				$("#add_formtip").html("该编码可以正常使用");
			}
		},'html');
	}
});

//修改
$('.modifygoodsinfo').click(function(){
	var m_id = $(this).attr('id');
	$.ajax({
		url:'queryGoodsInfoById.action',
		type:'POST',
		data:{id:m_id},
		dataType:'json',
		timeout:6000,
		error:function(){
			alert("error");
		},
		success:function(result){
			if ("failed" == result) {
				alert("操作超时");
			}else if("nodata" == result){
				alert("没有数据");
			}else{
				
				$('#m_id').val(result.id);
				$('#m_goodsName').val(result.goodsname);
				$('#m_goodsSN').val(result.goodssn);
				$('#m_marketPrice').val(result.marketprice);
				$('#m_realPrice').val(result.realprice);
				$('#m_num').val(result.num);
				$('#m_unit').val(result.unit);
				var state = result.state;
				var goodsFormat = result.goodsformat;
				var note = result.note;
				$('#m_state').html('');
				if (state==1) {
					$('#m_state').append("<input type=\"radio\" name=\"state\" checked=\"checked\" value=\"1\"/>上架<input type=\"radio\"  name=\"state\" value=\"2\"/>下架");
				}else if(state==2){
					$('#m_state').append("<label>状态：</label><input type=\"radio\" name=\"state\"  value=\"1\"/>上架<input type=\"radio\"  checked=\"checked\" name=\"state\" value=\"2\"/>下架");
				}
				$('#m_goodsFormatli').html('');
				$('#m_goodsFormatli').append("<span>商品规格：</span> <br/><textarea class=\"cleditor\" id=\"m_goodsFormat\" name=\"goodsformat\" rows=\"3\">"+goodsFormat+"</textarea>");
				$('#m_noteli').html('');
				$('#m_noteli').append("<span>商品说明：</span> <br/><textarea class=\"cleditor\" id=\"a_note\" name=\"note\" rows=\"3\">"+note+"</textarea>");
				
				$('#modifyGoodsInfoDiv').modal('show');

			}
		}
	});
	
});
//修改验证商品编码唯一
$("#m_goodsSN").blur(function(){
	
	var m_goodssn = $("#m_goodsSN").val();
	
	if (m_goodssn != "") {
		//异步判断
		$.post("goodsSNIsExist.action",{'goodssn':m_goodssn,'id':$("#m_id").val()},function(result){
			if (result == "repeat") {
				$("#modify_formtip").css("color","red");
				$("#modify_formtip").html("该编码已存在!");
				
			}else if(result == "failed"){
				alert("操作超时");
				
			}else if(result == 'only'){
				$("#modify_formtip").css("color","green");
				$("#modify_formtip").html("该编码可以正常使用");
			}
		},'html');
	}
});

//新增非空验证
function addGoodsInfoFunction(){
	var result= true;
	var a_goodsName = $("#a_goodsName").val();
	var a_goodsSN = $("#a_goodsSN").val();
	var a_marketPrice = $("#a_marketPrice").val();
	var a_realPrice = $("#a_realPrice").val();
	var a_num = $("#a_num").val();
	var a_unit = $("#a_unit").val();
	var a_state = $("#a_state").val();
	var a_goodsFormat = $("#a_goodsFormat").val();
	var a_note = $("#a_note").val();
	
	if (a_goodsName == null || a_goodsName == "") {
		$("#add_formtip").css("color","red");
		$('#add_formtip').append('<li>商品名称不能为空！</li>');
		result= false;
	}
	if(a_goodsSN == null || a_goodsSN == ""){
		$("#add_formtip").css("color","red");
		$('#add_formtip').append('<li>商品编码不能为空！</li>');
		result= false;
	}
	if(a_marketPrice == null || a_marketPrice == ""){
		$("#add_formtip").css("color","red");
		$('#add_formtip').append('<li>市场价格不能为空！</li>');
		result= false;
	}
	if(a_realPrice == null || a_realPrice == ""){
		$("#add_formtip").css("color","red");
		$('#add_formtip').append('<li>真实价格不能为空！</li>');
		result= false;
	}
	if(a_num == null || a_num == ""){
		$("#add_formtip").css("color","red");
		$('#add_formtip').append('<li>库存数量不能为空！</li>');
		result= false;
	}
	if(a_unit == null || a_unit == ""){
		$("#add_formtip").css("color","red");
		$('#add_formtip').append('<li>单位不能为空！</li>');
		result= false;
	}
	
	if (result == true) {
		alert("添加成功！");
	}
	return result;
 }




//新增按钮
$(".addGoodsInfo").click(function(){
	$("#addGoodsInfoDiv").modal('show');
	
});
//点击关闭清空内容
$(".addgoodsinfocancel").click(function(){
	$("#a_goodsName").val('');
	$("#a_goodsSN").val('');
	$("#a_marketPrice").val('');
	$("#a_realPrice").val('');
	$("#a_num").val('');
	$("#a_unit").val('');
	$("#a_goodsFormat").val('');
	$("#a_note").val('');
	
	$("#add_formtip").html('');
	//window.location.href = "queryGoodsInfo.action";
});
//点击关闭清空内容
$(".modifygoodsinfocancel").click(function(){
	$("#m_goodsName").val('');
	$("#m_goodsSN").val('');
	$("#m_marketPrice").val('');
	$("#m_realPrice").val('');
	$("#m_num").val('');
	$("#m_unit").val('');
	$("#m_goodsFormat").val('');
	$("#m_note").val('');
	
	$("#modify_formtip").html('');
	//window.location.href = "queryGoodsInfo.action";
});

//修改非空验证
function modifyGoodsInfoFunction(){
	var result= true;
	
	var m_goodsName = $("#m_goodsName").val();
	var m_goodsSN = $("#m_goodsSN").val();
	var m_marketPrice = $("#m_marketPrice").val();
	var m_realPrice = $("#m_realPrice").val();
	var m_num = $("#m_num").val();
	var m_unit = $("#m_unit").val();
	var m_state = $("#m_state").val();
	var m_goodsFormat = $("#m_goodsFormat").val();
	var m_note = $("#m_note").val();
	
	if (m_goodsName == null || m_goodsName == "") {
		$("#modify_formtip").css("color","red");
		$('#modify_formtip').append('<li>商品名称不能为空！</li>');
		result= false;
	}
	if(m_goodsSN == null || m_goodsSN == ""){
		$("#modify_formtip").css("color","red");
		$('#modify_formtip').append('<li>商品编码不能为空！</li>');
		result= false;
	}
	if(m_marketPrice == null || m_marketPrice == ""){
		$("#modify_formtip").css("color","red");
		$('#modify_formtip').append('<li>市场价格不能为空！</li>');
		result= false;
	}
	if(m_realPrice == null || m_realPrice == ""){
		$("#modify_formtip").css("color","red");
		$('#modify_formtip').append('<li>真实价格不能为空！</li>');
		result= false;
	}
	if(m_num == null || m_num == ""){
		$("#modify_formtip").css("color","red");
		$('#modify_formtip').append('<li>库存数量不能为空！</li>');
		result= false;
	}
	if(m_unit == null || m_unit == ""){
		$("#modify_formtip").css("color","red");
		$('#modify_formtip').append('<li>单位不能为空！</li>');
		result= false;
	}
	
	if (result == true) {
		alert($("#m_id").val());
		alert("添加成功！");
	}
	return result;
 }