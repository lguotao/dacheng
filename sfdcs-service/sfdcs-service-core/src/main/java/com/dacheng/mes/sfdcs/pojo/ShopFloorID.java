package com.dacheng.mes.sfdcs.pojo;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize
public class ShopFloorID {

	private int sfdsKey = 0;

	private String sfdsId = "";
	
	private String lineName = "";
	
	private String shiftTime = "";
	
	private int inactDays = 0;
	
	private String timeZone = "";

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getShiftTime() {
		return shiftTime;
	}

	public void setShiftTime(String shiftTime) {
		this.shiftTime = shiftTime;
	}

	public int getInactDays() {
		return inactDays;
	}

	public void setInactDays(int inactDays) {
		this.inactDays = inactDays;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public int getSfdsKey() {
		return sfdsKey;
	}

	public void setSfdsKey(int sfdsKey) {
		this.sfdsKey = sfdsKey;
	}

	public String getSfdsId() {
		return sfdsId;
	}

	public void setSfdsId(String sfdsId) {
		this.sfdsId = sfdsId;
	}
	
	

}
