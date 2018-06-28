package com.zplay.android.sdk.online.adapter.huawei;

import com.zplay.android.sdk.online.bean.ZplayBaseUserInfo;


public class ZplayUserInfo implements ZplayBaseUserInfo {
	
	// 玩家角色ID 玩家角色名  玩家角色等级  游戏区服ID 游戏区服名称  公会名称
	private String  roleId, roleName, roleLevel, zoneId, zoneName, sociatyName;

	public ZplayUserInfo(String roleId, String roleName, String roleLevel,
			String zoneId, String zoneName, String sociatyName) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleLevel = roleLevel;
		this.zoneId = zoneId;
		this.zoneName = zoneName;
		this.sociatyName = sociatyName;
		
	}
    public String getSociatyName() {
    	return sociatyName;
    }
    
    public void setSociatyName(String sociatyName){
    	 this.sociatyName = sociatyName;
    }
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	
	

}
