package com.prj.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.prj.bean.AuAuthority;
import com.prj.bean.AuFunction;
import com.prj.bean.AuRole;
import com.prj.bean.AuUser;
import com.prj.bean.DataDictionary;
import com.prj.bean.Menu;
import com.prj.mapper.AuUserMapper;
import com.prj.service.IAuFunctionService;
import com.prj.service.IAuRoleService;
import com.prj.service.IAuUserService;
import com.prj.service.IDataDictionaryService;
import com.prj.service.impl.AuUserServiceImpl;

import common.Constants;
import common.JsonDateValueProcessor;
import common.PageSupport;
import common.SQLTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
@Controller
@RequestMapping("/user")
public class AuUserController {
	@Resource
	IAuUserService auUserServiceImpl;
	@Resource
	private IAuFunctionService auFunctionServiceImpl;
	@Resource
	private IAuRoleService auRoleServiceImpl;
	@Resource
	private IDataDictionaryService dataDictionaryServiceImpl;
	
	//登陆成功
	@RequestMapping("main")
	public String main(HttpServletRequest request,Model model){
		//menulist
		AuUser currentUser = (AuUser) request.getSession().getAttribute("currentUser");
		List<Menu> mList = null;
		if(currentUser!=null){
			model.addAttribute("currentUser", currentUser);
			//根据当前用户获取菜单列表
			mList = getFunction(currentUser.getRoleid());
			
			//json
			if (mList!=null) {
				JSONArray jsonArray = JSONArray.fromObject(mList);
				String jsonString = jsonArray.toString();
				model.addAttribute("mList", jsonString);
				request.getSession().setAttribute("mList", jsonString);
			}
		}
		System.out.println(currentUser.getLogincode()+"-----"+currentUser.getRolename());
		
		return "main";
		
		
	}
	//获取功能列表
	protected List<Menu> getFunction(Long roleId){
		List<Menu> menuList = new ArrayList<Menu>();
		AuAuthority authority = new AuAuthority();
		authority.setRoleid(roleId);
		
		List<AuFunction> mList= auFunctionServiceImpl.queryMainFunctionList(authority);
		if (mList!=null) {
			for (AuFunction auFunction : mList) {
				Menu menu = new Menu();
				menu.setMainMenu(auFunction);
				auFunction.setRoleid(roleId);
				List<AuFunction> subList = auFunctionServiceImpl.querySubFunctionList(auFunction);
				if (subList!=null) {
					menu.setSubMenu(subList);
				}
				menuList.add(menu);
			}
		}
		
		return menuList;
 	}
	
	//验证登录
	@RequestMapping("login")
	@ResponseBody
	public String  login(@RequestParam String user,HttpServletRequest request){
		if(user == null || user == ""){
			return "nodata";
		}else{
			JSONObject userObject = JSONObject.fromObject(user);
			AuUser currentUser1 = (AuUser) JSONObject.toBean(userObject,AuUser.class);
			System.out.println(auUserServiceImpl.logincodeIsExist(currentUser1));
			try{
			if (auUserServiceImpl.logincodeIsExist(currentUser1)==0) {
				return "nologincode";
			}else{
				AuUser currentUser2 = auUserServiceImpl.login(currentUser1);
				if (currentUser2!=null) {
					//登陆成功
					request.getSession().setAttribute("currentUser", currentUser2);
					//更新当前登陆时间lastlogintime
					AuUser logintimeuser = new AuUser();
					logintimeuser.setId(currentUser2.getId());
					logintimeuser.setLastlogintime(new Date());
					auUserServiceImpl.updateByPrimaryKeySelective(logintimeuser);
					logintimeuser=null;
					return "success";
				}else{
					//密码错误 
					return "nopassword";
				}
			}
			}catch(Exception e){
				
				return "failed";
			}
		}	
		
	

	}
	//注销
	@RequestMapping("logout")
	public String logout(HttpServletRequest request){
		
		//清空SESSION
		request.getSession().invalidate();
		String path = request.getContextPath();
		return "login";
		
		
	}
	
