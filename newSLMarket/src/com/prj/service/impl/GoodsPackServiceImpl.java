package com.prj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prj.bean.GoodsPack;
import com.prj.bean.GoodsPackAffiliated;
import com.prj.mapper.GoodsPackMapper;
import com.prj.service.IGoodsPackService;
@Service
public class GoodsPackServiceImpl implements IGoodsPackService{
	@Autowired
	GoodsPackMapper goodsPackMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(GoodsPack record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(GoodsPack record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GoodsPack selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return goodsPackMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GoodsPack record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(GoodsPack record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(GoodsPack record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GoodsPack> queryGoodsPackByGoodsInfoId(GoodsPackAffiliated goodsPackAffiliated) {
		// TODO Auto-generated method stub
		return goodsPackMapper.queryGoodsPackByGoodsInfoId(goodsPackAffiliated);
	}

	@Override
	public int count(GoodsPack goodsPack) {
		// TODO Auto-generated method stub
		return goodsPackMapper.count(goodsPack);
	}

	@Override
	public List<GoodsPack> queryGoodsPacksByPage(GoodsPack goodsPack) {
		// TODO Auto-generated method stub
		return goodsPackMapper.queryGoodsPacksByPage(goodsPack);
	}

	
}