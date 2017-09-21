<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="common/head.jsp" %>

<div>
	<ul class="breadcrumb">
		<li><a href="#">后台管理</a> <span class="divider">/</span></li>
		<li><a href="queryAuthoritys.action">权限管理</a></li>
	</ul>
</div>

<div class="row-fluid sortable">		
	<div class="box span12">
		<div class="box-header well" data-original-title>
			<h2><i class="icon-user"></i> 权限管理 </h2>
		</div>
		
		<div class="box-content">
			<table class="table table-striped table-bordered bootstrap-datatable datatable">
			  <tbody>
			  <tr>
			  <td width="200px;">
			  <ul class="rolelistul">
			  <c:forEach items="${roleList}" var="role">
			  	<li>
			  		<a class="roleNameAuthority" rolename="${role.rolename}" roleid="${role.id}">${role.rolename}</a>
			  	</li>
			  </c:forEach>
			  </ul>
			  </td>
			  <td>
			  <h3 id="selectrole"></h3>
			  <input type="hidden" id="roleidhide" value=""/>
			    <p class="btn-group">
			    	<button class="btn" id="selectAll">全选</button>
				  	<button class="btn" id="unSelect">全不选</button>
				  	<button class="btn" id="reverse">反选</button>
				 </p>
			  	<ul id="functionList"></ul>
			  	<div class="clear"></div>
			  	<p class="center">
					<a id="confirmsave" class="btn btn-large btn-primary"><i class="icon-chevron-left icon-white"></i> 确定赋予权限</a> 
				</p>
			  </td>
			  <tr>
			  </tbody>
		  </table>   
	</div>
</div><!--/span-->
</div><!--/row-->
<%@include file="common/foot.jsp" %>
<script src="<%=request.getContextPath()%>/js/localjs/authoritylist.js"></script>