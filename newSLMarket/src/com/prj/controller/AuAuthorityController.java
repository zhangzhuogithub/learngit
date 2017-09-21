package com.prj.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prj.bean.AuAuthority;
import com.prj.bean.AuFunction;
import com.prj.bean.AuRole;
import com.prj.bean.AuUser;
import com.prj.bean.Menu;
import com.prj.bean.RoleFunctions;
import com.prj.service.IAuAuthorityService;
import com.prj.service.IAuFunctionService;
import com.prj.service.IAuRoleService;

import net.sf.json.JSONArray;
@Controller
@RequestMapping("authority")
public class AuAuthorityController {
	@Autowired
	IAuAuthorityService AuAuthorityServiceImpl;
	@Autowired
	IAuRoleService AuRoleServiceImpl;
	@Autowired
	IAuFunctionService AuFunctionServiceImpl;
	
	@Autowired
	AuUserController auUserController;
	
	//进入权限管理页面
	@RequestMapping("queryAuthoritys")
	public String queryAuthoritys(HttpServletRequest request,Model model){
		Object mList = request.getSession().getAttribute("mList");
		if (mList == null) {
			return "redirect:/";
		}else{
			List<AuRole> roleList = null;
			try{
				roleList = AuRoleServiceImpl.queryAllRoleByIsstart();
				model.addAttribute("roleList", roleList);
				
			}catch(Exception e){
				e.printStackTrace();
				roleList = null;
			}
		}
		return "authorityList";
		
	}
	
	
	//获取菜单功能列表
	@RequestMapping(value="queryAuthorityByRoleid",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String queryAuthorityByRoleid(){
		String resultString = "nodata";
		AuFunction function = new AuFunction();
		try{
			function.setId(Long.valueOf(0));
			List<AuFunction> fList = AuFunctionServiceImpl.querySubauFunctionList(function);
			List<RoleFunctions> rList = new ArrayList<RoleFunctions>();
			if (fList!=null) {
				for (AuFunction auFunction : fList) {
					RoleFunctions roleFunction = new RoleFunctions();
					roleFunction.setMainFunction(auFunction);
					roleFunction.setSubFunction(AuFunctionServiceImpl.querySubauFunctionList(auFunction));
					rList.add(roleFunction);
				
				}
				resultString = JSONArray.fromObject(rList).toString();
				
			}
		}catch(Exception e){
			
		}
		
		return resultString;
		
	}
	
	//获取菜单功能列表
	@RequestMapping(value="queryAuthorityDefault",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String queryAuthorityDefault(@RequestParam Integer rid,@RequestParam Integer fid){
		String result = "nodata";
		try{
			AuAuthority auAuthority = new AuAuthority();
			auAuthority.setRoleid(Long.valueOf(rid));
			auAuthority.setFunctionid((Long.valueOf(fid)));
			AuAuthority _auAuthority =  AuAuthorityServiceImpl.queryAuthorityByRoleidAndFuncid(auAuthority);
			System.out.println(_auAuthority);
			if (AuAuthorityServiceImpl.queryAuthorityByRoleidAndFuncid(auAuthority) !=null && !(AuAuthorityServiceImpl.queryAuthorityByRoleidAndFuncid(auAuthority).equals(""))) {
				result = "success";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;

	}
	
	//修改权限
	@RequestMapping(value="updateAuthority",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String updateAuthority(HttpServletRequest request,@RequestParam String ids){
		String result="nodata";
		try{
			if (ids!=null) {
				//String[] idsArrayStrings = StringUtils.split(ids,"-");
				String[] idsArrayStrings = ids.split("-");
				if (idsArrayStrings.length>0) {
					AuUser currentUser = (AuUser) request.getSession().getAttribute("currentUser");
					//权限表的修改       先把该角色下功能删除，再重新授权   ---事物
					AuAuthorityServiceImpl.hl_addAuthority(idsArrayStrings, currentUser.getLogincode());
					
					//redis缓存修改
					/*List<Menu> mList = null;
					mList = auUserController.getFunction(Long.valueOf(idsArrayStrings[0]));
					JSONArray jsonArray = JSONArray.fromObject(mList);
					redisAPI.set("menuList"+idsArrayStrings[0]),jsonArray.toString());*/
					
					//get all role url list to redis
					/*Authority authority = new Authority();
					authority.setRoleId(Integer.valueOf(idsArrayStrings[0]));
					List<Function> functionList = functionService.getFunctionListByRoId(authority);
					
					if(null != functionList || functionList.size() >= 0){
						StringBuffer sBuffer = new StringBuffer();
						for(Function f:functionList){
							sBuffer.append(f.getFuncUrl());
						}
						redisAPI.set("Role"+idsArrayStrings[0]+"UrlList", sBuffer.toString());
					}*/
					result = "success";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
		
	}
	
}