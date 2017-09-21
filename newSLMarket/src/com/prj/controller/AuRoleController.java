package com.prj.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prj.bean.AuRole;
import com.prj.bean.AuUser;
import com.prj.service.IAuRoleService;
import com.prj.service.IAuUserService;

import net.sf.json.JSONArray;
@Controller
@RequestMapping("/role")
public class AuRoleController {
	@Autowired
	IAuRoleService auRoleServiceImpl;
	@Autowired
	IAuUserService auUserServiceImpl;
	
	//查询全部
	@RequestMapping("queryRoles")
	public String queryRoles(Model model,HttpServletRequest request){
		List<AuRole> roleList = auRoleServiceImpl.queryAllRole();
		model.addAttribute("roleList", roleList);
		return "roleList";
	}
	
	//新增方法
	@RequestMapping("addRole")
	@ResponseBody
	public String addRole(HttpServletRequest request,
			@RequestParam(value="rolecode",required=false)String rolecode,
			@RequestParam(value="rolename",required=false)String rolename){
		AuUser currentUser = (AuUser) request.getSession().getAttribute("currentUser");
		
		//判断
		
		AuRole _role = new AuRole();
		if (rolecode==null||rolecode.equals("")||rolename==null||rolename.equals("")) {
			return  "nodata";
		}else{
			try{
				_role.setRolecode(rolecode);
				if (auRoleServiceImpl.checkIsExist(_role)!=null) {
					return "norolecode";
				}else{
					_role.setRolecode(null);
					_role.setRolename(rolename);
					if (auRoleServiceImpl.checkIsExist(_role)!=null) {
						return  "norolename";
					}else{
						_role.setRolename(rolename);
						_role.setRolecode(rolecode);
						_role.setCreatedate(new Date());
						_role.setCreatedby(currentUser.getLogincode());
						auRoleServiceImpl.insertSelective(_role);
						return "success";
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				return "failed";
			}
			
			
		}
		
	}
	
	
	//删除
	@RequestMapping("deleteRole")
	@ResponseBody
	public String deleteRole(
			@RequestParam(value="roleid",required=false)String roleid,
			@RequestParam(value="rolename",required=false)String rolename,
			HttpServletRequest request
			){
		String result = "";
		if (roleid == null || roleid.equals("") || rolename == null || rolename.equals("")) {
			
			return "nodata";
		}else{
			List<AuUser> _userList = new ArrayList<AuUser>();
			AuUser _User = new AuUser();
			_User.setRoleid(Long.valueOf(roleid));
			_User.setRolename(rolename);
			try{
				_userList = auUserServiceImpl.queryUsersByRoleid(_User);
				if (_userList.size()>0) {
					JSONArray jsonList = JSONArray.fromObject(_userList);
					String jsonUserList = jsonList.toString();
					return jsonUserList;
				}else{
					auRoleServiceImpl.deleteByPrimaryKey(Long.valueOf(roleid));
					return "success";
				}
			}catch(Exception e){
				e.printStackTrace();
				return "failed";
			}
		}
		
	}
	
	//改变是否启用
	@RequestMapping("updateIsstart")
	@ResponseBody
	public String updateIsstart(
			HttpServletRequest request,
			@RequestParam(value="roleid",required=false)String roleid,
			@RequestParam(value="isstart",required=false)String isstart
			){
		
		if (roleid == null || roleid.equals("") || isstart == null || isstart.equals("")) {
			return "nodata";
		}else{
			try{
				
					List<AuUser> _userList = new ArrayList<AuUser>();
					AuUser _User = new AuUser();
					_User.setRoleid(Long.valueOf(roleid));
				
					_userList = auUserServiceImpl.queryUsersByRoleid(_User);
					if (_userList.size()>0) {
						JSONArray jsonList = JSONArray.fromObject(_userList);
						String jsonUserList = jsonList.toString();
						return jsonUserList;
					}else{
						AuRole _role = new AuRole();
						_role.setId(Long.valueOf(roleid));
						_role.setIsstart(isstart.equals("true")?1:2);
						//调用方法
						auRoleServiceImpl.hl_updateByPrimaryKeySelective(_role);
						return "success";
					}
				
			}catch(Exception e){
				e.printStackTrace();
				return "failed";
			}
			
		}
	
	
	}
	
	//修改角色信息
	@RequestMapping("updateRole")
	@ResponseBody
	public String updateRole(
			@RequestParam(value="roleid",required=false)String roleid,
			@RequestParam(value="rolename",required=false)String rolename,
			@RequestParam(value="rolecode",required=false)String rolecode,
			HttpServletRequest request
			){
		
		if (roleid == null || roleid.equals("") || rolename == null || rolename.equals("") || rolecode == null || rolecode.equals("")) {
			return "nodata";
		}else{
			try{
				AuRole _role = new AuRole();
				
				//赋值修改信息
				_role.setId(Long.valueOf(roleid));
				_role.setRolecode(rolecode);
				_role.setRolename(rolename);
				_role.setCreatedate(new Date());
				//调用方法
				auRoleServiceImpl.hl_updateByPrimaryKeySelective(_role);
			
				return "success";
			}catch(Exception e){
				e.printStackTrace();
				return "failed";
			}
		}
	}
}