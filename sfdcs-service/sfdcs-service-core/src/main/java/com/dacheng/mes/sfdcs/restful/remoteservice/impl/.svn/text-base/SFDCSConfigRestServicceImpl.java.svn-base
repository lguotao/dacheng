package com.dacheng.mes.sfdcs.restful.remoteservice.impl;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailMessage;
import org.springframework.stereotype.Component;

import com.dacheng.mes.sfdcs.pojo.ShopFloorID;
import com.dacheng.mes.sfdcs.restful.remoteservice.SFDCSConfigRestServicce;
import com.dacheng.mes.sfdcs.service.SFDCSConfigService;



@Component
@Scope("prototype")
@Path("/SFDCSConfigRestService")
public class SFDCSConfigRestServicceImpl implements SFDCSConfigRestServicce {

	@Autowired
	private SFDCSConfigService sfdcsConfigService;
	

	@GET
	@Path("/getShopFloorIdTotal/{sfdsId}/{lineName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getShopFloorIdTotal(@PathParam("sfdsId")String sfdsId, 
			@PathParam("lineName")String lineName) {
		return this.sfdcsConfigService.getShopFloorIdTotal(sfdsId, lineName);
	}

	@GET
	@Path("/queryShopFloorID/{startRow}/{limitRow}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object>  queryShopFloorID(@PathParam("startRow") Integer startRow, 
			@PathParam("limitRow") Integer limitRow){
		Map<String, Object> map = sfdcsConfigService.queryShopFloorID(startRow, limitRow);
		return map;
	}	
	
	@GET
	@Path("/addShopFloorID/{sfdsId}/{lineName}/{inActive}/{timeZone}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> addShopFloorID(@PathParam("sfdsId") String sfdsId
			, @PathParam("lineName") String lineName, @PathParam("inActive") String inActive
			, @PathParam("timeZone") String timeZone) {
		Map<String, Object> map = sfdcsConfigService.addShopFloorID(sfdsId, lineName, inActive, timeZone);
		return map;
	}



	@GET
	@Path("/updateShopFloorID/{sfdsId}/{lineName}/{inActive}/{timeZone}/{sfdsKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> updateShopFloorID(@PathParam("sfdsId") String sfdsId
			, @PathParam("lineName") String lineName, @PathParam("inActive") String inActive
			, @PathParam("timeZone") String timeZone,@PathParam("sfdsKey") Integer sfdsKey) {
		Map<String, Object> map = sfdcsConfigService.updateShopFloorID(sfdsId, lineName, inActive, timeZone, sfdsKey);
		return map;
	}

	@GET
	@Path("/deleteShopFloorID/{sfdsKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> deleteShopFloorID(@PathParam("sfdsKey") Integer sfdsKey) {
		ShopFloorID shopFloorID = new ShopFloorID();
		shopFloorID.setSfdsKey(sfdsKey);
		Map<String, Object> map= sfdcsConfigService.deleteShopFloorID(sfdsKey);
		return map;
	}
	
	@GET
	@Path("/searchShopFloorID/{sfdsId}/{lineName}/{startRow}/{limitRow}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> searchShopFloorID(@PathParam("sfdsId") String sfdsId
			, @PathParam("lineName") String lineName,@PathParam("startRow") Integer startRow
			,@PathParam("limitRow") Integer limitRow) {
		Map<String, Object> map = sfdcsConfigService.searchShopFloorID(sfdsId, lineName, startRow, limitRow);
		return map;	
	}
	
	// Process


	@GET
	@Path("/queryProcess")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object>  queryProcess(){
		Map<String, Object> map = sfdcsConfigService.queryProcess();
		return map;
	}	

	@GET
	@Path("/addProcess/{processName}/{emvControlled}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> addProcess(@PathParam("processName") String processName, @PathParam("emvControlled") Integer emvControlled) {
		Map<String, Object> map = sfdcsConfigService.addProcess(processName, emvControlled);
		return map;
	}

	@GET
	@Path("/updateProcess/{processName}/{emvControlled}/{processKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> updateProcess(@PathParam("processKey") Integer processKey, @PathParam("processName") String processName, 
			@PathParam("emvControlled") Integer emvControlled) {
		Map<String, Object> map = sfdcsConfigService.updateProcess(processKey, processName, emvControlled);
		return map;
	}

	@GET
	@Path("/deleteProcess/{processKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> deleteProcess(@PathParam("processKey") Integer processKey) {
		Map<String, Object> map = sfdcsConfigService.deleteProcess(processKey);
		return map;
	}

