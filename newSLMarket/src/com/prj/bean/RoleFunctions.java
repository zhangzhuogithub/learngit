package com.prj.bean;

import java.util.List;

public class RoleFunctions {
	private AuFunction mainFunction;
	private List<AuFunction> subFunction;
	public AuFunction getMainFunction() {
		return mainFunction;
	}
	public void setMainFunction(AuFunction mainFunction) {
		this.mainFunction = mainFunction;
	}
	public List<AuFunction> getSubFunction() {
		return subFunction;
	}
	public void setSubFunction(List<AuFunction> subFunction) {
		this.subFunction = subFunction;
	}
	
}
