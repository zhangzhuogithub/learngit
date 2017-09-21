package com.prj.mapper;

import java.util.List;

import com.prj.bean.AuUser;
import com.prj.bean.GoodsPack;
import com.prj.bean.GoodsPackAffiliated;

public interface GoodsPackMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsPack record);

    int insertSelective(GoodsPack record);

    GoodsPack selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsPack record);

    int updateByPrimaryKeyWithBLOBs(GoodsPack record);

    int updateByPrimaryKey(GoodsPack record);
    
    //根据商品信息查询套餐
    List<GoodsPack> queryGoodsPackByGoodsInfoId(GoodsPackAffiliated goodsPackAffiliated);
    
    //查询总记录数
    public int count(GoodsPack goodsPack);
    
    //分页查询
    public List<GoodsPack> queryGoodsPacksByPage(GoodsPack goodsPack);
    
    
}