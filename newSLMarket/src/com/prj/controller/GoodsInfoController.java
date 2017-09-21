package com.prj.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prj.bean.AuRole;
import com.prj.bean.AuUser;
import com.prj.bean.GoodsInfo;
import com.prj.bean.GoodsInfoWithBLOBs;
import com.prj.bean.GoodsPack;
import com.prj.bean.GoodsPackAffiliated;
import com.prj.service.IGoodsInfoService;
import com.prj.service.IGoodsPackAffiliatedService;
import com.prj.service.IGoodsPackService;

import common.JsonDateValueProcessor;
import common.PageSupport;
import common.SQLTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/goods")
public class GoodsInfoController {
	@Autowired
	IGoodsInfoService goodsInfoServiceImpl;
	@Autowired
	IGoodsPackService goodsPackServiceImpl;
	

	//查询商品列表（分页）
	@RequestMapping("queryGoodsInfo")
	public String queryGoods(
			Model model,HttpServletRequest request,
			@RequestParam(value="currentPage",required=false)Integer currentPage,
			@RequestParam(value="s_goodsName",required=false)String s_goodsName,
			@RequestParam(value="s_state",required=false)String s_state
			){
		//获取当前对象列表权限
		String baseModel= (String) request.getSession().getAttribute("mList");
		System.out.println(baseModel);
		if (baseModel==null) {
			return "redirect:/";
		}else{
			//获取对象
			GoodsInfoWithBLOBs goodsInfo = new GoodsInfoWithBLOBs();
			if (s_goodsName!=null && !("".equals(s_goodsName))) {
				goodsInfo.setGoodsname(("%"+SQLTools.transfer(s_goodsName)+"%"));
			}else{
				goodsInfo.setGoodsname(null);
			}
			
			if (s_state!=null && !("".equals(s_state))) {
				goodsInfo.setState(Integer.valueOf(s_state));
			}else{
				goodsInfo.setState(null);
			}
			
			//分页
			PageSupport page = new PageSupport();
			//查询总条数
			page.setTotalCount(goodsInfoServiceImpl.count(goodsInfo));
			
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
				goodsInfo.setStartIndex((page.getCurrentPage()-1)*page.getPageSize());
				goodsInfo.setPageSize(page.getPageSize());
				List<GoodsInfo> goodsInfoList = null;
				try {
					goodsInfoList = goodsInfoServiceImpl.queryGoods(goodsInfo);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					goodsInfoList = null;
					if (page==null) {
						page = new PageSupport();
						page.setItems(null);
					}
				}
				page.setItems(goodsInfoList);
			}else{
				page.setItems(null);
				
			}
			
			//存储内容
			model.addAttribute("page", page);
			model.addAttribute("s_goodsName", s_goodsName);
			model.addAttribute("s_state", s_state);
		}
		
