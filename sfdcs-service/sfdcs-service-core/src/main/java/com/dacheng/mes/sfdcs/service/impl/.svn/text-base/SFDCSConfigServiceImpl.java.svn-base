package com.dacheng.mes.sfdcs.service.impl;

import java.util.Map;

import com.dacheng.mes.sfdcs.dao.SFDCSConfigDao;
import com.dacheng.mes.sfdcs.service.SFDCSConfigService;

public class SFDCSConfigServiceImpl implements SFDCSConfigService {

	private SFDCSConfigDao sfdcsConfigDao = null;
	
	
	public SFDCSConfigDao getSfdcsConfigDao() {
		return sfdcsConfigDao;
	}

	public void setSfdcsConfigDao(SFDCSConfigDao sfdcsConfigDao) {
		this.sfdcsConfigDao = sfdcsConfigDao;
	}



	@Override
	public Map<String, Object> getShopFloorIdTotal(String sfdsId, String lineName) {
		return this.sfdcsConfigDao.getShopFloorIdTotal(sfdsId, lineName);
	}
	
	
	public Map<String, Object> queryShopFloorID(Integer startRow, Integer limitRow) {
		return this.sfdcsConfigDao.queryShopFloorID(startRow, limitRow);
	}

	public Map<String, Object> addShopFloorID(String sfdsId, String lineName, String inActive, String timeZone) {
		return this.sfdcsConfigDao.addShopFloorID(sfdsId, lineName, inActive, timeZone);
	}

	public Map<String, Object> updateShopFloorID(String sfdsId, String lineName, String inActive, String timeZone,
			Integer sfdcsKey) {
		return this.sfdcsConfigDao.updateShopFloorID(sfdsId, lineName, inActive, timeZone, sfdcsKey);
	}

	public Map<String, Object> deleteShopFloorID(Integer sfdsKey) {
		return this.sfdcsConfigDao.deleteShopFloorID(sfdsKey);
	}

	public Map<String, Object> searchShopFloorID(String sfdsId, String lineName, Integer startRow, Integer limitRow) {
		return this.sfdcsConfigDao.searchShopFloorID(sfdsId, lineName, startRow, limitRow);
	}
	
	
	public Map<String, Object> queryProcess() {
		return this.sfdcsConfigDao.queryProcess();
	}

	@Override
	public Map<String, Object> addProcess(String processName, Integer emvControlled) {
		return this.sfdcsConfigDao.addProcess(processName, emvControlled);
	}

	@Override
	public Map<String, Object> updateProcess(Integer processKey, String processName, Integer emvControlled) {
		return this.sfdcsConfigDao.updateProcess(processKey, processName, emvControlled);
	}

	@Override
	public Map<String, Object> deleteProcess(Integer processKey) {
		return this.sfdcsConfigDao.deleteProcess(processKey);
	}

	@Override
	public Map<String, Object> queryDepartment() {
		return this.sfdcsConfigDao.queryDepartment();
	}

	@Override
	public Map<String, Object> addDepartment(String department) {
		return this.sfdcsConfigDao.addDepartment(department);
	}

	@Override
	public Map<String, Object> updateDepartment(Integer deptKey, String department) {
		return this.sfdcsConfigDao.updateDepartment(deptKey, department);
	}

	@Override
	public Map<String, Object> deleteDepartment(Integer deptKey) {
		return this.sfdcsConfigDao.deleteDepartment(deptKey);
	}

	@Override
	public Map<String, Object> queryUnitStatus() {
		return this.sfdcsConfigDao.queryUnitStatus();
	}

	@Override
	public Map<String, Object> getLocationInfoTotal() {
		return this.sfdcsConfigDao.getLocationInfoTotal();
	}

	@Override
	public Map<String, Object> getLocationInfoTotal(Integer sfdsKey, String description, Integer unitStatusKey,
			Integer processKey,Integer deptKey) {
		return this.sfdcsConfigDao.getLocationInfoTotal(sfdsKey, description, unitStatusKey, processKey , deptKey);
	}
	

	public Map<String, Object> queryLocationInfo(Integer start , Integer limit) {
		
		return this.sfdcsConfigDao.queryLocationInfo( start , limit);
	}
	
