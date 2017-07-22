package com.dacheng.mes.sfdcs.restful.remoteservice;

import java.util.Map;

import com.sun.imageio.plugins.common.I18N;




public interface SFDCSConfigRestServicce {

	public Map<String, Object> getShopFloorIdTotal(String sfdsId, String lineName);
	
	public Map<String, Object>  queryShopFloorID(Integer startRow , Integer limitRow);
	
	public Map<String, Object> addShopFloorID(String sfdsId, String lineName,String inActive, String timeZone);
	
	public Map<String, Object> updateShopFloorID(String sfdsId, String lineName,String inActive, String timeZone, Integer sfdcsKey);
	
	public Map<String, Object> deleteShopFloorID(Integer sfdsKey);
	
	public Map<String, Object> searchShopFloorID(String sfdsId, String lineName,Integer startRow , Integer limitRow);
	
	public Map<String, Object> queryProcess();
	
	public Map<String, Object> addProcess(String processName, Integer emvControlled);
	
	public Map<String, Object> updateProcess(Integer processKey , String processName , Integer emvControlled);
	
	public Map<String, Object> deleteProcess(Integer processKey);
	
	public Map<String, Object> queryDepartment();
	
	public Map<String, Object> addDepartment(String department);
	
	public Map<String, Object> updateDepartment(Integer deptKey,String department);
	
	public Map<String, Object> deleteDepartment(Integer deptKey);
	
	public Map<String, Object> queryUnitStatus();
	
	public Map<String, Object> getLocationInfoTotal();
	
	public Map<String, Object> getLocationInfoTotal(Integer sfdsKey, String description, Integer unitStatusKey,Integer processKey,Integer deptKey);
	
	public Map<String, Object> queryLocationInfo(Integer start , Integer limit);
	
	public Map<String, Object> queryLocation();
	
	public Map<String, Object> addLocation( Integer sfdsKey , String description , String stationType
			,Integer unitStatusKey,Integer processKey , Integer deptKey);
	
	public Map<String, Object> updateLocation(Integer sfdsKey , String description , String stationType
			,Integer unitStatusKey,Integer processKey , Integer deptKey , Integer locationKey);
	
	public Map<String, Object> deleteLocation(Integer locationKey);
	
	public Map<String, Object> searchLocationInfo(Integer sfdsKey, String description, Integer unitStatusKey,Integer processKey,
			Integer deptKey,Integer start , Integer limit);	
	
	public Map<String, Object> getDataCollectorTotal();
	
	public Map<String, Object> getDataCollectorTotal(Integer deviceNumber , Integer sfdsKey , Integer locationKey , Integer inactiveKey);
	
	public Map<String, Object> queryDataCollector(Integer start , Integer limit);
	
	public Map<String, Object> addScanner(Integer deviceNumber, Integer sfdsKey , Integer locationKey , Boolean inactive);
	
	public Map<String, Object> updateScanner(Integer deviceNumber, Integer sfdsKey , Integer locationKey , Boolean inactive , Integer scannerKey);
	
	public Map<String, Object> deleteScanner(Integer scannerKey);
	
	public Map<String , Object> searchDataCollector(Integer deviceNumber , Integer sfdsKey , Integer locationKey , Integer inactiveKey,Integer start , Integer limit);
	
	public Map<String, Object> queryDeviceNumber();
	
	public Map<String, Object> getDefectCodeTotal();
	
	public Map<String, Object> getDefectCodeTotal(String defectCode , String description , Integer codeType , Integer enabledKey);
	
	public Map<String, Object> queryDefectCode(Integer start , Integer limit);
	
	public Map<String, Object> addDefectCode(String defectCode , String description , Integer codeType , Boolean enabled);
	
	public Map<String, Object> updateDefectCode(String defectCode , String description , Integer codeType , Boolean enabled, Integer defcodeKey);
	
	public Map<String, Object> deleteDefectCode(Integer defcodeKey);
	
	public Map<String, Object> searchDefectCode(String defectCode , String description , Integer codeType , Integer enabledKey,Integer start , Integer limit );
	
	public Map<String, Object> getPartNumberListTotal();
	
	public Map<String, Object > partNumbers();
	
	public Map<String, Object> getPartNumberListTotal(String partNumber, Integer obsoleteKey , Integer partType , Integer  configMasterKey);
	
	public Map<String, Object> queryPartNumberList(Integer startRow , Integer limit);
	
	public Map<String, Object> searchPartNumberList(String partNumber, Integer obsoleteKey , Integer partType , Integer  configMasterKey,Integer start , Integer limit);
	
	public Map<String, Object> addPartNumber(String partNumber , String description ,Integer partType , Boolean obsolete,
			Boolean isErp , Boolean baseFlag , String country , Integer unitStartLocationKey ,  String partPending ,
			String userDefined , Integer travelerType , String unitId , Integer quantity , String modelNumber , String partOpType,
			String familyId , String customerCode , Integer configMasterKey , String warrantyCode , String extendedPN,
			Double length , Double width , Double weight , Double volume , Double height);
	