		return "goodsList";
		
	}
	
	//查看
	@RequestMapping(value="queryGoodsInfoById",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String queryGoodsInfoById(@RequestParam(value="id",required=false) String id){
		String cjson = "";
		if (id == null || id == "") {
			return "nodata";
		}else{
			try{
				GoodsInfoWithBLOBs goodsInfo = new GoodsInfoWithBLOBs();
				goodsInfo  = goodsInfoServiceImpl.selectByPrimaryKey(Long.valueOf(id));
				
				//user内所有日期属性都会按照此格式转换，对象转json
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
				JSONObject jo = JSONObject.fromObject(goodsInfo,jsonConfig);
				cjson = jo.toString();
			}catch(Exception e){
				e.printStackTrace();
				return "failed";
			}
			return cjson;
		}
	}
	
	//改变上架状态
	@RequestMapping(value="updateState",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String updateState(
			HttpServletRequest request,
			@RequestParam(value="state",required=false)String state,
			@RequestParam(value="goodsinfoid",required=false)String goodsinfoid
			){
		
		if (goodsinfoid == null || goodsinfoid.equals("") || state == null || state.equals("")) {
			return "nodata";
		}else{
			try{
				
					List<GoodsPack> _goodsPackList = new ArrayList<GoodsPack>();
					GoodsPackAffiliated _goodsPackAffiliated = new GoodsPackAffiliated();
					_goodsPackAffiliated.setGoodsinfoid((Long.valueOf(goodsinfoid)));
				
					_goodsPackList = goodsPackServiceImpl.queryGoodsPackByGoodsInfoId(_goodsPackAffiliated);
					if (_goodsPackList.size()>0) {
						JSONArray jsonList = JSONArray.fromObject(_goodsPackList);
						String jsonGoodsPackList = jsonList.toString();
						return jsonGoodsPackList;
					}else{
						GoodsInfoWithBLOBs _goodsInfo = new GoodsInfoWithBLOBs();
						_goodsInfo.setId(Long.valueOf(goodsinfoid));
						_goodsInfo.setState(state.equals("true")?1:2);
						//调用方法
						goodsInfoServiceImpl.updateByPrimaryKeySelective(_goodsInfo);
						return "success";
					}
				
			}catch(Exception e){
				e.printStackTrace();
				return "failed";
			}
			
		}
	}
	
	//删除
	@RequestMapping(value="deleteGoodInfo",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String deleteGoodInfo(
			@RequestParam(value="goodinfoid",required=false)String goodinfoid,
			@RequestParam(value="goodsName",required=false)String goodsName,
			HttpServletRequest request
			){
		String result = "";
		if (goodinfoid == null || goodinfoid.equals("") || goodsName == null || goodsName.equals("")) {
			
			return "nodata";
		}else{
			
			try{
				List<GoodsPack> _goodsPackList = new ArrayList<GoodsPack>();
				GoodsPackAffiliated _goodsPackAffiliated = new GoodsPackAffiliated();
				_goodsPackAffiliated.setGoodsinfoid((Long.valueOf(goodinfoid)));
			
				_goodsPackList = goodsPackServiceImpl.queryGoodsPackByGoodsInfoId(_goodsPackAffiliated);
				if (_goodsPackList.size()>0) {
					JSONArray jsonList = JSONArray.fromObject(_goodsPackList);
					String jsonGoodsPackList = jsonList.toString();
					return jsonGoodsPackList;
				}else{
					goodsInfoServiceImpl.deleteByPrimaryKey(Long.valueOf(goodinfoid));
					return "success";
				}
			}catch(Exception e){
				e.printStackTrace();
				return "failed";
			}
		}
		
	}
	
	//新增商品
	@RequestMapping("addGoodsInfo")
	public String addGoodsInfo(HttpServletRequest request,@ModelAttribute("addGoodsInfo") GoodsInfoWithBLOBs addGoodsInfo){
		if (request.getSession().getAttribute("mList") == null) {
			return "redirect:/";
		}else{
			AuUser currentUser = (AuUser) request.getSession().getAttribute("currentUser");
			addGoodsInfo.setCreatetime(new Date());
			addGoodsInfo.setCreatedby(currentUser.getLogincode());
			addGoodsInfo.setLastupdatetime(new Date());
			goodsInfoServiceImpl.insertSelective(addGoodsInfo);
			return  "redirect:queryGoodsInfo.action";
		}
		
	}
	
	//修改
	@RequestMapping(value="updateGoodsInfo",method=RequestMethod.POST)
	public String updateUser(HttpServletRequest request,@ModelAttribute("modifyGoodsInfo") GoodsInfoWithBLOBs modifyGoodsInfo
			){
		
		if (request.getSession().getAttribute("mList") == null) {
			return "redirect:/";
		}else{
			try{
				
				modifyGoodsInfo.setLastupdatetime(new Date());
				System.out.println(modifyGoodsInfo.getId());
				goodsInfoServiceImpl.updateByPrimaryKeySelective(modifyGoodsInfo);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return "redirect:queryGoodsInfo.action";
	}
	
	//验证编码唯一
	@RequestMapping(value="goodsSNIsExist",produces={"text/html;charset=UTF-8"})
	@ResponseBody
	public String goodsSNIsExist(
			@RequestParam(value="goodssn",required=false) String goodssn,
			@RequestParam(value="id",required=false) String id
			){
		String result = "failed";
		
		GoodsInfo _goodsInfo = new GoodsInfo();
		_goodsInfo.setGoodssn((goodssn));
		if (!id.equals("-1")) {  //修改操作判断Id
			_goodsInfo.setId(Long.valueOf(id));
		}
		if (goodsInfoServiceImpl.goodsInfoIsExist(_goodsInfo) == 0) {
			result = "only";
		}else{
			result = "repeat";
		}
		return result;
		
	}
	
	//
	
}