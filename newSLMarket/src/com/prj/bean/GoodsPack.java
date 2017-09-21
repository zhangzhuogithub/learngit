package com.prj.bean;

import java.util.Date;

public class GoodsPack extends Base{
    private Long id;

    private String goodspackname;

    private String goodspackcode;

    private Integer typeid;

    private String typename;

    private Double totalprice;

    private Integer state;

    private Integer num;

    private String createdby;

    private Date createtime;

    private Date lastupdatetime;

    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodspackname() {
        return goodspackname;
    }

    public void setGoodspackname(String goodspackname) {
        this.goodspackname = goodspackname == null ? null : goodspackname.trim();
    }

    public String getGoodspackcode() {
        return goodspackcode;
    }

    public void setGoodspackcode(String goodspackcode) {
        this.goodspackcode = goodspackcode == null ? null : goodspackcode.trim();
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
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

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby == null ? null : createdby.trim();
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

	@Override
	public String toString() {
		return "GoodsPack [id=" + id + ", goodspackname=" + goodspackname + ", goodspackcode=" + goodspackcode
				+ ", typeid=" + typeid + ", typename=" + typename + ", totalprice=" + totalprice + ", state=" + state
				+ ", num=" + num + ", createdby=" + createdby + ", createtime=" + createtime + ", lastupdatetime="
				+ lastupdatetime + ", note=" + note + "]";
	}
    
}