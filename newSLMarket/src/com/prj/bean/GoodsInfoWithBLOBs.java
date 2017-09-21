package com.prj.bean;

public class GoodsInfoWithBLOBs extends GoodsInfo {
	
    private String goodsformat;

    private String note;

    
	

	public String getGoodsformat() {
        return goodsformat;
    }

    public void setGoodsformat(String goodsformat) {
        this.goodsformat = goodsformat == null ? null : goodsformat.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

	@Override
	public String toString() {
		return "GoodsInfoWithBLOBs [goodsformat=" + goodsformat + ", note=" + note + "]";
	
	}
    
    
}