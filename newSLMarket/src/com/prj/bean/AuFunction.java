package com.prj.bean;

import java.util.Date;

public class AuFunction {
    private Long id;

    private String functioncode;

    private String functionname;

    private String funcurl;

    private Long parentid;

    private Date creationtime;

    private Long roleid;
    
    public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleId2) {
		this.roleid = roleId2;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunctioncode() {
        return functioncode;
    }

    public void setFunctioncode(String functioncode) {
        this.functioncode = functioncode == null ? null : functioncode.trim();
    }

    public String getFunctionname() {
        return functionname;
    }

    public void setFunctionname(String functionname) {
        this.functionname = functionname == null ? null : functionname.trim();
    }

    public String getFuncurl() {
        return funcurl;
    }

    public void setFuncurl(String funcurl) {
        this.funcurl = funcurl == null ? null : funcurl.trim();
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }
}