	@GET
	@Path("/queryDepartment")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryDepartment() {
		Map<String, Object> map = sfdcsConfigService.queryDepartment();
		return map;
	}

	@GET
	@Path("/addDepartment/{department}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> addDepartment(@PathParam("department") String department) {
		Map<String, Object> map = sfdcsConfigService.addDepartment(department);
		return map;
	}

	@GET
	@Path("/updateDepartment/{department}/{deptKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> updateDepartment(@PathParam("deptKey")Integer deptKey, 
			@PathParam("department")String department) {
		Map<String, Object> map = sfdcsConfigService.updateDepartment(deptKey, department);
		return map;
	}

	@GET
	@Path("/deleteDepartment/{deptKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> deleteDepartment(@PathParam("deptKey")Integer deptKey) {
		Map<String, Object> map = sfdcsConfigService.deleteDepartment(deptKey);
		return map;
	}

	@GET
	@Path("/queryUnitStatus")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryUnitStatus() {
		Map<String, Object> map = this.sfdcsConfigService.queryUnitStatus();
		return map;
	}

	
	@GET
	@Path("/getLocationInfoTotal")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getLocationInfoTotal() {
		Map<String, Object> map = this.sfdcsConfigService.getLocationInfoTotal();
		return map;
	}

	@GET
	@Path("/getLocationInfoTotal/{sfdsKey}/{description}/{unitStatusKey}/{processKey}/{deptKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getLocationInfoTotal(@PathParam("sfdsKey")Integer sfdsKey, 
			@PathParam("description")String description, @PathParam("unitStatusKey")Integer unitStatusKey,
			@PathParam("processKey")Integer processKey,@PathParam("deptKey")Integer deptKey) {
		Map<String, Object> map = this.sfdcsConfigService.getLocationInfoTotal(sfdsKey, description, unitStatusKey, processKey,deptKey);
		return map;
	}
	
	@GET
	@Path("/queryLocationInfo/{start}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryLocationInfo(@PathParam("start")Integer start ,
			@PathParam("limit")Integer limit) {
		Map<String, Object> map = this.sfdcsConfigService.queryLocationInfo(start , limit);
		return map;
	}
	
	
	@GET
	@Path("/queryLocation")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryLocation() {
		Map<String, Object> map = this.sfdcsConfigService.queryLocation();
		return map;
	}
	
	
	@GET
	@Path("/addLocation/{sfdsKey}/{description}/{stationType}/{unitStatusKey}/{processKey}/{deptKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> addLocation(@PathParam("sfdsKey")Integer sfdsKey, 
		@PathParam("description")String description,@PathParam("stationType")String stationType,
		@PathParam("unitStatusKey")Integer unitStatusKey, @PathParam("processKey")Integer processKey,
		@PathParam("deptKey") Integer deptKey) {
		Map<String, Object> map =this.sfdcsConfigService.addLocation(sfdsKey, description, stationType, unitStatusKey, processKey, deptKey);
		return map;
	}


	@GET
	@Path("/updateLocation/{sfdsKey}/{description}/{stationType}/{unitStatusKey}/{processKey}/{deptKey}/{locationKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> updateLocation(@PathParam("sfdsKey")Integer sfdsKey, 
			 @PathParam("description")String description,@PathParam("stationType")String stationType, 
			 @PathParam("unitStatusKey")Integer unitStatusKey, @PathParam("processKey")Integer processKey,
			 @PathParam("deptKey")Integer deptKey, @PathParam("locationKey")Integer locationKey) {
		Map<String, Object> map = this.sfdcsConfigService.updateLocation(sfdsKey, description, stationType, unitStatusKey, processKey, deptKey, locationKey);
		return map;
	}



	@GET
	@Path("/deleteLocation/{locationKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> deleteLocation(@PathParam("locationKey")Integer locationKey) {
		Map<String, Object> map = this.sfdcsConfigService.deleteLocation(locationKey);
		return map;
	}

	@GET
	@Path("/searchLocationInfo/{sfdsKey}/{description}/{unitStatusKey}/{processKey}/{deptKey}/{start}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> searchLocationInfo(@PathParam("sfdsKey")Integer sfdsKey, 
			@PathParam("description")String description, @PathParam("unitStatusKey")Integer unitStatusKey,
			@PathParam("processKey")Integer processKey, @PathParam("deptKey")Integer deptKey,
			@PathParam("start")Integer start , @PathParam("limit")Integer limit) {
		Map<String, Object> map = this.sfdcsConfigService.searchLocationInfo(sfdsKey, description, 
				unitStatusKey, processKey, deptKey,start,limit);
		return map;
	}

	
	@GET
	@Path("/getDataCollectorTotal")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getDataCollectorTotal() {
		Map<String, Object> map = this.sfdcsConfigService.getDataCollectorTotal();
		return map;
	}