	@Override
	public Map<String, Object> queryLocation() {
		return this.sfdcsConfigDao.queryLocation();
	}

	@Override
	public Map<String, Object> addLocation(Integer sfdsKey, String description, String stationType,
			Integer unitStatusKey, Integer processKey, Integer deptKey) {
		return this.sfdcsConfigDao.addLocation(sfdsKey, description, stationType, unitStatusKey, processKey, deptKey);
	}

	@Override
	public Map<String, Object> updateLocation(Integer sfdsKey, String description, String stationType,
			Integer unitStatusKey, Integer processKey, Integer deptKey, Integer locationKey) {
		return this.sfdcsConfigDao.updateLocation(sfdsKey, description, stationType, unitStatusKey, processKey, deptKey, locationKey);
	}
	public Map<String, Object> deleteLocation(Integer locationKey) {
		return this.sfdcsConfigDao.deleteLocation(locationKey);
	}

	@Override
	public Map<String, Object> searchLocationInfo(Integer sfdsKey, String description, Integer unitStatusKey,
			Integer processKey, Integer deptKey,Integer start, Integer limit) {
		return this.sfdcsConfigDao.searchLocationInfo(sfdsKey, description, unitStatusKey, processKey, deptKey, start ,  limit);
	}

	
	@Override
	public Map<String, Object> getDataCollectorTotal() {
		return this.sfdcsConfigDao.getDataCollectorTotal();
	}

	@Override
	public Map<String, Object> getDataCollectorTotal(Integer deviceNumber, Integer sfdsKey, Integer locationKey,
			Integer inactiveKey) {
		return this.sfdcsConfigDao.getDataCollectorTotal(deviceNumber, sfdsKey, locationKey, inactiveKey);
	}
	
	@Override
	public Map<String, Object> queryDataCollector(Integer start , Integer limit) {
		return this.sfdcsConfigDao.queryDataCollector(start,limit);
	}

	@Override
	public Map<String, Object> addScanner(Integer deviceNumber, Integer sfdsKey, Integer locationKey,
			Boolean inactive) {
		return  this.sfdcsConfigDao.addScanner(deviceNumber, sfdsKey, locationKey, inactive);
	}

	@Override
	public Map<String, Object> updateScanner(Integer deviceNumber, Integer sfdsKey, Integer locationKey,
			Boolean inactive, Integer scannerKey) {
		return this.sfdcsConfigDao.updateScanner(deviceNumber, sfdsKey, locationKey, inactive, scannerKey);
	}

	@Override
	public Map<String , Object> searchDataCollector(Integer deviceNumber , Integer sfdsKey , Integer locationKey , Integer inactiveKey,Integer start , Integer limit) {
		return this.sfdcsConfigDao.searchDataCollector(deviceNumber, sfdsKey, locationKey, inactiveKey,start,limit);
	}

	@Override
	public Map<String, Object> deleteScanner(Integer scannerKey) {
		return this.sfdcsConfigDao.deleteScanner(scannerKey);
	}

	@Override
	public Map<String, Object> queryDeviceNumber() {
		
		return this.sfdcsConfigDao.queryDeviceNumber();
	}

	
	@Override
	public Map<String, Object> getDefectCodeTotal() {
		return this.sfdcsConfigDao.getDefectCodeTotal();
	}

	@Override
	public Map<String, Object> getDefectCodeTotal(String defectCode, String description, Integer codeType,
			Integer enabledKey) {
		return this.sfdcsConfigDao.getDefectCodeTotal(defectCode, description, codeType, enabledKey);
	}
	
	
	public Map<String, Object> queryDefectCode(Integer start , Integer limit) {
		return this.sfdcsConfigDao.queryDefectCode(start,limit);
	}

	public Map<String, Object> addDefectCode(String defectCode, String description, Integer codeType, Boolean enabled) {
		
		return this.sfdcsConfigDao.addDefectCode(defectCode, description, codeType, enabled);
	}

	public Map<String, Object> updateDefectCode(String defectCode, String description, Integer codeType,
			Boolean enabled, Integer defcodeKey) {
		
		return this.sfdcsConfigDao.updateDefectCode(defectCode, description, codeType, enabled, defcodeKey);
	}