	public Map<String, Object> updatePartNumber(String partNumber , String description ,Integer partType , Boolean obsolete,
			Boolean isErp , Boolean baseFlag , String country , Integer unitStartLocationKey ,  String partPending ,
			String userDefined , Integer travelerType , String unitId , Integer quantity , String modelNumber , String partOpType,
			String familyId , String customerCode , Integer configMasterKey , String warrantyCode , String extendedPN,
			Double length , Double width , Double weight , Double volume , Double height , Integer partKey);  
	
	public Map<String, Object> deletePartNumber(Integer partKey);
	
	public Map<String, Object> queryPartNumber(Integer partKey);
	
	public Map<String, Object> queryPartFamily();
	
	public Map<String, Object> queryPartRevision(Integer partKey);
	
	public Map<String, Object> updatePartRevision(Integer revnum , String revisionList , Integer partKey);
	
	public Map<String,Object> getAssemblyTotal();
	
	public Map<String,Object> getAssemblyTotal(Integer sfdsKey , Integer locationKey , Integer partKey);
	
	public Map<String, Object> queryAssembly(Integer start,Integer limit);
	
	public Map<String, Object> addAssembly(Integer sfdsKey , Integer locationKey , Integer partKey , String revision);
	
//	public Map<String, String> addAssembly2(Integer sfdsKey , Integer locationKey , Integer partKey , String revision);
	
	public Map<String, Object> deleteAssembly(Integer assemblyKey);
	
	public Map<String, Object> searchAssembly(Integer sfdsKey , Integer locationKey , Integer partKey,Integer start,Integer limit);
	
	public Map<String, Object> queryAssemblyStep(Integer assemblyKey);
	
	public Map<String, Object> addAssemblyStep(Integer assemblyKey , Integer componentType , Integer step , 
			String compIdPrompt , String compIdMask ,String refDesignator ,String compPartMask );
	
	public Map<String, Object> updateAssemblyStep( Integer componentType , Integer step , 
			String compIdPrompt , String compIdMask ,String refDesignator ,String compPartMask  , Integer assemblyStepKey);
	
	public Map<String, Object> deleteAssemblyStep(Integer assemblyStepKey);
	
	public Map<String, Object> getAttrByLocationTotal();
	
	public Map<String, Object> getAttrByLocationTotal(Integer sfdsKey , Integer locationKey ,String partNumber);
	
	public Map<String, Object> queryAttrByLocation(Integer start,Integer limit);
	
	public Map<String, Object> addAttrByLocation(Integer sfdsKey , Integer locationKey , Integer partKey , String revision);
	
   public Map<String, Object> searchAttrByLocation(Integer sfdsKey , Integer locationKey ,String partNumber,Integer start,Integer limit);
	
	public Map<String, Object> deleteAttrByLocation(Integer attrDefKey);
	
public Map<String, Object> getAttrByProcessTotal();
	
	public Map<String, Object> getAttrByProcessTotal(Integer sfdsKey, String processName, String partNumber);
	
	public Map<String, Object> queryAttrByProcess(Integer start,Integer limit);
	
	public Map<String, Object> addAttrByProcess(Integer sfdsKey , Integer  processKey, Integer partKey, String revision);
	
	public Map<String, Object> searchAttrByProcess(Integer sfdsKey, String processName, String partNumber,Integer start,Integer limit);
	
	public Map<String, Object> queryAttrStep(Integer attrDefKey);
	
	public Map<String, Object> addAttrStep(Integer attrDefKey, Integer step,Boolean released,  Integer attrNameKey,
			Integer attrGroupKey, Integer attrMaskType, String attrMask);
	
	public Map<String, Object> updateAttrStep(Integer step,Boolean released,  Integer attrNameKey,
			Integer attrGroupKey, Integer attrMaskType, String attrMask, Integer attrDefStepKey);
	
	public Map<String, Object> deleteAttrStep(Integer attrDefStepKey);
	
	//  att-group -- att --name
	public Map<String, Object> queryAttrGroupAttrName();
	
	public Map<String, Object> addAttrGroupAttrName(String attributeName , String userPrompt ,String groupName);

//	public Map<String, Object> updateAttrGroupAttrName(String attributeName , String userPrompt ,String groupName,Integer attrNameKey ,Integer attrGroupKey);

	public Map<String, Object> updateAttrGroupAttrName(String attributeName , String userPrompt ,String groupName,Integer attrGroupAttrNameKey);
	
	public Map<String, Object> deleteAttrGroupAttrName(Integer attrGroupAttrNameKey);

	public Map<String, Object> searchLocationWithSfdsKey(Integer sfdsKey);

	public Map<String, Object> querySetUpData(Integer sfdsKey);
	
	public Map<String, Object> addSetUpData(Integer sfdsKey, String item);

	public Map<String, Object> queryAllShopFloorId();
	
	public Map<String, Object> queryAllLocation();
	
	public Map<String, Object> searchLocation(String description);
	
}