	@GET
	@Path("/getDataCollectorTotal/{deviceNumber}/{sfdsKey}/{locationKey}/{inactiveKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getDataCollectorTotal(@PathParam("deviceNumber")Integer deviceNumber, 
			@PathParam("sfdsKey")Integer sfdsKey, @PathParam("locationKey")Integer locationKey,
			@PathParam("inactiveKey")Integer inactiveKey) {
		Map<String, Object> map = this.sfdcsConfigService.getDataCollectorTotal(deviceNumber, sfdsKey, locationKey, inactiveKey);
		return map;
	}
	
	@GET
	@Path("/queryDataCollector/{start}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryDataCollector(@PathParam("start")Integer start , 
			@PathParam("limit")Integer limit) {
		Map<String, Object> map = this.sfdcsConfigService.queryDataCollector(start,limit);
		return map;
	}

	@GET
	@Path("/addScanner/{deviceNumber}/{sfdsKey}/{locationKey}/{inactive}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> addScanner(@PathParam("deviceNumber")Integer deviceNumber,
			@PathParam("sfdsKey")Integer sfdsKey, @PathParam("locationKey")Integer locationKey,
			@PathParam("inactive")Boolean inactive) {
		Map<String, Object> map= this.sfdcsConfigService.addScanner(deviceNumber, sfdsKey, locationKey, inactive);
		return map;
	}

	@GET
	@Path("/updateScanner/{deviceNumber}/{sfdsKey}/{locationKey}/{inactive}/{scannerKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> updateScanner(@PathParam("deviceNumber")Integer deviceNumber,
			@PathParam("sfdsKey")Integer sfdsKey, @PathParam("locationKey")Integer locationKey,
			@PathParam("inactive")Boolean inactive, @PathParam("scannerKey")Integer scannerKey) {
		Map<String, Object> map = this.sfdcsConfigService.updateScanner(deviceNumber, sfdsKey, locationKey, inactive, scannerKey);
		return map;
	}

	@GET
	@Path("/deleteScanner/{scannerKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> deleteScanner(@PathParam("scannerKey")Integer scannerKey) {
		Map<String, Object> map = this.sfdcsConfigService.deleteScanner(scannerKey);
		return map;
	}
	
	@GET
	@Path("/searchDataCollector/{deviceNumber}/{sfdsKey}/{locationKey}/{inactiveKey}/{start}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> searchDataCollector(@PathParam("deviceNumber")Integer deviceNumber, 
			@PathParam("sfdsKey")Integer sfdsKey, @PathParam("locationKey")Integer locationKey,
			@PathParam("inactiveKey")Integer inactiveKey,@PathParam("start")Integer start , 
			@PathParam("limit")Integer limit) {
		Map<String, Object> map = this.sfdcsConfigService.searchDataCollector(deviceNumber, sfdsKey, locationKey, inactiveKey,start,limit);
		return map;
	}

	@GET
	@Path("/queryDeviceNumber")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryDeviceNumber() {
		Map<String, Object> map = this.sfdcsConfigService.queryDeviceNumber();
		return map;
	}

	
	@GET
	@Path("/getDefectCodeTotal")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getDefectCodeTotal() {
		Map<String, Object> map = this.sfdcsConfigService.getDefectCodeTotal();
		return map;
	}

	@GET
	@Path("/getDefectCodeTotal/{defectCode}/{description}/{codeType}/{enabledKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getDefectCodeTotal(@PathParam("defectCode")String defectCode, 
			@PathParam("description")String description, @PathParam("codeType")Integer codeType,
			@PathParam("enabledKey")Integer enabledKey) {
		Map<String, Object> map = this.sfdcsConfigService.getDefectCodeTotal(defectCode, description, codeType, enabledKey);
		return map;
	}
	
	@GET
	@Path("/queryDefectCode/{start}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryDefectCode(@PathParam("start")Integer start , 
			@PathParam("limit")Integer limit) {
		Map<String, Object> map = this.sfdcsConfigService.queryDefectCode(start,limit);
		return map;
	}

	@GET
	@Path("/addDefectCode/{defectCode}/{description}/{codeType}/{enabled}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> addDefectCode(@PathParam("defectCode")String defectCode, 
			@PathParam("description")String description, @PathParam("codeType")Integer codeType, 
			@PathParam("enabled")Boolean enabled) {
		Map<String, Object> map = this.sfdcsConfigService.addDefectCode(defectCode, description, codeType, enabled);
		return map;
	}

