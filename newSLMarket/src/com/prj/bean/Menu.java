package com.prj.bean;

import java.util.List;



public class Menu {
	private AuFunction mainMenu;
	private List<AuFunction> subMenu;
	public AuFunction getMainMenu() {
		return mainMenu;
	}
	public void setMainMenu(AuFunction mainMenu) {
		this.mainMenu = mainMenu;
	}
	public List<AuFunction> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<AuFunction> subMenu) {
		this.subMenu = subMenu;
	}
	
}
