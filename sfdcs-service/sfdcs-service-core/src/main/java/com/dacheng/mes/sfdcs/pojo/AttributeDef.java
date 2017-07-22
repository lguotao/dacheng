package com.dacheng.mes.sfdcs.pojo;

public class AttributeDef {

	private int attrDefKey;
	
	private String partNumber;
	
	private String processName;
	
	private String sfdsId;
	
	private String description;
	
	private int steps;
	
	private String revision;

	public int getAttrDefKey() {
		return attrDefKey;
	}

	public void setAttrDefKey(int attrDefKey) {
		this.attrDefKey = attrDefKey;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getSfdsId() {
		return sfdsId;
	}

	public void setSfdsId(String sfdsId) {
		this.sfdsId = sfdsId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}
	
	
}