	@GET
	@Path("/updateDefectCode/{defectCode}/{description}/{codeType}/{enabled}/{defcodeKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> updateDefectCode(@PathParam("defectCode")String defectCode,
			@PathParam("description")String description, @PathParam("codeType")Integer codeType,
			@PathParam("enabled")Boolean enabled, @PathParam("defcodeKey")Integer defcodeKey) {
		Map<String, Object> map = this.sfdcsConfigService.updateDefectCode(defectCode, description, codeType, enabled, defcodeKey);
		return map;
	}

	
	
	@GET
	@Path("/deleteDefectCode/{defcodeKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> deleteDefectCode(@PathParam("defcodeKey")Integer defcodeKey) {
		Map<String, Object> map = this.sfdcsConfigService.deleteDefectCode(defcodeKey);
		return map;
	}
	
	@GET
	@Path("/searchDefectCode/{defectCode}/{description}/{codeType}/{enabledKey}/{start}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> searchDefectCode(@PathParam("defectCode")String defectCode,
			@PathParam("description")String description, @PathParam("codeType")Integer codeType,
			@PathParam("enabledKey")Integer enabledKey,@PathParam("start")Integer start , 
			@PathParam("limit")Integer limit) {
		Map<String, Object> map = this.sfdcsConfigService.searchDefectCode(defectCode, description, codeType, enabledKey,start,limit);
		return map;
	}
	
	@GET
	@Path("/getPartNumberListTotal")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getPartNumberListTotal() {
		Map<String, Object> map = this.sfdcsConfigService.getPartNumberListTotal();
		return map;
	}

	@GET
	@Path("/getPartNumberListTotal/{partNumber}/{obsoleteKey}/{partType}/{configMasterKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getPartNumberListTotal(@PathParam("partNumber")String partNumber,
			@PathParam("obsoleteKey")Integer obsoleteKey, @PathParam("partType")Integer partType,
			@PathParam("configMasterKey")Integer configMasterKey) {
		Map<String, Object> map = this.sfdcsConfigService.getPartNumberListTotal(partNumber, obsoleteKey, partType, configMasterKey);
		return map;
	}
	
	@GET
	@Path("/partNumbers")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> partNumbers() {
		Map<String, Object> map = this.sfdcsConfigService.partNumbers();
		return map;
	}
	
	@GET
	@Path("/queryPartNumberList/{start}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryPartNumberList(@PathParam("start")Integer start , 
			@PathParam("limit")Integer limit){
		Map<String, Object> map = this.sfdcsConfigService.queryPartNumberList(start, limit);
		return map;
	}

	@GET
	@Path("/searchPartNumberList/{partNumber}/{obsoleteKey}/{partType}/{configMasterKey}/{start}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> searchPartNumberList(@PathParam("partNumber")String partNumber,
			@PathParam("obsoleteKey")Integer obsoleteKey, @PathParam("partType")Integer partType,
			@PathParam("configMasterKey")Integer configMasterKey,@PathParam("start")Integer start , 
			@PathParam("limit")Integer limit) {
		Map<String, Object> map = this.sfdcsConfigService.searchPartNumberList(partNumber, obsoleteKey, partType, configMasterKey,start,limit);
		return map;
	}

	@GET
	@Path("/addPartNumber/{partNumber}/{description}/{partType}/{obsolete}/{isErp}/{baseFlag}/{country}/"
			+ "{unitStartLocationKey}/{partPending}/{userDefined}/{travelerType}/{unitId}/{quantity}/{modelNumber}/"
			+ "{partOpType}/{familyId}/{customerCode}/{configMasterKey}/{warrantyCode}/{extendedPN}/{length}/{width}/{weight}/"
			+ "{volume}/{height}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> addPartNumber(@PathParam("partNumber")String partNumber, 
			@PathParam("description")String description,@PathParam("partType") Integer partType, 
			@PathParam("obsolete")Boolean obsolete,@PathParam("isErp")Boolean isErp,
			@PathParam("baseFlag")Boolean baseFlag, @PathParam("country")String country,
			@PathParam("unitStartLocationKey")Integer unitStartLocationKey, @PathParam("partPending")String partPending,
			@PathParam("userDefined")String userDefined, @PathParam("travelerType")Integer travelerType,
			@PathParam("unitId")String unitId, @PathParam("quantity")Integer quantity, 
			@PathParam("modelNumber")String modelNumber,@PathParam("partOpType")String partOpType, 
			@PathParam("familyId")String familyId,@PathParam("customerCode") String customerCode,
			@PathParam("configMasterKey")Integer configMasterKey,@PathParam("warrantyCode") String warrantyCode,
			@PathParam("extendedPN")String extendedPN,@PathParam("length") Double length,
			@PathParam("width")Double width, @PathParam("weight")Double weight, 
			@PathParam("volume")Double volume, @PathParam("height")Double height) {
		return this.sfdcsConfigService.addPartNumber(partNumber, description, partType, obsolete, isErp, baseFlag, 
				country, unitStartLocationKey, partPending, userDefined, travelerType, unitId, quantity, modelNumber, partOpType, 
				familyId, customerCode, configMasterKey, warrantyCode, extendedPN, length, width, weight, volume, height);
	}

