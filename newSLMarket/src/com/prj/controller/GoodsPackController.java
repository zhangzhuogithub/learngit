package com.prj.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prj.bean.AuRole;
import com.prj.bean.AuUser;
import com.prj.bean.DataDictionary;
import com.prj.bean.GoodsInfo;
import com.prj.bean.GoodsInfoWithBLOBs;
import com.prj.bean.GoodsPack;
import com.prj.bean.GoodsPackAffiliated;
import com.prj.service.IDataDictionaryService;
import com.prj.service.IGoodsInfoService;
import com.prj.service.IGoodsPackService;

import common.JsonDateValueProcessor;
import common.PageSupport;
import common.SQLTools;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
@Controller
@RequestMapping("/goodsPack")
public class GoodsPackController {
	@Autowired
	IGoodsPackService goodsPackServiceImpl;
	@Autowired
	IDataDictionaryService dataDictionaryServiceImpl;
	@Autowired
	IGoodsInfoService goodsInfoServiceImpl;

	
	//分页查询
	
	@RequestMapping("queryGoodsPacks")
	public String queryGoodsPacks(
			Model model,HttpServletRequest request,
			@RequestParam(value="currentPage",required=false)Integer currentPage,
			@RequestParam(value="s_goodsPackName",required=false)String s_goodsPackName,
			@RequestParam(value="s_typeId",required=false)String s_typeId,
			@RequestParam(value="s_state",required=false)String s_state
			){
		//获取当前对象列表权限
		String baseModel= (String) request.getSession().getAttribute("mList");
		if (baseModel==null) {
			return "redirect:/";
		}else{
			//获取套餐类型
			DataDictionary dataDictionary = new DataDictionary();
			dataDictionary.setTypecode("PACK_TYPE");
			List<DataDictionary> packTypeList = dataDictionaryServiceImpl.queryDataDictionarys(dataDictionary);
			
			//
			GoodsPack goodsPack = new GoodsPack();
			if (s_goodsPackName!=null && !("".equals(s_goodsPackName))) {
				goodsPack.setGoodspackname("%"+SQLTools.transfer(s_goodsPackName)+"%");
			}
			
			if (s_state!=null && !("".equals(s_state))) {
				goodsPack.setState(Integer.valueOf(s_state));
			}else{
				goodsPack.setState(null);
			}
			
			//分页
			PageSupport page = new PageSupport();
			page.setTotalCount(goodsPackServiceImpl.count(goodsPack));
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
				goodsPack.setStartIndex((page.getCurrentPage()-1)*page.getPageSize());
				goodsPack.setPageSize(page.getPageSize());
				List<GoodsPack> goodsPackList = null;
				try {
					goodsPackList = goodsPackServiceImpl.queryGoodsPacksByPage(goodsPack);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					goodsPackList = null;
					if (page==null) {
						page = new PageSupport();
						page.setItems(null);
					}
				}
				page.setItems(goodsPackList);
			}else{
				page.setItems(null);
				
			}
			
			System.out.println(page);
			model.addAttribute("packTypeList", packTypeList);
			model.addAttribute("page", page);
			model.addAttribute("s_goodsPackName", s_goodsPackName);
			
			model.addAttribute("s_typeId", s_typeId);
			model.addAttribute("s_state", s_state);
			
			model.addAttribute(baseModel);
			
			return "goodsPackList";
			
		}
	}
		
	//查看view
	
	@RequestMapping(value="queryGoodsPackById",produces={"text/html;charset=UTF-8"})
	
	public String queryGoodsPackById(@RequestParam(value="id",required=false) String id,HttpServletRequest request){
		
		if (id == null || id == "") {
			return "goodsPackList";
		}else{
			GoodsPack goodsPack = new GoodsPack();
			
			goodsPack = goodsPackServiceImpl.selectByPrimaryKey(Long.valueOf(id));
			//查询商品信息
			GoodsPackAffiliated goodsPackAffiliated = new GoodsPackAffiliated();
			goodsPackAffiliated.setGoodspackid(Long.valueOf(id));
			List<GoodsInfo> goodsList = null;
			goodsList = goodsInfoServiceImpl.queryGoodsByGoodsPack(goodsPackAffiliated);
			
			request.setAttribute("goodsPack", goodsPack);
			request.setAttribute("goodsList", goodsList);
			return "viewGoodsPack";
		}
	}
		
}