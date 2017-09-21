package com.prj.service;

import java.util.List;

import com.prj.bean.GoodsInfo;
import com.prj.bean.GoodsInfoWithBLOBs;
import com.prj.bean.GoodsPackAffiliated;

public interface IGoodsInfoService {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsInfoWithBLOBs record);

    int insertSelective(GoodsInfoWithBLOBs record);

    GoodsInfoWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GoodsInfoWithBLOBs record);

    int updateByPrimaryKey(GoodsInfo record);
    
    //查询总条数
    public int count(GoodsInfo goodsInfo);
    //分页查询
    public List<GoodsInfo> queryGoods(GoodsInfo goodsInfo);
    
    //商品编码是否存在
    public int goodsInfoIsExist(GoodsInfo goodsInfo);
    
    //根据商品套餐编号查询所有商品信息
    public List<GoodsInfo> queryGoodsByGoodsPack(GoodsPackAffiliated goodsPackAffiliated);
}