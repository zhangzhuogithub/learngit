<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="common/head.jsp" %>

<div>
	<ul class="breadcrumb">
		<li><a href="#">后台管理</a> <span class="divider">/</span></li>
		<li><a href="goodsPack/queryGoodsPacks.action">商品套餐管理</a></li>
	</ul>
</div>
	<div class="row-fluid sortable">		
		<div class="box span12">
			<div class="box-header well" data-original-title>
				<h2><i class="icon-user"></i> 商品套餐列表</h2>
				<div class="box-icon">
					<span class="icon32 icon-color icon-add custom-setting addGoodsPack"/>
				</div>
			</div>
			
			<div class="box-content">
				<form action="<%=request.getContextPath()%>/goodsPack/queryGoodsPacks.action" method="post">
					<div class="searcharea">
					商品套餐名称:
					<input type="text" name="s_goodsPackName" value="${s_goodsPackName}" />
					套餐类型：
					 <select name="s_typeId" style="width:100px;">
			 			<option value="" selected="selected">--请选择--</option>
			 			<c:if test="${packTypeList != null}">
			 				<c:forEach items="${packTypeList}" var="packType">
			 					<option <c:if test="${s_typeId == packType.valueid}">selected = "selected"</c:if>
			 					value="${packType.valueid}">${packType.valuename}</option>
			 				</c:forEach>
			 			</c:if>
			 		</select>
					状态：
					 <select name="s_state" style="width:100px;">
						<option value="" selected="selected">--请选择--</option>
						　　 <c:if test="${s_state == 1}">  
							　　<option value="1" selected="selected">上架</option>
								<option value="2">下架</option>
						　　 </c:if>  
						　　 <c:if test="${s_state == 2}">  
							　    <option value="2" selected="selected">下架</option>
								<option value="1">上架</option>
						 	 </c:if>
						　　  <c:if test="${s_state == null || s_state == ''}">  
							　    <option value="2">下架</option>
								<option value="1">上架</option>
						 	</c:if>
				 	</select>
					<button type="submit" class="btn btn-primary"><i class="icon-search icon-white"></i> 查询 </button>
				</div>
				</form>
			
				<table class="table table-striped table-bordered bootstrap-datatable datatable">
				  <thead>
					  <tr>
						  <th>套餐编号</th>
						  <th>套餐名称</th>
						  <th>套餐总价(元)</th>
						  <th>库存量</th>
						  <th>状态(上架/下架)</th>
						  <th>套餐类型</th>
						  <th>最后更新时间</th>
						  <th>操作</th>
					  </tr>
				  </thead>   
				  <tbody>
				  <c:if test="${page.items != null}">
				  <c:forEach items="${page.items}" var="goodsPack">
					<tr>
						<td class="center">${goodsPack.goodspackcode}</td>
						<td class="center">${goodsPack.goodspackname}</td>
						<td class="center">${goodsPack.totalprice}</td>
						<td class="center">${goodsPack.num}</td>
						<td class="center">
						<input type="checkbox" title="直接勾选修改状态，立即生效" data-rel="tooltip" class="modifystate" state="${goodsPack.state}" goodspackid="${goodsPack.id}" <c:if test="${goodsPack.state == 1}"> checked="true"</c:if>/>
						</td>
						<td class="center">${goodsPack.typename}</td>
						<td class="center">
						<fmt:formatDate value="${goodsPack.lastupdatetime}" pattern="yyyy-MM-dd"/>
						</td>
						<td class="center">
							<a class="btn btn-success viewgoodspack" href="#" id="${goodsPack.id}">
								<i class="icon-zoom-in icon-white"></i>  
								查看                                           
							</a>
							<a class="btn btn-info modifygoodspack" href="#" id="${goodsPack.id}">
								<i class="icon-edit icon-white"></i>  
								修改                                            
							</a>
							<a class="btn btn-danger delgoodspack" href="#" id="${goodsPack.id}" goodsPackName="${goodsPack.goodspackname}">
								<i class="icon-trash icon-white"></i> 
								删除
							</a>
						</td>
					</tr>
				  </c:forEach>
				 </c:if>
				  </tbody>
			  </table>   
			<div class="pagination pagination-centered">
			  <ul>
			  <c:choose>
			  	<c:when test="${page.currentPage == 1}">
			  	<li class="active"><a href="javascript:void();" title="首页">首页</a></li>
			  	</c:when>
			  	<c:otherwise>
			  	<li><a href="<%=request.getContextPath()%>/goodsPack/queryGoodsPacks.action?currentpage=1&s_goodsPackName=${s_goodsPackName}&s_state=${s_state}&s_typeId=${s_typeId}" title="首页">首页</a></li>
			  	</c:otherwise>
			  </c:choose>
				<c:if test="${page.prevPages!=null}">
					<c:forEach items="${page.prevPages}" var="num">
						<li><a href="<%=request.getContextPath()%>/goodsPack/queryGoodsPacks.action?currentpage=${num}&s_goodsPackName=${s_goodsPackName}&s_state=${s_state}&s_typeId=${s_typeId}"
							class="number" title="${num}">${num}</a></li>
					</c:forEach>
				</c:if>
				<li class="active">
				  <a href="#" title="${page.currentPage}">${page.currentPage}</a>
				</li>
				<c:if test="${page.nextPages!=null}">
					<c:forEach items="${page.nextPages}" var="num">
						<li><a href="<%=request.getContextPath()%>/goodsPack/queryGoodsPacks.action?currentpage=${num}&s_goodsPackName=${s_goodsPackName}&s_state=${s_state}&s_typeId=${s_typeId}" title="${num}">
						${num} </a></li>
					</c:forEach>
				</c:if>
				<c:if test="${page.pageCount !=null}">
					<c:choose>
				  	<c:when test="${page.currentPage == page.pageCount}">
				  	<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
				  	</c:when>
				  	<c:otherwise>
				  	<li><a href="<%=request.getContextPath()%>/goodsPack/queryGoodsPacks.action?currentpage=${page.pageCount}&s_goodsPackName=${s_goodsPackName}&s_state=${s_state}&s_typeId=${s_typeId}" title="尾页">尾页</a></li>
				  	</c:otherwise>
				    </c:choose>
			    </c:if>
				<c:if test="${page.pageCount == null}">
				<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
			  	</c:if>
			  </ul>
		  </div>
		</div>
	</div><!--/span-->
</div><!--/row-->

<%@include file="common/foot.jsp" %>
<script src="<%=request.getContextPath()%>/js/localjs/goodspack.js"></script>