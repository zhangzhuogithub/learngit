package com.prj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prj.bean.GoodsInfo;
import com.prj.bean.GoodsInfoWithBLOBs;
import com.prj.bean.GoodsPackAffiliated;
import com.prj.mapper.GoodsInfoMapper;
import com.prj.service.IGoodsInfoService;
@Service
public class GoodsInfoServiceImpl implements IGoodsInfoService{
	@Autowired
	GoodsInfoMapper goodsInfoMapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(GoodsInfoWithBLOBs record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(GoodsInfoWithBLOBs record) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.insertSelective(record);
	}

	@Override
	public GoodsInfoWithBLOBs selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GoodsInfoWithBLOBs record) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(GoodsInfoWithBLOBs record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(GoodsInfo record) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public int count(GoodsInfo goodsInfo) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.count(goodsInfo);
	}

	@Override
	public List<GoodsInfo> queryGoods(GoodsInfo goodsInfo) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.queryGoods(goodsInfo);
	}

	@Override
	public int goodsInfoIsExist(GoodsInfo goodsInfo) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.goodsInfoIsExist(goodsInfo);
	}

	@Override
	public List<GoodsInfo> queryGoodsByGoodsPack(GoodsPackAffiliated goodsPackAffiliated) {
		// TODO Auto-generated method stub
		return goodsInfoMapper.queryGoodsByGoodsPack(goodsPackAffiliated);
	}

}