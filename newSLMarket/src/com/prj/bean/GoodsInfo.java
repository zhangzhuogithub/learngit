package com.prj.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class GoodsInfo extends Base{
    private Long id;

    private String goodssn;

    private String goodsname;

    private Double marketprice;

    private Double realprice;

    private Integer state;

    private Integer num;

    private String unit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createtime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastupdatetime;

    private String createdby;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodssn() {
        return goodssn;
    }

    public void setGoodssn(String goodssn) {
        this.goodssn = goodssn == null ? null : goodssn.trim();
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public Double getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(Double marketprice) {
        this.marketprice = marketprice;
    }

    public Double getRealprice() {
        return realprice;
    }

    public void setRealprice(Double realprice) {
        this.realprice = realprice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby == null ? null : createdby.trim();
    }

	@Override
	public String toString() {
		return "GoodsInfo [id=" + id + ", goodssn=" + goodssn + ", goodsname=" + goodsname + ", marketprice="
				+ marketprice + ", realprice=" + realprice + ", state=" + state + ", num=" + num + ", unit=" + unit
				+ ", createtime=" + createtime + ", lastupdatetime=" + lastupdatetime + ", createdby=" + createdby
				+ "]";
	}
    
}