	public Map<String, Object> deleteDefectCode(Integer defcodeKey) {
			
			return this.sfdcsConfigDao.deleteDefectCode(defcodeKey);
	}
	
	public Map<String, Object> searchDefectCode(String defectCode, String description, Integer codeType,
			Integer enabledKey,Integer start , Integer limit) {
		return this.sfdcsConfigDao.searchDefectCode(defectCode, description, codeType, enabledKey,start,limit);
	}
	
	@Override
	public Map<String, Object> getPartNumberListTotal() {
		return this.sfdcsConfigDao.getPartNumberListTotal();
	}

	@Override
	public Map<String, Object> getPartNumberListTotal(String partNumber, Integer obsoleteKey, Integer partType,
			Integer configMasterKey) {
		return this.sfdcsConfigDao.getPartNumberListTotal(partNumber, obsoleteKey, partType, configMasterKey);
	}
	

	@Override
	public Map<String, Object> partNumbers() {
		return this.sfdcsConfigDao.partNumbers();
	}

	@Override
	public Map<String, Object> queryPartNumberList(Integer start , Integer limit){
		return this.sfdcsConfigDao.queryPartNumberList(start, limit);
	}

	@Override
	public Map<String, Object> searchPartNumberList(String partNumber, Integer obsoleteKey ,
			Integer partType , Integer  configMasterKey,Integer start , Integer limit) {
		return this.sfdcsConfigDao.searchPartNumberList(partNumber, obsoleteKey,
				partType, configMasterKey, start, limit);
	}

	@Override
	public Map<String, Object> addPartNumber(String partNumber, String description, Integer partType, Boolean obsolete,
			Boolean isErp, Boolean baseFlag, String country, Integer unitStartLocationKey, String partPending,
			String userDefined, Integer travelerType, String unitId, Integer quntity, String modelNumber,
			String partOpType, String familyId, String customerCode, Integer configMasterKey, String warrantyCode,
			String extendedPN, Double length, Double width, Double weight, Double volume, Double height) {
		
		return this.sfdcsConfigDao.addPartNumber(partNumber, description, partType, obsolete, isErp, baseFlag,
				country, unitStartLocationKey, partPending, userDefined, travelerType, unitId, quntity, modelNumber, partOpType, 
				familyId, customerCode, configMasterKey, warrantyCode, extendedPN, length, width, weight, volume, height);
	}

	@Override
	public Map<String, Object> updatePartNumber(String partNumber, String description, Integer partType,
			Boolean obsolete, Boolean isErp, Boolean baseFlag, String country, Integer unitStartLocationKey,
			String partPending, String userDefined, Integer travelerType, String unitId, Integer quntity,
			String modelNumber, String partOpType, String familyId, String customerCode, Integer configMasterKey,
			String warrantyCode, String extendedPN, Double length, Double width, Double weight, Double volume,
			Double height, Integer partKey) {
		return this.sfdcsConfigDao.updatePartNumber(partNumber, description, partType, obsolete, isErp, baseFlag, 
				country, unitStartLocationKey, partPending, userDefined, travelerType, unitId, quntity, modelNumber, partOpType, 
				familyId, customerCode, configMasterKey, warrantyCode, extendedPN, length, width, weight, volume, height, partKey);
	}

	@Override
	public Map<String, Object> deletePartNumber(Integer partKey) {
		return this.sfdcsConfigDao.deletePartNumber(partKey);
	}

	@Override
	public Map<String, Object> queryPartNumber(Integer partKey) {
		return this.sfdcsConfigDao.queryPartNumber(partKey);
	}

	@Override
	public Map<String, Object> queryPartFamily() {
		return this.sfdcsConfigDao.queryPartFamily();
	}

	@Override
	public Map<String, Object> queryPartRevision(Integer partKey) {
		return this.sfdcsConfigDao.queryPartRevision(partKey);
	}

	@Override
	public Map<String, Object> updatePartRevision(Integer revnum, String revisionList, Integer partKey) {
		return this.sfdcsConfigDao.updatePartRevision(revnum, revisionList, partKey);
	}

	
	@Override
	public Map<String, Object> getAssemblyTotal() {
		return this.sfdcsConfigDao.getAssemblyTotal();
	}

