package com.dacheng.mes.sfdcs.pojo;

public class AttributeDefStep {

	private int attrDefStepKey;
	
	private int attrNameKey;
	
	private int attrGroupKey;
	
	private int step;
	
	private boolean released;
	
	private String attributeName;
	
	private String  userPrompt;
	
	private String groupName;
	
	private int attrMaskType;
	
	private String attrMask;
	
	public int getAttrDefStepKey() {
		return attrDefStepKey;
	}

	public void setAttrDefStepKey(int attrDefStepKey) {
		this.attrDefStepKey = attrDefStepKey;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public boolean isReleased() {
		return released;
	}

	public void setReleased(boolean released) {
		this.released = released;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getUserPrompt() {
		return userPrompt;
	}

	public void setUserPrompt(String userPrompt) {
		this.userPrompt = userPrompt;
	}

	public String getAttrMask() {
		return attrMask;
	}

	public void setAttrMask(String attrMask) {
		this.attrMask = attrMask;
	}

	public int getAttrMaskType() {
		return attrMaskType;
	}

	public void setAttrMaskType(int attrMaskType) {
		this.attrMaskType = attrMaskType;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getAttrNameKey() {
		return attrNameKey;
	}

	public void setAttrNameKey(int attrNameKey) {
		this.attrNameKey = attrNameKey;
	}

	public int getAttrGroupKey() {
		return attrGroupKey;
	}

	public void setAttrGroupKey(int attrGroupKey) {
		this.attrGroupKey = attrGroupKey;
	}
}
