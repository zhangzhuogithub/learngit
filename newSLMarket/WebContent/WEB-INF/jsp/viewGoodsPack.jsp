<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="common/head.jsp" %>

<div>
	<ul class="breadcrumb">
		<li><a href="#">后台管理</a> <span class="divider">/</span></li>
		<li><a href="<%=request.getContextPath()%>/goodsPack/viewGoodsPackById.action">查看商品套餐</a></li>
	</ul>
</div>
			<div class="row-fluid sortable">		
				<div class="box span12">
					<div class="box-header well" data-original-title>
						<h2><i class="icon-user"></i>查看商品套餐</h2>
					</div>
			<div class="box-content">
			<legend>查看商品套餐</legend>
			<form class="form-horizontal">
					  <div class="control-group">
					  <label class="control-label" for="typeahead">套餐名称: </label>
					  <div class="controls">
						<input type="text" id="v_goodsPackName" name="goodspackname" value="${goodsPack.goodspackname}" readonly="readonly"/>
					  </div>
					  </div>
					  <div class="control-group">
					  <label class="control-label" for="typeahead">套餐编号: </label>
					  <div class="controls">
						<input type="text" id="v_goodsPackCode" name="goodspackcode" value="${goodsPack.goodspackcode}" readonly="readonly"/>
					  </div>
					</div>
					<div class="control-group">
					  <label class="control-label" for="typeahead">套餐类型: </label>
					  <div class="controls">
					  <input id="v_typeName" type="text" value="${goodsPack.typename}" name="typename" readonly="readonly"/>
					  </div>
					 </div>
					 
					<div class="control-group">
						<label class="control-label" for="focusedInput">库存量：</label>
						<div class="controls">
						<input type="text" id="v_num" value="${goodsPack.num}" readonly="readonly"/>
						</div>
					</div>
					
					<div class="control-group">
					  <label class="control-label" for="typeahead">套餐总价: </label>
					  <div class="controls">
						<input type="text" id="v_totalPrice" name="totalprice" value="${goodsPack.totalprice}" readonly="readonly"/>
					  </div>
					</div>
					
					<div class="control-group">
					  <label class="control-label" for="typeahead">状态: </label>
					  <div class="controls">
					  <c:if test="${goodsPack.state == '1'}">
					 	上架
					  </c:if>
					  <c:if test="${goodsPack.state == '2'}">
					  	下架
					  </c:if>
					  </div>
					</div>
					
					<div class="control-group">
					  <label class="control-label" for="typeahead">相关商品: </label>
					  <div class="controls">
					 	 <c:if test="${goodsList != null}">
						  <c:forEach items="${goodsList}" var="goods">
							<ul>
								<li>${goods.goodsname}&nbsp;&nbsp;&nbsp;
								${goods.num}&nbsp;&nbsp;&nbsp;
								${goods.unit}</li>
							</ul>
						  </c:forEach>
						 </c:if>
						 <c:if test="${goodsList == null}">
						 暂无
						 </c:if>
					  </div>
					</div>
					        
					<div class="control-group">
					  <label class="control-label" for="textarea2">套餐说明:</label>
					  <div class="controls">
					    <div id="v_note" readonly="readonly" rows="3">${goodsPack.note}</div>
					  </div>
					</div>
					<div class="form-actions">
					  <button type="button" class="btn backbtn">返回</button>
					</div>
			</div>
		</div><!--/span-->
		</div><!--/row-->

<%@include file="common/foot.jsp" %>
<script src="<%=request.getContextPath()%>/js/localjs/goodspack.js"></script>