	@Override
	public Map<String, Object> getAssemblyTotal(Integer sfdsKey, Integer locationKey, Integer partKey) {
		return this.sfdcsConfigDao.getAssemblyTotal(sfdsKey, locationKey, partKey);
	}
	
	@Override
	public Map<String, Object> queryAssembly(Integer start,Integer limit) {
		return this.sfdcsConfigDao.queryAssembly(start,limit);
	}

	@Override
	public Map<String, Object> addAssembly(Integer sfdsKey, Integer locationKey, Integer partKey, String revision) {
		return this.sfdcsConfigDao.addAssembly(sfdsKey, locationKey, partKey, revision);
	}
/*
	@Override
	public Map<String, String> addAssembly2(Integer sfdsKey, Integer locationKey, Integer partKey, String revision) {
		return this.sfdcsConfigDao.addAssembly2(sfdsKey, locationKey, partKey, revision);
	}
	
	*/
	
	@Override
	public Map<String, Object> deleteAssembly(Integer assemblyKey) {
		return this.sfdcsConfigDao.deleteAssembly(assemblyKey);
	}
	
	@Override
	public Map<String, Object> searchAssembly(Integer sfdsKey, Integer locationKey, Integer partKey,Integer start,Integer limit) {
		return this.sfdcsConfigDao.searchAssembly(sfdsKey, locationKey, partKey,start,limit);
	}

	@Override
	public Map<String, Object> queryAssemblyStep(Integer assemblyKey) {
		return this.sfdcsConfigDao.queryAssemblyStep(assemblyKey);
	}

	@Override
	public Map<String, Object> addAssemblyStep(Integer assemblyKey, Integer componentType, Integer step,
			String compIdPrompt, String compIdMask, String refDesignator, String compPartMask) {
		return this.sfdcsConfigDao.addAssemblyStep(assemblyKey, componentType, step, compIdPrompt, compIdMask,
				refDesignator, compPartMask);
	}

	@Override
	public Map<String, Object> updateAssemblyStep(Integer componentType, Integer step,
			String compIdPrompt, String compIdMask, String refDesignator, String compPartMask,
			Integer assemblyStepKey) {
		return this.sfdcsConfigDao.updateAssemblyStep(componentType,
				step, compIdPrompt, compIdMask, refDesignator, compPartMask, assemblyStepKey);
	}

	@Override
	public Map<String, Object> deleteAssemblyStep(Integer assemblyStepKey) {
		return this.sfdcsConfigDao.deleteAssemblyStep(assemblyStepKey);
	}

/*	@Override
	public Map<String, Object> queryAllAssemblyStep() {
		return this.sfdcsConfigDao.queryAllAssemblyStep();
	}*/


	@Override
	public Map<String, Object> queryAttrByLocation(Integer start,Integer limit) {
		return this.sfdcsConfigDao.queryAttrByLocation(start,limit);
	}
	
	@Override
	public Map<String, Object> addAttrByLocation(Integer sfdsKey, Integer locationKey, Integer partKey,
			String revision) {
		return this.sfdcsConfigDao.addAttrByLocation(sfdsKey, locationKey, partKey, revision);
	}

	@Override
	public Map<String, Object> searchAttrByLocation(Integer sfdsKey, Integer locationKey, String partNumber,Integer start,Integer limit) {
		return this.sfdcsConfigDao.searchAttrByLocation(sfdsKey, locationKey, partNumber,start,limit);
	}

	@Override
	public Map<String, Object> deleteAttrByLocation(Integer attrDefKey) {
		return this.sfdcsConfigDao.deleteAttrByLocation(attrDefKey);
	}

	
	@Override
	public Map<String, Object> queryAttrStep(Integer attrDefKey) {
		return this.sfdcsConfigDao.queryAttrStep(attrDefKey);
	}

	@Override
	public Map<String, Object> addAttrStep(Integer attrDefKey, Integer step, Boolean released, Integer attrNameKey,
			Integer attrGroupKey, Integer attrMaskType, String attrMask) {
		return this.sfdcsConfigDao.addAttrStep(attrDefKey, step, released, attrNameKey, attrGroupKey, attrMaskType, attrMask);
	}

