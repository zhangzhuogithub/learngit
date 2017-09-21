<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="common/head.jsp" %>

<div>
	<ul class="breadcrumb">
		<li><a href="#">后台管理</a> <span class="divider">/</span></li>
		<li><a href="<%=request.getContextPath()%>/goodsPack/updateGoodsPack.action">修改商品套餐</a></li>
	</ul>
</div>
			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-user"></i>修改商品套餐</h2>
					</div>
			<div class="box-content">
			<ul id="modify_formtip"></ul>
			<legend>修改商品套餐</legend>
                 <form action="/backend/savemodifygoodspack.html" class="form-horizontal" enctype="multipart/form-data" method="post" onsubmit="return modifyGoodsPackFunc();">
					  <div class="control-group">
					  <input id="m_id" type="hidden" name="id" value="${goodsPack.id}"/>
					  <label class="control-label" for="typeahead">套餐名称: </label>
					  <div class="controls">
						<input type="text" id="m_goodsPackName" name="goodspackname" value="${goodsPack.goodspackname}" />
						 <span style="color:red;font-weight: bold;">*</span>
					  </div>
					  </div>
					  <div class="control-group">
					  <label class="control-label" for="typeahead">套餐编号: </label>
					  <div class="controls">
						<input type="text" id="m_goodsPackCode" name="goodspackcode" value="${goodspack.goodsPackcode}" />
						 <span style="color:red;font-weight: bold;">*</span>
					  </div>
					</div>
					<div class="control-group">
					  <label class="control-label" for="typeahead">套餐类型: </label>
					  <div class="controls">
					  <input id="m_typeName" type="hidden" value="${goodsPack.typename}" name="typename"/>
					  <select id="m_typeId" name="typeid" style="width:100px;">
			 			<option value="" selected="selected">--请选择--</option>
			 			<c:if test="${packTypeList != null}">
					 				<c:forEach items="${packTypeList}" var="packType">
					 					<option <c:if test="${goodsPack.typeid == packType.valueid}">selected = "selected"</c:if>
					 					value="${packType.valueid}">${packType.valuename}</option>
					 				</c:forEach>
					 	</c:if>
			 		  </select>
			 		   <span style="color:red;font-weight: bold;">*</span>
					  </div>
					 </div>
					 
					<div class="control-group">
						<label class="control-label" for="focusedInput">库存量：</label>
						<div class="controls">
						<input type="text" id="m_num" name="num" value="${goodsPack.num}" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
						<span style="color:red;font-weight: bold;">*</span>
						</div>
					</div>
					
					<div class="control-group">
					  <label class="control-label" for="typeahead">套餐总价: </label>
					  <div class="controls">
						<input type="text" id="m_totalPrice" name="totalPrice" value="${goodsPack.totalprice}" onkeyup="if(this.value==this.value2)return;if(this.value.search(/^\d*(?:\.\d{0,2})?$/)==-1)this.value=(this.value2)?this.value2:'';else this.value2=this.value;"/>
						<span style="color:red;font-weight: bold;">*</span>
					  </div>
					</div>
					
					<div class="control-group">
					  <label class="control-label" for="typeahead">状态: </label>
					  <div class="controls">
					  <c:if test="${goodsPack.state == '1'}">
					 	<input type="radio" name="state" checked="checked" value="1"/>上架
					  	<input type="radio" name="state" value="2"/>下架
					  </c:if>
					  <c:if test="${goodsPack.state == '0'}">
					 	<input type="radio" name="state" value="1"/>上架
					  	<input type="radio" name="state" checked="checked" value="2"/>下架
					  </c:if>
					  </div>
					</div>
					
					<div class="control-group">
					  <label class="control-label" for="typeahead">相关商品: </label>
					  <div class="controls">
						<ul class="aboutproductsList">
						  	<li><iframe id="goodsListFrame" class="goodsListFrame" src="<%=request.getContextPath()%>/goods/queryGoodsInfo.action"></iframe></li>
						  	<li id="selectgoodslist">
						  		<c:if test="${goodsList != null}">
					 				<c:forEach items="${goodsList}" var="goods">
					 				<div id="selectdiv">
					 				<label class="goodsname">${goods.goodsname}</label>
									<label class="goodscount"><input class="finalresult" goodsid="${goods.goodsinfoid}" rprice="${goods.realprice}" type="text" value="${goods.goodsnum}"/></label>
									<label class="del" rprice="${goods.realprice}"><img src="<%=request.getContextPath()%>/img/cancel-on.png"/></label>
									<label class="clear"></label>
									</div>
					 				</c:forEach>
					 	    	</c:if>
						  	</li>
					  	</ul>
					  	<input id="goodsJson" type="hidden" name="goodsJson"/>
					  </div>
					</div>
					        
					<div class="control-group">
					  <label class="control-label" for="textarea2">套餐说明:</label>
					  <div class="controls">
					    <textarea class="cleditor" id="m_note" name="note" rows="3">${goodsPack.note}</textarea>
					  </div>
					</div>
					<div class="form-actions">
					  <button type="submit" class="btn btn-primary">保存</button>
					  <button type="button" class="btn backbtn">返回</button>
					</div>
				</form>
			</div>
		</div><!--/span-->
		</div><!--/row-->

<%@include file="common/foot.jsp" %>
<script src="<%=request.getContextPath()%>/js/localjs/goodspack.js"></script>