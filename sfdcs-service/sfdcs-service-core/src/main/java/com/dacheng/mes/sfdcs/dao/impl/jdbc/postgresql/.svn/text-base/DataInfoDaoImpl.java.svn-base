package com.dacheng.mes.sfdcs.dao.impl.jdbc.postgresql;

import org.apache.log4j.Logger;

import com.dacheng.mes.sfdcs.dao.DataInfoDao;


public class DataInfoDaoImpl implements DataInfoDao {
	protected static Logger logger = Logger
			.getLogger(DataInfoDaoImpl.class);

	private String agileHost="";


	/**
	 * get default parameter from setting file
	 */
	public String[] getDefineResource() {
		logger.info("begin execute getDefineResource()");

		String resAgileHost = "";
		resAgileHost = this.getAgileHost();
		logger.info("getAgileHost is " + resAgileHost);
		if (resAgileHost == null)
			resAgileHost = "";

		return new String[] { resAgileHost};
	}

	public String getAgileHost() {
		return agileHost;
	}


}
