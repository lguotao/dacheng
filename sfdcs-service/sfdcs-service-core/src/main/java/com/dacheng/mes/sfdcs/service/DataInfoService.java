package com.dacheng.mes.sfdcs.service;

import org.springframework.dao.DataAccessException;


public interface DataInfoService {
	
	public static final String DATA_EMULATOR_INFO_SERVICE = "dataEmulatorInfoService";

	public String[] getDefineResource() throws DataAccessException;
	

}