	//修改密码
	@RequestMapping("updatepwd")
	@ResponseBody
	public String updatepwd(HttpServletRequest request,@RequestParam String userJson){
		//获取当前对象
		AuUser currentUser = (AuUser) request.getSession().getAttribute("currentUser");
		
		if (userJson==null && userJson.equals("")) {
			return "nodata";
		}else{
			JSONObject userObject = JSONObject.fromObject(userJson);
			AuUser user = (AuUser) userObject.toBean(userObject,AuUser.class);
			user.setId(currentUser.getId());
			user.setLogincode(currentUser.getLogincode());
			
			try {
				if (auUserServiceImpl.login(user)!=null) {
					user.setPassword(user.getPassword2());
					user.setPassword2(null);
					auUserServiceImpl.updateByPrimaryKeySelective(user);
				}else{
					return "oldpwdwrong";
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				return "failed";
			}
		}
		return "success";
	}
	
	//获取用户列表
	@RequestMapping("queryUsers")
	public String queryUsers(Model model,HttpServletRequest request,
			@RequestParam(value="currentPage",required=false)Integer currentPage,
			@RequestParam(value="s_logincode",required=false)String s_logincode,
			@RequestParam(value="s_refercode",required=false)String s_refercode,
			@RequestParam(value="s_roleid",required=false)String s_roleid,
			@RequestParam(value="s_isstart",required=false)String s_isstart){
		//获取当前对象列表权限
		String baseModel= (String) request.getSession().getAttribute("mList");
		System.out.println(baseModel);
		if (baseModel==null) {
			return "redirect:/";
		}else{
			//获取roleList and cardTypeList 
			List<AuRole> roleList = auRoleServiceImpl.queryAllRoleByIsstart();
			DataDictionary dataDictionary = new DataDictionary();
			dataDictionary.setTypecode("CARD_TYPE");
			List<DataDictionary> cardTypeList = dataDictionaryServiceImpl.queryDataDictionarys(dataDictionary);
			
			//
			AuUser user = new AuUser();
			if (s_logincode!=null && !("".equals(s_logincode))) {
				user.setLogincode("%"+SQLTools.transfer(s_logincode)+"%");
			}
			if (s_refercode!=null && !("".equals(s_refercode))) {
				user.setRefercode("%"+SQLTools.transfer(s_refercode)+"%");
			}
			if (s_isstart!=null && !("".equals(s_isstart))) {
				user.setIsstart(Integer.valueOf(s_isstart));
			}else{
				user.setIsstart(null);
				
			}
			if (s_roleid!=null && !("".equals(s_roleid))) {
				user.setRoleid(Long.valueOf(s_roleid));
			}else{
				user.setRoleid(null);
			}
			//分页
			PageSupport page = new PageSupport();
			page.setTotalCount(auUserServiceImpl.count(user));
			if (page.getTotalCount()>0) {
				if (currentPage !=null) {
					page.setCurrentPage(currentPage);
				}
				if(page.getCurrentPage() <=0){
					page.setCurrentPage(1);
				}
				if(page.getCurrentPage() > page.getPageCount()){
					page.setCurrentPage(page.getPageCount());
				}
				//分页查询limit ?,?
				user.setStartIndex((page.getCurrentPage()-1)*page.getPageSize());
				user.setPageSize(page.getPageSize());
				List<AuUser> userList = null;
				try {
					userList = auUserServiceImpl.queryUsersByPage(user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					userList = null;
					if (page==null) {
						page = new PageSupport();
						page.setItems(null);
					}
				}
				page.setItems(userList);
			}else{
				page.setItems(null);
				
			}
			
			System.out.println(page);
			model.addAttribute("cardTypeList", cardTypeList);
			model.addAttribute("page", page);
			model.addAttribute("s_logincode", s_logincode);
			model.addAttribute("s_refercode", s_refercode);
			model.addAttribute("s_roleid", s_roleid);
			model.addAttribute("s_isstart", s_isstart);
			model.addAttribute("roleList", roleList);
			model.addAttribute(baseModel);
			System.out.println(page.getNextPages());
			return "userList";
			
		}
		
	
	}
	
	
	//加载新增用户类型
	@RequestMapping(value="loadUserTypeList",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String loadUserTypeList(@RequestParam(value="s_roleid",required=false) String s_roleid){
		String cjsonString = "";
		DataDictionary dataDictionary = new DataDictionary();
		dataDictionary.setTypecode("USER_TYPE");
		List<DataDictionary> userTypeList = dataDictionaryServiceImpl.queryDataDictionarys(dataDictionary);
		JSONArray jo = JSONArray.fromObject(userTypeList);
		cjsonString = jo.toString();
		return cjsonString;	
	}
	
	//
	@RequestMapping(value="logincodeIsExist",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String logincodeIsExist(@RequestParam(value="logincode",required=false) String logincode,@RequestParam(value="id",required=false) String id){
		
		String result = "failed";
		
		AuUser _user = new AuUser();
		_user.setLogincode(logincode);
		if (!id.equals("-1")) {//修改操作判断Id
			_user.setId(Long.valueOf(id));
		}
		if (auUserServiceImpl.logincodeIsExist(_user) == 0) {
			result = "only";
		}else{
			result = "repeat";
		}
		
		
		return result;
	}
	
	//新增
	@RequestMapping(value="addUser",method = RequestMethod.POST)
	public String addUser(Model mdoel,HttpServletRequest request,@ModelAttribute("addUser") AuUser addUser){
		System.out.println(addUser);
		if (request.getSession().getAttribute("mList") == null) {
			return "redirect:/";
		}else{
			String idCard = addUser.getIdcard();
			String ps = idCard.substring(idCard.length()-6);
			addUser.setPassword(ps);
			addUser.setPassword2(ps);
			AuUser currentUser = (AuUser) request.getSession().getAttribute("currentUser");
			addUser.setReferid(currentUser.getId());
			addUser.setRefercode(currentUser.getLogincode());
			addUser.setLastupdatetime(new Date());
			addUser.setCreatetime(new Date());
			auUserServiceImpl.insertSelective(addUser);
			
			return  "redirect:queryUsers.action";
		}
		
	}
	
	//上传图片
	@RequestMapping(value="upload",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String upload(
			@RequestParam(value="a_fileInputID",required=false) MultipartFile cardFile,
			@RequestParam(value="a_fileInputBank",required=false) MultipartFile bankFile,
			@RequestParam(value="m_fileInputID",required=false) MultipartFile mCardFile,
			@RequestParam(value="m_fileInputBank",required=false) MultipartFile mBankFile,
			@RequestParam(value="logincode",required=false) String logincode,
			HttpServletRequest request){
		//根据服务器操作系统自动获取路径，自适应
		String path = request.getSession().getServletContext().getRealPath(File.separator+"uploadFile");
		DataDictionary dataDictionary = new DataDictionary();
		dataDictionary.setTypecode("PERSONALFILE_SIZE");
		List<DataDictionary> list=null;
		try{
			list = dataDictionaryServiceImpl.queryDataDictionarys(dataDictionary);
		}catch(Exception e){
			e.printStackTrace();
		}
		int filesize = 50000;
		if (list!=null) {
			if (list.size()==1) {
				filesize = Integer.valueOf(list.get(0).getValuename());
			}
		}
		if (cardFile!=null) {
			String oldFileName = cardFile.getOriginalFilename();
			String prefix = FilenameUtils.getExtension(oldFileName);
			if (cardFile.getSize()>filesize) {
				return "1";
			}else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){
				//给源文件重命名：系统毫秒数+100W以内随机数
				String filename = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"IDcard.jpg";
				File targetFile = new File(path,filename);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				//保存
				try{
					cardFile.transferTo(targetFile);
				}catch(Exception e){
					e.printStackTrace();
				}
				String url = request.getContextPath()+"/uploadFile/"+filename;
				return url;
			}else{
				return "2";
			}
		}
		
		if(bankFile != null){
        	String oldFileName = bankFile.getOriginalFilename();
           
            String prefix=FilenameUtils.getExtension(oldFileName);     
            if(bankFile.getSize() >  filesize){//上传大小不得超过 50k
            	return "1";
            }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
            		|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){
            	String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"bank.jpg";  
                
                File targetFile = new File(path, fileName);  
                if(!targetFile.exists()){  
                    targetFile.mkdirs();  
                }  
                //保存  
                try {  
                	bankFile.transferTo(targetFile);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                String url = request.getContextPath()+"/uploadFile/"+fileName;
                return url;  
            }else{//上传图片格式不正确
            	return "2";
            }
        }
        if(mCardFile != null){
        	String oldFileName = mCardFile.getOriginalFilename();
            String prefix=FilenameUtils.getExtension(oldFileName);     
            if(mCardFile.getSize() >  filesize){//上传大小不得超过 50k
            	return "1";
            }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
            		|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式不正确
            	String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"IDcard.jpg";  
                
                File targetFile = new File(path, fileName);  
                if(!targetFile.exists()){  
                    targetFile.mkdirs();  
                }  
                //保存  
                try {  
                	mCardFile.transferTo(targetFile);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                String url = request.getContextPath()+"/uploadFile/"+fileName;
                return url;  
            }else{
            	return "2";
            }
        }
        if(mBankFile != null){
        	String oldFileName = mBankFile.getOriginalFilename();
            
            String prefix=FilenameUtils.getExtension(oldFileName);     
            if(mBankFile.getSize() >  filesize){//上传大小不得超过 50k
            	return "1";
            }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
            		|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式不正确
            	String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"bank.jpg";  
                
                File targetFile = new File(path, fileName);  
                if(!targetFile.exists()){  
                    targetFile.mkdirs();  
                }  
                //保存  
                try {  
                	mBankFile.transferTo(targetFile);  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
                String url = request.getContextPath()+"/uploadFile/"+fileName;
                return url;  
            }else{
            	return "2";
            }
        }
		
		return null;
	}
	
	//删除图片
	@RequestMapping(value="delpic",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String delpic(
			HttpServletRequest request,
			@RequestParam(value="id",required=false) String id,
			@RequestParam(value="picPath",required=false) String picPath){
		String result = "failed";
		if (picPath == null || picPath.equals("")) {
			result = "success";
		}else{
			String[] paths = picPath.split("/");
			//解析称物理路径
			String path = request.getSession().getServletContext().getRealPath(File.separator+paths[2]+File.separator+paths[3]);
		    File file = new File(path);
		    if (file.exists()) {
		    	if (file.delete()) {
					if (id.equals("0")) {
						result = "success";
					}else{//修改用户时删除上传图片
						AuUser user = new  AuUser();
						user.setId(Long.valueOf(id));
						if (picPath.indexOf("IDcard.jpg")!=-1) {
							user.setIdcardpicpath(picPath);
						}else if(picPath.indexOf("bank.jpg")!=-1){
							user.setBankpicpath(picPath);
							
						}
						try{
							if (auUserServiceImpl.delUserPic(user)>0) {
								result="success";
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						
					}
				}
			}
		}
		
		return result;
	}
	
	
	@RequestMapping(value="queryUserById",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public Object queryUserById(@RequestParam(value="id",required=false) String id){
		String cjson = "";
		if (id == null || id == "") {
			return "nodata";
		}else{
			try{
				AuUser user = new AuUser();
				user  = auUserServiceImpl.selectByPrimaryKey(Long.valueOf(id));
				
				//user内所有日期属性都会按照此格式转换，对象转json
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
				JSONObject jo = JSONObject.fromObject(user,jsonConfig);
				cjson = jo.toString();
			}catch(Exception e){
				e.printStackTrace();
				return "failed";
			}
			return cjson;
		}
	}
		
	@RequestMapping(value="updateUser",method=RequestMethod.POST)
	public String updateUser(Model model,HttpServletRequest request,@ModelAttribute("modifyUser")AuUser modifyUser){
		if (request.getSession().getAttribute("mList") == null) {
			return "redirect:/";
		}else{
			try{
				modifyUser.setLastupdatetime(new Date());
				auUserServiceImpl.updateByPrimaryKeySelective(modifyUser);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return "redirect:queryUsers.action";
	}
	
	@RequestMapping(value="deleteUser",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String deleteUser(@RequestParam(value="delId",required=false)String delId,
			@RequestParam(value="delIdCardPicPath",required=false)String delIdCardPicPath,
			@RequestParam(value="delBankPicPath",required=false)String delBankPicPath,
			@RequestParam(value="delUserType",required=false)String delUserType,
			HttpServletRequest request){
		String result = "failed";
		AuUser delUser = new AuUser();
		delUser.setId(Long.valueOf(delId));
		try{
			//除了注册会员,其他类型无法被删除
			if (delUserType.equals("2") || delUserType.equals("3") ||delUserType.equals("4")) {
				result = "noallow";
			}else{
				//先删除附件,在删除数据
				if(this.delpic(request,delId,delIdCardPicPath).equals("success") && this.delpic(request,delId,delBankPicPath).equals("success")) {
					if (auUserServiceImpl.deleteByPrimaryKey(Long.valueOf(delId))>0) {
						result="success";
					}
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
}