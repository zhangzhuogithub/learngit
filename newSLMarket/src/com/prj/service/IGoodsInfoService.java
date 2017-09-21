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
    
    //��ѯ������
    public int count(GoodsInfo goodsInfo);
    //��ҳ��ѯ
    public List<GoodsInfo> queryGoods(GoodsInfo goodsInfo);
    
    //��Ʒ�����Ƿ����
    public int goodsInfoIsExist(GoodsInfo goodsInfo);
    
    //������Ʒ�ײͱ�Ų�ѯ������Ʒ��Ϣ
    public List<GoodsInfo> queryGoodsByGoodsPack(GoodsPackAffiliated goodsPackAffiliated);
}