	@GET
	@Path("/updatePartNumber/{partNumber}/{description}/{partType}/{obsolete}/{isErp}/{baseFlag}/{country}/"
			+ "{unitStartLocationKey}/{partPending}/{userDefined}/{travelerType}/{unitId}/{quantity}/{modelNumber}/"
			+ "{partOpType}/{familyId}/{customerCode}/{configMasterKey}/{warrantyCode}/{extendedPN}/{length}/{width}/{weight}/"
			+ "{volume}/{height}/{partKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> updatePartNumber(@PathParam("partNumber")String partNumber, 
			@PathParam("description")String description,@PathParam("partType") Integer partType, 
			@PathParam("obsolete")Boolean obsolete,@PathParam("isErp")Boolean isErp,
			@PathParam("baseFlag")Boolean baseFlag, @PathParam("country")String country,
			@PathParam("unitStartLocationKey")Integer unitStartLocationKey, @PathParam("partPending")String partPending,
			@PathParam("userDefined")String userDefined, @PathParam("travelerType")Integer travelerType,
			@PathParam("unitId")String unitId, @PathParam("quantity")Integer quantity, 
			@PathParam("modelNumber")String modelNumber,@PathParam("partOpType")String partOpType, 
			@PathParam("familyId")String familyId,@PathParam("customerCode") String customerCode,
			@PathParam("configMasterKey")Integer configMasterKey,@PathParam("warrantyCode") String warrantyCode,
			@PathParam("extendedPN")String extendedPN,@PathParam("length") Double length,
			@PathParam("width")Double width, @PathParam("weight")Double weight, 
			@PathParam("volume")Double volume, @PathParam("height")Double height,
			@PathParam("partKey")Integer partKey) {
		return this.sfdcsConfigService.updatePartNumber(partNumber, description, partType, obsolete, isErp, baseFlag,
				country, unitStartLocationKey, partPending, userDefined, travelerType, unitId, quantity, modelNumber, partOpType,
				familyId, customerCode, configMasterKey, warrantyCode, extendedPN, length, width, weight, volume, height, partKey);
	}

	@GET
	@Path("/deletePartNumber/{partKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> deletePartNumber(@PathParam("partKey")Integer partKey) {
		Map<String, Object> map = this.sfdcsConfigService.deletePartNumber(partKey);
		return map;
	}

	@GET
	@Path("/queryPartNumber/{partKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryPartNumber(@PathParam("partKey")Integer partKey) {
		Map<String, Object> map = this.sfdcsConfigService.queryPartNumber(partKey);
		return map;
	}

	@GET
	@Path("/queryPartFamily")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryPartFamily() {
		Map<String, Object> map = this.sfdcsConfigService.queryPartFamily();
		return map;
	}

	@GET
	@Path("/queryPartRevision/{partKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryPartRevision(@PathParam("partKey")Integer partKey) {
		Map<String, Object> map =  this.sfdcsConfigService.queryPartRevision(partKey);
		return map;
	}

	@GET
	@Path("/updatePartRevision/{revnum}/{revisionList}/{partKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> updatePartRevision(@PathParam("revnum")Integer revnum, 
			@PathParam("revisionList")String revisionList,@PathParam("partKey") Integer partKey) {
		Map<String, Object> map = this.sfdcsConfigService.updatePartRevision(revnum, revisionList, partKey);
		return map;
	}

	@GET
	@Path("/getAssemblyTotal")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getAssemblyTotal() {
		Map<String,Object> map = this.sfdcsConfigService.getAssemblyTotal();
		return map;
	}

	@GET
	@Path("/getAssemblyTotal/{sfdsKey}/{locationKey}/{partKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getAssemblyTotal(@PathParam("sfdsKey")Integer sfdsKey,
			@PathParam("locationKey")Integer locationKey, @PathParam("partKey")Integer partKey) {
		Map<String, Object> map = this.sfdcsConfigService.getAssemblyTotal(sfdsKey, locationKey, partKey);
		return map;
	}
	