	@Override
	public Map<String, Object> updateAttrStep(Integer step, Boolean released, Integer attrNameKey,
			Integer attrGroupKey, Integer attrMaskType, String attrMask, Integer attrDefStepKey) {
		return this.sfdcsConfigDao.updateAttrStep( step, released, attrNameKey, attrGroupKey, attrMaskType, attrMask, attrDefStepKey);
	}

	@Override
	public Map<String, Object> deleteAttrStep(Integer attrDefStepKey) {
		return this.sfdcsConfigDao.deleteAttrStep(attrDefStepKey);
	}
	

	@Override
	public Map<String, Object> queryAttrGroupAttrName() {
		return this.sfdcsConfigDao.queryAttrGroupAttrName();
	}
	
	public Map<String, Object> addAttrGroupAttrName(String attributeName, String userPrompt, String groupName) {
		return this.sfdcsConfigDao.addAttrGroupAttrName(attributeName, userPrompt, groupName);
	}

	@Override
	public Map<String, Object> updateAttrGroupAttrName(String attributeName, String userPrompt, String groupName,
			Integer attrGroupAttrNameKey) {
		return this.sfdcsConfigDao.updateAttrGroupAttrName(attributeName, userPrompt, groupName, attrGroupAttrNameKey);
	}
	/*@Override
	public Map<String, Object> updateAttrGroupAttrName(String attributeName, String userPrompt, String groupName,
			Integer attrNameKey, Integer attrGroupKey) {
		return this.sfdcsConfigDao.updateAttrGroupAttrName(attributeName, userPrompt, groupName, attrNameKey, attrGroupKey);
	}*/

	@Override
	public Map<String, Object> deleteAttrGroupAttrName(Integer attrGroupAttrNameKey) {
		return this.sfdcsConfigDao.deleteAttrGroupAttrName(attrGroupAttrNameKey);
	}

	@Override
	public Map<String, Object> queryAttrByProcess(Integer start,Integer limit) {
		return this.sfdcsConfigDao.queryAttrByProcess(start,limit);
	}

	@Override
	public Map<String, Object> addAttrByProcess(Integer sfdsKey, Integer processKey, Integer partKey, String revision) {
		return this.sfdcsConfigDao.addAttrByProcess(sfdsKey, processKey, partKey, revision);
	}

	@Override
	public Map<String, Object> searchAttrByProcess(Integer sfdsKey, String processName, String partNumber,Integer start,Integer limit) {
		return this.sfdcsConfigDao.searchAttrByProcess(sfdsKey, processName, partNumber,start,limit);
	}

	@Override
	public Map<String, Object> searchLocationWithSfdsKey(Integer sfdsKey) {
		return this.sfdcsConfigDao.searchLocationWithSfdsKey(sfdsKey);
	}

	@Override
	public Map<String, Object> getAttrByLocationTotal() {
		return this.sfdcsConfigDao.getAttrByLocationTotal();
	}

	@Override
	public Map<String, Object> getAttrByLocationTotal(Integer sfdsKey, Integer locationKey, String partNumber) {
		return this.sfdcsConfigDao.getAttrByLocationTotal(sfdsKey, locationKey, partNumber);
	}

	@Override
	public Map<String, Object> getAttrByProcessTotal() {
		return this.sfdcsConfigDao.getAttrByProcessTotal();
	}

	@Override
	public Map<String, Object> getAttrByProcessTotal(Integer sfdsKey, String processName, String partNumber) {
		return this.sfdcsConfigDao.getAttrByProcessTotal(sfdsKey, processName, partNumber);
	}

	@Override
	public Map<String, Object> addSetUpData(Integer sfdsKey, String item) {
		return this.sfdcsConfigDao.addSetUpData(sfdsKey, item);
	}

	@Override
	public Map<String, Object> querySetUpData(Integer sfdsKey) {
		return this.sfdcsConfigDao.querySetUpData(sfdsKey);
	}

	@Override
	public Map<String, Object> queryAllShopFloorId() {
		return this.sfdcsConfigDao.queryAllShopFloorId();
	}

	@Override
	public Map<String, Object> queryAllLocation() {
		return this.sfdcsConfigDao.queryAllLocation();
	}

	@Override
	public Map<String, Object> searchLocation(String description) {
		return this.sfdcsConfigDao.searchLocation(description);
	}
}
