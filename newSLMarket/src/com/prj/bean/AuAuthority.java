package com.prj.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AuAuthority {
    private Long id;

    private Long roleid;

    private Long functionid;

    private Long usertypeid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationtime;

    private String createdby;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public Long getFunctionid() {
        return functionid;
    }

    public void setFunctionid(Long functionid) {
        this.functionid = functionid;
    }

    public Long getUsertypeid() {
        return usertypeid;
    }

    public void setUsertypeid(Long usertypeid) {
        this.usertypeid = usertypeid;
    }

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby == null ? null : createdby.trim();
    }

	@Override
	public String toString() {
		return "AuAuthority [id=" + id + ", roleid=" + roleid + ", functionid=" + functionid + ", usertypeid="
				+ usertypeid + ", creationtime=" + creationtime + ", createdby=" + createdby + "]";
	}
    
}