	@GET
	@Path("/queryAssembly/{start}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryAssembly(@PathParam("start")Integer start,
			@PathParam("limit")Integer limit) {
		Map<String, Object> map = this.sfdcsConfigService.queryAssembly(start,limit);
		return map;
	}

	@GET
	@Path("/addAssembly/{sfdsKey}/{locationKey}/{partKey}/{revision}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> addAssembly(@PathParam("sfdsKey")Integer sfdsKey, 
			@PathParam("locationKey")Integer locationKey, @PathParam("partKey")Integer partKey, 
			@PathParam("revision")String revision) {
		Map<String, Object> map = this.sfdcsConfigService.addAssembly(sfdsKey, locationKey, partKey, revision);
		return map;
	}

	/*@GET
	@Path("/addAssembly2/{sfdsKey}/{locationKey}/{partKey}/{revision}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> addAssembly2(@PathParam("sfdsKey")Integer sfdsKey, 
			@PathParam("locationKey")Integer locationKey, @PathParam("partKey")Integer partKey, 
			@PathParam("revision")String revision) {
		return this.sfdcsConfigService.addAssembly2(sfdsKey, locationKey, partKey, revision);
	}*/

	
	
	@GET
	@Path("/deleteAssembly/{assemblyKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> deleteAssembly(@PathParam("assemblyKey")Integer assemblyKey) {
		Map<String, Object> map =  this.sfdcsConfigService.deleteAssembly(assemblyKey);
		return map;
	}
	
	@GET
	@Path("/searchAssembly/{sfdsKey}/{locationKey}/{partKey}/{start}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> searchAssembly(@PathParam("sfdsKey")Integer sfdsKey,
			@PathParam("locationKey")Integer locationKey, @PathParam("partKey")Integer partKey,
			@PathParam("start")Integer start,@PathParam("limit")Integer limit) {
		Map<String, Object> map = this.sfdcsConfigService.searchAssembly(sfdsKey, locationKey, partKey,start,limit);
		return map;
	}

	@GET
	@Path("/queryAssemblyStep/{assemblyKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryAssemblyStep(@PathParam("assemblyKey")Integer assemblyKey) {
		Map<String, Object> map = this.sfdcsConfigService.queryAssemblyStep(assemblyKey);
		return  map;
	}

	@GET
	@Path("/addAssemblyStep/{assemblyKey}/{componentType}/{step}/{compIdPrompt}/{compIdMask}/{refDesignator}/{compPartMask}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> addAssemblyStep(@PathParam("assemblyKey")Integer assemblyKey, 
			@PathParam("componentType")Integer componentType, @PathParam("step")Integer step,
			@PathParam("compIdPrompt")String compIdPrompt,@PathParam("compIdMask") String compIdMask,
			@PathParam("refDesignator")String refDesignator, @PathParam("compPartMask")String compPartMask) {
		Map<String, Object> map = this.sfdcsConfigService.addAssemblyStep(assemblyKey, componentType, step, compIdPrompt, 
				compIdMask, refDesignator, compPartMask);
		return map;
	}

	@GET
	@Path("/updateAssemblyStep/{componentType}/{step}/{compIdPrompt}/"
			+ "{compIdMask}/{refDesignator}/{compPartMask}/{assemblyStepKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> updateAssemblyStep(
			@PathParam("componentType")Integer componentType, @PathParam("step")Integer step,
			@PathParam("compIdPrompt")String compIdPrompt, @PathParam("compIdMask")String compIdMask,
			@PathParam("refDesignator")String refDesignator, @PathParam("compPartMask")String compPartMask,
			@PathParam("assemblyStepKey")Integer assemblyStepKey) {
		Map<String, Object> map = this.sfdcsConfigService.updateAssemblyStep( componentType, step, compIdPrompt,
				compIdMask, refDesignator, compPartMask, assemblyStepKey);
		return map;
	}

	@GET
	@Path("/deleteAssemblyStep/{assemblyStepKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> deleteAssemblyStep(@PathParam("assemblyStepKey")Integer assemblyStepKey) {
		Map<String, Object> map = this.sfdcsConfigService.deleteAssemblyStep(assemblyStepKey);
		return map;
	}



	@GET
	@Path("/getAttrByLocationTotal")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getAttrByLocationTotal() {
		Map<String, Object> map = this.sfdcsConfigService.getAttrByLocationTotal();
		return map;
	}

