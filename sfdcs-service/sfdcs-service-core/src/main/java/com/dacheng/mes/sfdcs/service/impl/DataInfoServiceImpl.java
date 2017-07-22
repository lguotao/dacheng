package com.dacheng.mes.sfdcs.service.impl;

import org.springframework.dao.DataAccessException;

import com.dacheng.mes.sfdcs.dao.DataInfoDao;
import com.dacheng.mes.sfdcs.service.DataInfoService;



public class DataInfoServiceImpl implements DataInfoService {

	private DataInfoDao dataInfoDao;

	public DataInfoDao getDataInfoDao() {
		return dataInfoDao;
	}

	public void setDataInfoDao(DataInfoDao dataInfoDao) {
		this.dataInfoDao = dataInfoDao;
	}

	public String[] getDefineResource() throws DataAccessException {
		return dataInfoDao.getDefineResource();
	}


}
