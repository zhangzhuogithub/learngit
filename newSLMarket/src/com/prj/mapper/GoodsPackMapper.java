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
    
    //������Ʒ��Ϣ��ѯ�ײ�
    List<GoodsPack> queryGoodsPackByGoodsInfoId(GoodsPackAffiliated goodsPackAffiliated);
    
    //��ѯ�ܼ�¼��
    public int count(GoodsPack goodsPack);
    
    //��ҳ��ѯ
    public List<GoodsPack> queryGoodsPacksByPage(GoodsPack goodsPack);
    
    
}