	@GET
	@Path("/getAttrByLocationTotal/{sfdsKey}/{locationKey}/{partNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getAttrByLocationTotal(@PathParam("sfdsKey")Integer sfdsKey, 
			@PathParam("locationKey")Integer locationKey, @PathParam("partNumber")String partNumber) {
		Map<String, Object> map = this.sfdcsConfigService.getAttrByLocationTotal(sfdsKey, locationKey, partNumber);
		return map;
	}

	@GET
	@Path("/queryAttrByLocation/{start}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryAttrByLocation(@PathParam("start")Integer start,
			@PathParam("limit")Integer limit) {
		Map<String, Object> map = this.sfdcsConfigService.queryAttrByLocation(start,limit);
		return map;
	}

	@GET
	@Path("/addAttrByLocation/{sfdsKey}/{locationKey}/{partKey}/{revision}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> addAttrByLocation(@PathParam("sfdsKey")Integer sfdsKey,
			@PathParam("locationKey")Integer locationKey,@PathParam("partKey") Integer partKey,
			@PathParam("revision")String revision) {
		Map<String, Object> map = this.sfdcsConfigService.addAttrByLocation(sfdsKey, locationKey, partKey, revision);
		return map;
	}
	
	@GET
	@Path("/searchAttrByLocation/{sfdsKey}/{locationKey}/{partNumber}/{start}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> searchAttrByLocation(@PathParam("sfdsKey")Integer sfdsKey,
			@PathParam("locationKey")Integer locationKey, @PathParam("partNumber")String partNumber,
			@PathParam("start")Integer start,@PathParam("limit")Integer limit) {
		Map<String, Object> map = this.sfdcsConfigService.searchAttrByLocation(sfdsKey, locationKey, partNumber,start,limit);
		return map;
	}

	@GET
	@Path("/deleteAttrByLocation/{attrDefKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> deleteAttrByLocation(@PathParam("attrDefKey")Integer attrDefKey) {
		Map<String, Object> map = this.sfdcsConfigService.deleteAttrByLocation(attrDefKey);
		return map;
	}

	@GET
	@Path("/queryAttrByProcess/{start}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryAttrByProcess(@PathParam("start")Integer start,
			@PathParam("limit")Integer limit) {
		Map<String, Object> map =  this.sfdcsConfigService.queryAttrByProcess( start, limit);
		return map ;
	}

	@GET
	@Path("/addAttrByProcess/{sfdsKey}/{processKey}/{partKey}/{revision}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> addAttrByProcess(@PathParam("sfdsKey")Integer sfdsKey, 
			@PathParam("processKey")Integer processKey, @PathParam("partKey")Integer partKey, 
			@PathParam("revision")String revision) {
		Map<String, Object> map = this.sfdcsConfigService.addAttrByProcess(sfdsKey, processKey, partKey, revision);
		return map;
	}

	@GET
	@Path("/searchAttrByProcess/{sfdsKey}/{processName}/{partNumber}/{start}/{limit}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> searchAttrByProcess(@PathParam("sfdsKey")Integer sfdsKey, 
			@PathParam("processName")String processName, @PathParam("partNumber")String partNumber,
			@PathParam("start")Integer start,@PathParam("limit")Integer limit) {
		return this.sfdcsConfigService.searchAttrByProcess(sfdsKey, processName, partNumber,start,limit);
	}

	@GET
	@Path("/queryAttrStep/{attrDefKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryAttrStep(@PathParam("attrDefKey")Integer attrDefKey) {
		return this.sfdcsConfigService.queryAttrStep(attrDefKey);
	}

	@GET
	@Path("/addAttrStep/{attrDefKey}/{step}/{released}/{attrNameKey}/{attrGroupKey}/{attrMaskType}/{attrMask}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> addAttrStep(@PathParam("attrDefKey")Integer attrDefKey, 
			@PathParam("step")Integer step,@PathParam("released") Boolean released, 
			@PathParam("attrNameKey")Integer attrNameKey,@PathParam("attrGroupKey")Integer attrGroupKey, 
			@PathParam("attrMaskType")Integer attrMaskType,@PathParam("attrMask") String attrMask) {
		return this.sfdcsConfigService.addAttrStep(attrDefKey, step, released, attrNameKey, attrGroupKey, attrMaskType, attrMask);
	}

	@GET
	@Path("/updateAttrStep/{step}/{released}/{attrNameKey}/{attrGroupKey}/{attrMaskType}/{attrMask}/{attrDefStepKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> updateAttrStep(
			@PathParam("step")Integer step,@PathParam("released") Boolean released, 
			@PathParam("attrNameKey")Integer attrNameKey,@PathParam("attrGroupKey")Integer attrGroupKey, 
			@PathParam("attrMaskType")Integer attrMaskType,@PathParam("attrMask") String attrMask,
			@PathParam("attrDefStepKey")Integer attrDefStepKey) {
		return this.sfdcsConfigService.updateAttrStep( step, released, attrNameKey, attrGroupKey, attrMaskType, attrMask, attrDefStepKey);
	}

