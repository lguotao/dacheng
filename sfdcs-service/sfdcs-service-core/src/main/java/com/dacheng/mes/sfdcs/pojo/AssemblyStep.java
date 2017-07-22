package com.dacheng.mes.sfdcs.pojo;

public class AssemblyStep {

	private int assemblyStepKey;
	
	private int assemblyKey;
	
	private int step;
	
	private String refDesignator;
	
	private String compIdMask;
	
	private String compIdPrompt;
	
	private int compLocKey;
	
	private String compPartMask;
	
	private int componentType;

	public int getAssemblyStepKey() {
		return assemblyStepKey;
	}

	public void setAssemblyStepKey(int assemblyStepKey) {
		this.assemblyStepKey = assemblyStepKey;
	}

	public int getAssemblyKey() {
		return assemblyKey;
	}

	public void setAssemblyKey(int assemblyKey) {
		this.assemblyKey = assemblyKey;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getRefDesignator() {
		return refDesignator;
	}

	public void setRefDesignator(String refDesignator) {
		this.refDesignator = refDesignator;
	}

	public String getCompIdMask() {
		return compIdMask;
	}

	public void setCompIdMask(String compIdMask) {
		this.compIdMask = compIdMask;
	}

	public String getCompIdPrompt() {
		return compIdPrompt;
	}

	public void setCompIdPrompt(String compIdPrompt) {
		this.compIdPrompt = compIdPrompt;
	}

	public int getCompLocKey() {
		return compLocKey;
	}

	public void setCompLocKey(int compLocKey) {
		this.compLocKey = compLocKey;
	}

	public String getCompPartMask() {
		return compPartMask;
	}

	public void setCompPartMask(String compPartMask) {
		this.compPartMask = compPartMask;
	}

	public int getComponentType() {
		return componentType;
	}

	public void setComponentType(int componentType) {
		this.componentType = componentType;
	}
}