	@GET
	@Path("/deleteAttrStep/{attrDefStepKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> deleteAttrStep(@PathParam("attrDefStepKey")Integer attrDefStepKey) {
		return this.sfdcsConfigService.deleteAttrStep(attrDefStepKey);
	}
	
	//================
	@GET
	@Path("/queryAttrGroupAttrName")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryAttrGroupAttrName() {
		return this.sfdcsConfigService.queryAttrGroupAttrName();
	}

	
	@GET
	@Path("/addAttrGroupAttrName/{attributeName}/{userPrompt}/{groupName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> addAttrGroupAttrName(@PathParam("attributeName")String attributeName, 
			@PathParam("userPrompt")String userPrompt, @PathParam("groupName")String groupName) {
		return this.sfdcsConfigService.addAttrGroupAttrName(attributeName, userPrompt, groupName);
	}
	
	@GET
	@Path("/updateAttrGroupAttrName/{attributeName}/{userPrompt}/{groupName}/{attrGroupAttrNameKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> updateAttrGroupAttrName(@PathParam("attributeName")String attributeName, 
			@PathParam("userPrompt")String userPrompt, @PathParam("groupName")String groupName,
			@PathParam("attrGroupAttrNameKey")Integer attrGroupAttrNameKey) {
		return this.sfdcsConfigService.updateAttrGroupAttrName(attributeName, userPrompt, groupName, attrGroupAttrNameKey);
	}
/*	@GET
	@Path("/updateAttrGroupAttrName/{attributeName}/{userPrompt}/{groupName}/{attrNameKey}/{attrGroupKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> updateAttrGroupAttrName(@PathParam("attributeName")String attributeName,
			@PathParam("userPrompt")String userPrompt, @PathParam("groupName")String groupName,
			@PathParam("attrNameKey")Integer attrNameKey, @PathParam("attrGroupKey")Integer attrGroupKey) {
		return this.sfdcsConfigService.updateAttrGroupAttrName(attributeName, userPrompt, groupName, attrNameKey, attrGroupKey);
	}*/

	@GET
	@Path("/deleteAttrGroupAttrName/{attrGroupAttrNameKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> deleteAttrGroupAttrName(@PathParam("attrGroupAttrNameKey")Integer attrGroupAttrNameKey) {
		return this.sfdcsConfigService.deleteAttrGroupAttrName(attrGroupAttrNameKey);
	}

	@GET
	@Path("/searchLocationWithSfdsKey/{sfdsKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> searchLocationWithSfdsKey(@PathParam("sfdsKey")Integer sfdsKey) {
		return this.sfdcsConfigService.searchLocationWithSfdsKey(sfdsKey);
	}

	@GET
	@Path("/getAttrByProcessTotal")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getAttrByProcessTotal() {
		Map<String, Object> map = this.sfdcsConfigService.getAttrByProcessTotal();
		return map;
	}

	@GET
	@Path("/getAttrByProcessTotal/{sfdsKey}/{processName}/{partNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getAttrByProcessTotal(@PathParam("sfdsKey")Integer sfdsKey, 
			@PathParam("processName")String processName, @PathParam("partNumber")String partNumber) {
		Map<String, Object> map = this.sfdcsConfigService.getAttrByProcessTotal(sfdsKey, processName, partNumber);
		return map;
	}

	@GET
	@Path("/addSetUpData/{sfdsKey}/{item}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> addSetUpData(@PathParam("sfdsKey")Integer sfdsKey, 
			@PathParam("item")String item) {
		System.out.println(sfdsKey+","+item);
		return this.sfdcsConfigService.addSetUpData(sfdsKey, item);
	}

	@GET
	@Path("/querySetUpData/{sfdsKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> querySetUpData(@PathParam("sfdsKey")Integer sfdsKey) {
		return this.sfdcsConfigService.querySetUpData(sfdsKey);
	}

	@GET
	@Path("/queryAllShopFloorId")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryAllShopFloorId() {
		return this.sfdcsConfigService.queryAllShopFloorId();
	}

	@GET
	@Path("/queryAllLocation")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> queryAllLocation() {
		return this.sfdcsConfigService.queryAllLocation();
	}

	@GET
	@Path("/searchLocation/{description}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> searchLocation(@PathParam("description")String description) {
		return this.sfdcsConfigService.searchLocation(description);
	}

}