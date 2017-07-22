package com.dacheng.mes.sfdcs.dao.impl.jdbc.postgresql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.dacheng.mes.sfdcs.dao.SFDCSConfigDao;
import com.dacheng.mes.sfdcs.pojo.ShopFloorID;
import com.dacheng.mes.sfdcs.pojo.UnitStatus;
import com.dacheng.mes.sfdcs.pojo.Assembly;
import com.dacheng.mes.sfdcs.pojo.AssemblyStep;
import com.dacheng.mes.sfdcs.pojo.AttrGroupAttrName;
import com.dacheng.mes.sfdcs.pojo.AttributeDef;
import com.dacheng.mes.sfdcs.pojo.AttributeDefStep;
import com.dacheng.mes.sfdcs.pojo.DataCollector;
import com.dacheng.mes.sfdcs.pojo.DefCode;
import com.dacheng.mes.sfdcs.pojo.Department;
import com.dacheng.mes.sfdcs.pojo.Location;
import com.dacheng.mes.sfdcs.pojo.LocationInfo;
import com.dacheng.mes.sfdcs.pojo.Part;
import com.dacheng.mes.sfdcs.pojo.PartRevision;
import com.dacheng.mes.sfdcs.pojo.Process;
import com.dacheng.mes.sfdcs.pojo.Scanner;

public class SFDCSConfigDaoImpl implements SFDCSConfigDao {

	protected static Log logger = LogFactory.getLog(SFDCSConfigDaoImpl.class);
	
	private JdbcTemplate sfdcsjdbcTemplate = null;
	
	private static String Success = "success";
	
	private static String failure = "failure";
	
	private static String error = "error";
	
	private static String data = "data";
	
	private static String message = "message";
	
	
	public JdbcTemplate getSfdcsjdbcTemplate() {
		return sfdcsjdbcTemplate;
	}

	public void setSfdcsjdbcTemplate(JdbcTemplate sfdcsjdbcTemplate) {
		this.sfdcsjdbcTemplate = sfdcsjdbcTemplate;
	}

	/**
	 * 
	 * get Count of  sfdsId
	 */
	private String GET_SHOP_FLOOR_ID_COUNT="select count(sfds_key) as total from sfds_config ";
	public Map<String, Object> getShopFloorIdTotal(String sfdsId, String lineName) {
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(GET_SHOP_FLOOR_ID_COUNT);
			buffer.append("where sfds_id like '%"+sfdsId.trim()+"%' and line_name like '%"+lineName.trim()+"%'" );
			System.out.println(buffer);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
						map.put(message, Success);
						map.put(data, rs.getInt("total"));
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		/*	String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}
	
	/*
	 * query sfdc_config get all info
	 * 
	 */
	
	private String QUERY_SHOP_FLOOR_ID = "select sfds_key, sfds_id, line_name, shift_time, inact_days, timezone from mdcs.sfds_config ";
	
	public Map<String, Object>  queryShopFloorID(Integer startRow , Integer limitRow) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<ShopFloorID> resultList = new ArrayList<ShopFloorID>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_SHOP_FLOOR_ID + " ORDER BY sfds_key asc  offset "+startRow + " LIMIT "+limitRow);
			logger.info("start load all  shop floor id " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						ShopFloorID shopFloorID = new ShopFloorID();
						shopFloorID.setSfdsKey(rs.getInt("sfds_key"));
						shopFloorID.setSfdsId(rs.getString("sfds_id"));
						shopFloorID.setLineName(rs.getString("line_name"));
						shopFloorID.setShiftTime(rs.getString("shift_time"));
						shopFloorID.setTimeZone(rs.getString("timezone"));
						resultList.add(shopFloorID);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			/*String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}
	
	/*
	 * insert (sfds_id , line_name , timezone) into mdcs.sfds_config 
	 * 
	 */
	private String INSERT_SHOP_FLOOR_ID = "INSERT INTO mdcs.sfds_config (sfds_id , line_name , timezone) ";
	@Override
	public Map<String, Object> addShopFloorID(final String sfdsId, final String lineName,String inActive, final String timeZone) {
		final Map<String, Object> map = new HashMap<String, Object>();

		String sql = INSERT_SHOP_FLOOR_ID +"SELECT '"+sfdsId.trim()+"','"+lineName.trim()
						+ "','"+timeZone.trim()+"' WHERE NOT EXISTS (SELECT sfds_key FROM sfds_config WHERE sfds_id='"+
						sfdsId.trim() +"')";
		logger.info("start insert new shop floor Id:"+sql);
//		System.out.println(sql);
		try {
			logger.info("Check to see sfdsId if it exists ");
			sfdcsjdbcTemplate.query(SELECT_COUNT_SHOP_FLOOR_ID, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, sfdsId.trim());
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("count")>0) {
						map.put(message, failure);
						map.put(error, "This Shop Floor ID is existence");
					}else{
						logger.info("start insert  sfdsId");
						int i = sfdcsjdbcTemplate.update( INSERT_SHOP_FLOOR_ID, new PreparedStatementSetter() {
							@Override
							public void setValues(PreparedStatement ps) throws SQLException {
									ps.setString(1, sfdsId.trim());
									ps.setString(2, lineName.trim());
									ps.setString(3, timeZone.trim());
							}
						});
						if (i>0) {
							map.put(message, Success);
							map.put(data, "");
						}
					}
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}
	
	/*
	 * update mdcs.sfds_config (sfdsId , lineName , timeZone) 
	 */
	
	private String  UPDATE_SHOP_FLOOR_ID = "update mdcs.sfds_config set sfds_id=?  , line_name = ? ,  timezone = ?  where sfds_key = ?";
	private String SELECT_COUNT_SHOP_FLOOR_ID = "select count(sfds_key) from sfds_config where sfds_id = ?";
	public Map<String, Object> updateShopFloorID(final String sfdsId, final String lineName,String inActive, final String timeZone, final Integer sfdcsKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			logger.info("Check to see sfdsId if it exists ");
			sfdcsjdbcTemplate.query(SELECT_COUNT_SHOP_FLOOR_ID, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, sfdsId.trim());
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("count")>0) {
						map.put(message, failure);
						map.put(error, "This Shop Floor ID is existence");
					}else{
						logger.info("start update sfdsId");
						int i = sfdcsjdbcTemplate.update( UPDATE_SHOP_FLOOR_ID, new PreparedStatementSetter() {
							@Override
							public void setValues(PreparedStatement ps) throws SQLException {
									ps.setString(1, sfdsId.trim());
									ps.setString(2, lineName.trim());
									ps.setString(3, timeZone.trim());
									ps.setInt(4, sfdcsKey);
							}
						});
						if (i>0) {
							map.put(message, Success);
							map.put(data, "");
						}
					}
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}
	
	
	/*
	 *  remove shopfloorID in mdcs.sfds_config
	 */
	
	private String DELETE_SHOP_FLOOR_ID = "DELETE FROM mdcs.sfds_config  where sfds_key = ? ";
	public Map<String, Object> deleteShopFloorID(final Integer sfdcsKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		logger.info("start delete shop floor Id ");
		try {
			int i = sfdcsjdbcTemplate.update( DELETE_SHOP_FLOOR_ID, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps)  {
					try {
						ps.setInt(1, sfdcsKey);
						
					} catch (SQLException e) {
						logger.error("SQL Exception : "+e.getMessage());
						map.put(message,failure );
						map.put(error, "SQLException");
					}
				}
			});
			if (i>0) {
				map.put(message, Success);
				map.put(data, "");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
	/*		String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}
	
	/*
	 * search mdcs.sfds_config 
	 * 
	 */
private String SEARCH_SHOP_FLOOR_ID = "select sfds_key, sfds_id, line_name, shift_time, inact_days, timezone from mdcs.sfds_config ";
	
	public Map<String, Object> searchShopFloorID(String sfdsId, String lineName, Integer startRow, Integer limitRow) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<ShopFloorID> resultList = new ArrayList<ShopFloorID>();
		System.out.println("sfdsId:"+sfdsId +" lineName:" +lineName);
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(SEARCH_SHOP_FLOOR_ID+ " where sfds_id like '%"+sfdsId.trim()+"%' and line_name like '%"+lineName.trim()+"%'");
			System.out.println(buffer);
			buffer.append(" ORDER BY sfds_key asc  offset "+startRow+" LIMIT "+limitRow);
			logger.info("Select device and location sql is " + buffer.toString());
			System.out.println(buffer);
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
						ShopFloorID shopFloorID = new ShopFloorID();
						shopFloorID.setSfdsKey(rs.getInt("sfds_key"));
						shopFloorID.setSfdsId(rs.getString("sfds_id"));
						shopFloorID.setLineName(rs.getString("line_name"));
						shopFloorID.setShiftTime(rs.getString("shift_time"));
						shopFloorID.setTimeZone(rs.getString("timezone"));
						resultList.add(shopFloorID);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		/*	String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}


	/*
	 * 
	 * get All process
	 * 
	 */
	private String QUERY_PROCESS ="select * from mdcs.process";
	public Map<String, Object> queryProcess() {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<Process> resultList = new ArrayList<Process>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_PROCESS);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException  {
						Process process = new Process();
						process.setProcessKey(rs.getInt("process_key"));
						process.setProcessName(rs.getString("process_name"));
						process.setEmvControlled(rs.getInt("emv_controlled"));
						resultList.add(process);
						map.put(message, Success);
						map.put(data, resultList);
				
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
/*			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}
	
	/*
	 * insert mdcs.process
	 * 
	 */
	
	private String INSERT_PROCESS = "INSERT INTO mdcs.process(process_name , emv_controlled)";
	@Override
	public Map<String, Object> addProcess(final String processName, final Integer emvControlled) {
		final Map<String, Object> map = new HashMap<String, Object>();
		String sql =INSERT_PROCESS + "select '"+processName.trim()+"',"+emvControlled+ 
				"  WHERE NOT EXISTS  (SELECT process_key from process where process_name='"+processName.trim()+"')";
		logger.info(sql);
	//	System.out.println(sql);
		try {
			int i = sfdcsjdbcTemplate.update(sql);
			if (i>0) {
				map.put(message, Success);
				map.put(data, "");
			}
			if (i==0) {
				map.put(message,failure );
				map.put(error, "This Process Name is existence");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
/*			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}

	/*
	 * 
	 * update process
	 */
	
	private String UPDATE_PROCESS="update mdcs.process set process_name = ?  , emv_controlled = ?  where process_key = ? ";
	private String SELECT_COUNT_PROCESS="select count(process_key) from process where process_name = ?";
	public Map<String, Object> updateProcess(final Integer processKey, final String processName, final Integer emvControlled) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			sfdcsjdbcTemplate.query(SELECT_COUNT_PROCESS, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1,processName.trim());
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("count")>0) {
						map.put(message, failure);
						map.put(error, "This Process Name  is existence");
					}else{
						int i = sfdcsjdbcTemplate.update(UPDATE_PROCESS, new PreparedStatementSetter() {
							@Override
							public void setValues(PreparedStatement ps) {
								try {
									ps.setString(1, processName.trim());
									ps.setInt(2, emvControlled);
									ps.setInt(3, processKey);
								} catch (SQLException  e) {
									logger.error("SQL Exception : "+e.getMessage());
									map.put(message, failure);
									map.put(error, "SQL Exception");
								}
							}
						});
						if (i>0) {
							map.put(message, Success);
							map.put(data, "");
						}
					}
				}
			});
			
		} catch (Exception ex) {
			logger.error(ex.getMessage());
/*			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}
	
	/*
	 * delele process
	 * 
	 */

	private String DELETE_PROCESS = "delete from mdcs.process  where process_key = ?";
	public Map<String, Object> deleteProcess(final Integer processKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			int i = sfdcsjdbcTemplate.update( DELETE_PROCESS, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps)  {
					try {
						ps.setInt(1,processKey );
					} catch (SQLException e) {
						logger.error("SQL Exception : "+e.getMessage());
						map.put(message,failure );
						map.put(error, "SQLException");
					}
				}
			});
			if (i>0) {
				map.put(message, Success);
				map.put(data, "");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			/*String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}

	/*
	 * get all departments
	 */
	private String QUERY_DEPARTMENT = "select dept_key, department from department ";
	public Map<String, Object> queryDepartment() {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<Department> resultList = new ArrayList<Department>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_DEPARTMENT);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						Department department = new Department();
						department.setDepartment(rs.getString("department"));
						department.setDeptKey(rs.getInt("dept_key"));
						resultList.add(department);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		/*	String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		
		return map;
	}
	
	/*
	 * add new department
	 */

	private String INSERT_DEPARTMENT = "INSERT INTO department(department) ";
	public Map<String, Object> addDepartment(final String department) {
		final Map<String, Object> map = new HashMap<String, Object>();
		String sql = INSERT_DEPARTMENT +"select '"+department.trim()+
				"' WHERE  NOT  EXISTS  ( SELECT dept_key from department where department='"+department.trim()+"')";
		logger.info(sql);
		System.out.println(sql);
		try {
				int i = sfdcsjdbcTemplate.update(sql);
				if (i>0) {
					map.put(message, Success);
					map.put(data, "");
				}
				if (i==0) {
					map.put(message,failure );
					map.put(error, "This Department  is existence");
				}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	
	/*
	 * update department
	 * 
	 */

	private String UPDATE_DEPARTMENT ="update department set  department = ? where dept_key = ?   ";
	private String SELECT_COUNT_DEPARTMENT="select count(dept_key) from department where department = ?";
	public Map<String, Object> updateDepartment(final Integer deptKey, final String department) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			sfdcsjdbcTemplate.query(SELECT_COUNT_DEPARTMENT, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1,department.trim());
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("count")>0) {
						map.put(message, failure);
						map.put(error, "This  Department  is existence");
					}else{
						int i = sfdcsjdbcTemplate.update(UPDATE_DEPARTMENT, new PreparedStatementSetter() {
							@Override
							public void setValues(PreparedStatement ps) {
								try {
									ps.setString(1, department.trim());
									ps.setInt(2,deptKey);
								} catch (SQLException e) {
									logger.error("SQL Exception : "+e.getMessage());
									map.put(message, failure);
									map.put(error, e.getMessage().toString());
								}
							}
						});
						if (i>0) {
							map.put(message, Success);
							map.put(data, "");
						}
					}
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}
	
	/*
	 * delete department
	 * 
	 */

	private String DELETE_DEPARTMENT = "delete from department  where dept_key = ?";
	public Map<String, Object> deleteDepartment(final Integer deptKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			int i = sfdcsjdbcTemplate.update( DELETE_DEPARTMENT, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException  {
						ps.setInt(1,deptKey );
						map.put(message, Success);
						map.put(data, "");
				}
			});
			if (i>0) {
				map.put(message, Success);
				map.put(data, "");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	
	private String QUERY_UNIT_STATUS = "select unit_status_key , unit_status  from unit_status";
	public Map<String, Object> queryUnitStatus() {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<UnitStatus> resultList = new ArrayList<UnitStatus>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_UNIT_STATUS);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
						UnitStatus unitStatus = new UnitStatus();
						unitStatus.setUnitStatus(rs.getString("unit_status"));
						unitStatus.setUnitStatusKey(rs.getInt("unit_status_key"));
						resultList.add(unitStatus);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
	/*		String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		
		return map;
	}

	/**
	 * 
	 * Location
	 */
	
	/*
	 *  getAllLocationInfo
	 */
	private String QUERY_LOCATION_INFO  = "select a.sfds_key , a.sfds_id , b.unit_status_key , b.unit_status , c.process_key , c.process_name ,"
			+ " d.dept_key , d.department , e.location_key , e.description"
			+ " from sfds_config as a , unit_status as b , process as c , department as d,  location as e"
			+ " where e.sfds_key =  a.sfds_key and e.unit_status_key = b.unit_status_key and e.process_key = c.process_key  and e.dept_key = d.dept_key";
	public Map<String, Object> queryLocationInfo(Integer start , Integer limit) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<LocationInfo> resultList = new ArrayList<LocationInfo>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_LOCATION_INFO);
			buffer.append(" ORDER BY e.location_key asc  offset "+start +" LIMIT "+limit);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
						LocationInfo locationInfo = new LocationInfo();
						locationInfo.setSfdsKey(rs.getInt("sfds_key"));
						locationInfo.setSfdsId(rs.getString("sfds_id"));
						locationInfo.setUnitStatusKey(rs.getInt("unit_status_key"));
						locationInfo.setUnitStatus(rs.getString("unit_status"));
						locationInfo.setProcessKey(rs.getInt("process_key"));
						locationInfo.setProcessName(rs.getString("process_name"));
						locationInfo.setDeptKey(rs.getInt("dept_key"));
						locationInfo.setDepartment(rs.getString("department"));
						locationInfo.setLocationKey(rs.getInt("location_key"));
						locationInfo.setDescription(rs.getString("description"));
						resultList.add(locationInfo);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
/*			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		
		return map;
	}
	
	/*
	 * 
	 * getLocation (location_key , description)-- locationName
	 */
	
	
	public String QUERY_LOCATION ="select  location_key , description  from location";
	public Map<String, Object> queryLocation() {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<Location> resultList = new ArrayList<Location>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_LOCATION);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						Location location = new Location();
						location.setLocationKey(rs.getInt("location_key"));
						location.setDescription(rs.getString("description"));
						resultList.add(location);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		/*	String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		
		return map;
	}
	
	
	/*
	 * 
	 * insert location (sfds_key , workstation , description , station_type, unit_status_key , process_key , dept_key);
	 * 
	 */
	
	
	private String INSERT_LOCATION = "INSERT INTO location (sfds_key ,  workstation , description , station_type, unit_status_key , process_key , dept_key )  values(?,?,?,?,?,?,?)";
	private String INSERT_LOCATION_NO_PROCESS = "INSERT INTO location (sfds_key ,  workstation , description , station_type, unit_status_key  , dept_key )  values(?,?,?,?,?,?)";
	private String SELECT_COUNT_LOCATION="select count(location_key) ,max(location_key) from location where description = ?  and sfds_key = ?";
	public Map<String, Object> addLocation(final Integer sfdsKey, final String description,final String stationType,
			final Integer unitStatusKey, final Integer processKey, final Integer deptKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			sfdcsjdbcTemplate.query(SELECT_COUNT_LOCATION, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1,description.trim());
					ps.setInt(2, sfdsKey);
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("count")>0) {
						map.put(message, failure);
						map.put(error, "This record already exists in database. Detail: Key (sfds_key, short_workstation)=("+sfdsKey+","+ description.trim()+") already exists");
					}else{
						if(processKey>0){
							int i = sfdcsjdbcTemplate.update(INSERT_LOCATION, new PreparedStatementSetter() {
								@Override
								public void setValues(PreparedStatement ps) throws SQLException{
										ps.setInt(1, sfdsKey);
										ps.setString(2, description.trim().substring(0, 4));
										ps.setString(3, description.trim());
										ps.setString(4, stationType.trim());
										ps.setInt(5, unitStatusKey);
										ps.setInt(6, processKey);
										ps.setInt(7, deptKey);
								}
							});
							if (i>0) {
								map.put(message, Success);
								map.put(data, "");
							}
						}else{
							int i = sfdcsjdbcTemplate.update(INSERT_LOCATION_NO_PROCESS, new PreparedStatementSetter() {
								@Override
								public void setValues(PreparedStatement ps) throws SQLException{
										ps.setInt(1, sfdsKey);
										ps.setString(2, description.trim().substring(0, 4));
										ps.setString(3, description.trim());
										ps.setString(4, stationType.trim());
										ps.setInt(5, unitStatusKey);
										ps.setInt(6, deptKey);
								}
							});
							if (i>0) {
								map.put(message, Success);
								map.put(data, "");
							}
						}
					}
				}
			});
			/*int i = sfdcsjdbcTemplate.update(INSERT_LOCATION, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) {
					try {
						ps.setInt(1, sfdsKey);
						ps.setString(2, description.trim().substring(0, 4));
						ps.setString(3, description.trim());
						ps.setString(4, stationType.trim());
						ps.setInt(5, unitStatusKey);
						ps.setInt(6, processKey);
						ps.setInt(7, deptKey);
					} catch (Exception e) {
						logger.error("Exception : "+e.getMessage());
						map.put(message, failure);
						map.put(error, e.getMessage().toString());
					}
				}
			});
			if (i>0) {
				map.put(message, Success);
				map.put(data, "");
			}*/
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/*
	 * updateLocation()
	 */
	private String UPDATE_LOCATION = "update location set sfds_key = ? , workstation = ? , description = ? , station_type = ? , unit_status_key = ?,"
			+ " process_key = ? , dept_key = ?  where location_key = ?";
	public Map<String, Object> updateLocation(final Integer sfdsKey, final String description,
			final String stationType, final Integer unitStatusKey, final Integer processKey, final Integer deptKey, final Integer locationKey) {
		final Map<String, Object> map = new HashMap<String,Object>();
		try {	
			sfdcsjdbcTemplate.query(SELECT_COUNT_LOCATION, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1,description.trim());
					ps.setInt(2, sfdsKey);
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("count")>0 && rs.getInt("max")!= locationKey) {
						map.put(message, failure);
						map.put(error, "This record already exists in database. Detail: Key (sfds_key, short_workstation)=("+sfdsKey+","+ description.trim()+") already exists");
					}else{
						int i = sfdcsjdbcTemplate.update(UPDATE_LOCATION, new PreparedStatementSetter() {
							public void setValues(PreparedStatement ps) throws SQLException{
									ps.setInt(1, sfdsKey);
									ps.setString(2, description.trim().substring(0, 4));
									ps.setString(3, description.trim());
									ps.setString(4, stationType.trim());
									ps.setInt(5, unitStatusKey);
									ps.setInt(6, processKey);
									ps.setInt(7, deptKey);
									ps.setInt(8, locationKey);
							}
						});
						if (i>0) {
							map.put(message, Success);
							map.put(data, "");
						}
					}
				}
			});

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/*
	 * search LocationInfo 
	 */
	private String SEARCH_LOCATION_INFO = QUERY_LOCATION_INFO;
	
	
	public Map<String, Object> searchLocationInfo(Integer sfdsKey, String description, Integer unitStatusKey,
			Integer processKey, Integer deptKey,Integer start , Integer limit) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<LocationInfo> resultList = new ArrayList<LocationInfo>();
		logger.info(sfdsKey  +  description  + unitStatusKey + processKey + deptKey);
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(SEARCH_LOCATION_INFO +  " and description like '%" + description.trim()+"%'");
			if (sfdsKey > 0) {
				buffer.append(" and e.sfds_key = "+ sfdsKey);
			}if (unitStatusKey > 0) {
				buffer.append(" and  e.unit_status_key = "+ unitStatusKey);
			}if (processKey > 0) {
				buffer.append(" and e.process_key = " + processKey);
			}if (deptKey > 0) {
				buffer.append("  and e.dept_key = "+ deptKey);
			}
			buffer.append(" ORDER BY e.location_key asc  offset "+start +" LIMIT "+limit);
			System.out.println(buffer);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						LocationInfo locationInfo = new LocationInfo();
						locationInfo.setSfdsKey(rs.getInt("sfds_key"));
						locationInfo.setSfdsId(rs.getString("sfds_id"));
						locationInfo.setUnitStatusKey(rs.getInt("unit_status_key"));
						locationInfo.setUnitStatus(rs.getString("unit_status"));
						locationInfo.setProcessKey(rs.getInt("process_key"));
						locationInfo.setProcessName(rs.getString("process_name"));
						locationInfo.setDeptKey(rs.getInt("dept_key"));
						locationInfo.setDepartment(rs.getString("department"));
						locationInfo.setLocationKey(rs.getInt("location_key"));
						locationInfo.setDescription(rs.getString("description"));
						resultList.add(locationInfo);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/*
	 * 
	 * delete location
	 */
	private String DELETE_LOCATION = "delete from location where  location_key = ?";
	
	public Map<String, Object> deleteLocation(final Integer locationKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			int i = sfdcsjdbcTemplate.update( DELETE_LOCATION, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps)  throws SQLException{
						ps.setInt(1,locationKey);
				}
			});
			if (i>0) {
				map.put(message, Success);
				map.put(data, "");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	
	/**
	 * 
	 * 
	 * get DataCollector count
	 */

	private String GET_DATA_COLLECTOR_TOTAL="select count(scanner_key) as total  "
			+ " from sfds_config as a , location as e , scanner as f  "
			+" where a.sfds_key = f.sfds_key and e.location_key = f.location_key ";
	public Map<String, Object> getDataCollectorTotal() {
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(GET_DATA_COLLECTOR_TOTAL);
			System.out.println(buffer);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
						map.put(message, Success);
						map.put(data, rs.getInt("total"));
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		/*	String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}

	@Override
	public Map<String, Object> getDataCollectorTotal(Integer deviceNumber, Integer sfdsKey, Integer locationKey,
			Integer inactiveKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(GET_DATA_COLLECTOR_TOTAL);
			if (deviceNumber > 0) {
				buffer.append(" and f.device_number = " + deviceNumber);
			}if ( sfdsKey > 0) {
				buffer.append(" and  f.sfds_key  =  "+ sfdsKey);
			}if (locationKey > 0) {
				buffer.append(" and f.location_key = " + locationKey);
			}
			if (inactiveKey == 0 ) {
				buffer.append(" and f.inactive =  " + false);
			}if (inactiveKey == 1) {
				buffer.append(" and f.inactive =  " + true);
			}
			System.out.println(buffer);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
						map.put(message, Success);
						map.put(data, rs.getInt("total"));
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		/*	String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}
	
	
	/*
	 * 
	 *  getAllDataCollector
	 */
	private String QUERY_DATA_COLLECTOR = "select a.sfds_key , a.sfds_id , e.location_key , e.description , f.scanner_key , f.device_number, f.inactive  "
			+ " from sfds_config as a , location as e , scanner as f  "
			+" where a.sfds_key = f.sfds_key and e.location_key = f.location_key ";
		
	public Map<String, Object> queryDataCollector(Integer start , Integer limit) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<DataCollector> resultList = new ArrayList<DataCollector>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_DATA_COLLECTOR+ " ORDER BY scanner_key asc  offset "+start+ " LIMIT "+limit);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
						DataCollector dataCollector = new DataCollector();
						dataCollector.setSfdsKey(rs.getInt("sfds_key"));
						dataCollector.setSfdsId(rs.getString("sfds_id"));
						dataCollector.setLocationKey(rs.getInt("location_key"));
						dataCollector.setDescription(rs.getString("description"));
						dataCollector.setScannerKey(rs.getInt("scanner_key"));
						dataCollector.setDeviceNumber(rs.getInt("device_number"));
						dataCollector.setInactive(rs.getBoolean("inactive"));
						resultList.add(dataCollector);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}


	private String INSERT_SCANNER = "insert into scanner(sfds_key , device_number , location_key , inactive) values(?,?,?,?);";
	private String SELECT_COUNT_SCANNER="select count(scanner_key) from scanner where sfds_key = ?  and location_key = ? and device_number = ? ";
	public Map<String, Object> addScanner(final Integer deviceNumber, final Integer sfdsKey, final Integer locationKey,
			final Boolean inactive) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			sfdcsjdbcTemplate.query(SELECT_COUNT_SCANNER, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, sfdsKey);
						ps.setInt(2, locationKey);
						ps.setInt(3, deviceNumber);
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("count")>0) {
						map.put(message, failure);
						map.put(error, "This Device in this Location of  this  shop floor Id is existence");
					}else{
						int i = sfdcsjdbcTemplate.update(INSERT_SCANNER, new PreparedStatementSetter() {
							public void setValues(PreparedStatement ps) throws SQLException{
									ps.setInt(1, sfdsKey);
									ps.setInt(2, deviceNumber);
									ps.setInt(3, locationKey);
									ps.setBoolean(4, inactive);
							}
						});
						if (i>0) {
							map.put(message, Success);
							map.put(data, "");
						}
					}
				}
			});
			
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}
	
	/*
	 * 
	 */
	private String UPDATE_SCANNER = "update scanner set sfds_key = ? , device_number = ? , location_key = ? ,  inactive = ?  where scanner_key = ?";
	public Map<String, Object> updateScanner(final Integer deviceNumber, final Integer sfdsKey, final Integer locationKey,
			final Boolean inactive, final Integer scannerKey) {
		final Map<String, Object> map = new HashMap<String,Object>();
		try {	
			
			sfdcsjdbcTemplate.query(SELECT_COUNT_PROCESS, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, sfdsKey);
						ps.setInt(2, locationKey);
						ps.setInt(3, deviceNumber);
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("count")>0) {
						map.put(message, failure);
						map.put(error, "This Device  is existence");
					}else{
						int i = sfdcsjdbcTemplate.update(UPDATE_SCANNER, new PreparedStatementSetter() {
							@Override
							public void setValues(PreparedStatement ps) throws SQLException{
									ps.setInt(1, sfdsKey);
									ps.setInt(2, deviceNumber);
									ps.setInt(3, locationKey);
									ps.setBoolean(4, inactive);
									ps.setInt(5, scannerKey);
							}
						});
						if (i>0) {
							map.put(message, Success);
							map.put(data, "");
						}
					}
				}
			});
	
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/*
	 * 
	 * 
	 */
	private String DELETE_SCANNER = "delete from scanner where scanner_key = ?";
	public Map<String, Object> deleteScanner(final Integer scannerKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			int i = sfdcsjdbcTemplate.update( DELETE_SCANNER, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps)  throws SQLException{
						ps.setInt(1,scannerKey);
				}
			});
			if (i>0) {
				map.put(message, Success);
				map.put(data, "");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}
	
	
	/*
	 *  
	 */
	public Map<String, Object> searchDataCollector(Integer deviceNumber, Integer sfdsKey, Integer locationKey,
			Integer inactiveKey,Integer start , Integer limit) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<DataCollector> resultList = new ArrayList<DataCollector>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_DATA_COLLECTOR);
			if (deviceNumber > 0) {
				buffer.append(" and f.device_number = " + deviceNumber);
			}if ( sfdsKey > 0) {
				buffer.append(" and  f.sfds_key  =  "+ sfdsKey);
			}if (locationKey > 0) {
				buffer.append(" and f.location_key = " + locationKey);
			}
			if (inactiveKey == 0 ) {
				buffer.append(" and f.inactive =  " + false);
			}if (inactiveKey == 1) {
				buffer.append(" and f.inactive =  " + true);
			}
			buffer.append( " ORDER BY scanner_key asc  offset "+start+ " LIMIT "+limit);
			System.out.println(buffer.toString());
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						DataCollector dataCollector = new DataCollector();
						dataCollector.setSfdsKey(rs.getInt("sfds_key"));
						dataCollector.setSfdsId(rs.getString("sfds_id"));
						dataCollector.setLocationKey(rs.getInt("location_key"));
						dataCollector.setDescription(rs.getString("description"));
						dataCollector.setScannerKey(rs.getInt("scanner_key"));
						dataCollector.setDeviceNumber(rs.getInt("device_number"));
						dataCollector.setInactive(rs.getBoolean("inactive"));
						resultList.add(dataCollector);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/*
	 * get All deviceNumber 
	 */
	private String QUERY_DEVICE_NUMBER = "select device_number from scanner";
	public Map<String, Object> queryDeviceNumber() {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<Scanner> resultList = new ArrayList<Scanner>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_DEVICE_NUMBER);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						Scanner scanner = new Scanner();
						scanner.setDeviceNumber(rs.getInt("device_number"));
						resultList.add(scanner);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	
	
	
	/**
	 * 
	 * 
	 */
	private String GET_DEFECT_CODE_TOTAL="select count(defcode_key) as total  from defcode ";
	public Map<String, Object> getDefectCodeTotal() {
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(GET_DEFECT_CODE_TOTAL);
			System.out.println(buffer);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
						map.put(message, Success);
						map.put(data, rs.getInt("total"));
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		/*	String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}

	
	@Override
	public Map<String, Object> getDefectCodeTotal(String defectCode, String description, Integer codeType,
			Integer enabledKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(GET_DEFECT_CODE_TOTAL+"  where defect_code like '%" + defectCode.trim() + "%' and  description like '%"+description.trim()+"%' ");
			if (codeType >= 0) {
				buffer.append(" and  code_type= "+codeType);
			}if (enabledKey == 0) {
				buffer.append(" and enabled =  false");
			}if (enabledKey == 1) {
				buffer.append("  and enabled =  true");
			}
			System.out.println(buffer);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
						map.put(message, Success);
						map.put(data, rs.getInt("total"));
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		/*	String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}

	
	/*
	 * get All DefectCode()
	 */
	private String QUERY_DEFECT_CODE = "select defcode_key , defect_code , description , code_type , enabled  from  defcode";
	public Map<String, Object> queryDefectCode(Integer start , Integer limit) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<DefCode> resultList = new ArrayList<DefCode>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_DEFECT_CODE);
			buffer.append(" ORDER BY defcode_key asc  offset "+start+" LIMIT "+limit);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						DefCode defCode = new DefCode();
						defCode.setDefcodeKey(rs.getInt("defcode_key"));
						defCode.setDefectCode(rs.getString("defect_code"));
						defCode.setDescription(rs.getString("description"));
						defCode.setCodeType(rs.getInt("code_type"));
						defCode.setEnabled(rs.getBoolean("enabled"));
						resultList.add(defCode);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/*
	 * 
	 */
	private String INSERT_DEFECTCODE = "insert into defcode(defect_code , description , code_type , enabled)  values(?,?,?,?)";
	private String SELECT_COUNT_DEFECT_CODE="select count(defcode_key) from defcode where defect_code = ? ";
	public Map<String, Object> addDefectCode(final String defectCode, final String description, final Integer codeType, final Boolean enabled) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			sfdcsjdbcTemplate.query(SELECT_COUNT_DEFECT_CODE, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1,defectCode.trim());
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("count")>0) {
						map.put(message, failure);
						map.put(error, "This Defcode   is existence");
					}else{
						int i = sfdcsjdbcTemplate.update(INSERT_DEFECTCODE, new PreparedStatementSetter() {
							@Override
							public void setValues(PreparedStatement ps) {
								try {
									ps.setString(1, defectCode);
									ps.setString(2, description);
									ps.setInt(3, codeType);
									ps.setBoolean(4, enabled);
								} catch (Exception e) {
									logger.error("Exception : "+e.getMessage());
									map.put(message, failure);
									map.put(error, e.getMessage().toString());
								}
							}
						});
						if (i>0) {
							map.put(message, Success);
							map.put(data, "");
						}
					}
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/*
	 * 
	 * 
	 */
	private String UPDATE_DEFECTCODE = "update defcode set defect_code = ? , description = ? , code_type = ? ,  enabled = ?  where defcode_key = ?";
	public Map<String, Object> updateDefectCode(final String defectCode, final String description, final Integer codeType,
			final Boolean enabled, final Integer defcodeKey) {
		final Map<String, Object> map = new HashMap<String,Object>();
		try {	
			sfdcsjdbcTemplate.query(SELECT_COUNT_PROCESS, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1,defectCode.trim());
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("count")>0) {
						map.put(message, failure);
						map.put(error, "This Defcode   is existence");
					}else{
						int i = sfdcsjdbcTemplate.update(UPDATE_DEFECTCODE, new PreparedStatementSetter() {
							@Override
							public void setValues(PreparedStatement ps) throws SQLException{
									ps.setString(1, defectCode);
									ps.setString(2, description);
									ps.setInt(3, codeType);
									ps.setBoolean(4, enabled);
									ps.setInt(5, defcodeKey);
							}
						});
						if (i>0) {
							map.put(message, Success);
							map.put(data, "");
						}
					}
				}
			});
	
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/*
	 * 	delete defectCode
	 * 
	 */
	private String DELTE_DEFECTCODE = "delete from defcode where defcode_key = ?";
	public Map<String, Object> deleteDefectCode(final Integer defcodeKey) {
		final Map<String, Object> map = new HashMap<String,Object>();
		try {	
			int i = sfdcsjdbcTemplate.update(DELTE_DEFECTCODE, new PreparedStatementSetter() {
				public void setValues(PreparedStatement ps) throws SQLException{
						ps.setInt(1, defcodeKey);
				}
			});
			if (i>0) {
				map.put(message, Success);
				map.put(data, "");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}
	
	/**
	 * 
	 * search defectCode with defectcode or description or codeType or enable
	 * 
	 */
	public Map<String, Object> searchDefectCode(String defectCode, String description, Integer codeType,
			Integer enabledKey,Integer start , Integer limit) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<DefCode> resultList = new ArrayList<DefCode>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_DEFECT_CODE+"  where defect_code like '%" + defectCode.trim() + "%' and  description like '%"+description.trim()+"%' ");
			if (codeType >= 0) {
				buffer.append(" and  code_type= "+codeType);
			}if (enabledKey == 0) {
				buffer.append(" and enabled =  false");
			}if (enabledKey == 1) {
				buffer.append("  and enabled =  true");
			}
			buffer.append(" ORDER BY defcode_key asc  offset "+start+" LIMIT "+limit);
			System.out.println(buffer.toString());
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
						DefCode defCode = new DefCode();
						defCode.setDefcodeKey(rs.getInt("defcode_key"));
						defCode.setDefectCode(rs.getString("defect_code"));
						defCode.setDescription(rs.getString("description"));
						defCode.setCodeType(rs.getInt("code_type"));
						defCode.setEnabled(rs.getBoolean("enabled"));
						resultList.add(defCode);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	
	/**
	 * 
	 * get partNumberList total
	 */
	
	private String GET_PART_NUMBER_LIST_TOTAL="select count(part_key) as total from part";
	
	public Map<String, Object> getPartNumberListTotal() {
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(GET_PART_NUMBER_LIST_TOTAL);
			System.out.println(buffer);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
						map.put(message, Success);
						map.put(data, rs.getInt("total"));
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		/*	String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}

	@Override
	public Map<String, Object> getPartNumberListTotal(String partNumber, Integer obsoleteKey, Integer partType,
			Integer configMasterKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(GET_PART_NUMBER_LIST_TOTAL+ " where part_number like '%"+partNumber.trim()+"%'");
			if (obsoleteKey ==1) {
				buffer.append(" and obsolete=true");
			}else if (obsoleteKey == 0) {
				buffer.append("and obsolete=false");
			}
			if (partType!=-1) {
				buffer.append("  and part_type = "+partType );
			}if (configMasterKey>0) {
				buffer.append("  and config_master_key = " + configMasterKey );
			}
			System.out.println(buffer);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
						map.put(message, Success);
						map.put(data, rs.getInt("total"));
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		/*	String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}
	
	/**
	 * 
	 * get all part numbers
	 */
	private String GET_ALL_PART_NUMBERS="select part_key , part_number from part";
	public Map<String, Object> partNumbers() {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<Part> resultList = new ArrayList<Part>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(GET_ALL_PART_NUMBERS);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						Part part = new Part();
						part.setPartKey(rs.getInt("part_key"));
						part.setPartNumber(rs.getString("part_number"));
						resultList.add(part);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}
	
	/*
	 * get all partNumberList
	 * 
	 */
	//-------------//
	private String QUERY_PART_NUMBER_LIST = "select part_key , part_number , description , part_type , config_master_key  from part";
	public Map<String, Object> queryPartNumberList(Integer start, Integer limit) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<Part> resultList = new ArrayList<Part>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_PART_NUMBER_LIST + " ORDER BY part_key asc  offset "+start+" LIMIT "+limit);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						Part part = new Part();
						part.setPartKey(rs.getInt("part_key"));
						part.setPartNumber(rs.getString("part_number"));
						part.setDescription(rs.getString("description"));
						part.setPartType(rs.getInt("part_type"));
						part.setConfigMasterKey(rs.getInt("config_master_key"));
						resultList.add(part);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/*
	 * 	search part with partNumber ,  obsolete ,  partType , configMasterKey 
	 * 
	 */
	
	//--/////////
	public Map<String, Object>searchPartNumberList(String partNumber, Integer obsoleteKey, Integer partType,
			Integer configMasterKey, Integer start, Integer limit){
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<Part> resultList = new ArrayList<Part>();
		StringBuffer buffer = new StringBuffer();
		try {
				buffer.append(QUERY_PART_NUMBER_LIST+ " where part_number like '%"+partNumber.trim()+"%'");
				if (obsoleteKey ==1) {
					buffer.append(" and obsolete=true");
				}else if (obsoleteKey == 0) {
					buffer.append("and obsolete=false");
				}
				if (partType!=-1) {
					buffer.append("  and part_type = "+partType );
				}if (configMasterKey>0) {
					buffer.append("  and config_master_key = " + configMasterKey );
				}
				buffer.append(" ORDER BY part_key asc  offset "+start+" LIMIT "+limit);
				System.out.println("Select device and location sql is " + buffer.toString());
				logger.info("Select device and location sql is " + buffer.toString());
				sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
					public void processRow(ResultSet rs)  throws SQLException{
							Part part = new Part();
							part.setPartKey(rs.getInt("part_key"));
							part.setPartNumber(rs.getString("part_number"));
							part.setDescription(rs.getString("description"));
							part.setPartType(rs.getInt("part_type"));
							part.setConfigMasterKey(rs.getInt("config_master_key"));
							resultList.add(part);
							map.put(message, Success);
							map.put(data, resultList);
					}
				});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		
		return map;
	}

	/**
	 * 
	 *	 Add PartNumber 
	 */
	private String SELECT_COUNT_PART_NUMBER="select count(part_number) from part where part_number = ? ";
	
	private String INSERT_PART_NUMBER = "insert into part( part_number ,description ,part_type,"
			+ " obsolete, is_erp , base_flag, country, unit_start_location_key, part_pending, user_defined,"
			+ " trav_type , unit_id , quantity , model_number , part_op_type, family_id, customer_code,"
			+ " config_master_key, warranty_code, extended_part_number, length, width, weight, volume, height)  "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private String INSERT_PART_NUMBER_NO_PART ="insert into part( part_number ,description ,part_type,"
			+ " obsolete, is_erp , base_flag, country, unit_start_location_key, part_pending, user_defined,"
			+ " trav_type , unit_id , quantity , model_number , part_op_type, family_id, customer_code,"
			+ "  warranty_code, extended_part_number, length, width, weight, volume, height)  "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private String INSERT_PART_NUMBER_NO_PART_NO_LOCATION ="insert into part( part_number ,description ,part_type,"
			+ " obsolete, is_erp , base_flag, country,  part_pending, user_defined,"
			+ " trav_type , unit_id , quantity , model_number , part_op_type, family_id, customer_code,"
			+ "  warranty_code, extended_part_number, length, width, weight, volume, height)  "
			+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	
	
	public Map<String, Object> addPartNumber(final String partNumber, final String description, final Integer partType, final Boolean obsolete,
			final Boolean isErp, final Boolean baseFlag, final String country, final Integer unitStartLocationKey, final String partPending,
			final String userDefined, final Integer travelerType, final String unitId, final Integer quantity, final String modelNumber,
			final String partOpType, final String familyId, final String customerCode,final  Integer configMasterKey, final String warrantyCode,
			final String extendedPN, final Double length,final  Double width, final Double weight, final Double volume, final Double height) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			sfdcsjdbcTemplate.query(SELECT_COUNT_PART_NUMBER, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1,partNumber.trim());
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("count")>0) {
						map.put(message, failure);
						map.put(error, "This PartNumber "+partNumber.trim()+" is existence");
					}else{
						if (configMasterKey != 0) {
							System.out.println(INSERT_PART_NUMBER);
							int i = sfdcsjdbcTemplate.update(INSERT_PART_NUMBER, new PreparedStatementSetter() {
								@Override
								public void setValues(PreparedStatement ps) throws SQLException{
										ps.setString(1, partNumber.trim());
										ps.setString(2, description.trim());
										ps.setInt(3, partType);
										ps.setBoolean(4, obsolete);
										ps.setBoolean(5, isErp);
										ps.setBoolean(6, baseFlag);
										ps.setString(7, country.trim());
										ps.setInt(8, unitStartLocationKey);
										ps.setString(9, partPending.trim());
										ps.setString(10, userDefined.trim());
										ps.setInt(11, travelerType);
										ps.setString(12, unitId.trim());
										ps.setInt(13, quantity);
										ps.setString(14, modelNumber.trim());
										ps.setString(15, partOpType.trim());
										ps.setString(16, familyId.trim());
										ps.setString(17, customerCode.trim());
										ps.setInt(18, configMasterKey);
										ps.setString(19, warrantyCode.trim());
										ps.setString(20, extendedPN.trim());
										ps.setDouble(21, length);
										ps.setDouble(22, width);
										ps.setDouble(23,weight);
										ps.setDouble(24, volume);
										ps.setDouble(25, height);
								}
							});
							if (i>0) {
								map.put(message, Success);
								map.put(data, "");
						}
					}else {
						System.out.println(INSERT_PART_NUMBER_NO_PART);
						if (unitStartLocationKey > 0) {
							int i = sfdcsjdbcTemplate.update(INSERT_PART_NUMBER_NO_PART, new PreparedStatementSetter() {
								@Override
								public void setValues(PreparedStatement ps) throws SQLException{
										ps.setString(1, partNumber.trim());
										ps.setString(2, description.trim());
										ps.setInt(3, partType);
										ps.setBoolean(4, obsolete);
										ps.setBoolean(5, isErp);
										ps.setBoolean(6, baseFlag);
										ps.setString(7, country.trim());
										ps.setInt(8, unitStartLocationKey);
										ps.setString(9, partPending.trim());
										ps.setString(10, userDefined.trim());
										ps.setInt(11, travelerType);
										ps.setString(12, unitId.trim());
										ps.setInt(13, quantity);
										ps.setString(14, modelNumber.trim());
										ps.setString(15, partOpType.trim());
										ps.setString(16, familyId.trim());
										ps.setString(17, customerCode.trim());
									//	ps.setInt(17, configMasterKey);
										ps.setString(18, warrantyCode.trim());
										ps.setString(19, extendedPN.trim());
										ps.setDouble(20, length);
										ps.setDouble(21, width);
										ps.setDouble(22,weight);
										ps.setDouble(23, volume);
										ps.setDouble(24, height);
								}
							});
							if (i>0) {
								map.put(message, Success);
								map.put(data, "");
							}
						}else  {
							int i = sfdcsjdbcTemplate.update(INSERT_PART_NUMBER_NO_PART_NO_LOCATION, new PreparedStatementSetter() {
								@Override
								public void setValues(PreparedStatement ps) throws SQLException{
										ps.setString(1, partNumber.trim());
										ps.setString(2, description.trim());
										ps.setInt(3, partType);
										ps.setBoolean(4, obsolete);
										ps.setBoolean(5, isErp);
										ps.setBoolean(6, baseFlag);
										ps.setString(7, country.trim());
								//		ps.setInt(8, unitStartLocationKey);
										ps.setString(8, partPending.trim());
										ps.setString(9, userDefined.trim());
										ps.setInt(10, travelerType);
										ps.setString(11, unitId.trim());
										ps.setInt(12, quantity);
										ps.setString(13, modelNumber.trim());
										ps.setString(14, partOpType.trim());
										ps.setString(15, familyId.trim());
										ps.setInt(16, configMasterKey);
										ps.setString(17, warrantyCode.trim());
										ps.setString(18, extendedPN.trim());
										ps.setDouble(19, length);
										ps.setDouble(20, width);
										ps.setDouble(21,weight);
										ps.setDouble(22, volume);
										ps.setDouble(23, height);
								}
							});
							if (i>0) {
								map.put(message, Success);
								map.put(data, "");
							}
						}
					}
				}
			}
		});
			
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	
	/**
	 *  update PartNumber
	 * 
	 */
	
	private String SELECT_COUNT_PART_NUMBER2="select part_key from part where part_number = ? ";
	
	private String UPDATE_PART_NUMBER ="update part set part_number = ? , description = ? , part_type = ? , obsolete = ? , is_erp = ? , base_flag= ? , "
			+ " country = ? , unit_start_location_key = ? , part_pending = ? , user_defined = ? ,trav_type = ? , unit_id= ?, quantity = ?, model_number = ? ,"
			+ " part_op_type = ? ,family_id = ? , customer_code = ? , config_master_key = ? , warranty_code = ? , extended_part_number = ? ,"
			+ " length = ? , width = ? , weight = ? , volume = ? , height = ?  where part_key = ? ";
	
	private String UPDATE_PART_NUMBER_NO_PART ="update part set part_number = ? , description = ? , part_type = ? , obsolete = ? , is_erp = ? , base_flag= ? , "
			+ " country = ? , unit_start_location_key = ? , part_pending = ? , user_defined = ? ,trav_type = ? , unit_id= ?, quantity = ?, model_number = ? ,"
			+ " part_op_type = ? ,family_id = ? , customer_code = ? , warranty_code = ? , extended_part_number = ? ,"
			+ " length = ? , width = ? , weight = ? , volume = ? , height = ?  where part_key = ? ";
	private String UPDATE_PART_NUMBER_NO_PART_NO_LOCATION ="update part set part_number = ? , description = ? , part_type = ? , obsolete = ? , is_erp = ? , base_flag= ? , "
			+ " country = ? ,  part_pending = ? , user_defined = ? ,trav_type = ? , unit_id= ?, quantity = ?, model_number = ? ,"
			+ " part_op_type = ? ,family_id = ? , customer_code = ? ,  warranty_code = ? , extended_part_number = ? ,"
			+ " length = ? , width = ? , weight = ? , volume = ? , height = ?  where part_key = ? ";
	
	
	
	public Map<String, Object> updatePartNumber(final String partNumber, final String description, final Integer partType,
			final Boolean obsolete, final Boolean isErp, final Boolean baseFlag, final String country, final Integer unitStartLocationKey,
			final String partPending, final String userDefined, final Integer travelerType, final String unitId, final Integer quantity,
			final String modelNumber,final  String partOpType, final String familyId, final String customerCode,final  Integer configMasterKey,
			final String warrantyCode, final String extendedPN, final Double length, final Double width, final Double weight, final Double volume,
			final Double height, final Integer partKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			sfdcsjdbcTemplate.query(SELECT_COUNT_PART_NUMBER2, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1,partNumber.trim());
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("part_key")!=partKey) {
						map.put(message, failure);
						map.put(error, "This "+partNumber.trim()+"  is existence");
					}else{
						if (configMasterKey != 0) {
				System.out.println(UPDATE_PART_NUMBER);
				int i = sfdcsjdbcTemplate.update(UPDATE_PART_NUMBER, new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps) {
						try {
							ps.setString(1, partNumber.trim());
							ps.setString(2, description.trim());
							ps.setInt(3, partType);
							ps.setBoolean(4, obsolete);
							ps.setBoolean(5, isErp);
							ps.setBoolean(6, baseFlag);
							ps.setString(7, country.trim());
							ps.setInt(8, unitStartLocationKey);
							ps.setString(9, partPending.trim());
							ps.setString(10, userDefined.trim());
							ps.setInt(11, travelerType);
							ps.setString(12, unitId.trim());
							ps.setInt(13, quantity);
							ps.setString(14, modelNumber.trim());
							ps.setString(15, partOpType.trim());
							ps.setString(16, familyId.trim());
							ps.setString(17, customerCode.trim());
							ps.setInt(18, configMasterKey);
							ps.setString(19, warrantyCode.trim());
							ps.setString(20, extendedPN.trim());
							ps.setDouble(21, length);
							ps.setDouble(22, width);
							ps.setDouble(23,weight);
							ps.setDouble(24, volume);
							ps.setDouble(25, height);
							ps.setInt(26, partKey);
						} catch (Exception e) {
							System.out.println(e.getMessage());
							logger.error("Exception : "+e.getMessage());
							map.put(message, failure);
							map.put(error, e.getMessage().toString());
						}
					}
				});
				if (i>0) {
					map.put(message, Success);
					map.put(data, "");
				}
			}else {
				System.out.println(UPDATE_PART_NUMBER_NO_PART);
				if (unitStartLocationKey > 0) {
					int i = sfdcsjdbcTemplate.update(UPDATE_PART_NUMBER_NO_PART, new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) {
							try {
								ps.setString(1, partNumber.trim());
								ps.setString(2, description.trim());
								ps.setInt(3, partType);
								ps.setBoolean(4, obsolete);
								ps.setBoolean(5, isErp);
								ps.setBoolean(6, baseFlag);
								ps.setString(7, country.trim());
								ps.setInt(8, unitStartLocationKey);
								ps.setString(9, partPending.trim());
								ps.setString(10, userDefined.trim());
								ps.setInt(11, travelerType);
								ps.setString(12, unitId.trim());
								ps.setInt(13, quantity);
								ps.setString(14, modelNumber.trim());
								ps.setString(15, partOpType.trim());
								ps.setString(16, familyId.trim());
								ps.setString(17, customerCode.trim());
							//	ps.setInt(17, configMasterKey);
								ps.setString(18, warrantyCode.trim());
								ps.setString(19, extendedPN.trim());
								ps.setDouble(20, length);
								ps.setDouble(21, width);
								ps.setDouble(22,weight);
								ps.setDouble(23, volume);
								ps.setDouble(24, height);
								ps.setInt(25, partKey);
							} catch (Exception e) {
								System.out.println(e.getMessage());
								logger.error("Exception : "+e.getMessage());
								map.put(message, failure);
								map.put(error, e.getMessage().toString());
							}
						}
					});
					if (i>0) {
						map.put(message, Success);
						map.put(data, "");
					}
				}else  {
					System.out.println(UPDATE_PART_NUMBER_NO_PART_NO_LOCATION);
					int i = sfdcsjdbcTemplate.update(UPDATE_PART_NUMBER_NO_PART_NO_LOCATION, new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) {
							try {
								ps.setString(1, partNumber.trim());
								ps.setString(2, description.trim());
								ps.setInt(3, partType);
								ps.setBoolean(4, obsolete);
								ps.setBoolean(5, isErp);
								ps.setBoolean(6, baseFlag);
								ps.setString(7, country.trim());
						//		ps.setInt(8, unitStartLocationKey);
								ps.setString(8, partPending.trim());
								ps.setString(9, userDefined.trim());
								ps.setInt(10, travelerType);
								ps.setString(11, unitId.trim());
								ps.setInt(12, quantity);
								ps.setString(13, modelNumber.trim());
								ps.setString(14, partOpType.trim());
								ps.setString(15, familyId.trim());
								ps.setInt(16, configMasterKey);
								ps.setString(17, warrantyCode.trim());
								ps.setString(18, extendedPN.trim());
								ps.setDouble(19, length);
								ps.setDouble(20, width);
								ps.setDouble(21,weight);
								ps.setDouble(22, volume);
								ps.setDouble(23, height);
								ps.setInt(24, partKey);
							} catch (Exception e) {
								System.out.println(e.getMessage());
								logger.error("Exception : "+e.getMessage());
								map.put(message, failure);
								map.put(error, e.getMessage().toString());
							}
						}
					});
					if (i>0) {
						map.put(message, Success);
						map.put(data, "");
					}
				}
			}
					}
				}
			});
			
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/**
	 * delete PartNumber 
	 * 
	 */
	private String DELETE_PART_NUMBER = "delete from part  where part_key = ?";
	public Map<String, Object> deletePartNumber(final Integer partKey) {
		final Map<String, Object> map = new HashMap<String,Object>();
		try {	
			int i = sfdcsjdbcTemplate.update(DELETE_PART_NUMBER, new PreparedStatementSetter() {
				public void setValues(PreparedStatement ps) throws SQLException{
						ps.setInt(1, partKey);
				}
			});
			if (i>0) {
				map.put(message, Success);
				map.put(data, "");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/**
	 * 
	 *  get one partNumber all info 
	 */
	private String QUERY_PART_NUMBER = "select * from part ";
	public Map<String, Object> queryPartNumber(Integer partKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<Part> resultList = new ArrayList<Part>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_PART_NUMBER + " where part_key = " + partKey);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						Part part =new Part();
						part.setPartNumber(rs.getString("part_number"));
						part.setDescription(rs.getString("description"));
						part.setPartType(rs.getInt("part_type"));
						part.setObsolete(rs.getBoolean("obsolete"));
						part.setErp(rs.getBoolean("is_erp"));
						part.setBaseFlag(rs.getBoolean("base_flag"));
						part.setCountry(rs.getString("country"));
						part.setUnitStartLocationKey(rs.getInt("unit_start_location_key"));
						part.setPartPending(rs.getString("part_pending"));
						part.setUserDefined(rs.getString("user_defined"));
						part.setTravelerType(rs.getInt("trav_type"));
						part.setUnitId(rs.getString("unit_id"));
						part.setQuantity(rs.getInt("quantity"));
						part.setModelNumber(rs.getString("model_number"));
						part.setPartOpType(rs.getString("part_op_type"));
						part.setFamilyId(rs.getString("family_id"));
						part.setCustomerCode(rs.getString("customer_code"));
						part.setConfigMasterKey(rs.getInt("config_master_key"));
						part.setWarrantyCode(rs.getString("warranty_code"));
						part.setExtendedPN(rs.getString("extended_part_number"));
						part.setLength(rs.getDouble("length"));
						part.setWidth(rs.getDouble("width"));
						part.setWeight(rs.getDouble("weight"));
						part.setVolume(rs.getDouble("volume"));
						part.setHeight(rs.getDouble("height"));
						part.setRevnum(rs.getInt("revnum"));
						resultList.add(part);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		
		return map;
	}

	/**
	 * 
	 * get All PartFamily 
	 */
	private String QUERY_PART_FAMILY ="select part_number , part_key  from part where part_key = (select distinct config_master_key from part where config_master_key > 0) ";
	public Map<String, Object> queryPartFamily() {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<Part> resultList = new ArrayList<Part>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_PART_FAMILY);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						Part part = new Part();
						part.setPartNumber(rs.getString("part_number"));
						part.setPartKey(rs.getInt("part_key"));
						resultList.add(part);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	
	/**
	 * 
	 *  get partNumber  ,  description , revnum , revisionList 
	 */
	private String QUERY_PART_REVISION = "select  part_number,  revision_list  from part";
	public Map<String, Object> queryPartRevision(Integer partKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<PartRevision> resultLists = new ArrayList<PartRevision>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_PART_REVISION + " where part_key = " +partKey);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
						String revisionList = rs.getString("revision_list");
						if (!revisionList.trim().equals("")) {
							String[] aStrings = revisionList.split(",");
							for (int i = 0; i < aStrings.length; i++) {
								PartRevision partRevision = new PartRevision();
								partRevision.setRevisionNumber(aStrings[i]);
								resultLists.add(partRevision);
							}
						}
						map.put(message, Success);
						map.put(data, resultLists);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	
	/**
	 * 
	 * update partNumber revision
	 * 
	 */
	private String UPDATE_PART_REVISION = "update part set revnum = ? , revision_list = ?  where part_key = ? ";
	public Map<String, Object> updatePartRevision(final Integer revnum, final String revisionList, final Integer partKey) {
		final Map<String, Object> map = new HashMap<String,Object>();
		try {	
			int i = sfdcsjdbcTemplate.update(UPDATE_PART_REVISION, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException{
						ps.setInt(1, revnum);
						ps.setString(2, revisionList);
						ps.setInt(3, partKey);
				}
			});
			if (i>0) {
				map.put(message, Success);
				map.put(data, "");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/**
	 * 
	 * get total of assembly
	 */
	public String GET_ASSEMBLY_TOTAL="select count(assembly_key) as total"
			+ " from assembly a, part p, location l,  sfds_config sc"
			+ " where a.part_key=p.part_key "
			+ "and a.location_key=l.location_key"
			+ " and a.sfds_key=sc.sfds_key";
	public Map<String, Object> getAssemblyTotal() {
		final Map<String, Object> map = new HashMap<String,Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(GET_ASSEMBLY_TOTAL);
			logger.info("Select device and location sql is " + buffer.toString());
			System.out.println(GET_ASSEMBLY_TOTAL);
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						map.put(message, Success);
						map.put(data, rs.getInt("total"));
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	@Override
	public Map<String, Object> getAssemblyTotal(Integer sfdsKey, Integer locationKey, Integer partKey) {
		final Map<String, Object> map = new HashMap<String,Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(GET_ASSEMBLY_TOTAL);
			if (sfdsKey > 0) {
				buffer.append(" and a.sfds_key = " + sfdsKey);
			}if (locationKey > 0) {
				buffer.append(" and a.location_key = "+ locationKey);
			}if (partKey > 0) {
				buffer.append(" and a.part_key = "+ partKey);
			}
			System.out.println(buffer);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						map.put(message, Success);
						map.put(data, rs.getInt("total"));
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/**
	 * get Shop Floor ID , Location , Part , Step , Assembly Key
	 * 
	 */
	
	private String QUERY_ASSEMBLY = "select sc.sfds_id,a.assembly_key,a.revision, p.part_number, l.description, "
			+ "(select count(assembly_step_key) from assembly_step ap where ap.assembly_key = a.assembly_key)"
			+ " from assembly a, part p, location l,  sfds_config sc"
			+ " where a.part_key=p.part_key "
			+ " and a.location_key=l.location_key"
			+ " and a.sfds_key=sc.sfds_key ";
	
	public Map<String, Object> queryAssembly(Integer start,Integer limit) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<Assembly> resultList = new ArrayList<Assembly>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_ASSEMBLY);
			buffer.append(" ORDER BY a.assembly_key asc  offset "+start+ " limit "+limit);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						Assembly assembly= new Assembly();
						assembly.setAssemblyKey(rs.getInt("assembly_key"));
						assembly.setSfdsId(rs.getString("sfds_id"));
						assembly.setDescription(rs.getString("description"));
						assembly.setPartNumber(rs.getString("part_number"));
						assembly.setSteps(rs.getInt("count"));
						assembly.setRevision(rs.getString("revision"));
						resultList.add(assembly);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	
	
	
	 
	/*private String INSERT_ASSEMBLY2 = "insert into  assembly(sfds_key , location_key , part_key , revision) ";
	public Map<String, String> addAssembly2( final Integer sfdsKey,  final Integer locationKey,  final Integer partKey,  final String revision) {
		final Map<String, String> map = new HashMap<String, String>();
		StringBuffer buffer = new StringBuffer();
		final StringBuffer buffer2 = new StringBuffer() ;
		try {
			buffer.append("select * from assembly where  sfds_key="+sfdsKey+" and location_key = "+ locationKey + " and part_key = "+partKey + " and revision='"+revision.trim()+"'");
			logger.info("Select device and location sql is " + buffer.toString());
			System.out.println(buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  {
					try {
						if (rs.getInt("assembly_key") > 0) {
							map.put(message, failure);
							map.put(error, "This Assembly is exit ");
						}else {
							try {
								buffer2.append(INSERT_ASSEMBLY+" SELECT "+sfdsKey +","+locationKey +","+partKey+",'"+revision.trim()+"'");
								buffer2.append(" WHERE NOT EXISTS (SELECT assembly_key FROM assembly WHERE sfds_key ="+sfdsKey + " and  location_key = "+locationKey
										+ " and  part_key ="+partKey +" and revision = '"+revision.trim()+"') returning  assembly_key");
								System.out.println(buffer2);
								logger.info("Select device and location sql is " + buffer2.toString());
								sfdcsjdbcTemplate.query(buffer2.toString(), new RowCallbackHandler() {
									public void processRow(ResultSet rs)  {
										try {
											map.put(message, Success);
											map.put(data, String.valueOf(rs.getInt("assembly_key")));
										} catch (SQLException e) {
											logger.error("SQL Exception : "+e.getMessage());
											map.put(message, failure);
											map.put(error, "SQL Exception");
										}catch (Exception er) {
											System.out.println(er.getMessage());
										}
									}
								});
							} catch (Exception ex) {
								logger.error(ex.getMessage());
								map.put(message,failure );
								map.put(error, "Server exception");
							}
						}
					} catch (Exception  e) {
						logger.error("SQL Exception : "+e.getMessage());
						map.put(message, failure);
						map.put(error, "SQL Exception");
					}
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			map.put(message,failure );
			map.put(error, "Server exception");
		}
		
		return map;
	}
*/
	
	/*
	 *   another function insert_assembly
	 */
	private String SELECT_COUNT_ASSEMBLY="select count(assembly_key) from assembly where"
			+ " sfds_key = ? and location_key = ? and  part_key = ? and  revision = ?";
	private String INSERT_ASSEMBLY = "INSERT INTO assembly(sfds_key, location_key,part_key , revision) values(?,?,?,?) returning assembly_key";
	public Map<String, Object> addAssembly( final Integer sfdsKey,  final Integer locationKey,  final Integer partKey,  final String revision) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<Assembly> resultList = new ArrayList<Assembly>();
	try {
				logger.info("Check to see assembly  if it exists ");
				sfdcsjdbcTemplate.query(SELECT_COUNT_ASSEMBLY, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, sfdsKey);
					ps.setInt(2, locationKey);
					ps.setInt(3, partKey);
					ps.setString(4, revision.trim());
				}
				},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("count")>0) {
						map.put(message, failure);
						map.put(error, "This Assembly  is existence");
					}else{
						logger.info("start insert  assembly");
						int i = sfdcsjdbcTemplate.update( INSERT_ASSEMBLY, new PreparedStatementSetter() {
							@Override
							public void setValues(PreparedStatement ps) throws SQLException {
									ps.setInt(1, sfdsKey);
									ps.setInt(2, locationKey);
									ps.setInt(3, partKey);
									ps.setString(4, revision.trim());
							}
						},new RowCallbackHandler() {
							public void processRow(ResultSet rs)  throws SQLException{
								Assembly assembly = new Assembly();
								assembly.setAssemblyKey(rs.getInt("assembly_key"));
								resultList.add(assembly);
								map.put(message, Success);
								map.put(data, resultList);
						}
					});
						if (i>0) {
							map.put(message, Success);
							map.put(data, "");
						}
					}
				}
				});
				} catch (Exception ex) {
				logger.error(ex.getMessage());
				String aString = ex.getMessage();
				String bString  = aString.substring(aString.indexOf("Detail"));
				String c = bString.substring(0,bString.indexOf(";"));
				map.put(message,failure );
				map.put(error, c);
				}
				return map;
	}
	

	
	
	
	
	/**
	 * delete Assembly 
	 * 
	 */
	private String DELETE_ASSEMBLY = "delete from assembly where assembly_key = ?";
	public Map<String, Object> deleteAssembly(final Integer assemblyKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			int i = sfdcsjdbcTemplate.update("delete from assembly_step where assembly_key = ?", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, assemblyKey);
				}
			});
			System.out.println(i);
			int a = sfdcsjdbcTemplate.update(DELETE_ASSEMBLY, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException{
						ps.setInt(1, assemblyKey);
				}
			});
			if (a>0) {
				map.put(message, Success);
				map.put(data, "");
			}
			System.out.println(a);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/**
	 *   search Assembly with sfdsId , location , partNumber
	 * 
	 */

	@Override
	public Map<String, Object> searchAssembly(Integer sfdsKey, Integer locationKey, Integer partKey,Integer start,Integer limit) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<Assembly> resultList = new ArrayList<Assembly>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_ASSEMBLY);
			if (sfdsKey > 0) {
				buffer.append(" and a.sfds_key = " + sfdsKey);
			}if (locationKey > 0) {
				buffer.append(" and a.location_key = "+ locationKey);
			}if (partKey > 0) {
				buffer.append(" and a.part_key = "+ partKey);
			}
			buffer.append(" ORDER BY a.assembly_key asc  offset "+start+ " limit "+limit);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
					Assembly assembly= new Assembly();
					assembly.setAssemblyKey(rs.getInt("assembly_key"));
					assembly.setSfdsId(rs.getString("sfds_id"));
					assembly.setDescription(rs.getString("description"));
					assembly.setPartNumber(rs.getString("part_number"));
					assembly.setSteps(rs.getInt("count"));
					assembly.setRevision(rs.getString("revision"));
					resultList.add(assembly);
					map.put(message, Success);
					map.put(data, resultList);
			}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}
	
	
	
	/**
	 * 
	 *  get All AssemblyStep of an   Assembly
	 * 
	 */
	private String QUERY_ASSEMBLY_STEP ="select component_type , step , comp_id_prompt ,comp_id_mask , ref_designator , comp_part_mask , assembly_step_key "
			+ " from assembly_step ";
	public Map<String, Object> queryAssemblyStep(Integer assemblyKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<AssemblyStep> resultList = new ArrayList<AssemblyStep>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_ASSEMBLY_STEP + " where assembly_key = " + assemblyKey);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						AssemblyStep assemblyStep = new AssemblyStep();
						assemblyStep.setComponentType(rs.getInt("component_type"));
						assemblyStep.setStep(rs.getInt("step"));
						assemblyStep.setCompIdPrompt(rs.getString("comp_id_prompt"));
						assemblyStep.setCompIdMask(rs.getString("comp_id_mask"));
						assemblyStep.setCompIdMask(rs.getString("comp_id_mask"));
						assemblyStep.setRefDesignator(rs.getString("ref_designator"));
						assemblyStep.setCompPartMask(rs.getString("comp_part_mask"));
						assemblyStep.setAssemblyStepKey(rs.getInt("assembly_step_key"));
						resultList.add(assemblyStep);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		
		return map;
	}

	/**
	 * 
	 * 
	 */
	
	private String INSERT_ASSEMBLY_STEP = "insert into assembly_step (assembly_key , component_type , step , comp_id_prompt ,comp_id_mask,ref_designator,comp_part_mask) "
			+ " values (?,?,?,?,?,?,?)";
	private String SELECT_COUNT_ASSEMBLY_STEP = "SELECT COUNT(assembly_step_key) FROM assembly_step where step=?   and assembly_key = ?";
	
	
	public Map<String, Object> addAssemblyStep(final Integer assemblyKey, final Integer componentType, final Integer step,
			final String compIdPrompt, final String compIdMask, final String refDesignator, final String compPartMask) {
		final Map<String, Object> map = new HashMap<String, Object>();
			try {
				sfdcsjdbcTemplate.query(SELECT_COUNT_ASSEMBLY_STEP, new PreparedStatementSetter(){
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, step);
						ps.setInt(2, assemblyKey);
					}
				},new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						if (rs.getInt("count")>0) {
							map.put(message, failure);
							map.put(error, "This Step   is existence");
						}else{
							System.out.println(INSERT_ASSEMBLY_STEP);
							int i = sfdcsjdbcTemplate.update(INSERT_ASSEMBLY_STEP, new PreparedStatementSetter() {
								@Override
								public void setValues(PreparedStatement ps) throws SQLException{
										ps.setInt(1, assemblyKey);
										ps.setInt(2, componentType);
										ps.setInt(3, step);
										ps.setString(4, compIdPrompt);
										ps.setString(5, compIdMask);
										ps.setString(6, refDesignator);
										ps.setString(7, compPartMask);
								}
							});
							System.out.println(i);
							if (i>0) {
								map.put(message, Success);
								map.put(data, "");
							}
						}
					}
				});
			} catch (Exception ex) {
				logger.error(ex.getMessage());
				String aString = ex.getMessage();
				String bString  = aString.substring(aString.indexOf("Detail"));
				String c = bString.substring(0,bString.indexOf(";"));
				System.out.println(bString);
				map.put(message,failure );
				map.put(error, c);
			}
		
		return map;
	}

	/**
	 * 
	 * 
	 */
	
	private String UPDATE_ASSEMBLY_STEP=" update  assembly_step set  component_type = ? , step = ? ,"
			+ "comp_id_prompt = ? , comp_id_mask = ? , ref_designator = ?, comp_part_mask = ? "
			+ " where assembly_step_key = ?";
	private String SELECT_COUNT_ASSEMBLY_STEP_BY_STEP_KEY="SELECT COUNT(assembly_step_key) FROM assembly_step where step=?  and assembly_key = (select assembly_key FROM assembly_step where assembly_step_key= ?)";
	public Map<String, Object> updateAssemblyStep( final Integer componentType, final Integer step,
			final String compIdPrompt, final String compIdMask, final String refDesignator, final String compPartMask,
			final Integer assemblyStepKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			sfdcsjdbcTemplate.query(SELECT_COUNT_ASSEMBLY_STEP_BY_STEP_KEY, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, step);
					ps.setInt(2, assemblyStepKey);
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("count")>0) {
						map.put(message, failure);
						map.put(error, "This Step   is existence");
					}else{
						System.out.println(UPDATE_ASSEMBLY_STEP);
						int i = sfdcsjdbcTemplate.update(UPDATE_ASSEMBLY_STEP, new PreparedStatementSetter() {
							@Override
							public void setValues(PreparedStatement ps) throws SQLException{
									ps.setInt(1, componentType);
									ps.setInt(2, step);
									ps.setString(3, compIdPrompt);
									ps.setString(4, compIdMask);
									ps.setString(5, refDesignator);
									ps.setString(6, compPartMask);
									ps.setInt(7, assemblyStepKey);
							}
						});
						if (i>0) {
							map.put(message, Success);
							map.put(data, "");
						}
					}
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
	
	return map;
	}

	
	/**
	 * 
	 * 
	 */
	private String DELETE_ASSEMBLY_STEP="delete from assembly_step where assembly_step_key = ?";
	public Map<String, Object> deleteAssemblyStep(final Integer assemblyStepKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			int i = sfdcsjdbcTemplate.update(DELETE_ASSEMBLY_STEP, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException{
						ps.setInt(1, assemblyStepKey);
				}
			});
			if (i>0) {
				map.put(message, Success);
				map.put(data, "");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
	return map;
	}

	/**
	 * 
	 * get count of attribute by location
	 */
	private String GET_ATTR_BY_LOCATION_TOTAL="select count(ad.attr_def_key) as total"
			+" from attr_def  ad, part p, location l,  sfds_config sc "
			+" where ad.sfds_key=sc.sfds_key "
			+"  and ad.location_key=l.location_key"
			+"  and ad.part_key=p.part_key";
	public Map<String, Object> getAttrByLocationTotal() {
		final Map<String, Object> map = new HashMap<String,Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(GET_ATTR_BY_LOCATION_TOTAL);
			System.out.println(buffer);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						map.put(message, Success);
						map.put(data, rs.getInt("total"));
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	@Override
	public Map<String, Object> getAttrByLocationTotal(Integer sfdsKey, Integer locationKey, String partNumber) {
		final Map<String, Object> map = new HashMap<String,Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(GET_ATTR_BY_LOCATION_TOTAL);
			if (sfdsKey>0) {
				buffer.append(" and ad.sfds_key="+sfdsKey);
			}if (locationKey>0) {
				buffer.append(" and ad.location_key="+locationKey );
			}
			buffer.append( " and p.part_number like'%"+partNumber.trim()+"%'");
			System.out.println(buffer);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						map.put(message, Success);
						map.put(data, rs.getInt("total"));
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}
	
	
	/**
	 * 
	 *  get sfds_id , description , part_number , steps
	 */
	private String QUERY_ATT_BY_LOCATION="select ad.attr_def_key,sc.sfds_id,ad.revision, p.part_number, l.description, "
			+ " (select count(attr_def_step_key) from attr_def_step ads  where ads.attr_def_key = ad.attr_def_key) "
			+" from attr_def  ad, part p, location l,  sfds_config sc "
			+" where ad.sfds_key=sc.sfds_key "
			+"  and ad.location_key=l.location_key"
			+"  and ad.part_key=p.part_key";
		
	public Map<String, Object> queryAttrByLocation(Integer start,Integer limit) {
		final Map<String, Object> map = new HashMap<>();
		final List<AttributeDef> resultList = new ArrayList<AttributeDef>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_ATT_BY_LOCATION);
			buffer.append(" ORDER BY ad.attr_def_key  asc  offset "+start +" limit "+ limit );
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						AttributeDef  attributeDef = new AttributeDef();
						attributeDef.setAttrDefKey(rs.getInt("attr_def_key"));
						attributeDef.setSfdsId(rs.getString("sfds_id"));
						attributeDef.setDescription(rs.getString("description"));
						attributeDef.setPartNumber(rs.getString("part_number"));
						attributeDef.setSteps(rs.getInt("count"));
						attributeDef.setRevision(rs.getString("revision"));
						resultList.add(attributeDef);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/**
	 * 
	 * 
	 * 
	 */
	private String  ATTR_BY_LOCATION_COUNT="select count(attr_def_key) from attr_def where sfds_key = ? and location_key =?  and part_key = ? and revision = ?";
	private String INSERT_ATTR_BY_LOCATION = "INSERT INTO attr_def(sfds_key, location_key,part_key , revision) values(?,?,?,?)  returning  attr_def_key";
	@Override
	public Map<String, Object> addAttrByLocation(final Integer sfdsKey, final Integer locationKey, final Integer partKey,
			final String revision) {
	final Map<String, Object> map = new HashMap<String, Object>();
	final List<AttributeDef> resultList = new ArrayList<AttributeDef>();
	try {
			logger.info("Check to see assembly  if it exists ");
			sfdcsjdbcTemplate.query(ATTR_BY_LOCATION_COUNT, new PreparedStatementSetter(){
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, sfdsKey);
				ps.setInt(2, locationKey);
				ps.setInt(3, partKey);
				ps.setString(4, revision.trim());
			}
			},new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				if (rs.getInt("count")>0) {
					map.put(message, failure);
					map.put(error, "This Attribute  is existence");
				}else{
					logger.info("start insert  attribute");
					int i = sfdcsjdbcTemplate.update( INSERT_ATTR_BY_LOCATION, new PreparedStatementSetter() {
						@Override
						public void setValues(PreparedStatement ps) throws SQLException {
								ps.setInt(1, sfdsKey);
								ps.setInt(2, locationKey);
								ps.setInt(3, partKey);
								ps.setString(4, revision.trim());
						}
					},new RowCallbackHandler() {
						public void processRow(ResultSet rs)  throws SQLException{
							AttributeDef attributeDef = new AttributeDef();
							attributeDef.setAttrDefKey(rs.getInt("attr_def_key"));
							resultList.add(attributeDef);
							map.put(message, Success);
							map.put(data, resultList);
					}
				});
					if (i>0) {
						map.put(message, Success);
						map.put(data, "");
					}
				}
			}
			});
			} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			map.put(message,failure );
			map.put(error, c);
			}
			return map;
	}
	
	
	
	/**
	 * 
	 *   seacrh  attrByLocation with  sfdsKey , locationKey , partNumber
	 */
			
			
	public Map<String, Object> searchAttrByLocation(Integer sfdsKey, Integer locationKey, String partNumber,Integer start,Integer limit) {
		final Map<String, Object> map = new HashMap<>();
		final List<AttributeDef> resultList = new ArrayList<AttributeDef>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_ATT_BY_LOCATION);
			if (sfdsKey>0) {
				buffer.append(" and ad.sfds_key="+sfdsKey);
			}if (locationKey>0) {
				buffer.append(" and ad.location_key="+locationKey );
			}
			buffer.append( " and p.part_number like'%"+partNumber.trim()+"%'");
			buffer.append(" ORDER BY ad.attr_def_key  asc  offset "+start +" limit "+ limit );
			logger.info("Select device and location sql is " + buffer.toString());
			System.out.println(buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						AttributeDef  attributeDef = new AttributeDef();
						attributeDef.setAttrDefKey(rs.getInt("attr_def_key"));
						attributeDef.setSfdsId(rs.getString("sfds_id"));
						attributeDef.setDescription(rs.getString("description"));
						attributeDef.setPartNumber(rs.getString("part_number"));
						attributeDef.setSteps(rs.getInt("count"));
						attributeDef.setRevision(rs.getString("revision"));
						resultList.add(attributeDef);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/**
	 * 
	 * delete Attribute
	 * 
	 */
	public Map<String, Object> deleteAttrByLocation(final Integer attrDefKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			int i = sfdcsjdbcTemplate.update("delete from attr_def_step  where attr_def_key = ?", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException{
						ps.setInt(1, attrDefKey);
				}
			});
			int a = sfdcsjdbcTemplate.update(" delete from attr_def  where attr_def_key = ?", new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException{
						ps.setInt(1, attrDefKey);
				}
			});
			if (a>0) {
				map.put(message, Success);
				map.put(data, "");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	
	/**
	 * 
	 * get count of attribute by process
	 */
	private String GET_ATTR_BY_PROCESS_TOTAL="select count(ad.attr_def_key) as total "
			+" from attr_def  ad, part p, process  pr,  sfds_config sc "
			+" where ad.sfds_key=sc.sfds_key "
			+"  and ad.process_key=pr.process_key"
			+"  and ad.part_key=p.part_key";
	public Map<String, Object> getAttrByProcessTotal() {
		final Map<String, Object> map = new HashMap<String,Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(GET_ATTR_BY_PROCESS_TOTAL);
			System.out.println(buffer);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						map.put(message, Success);
						map.put(data, rs.getInt("total"));
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	@Override
	public Map<String, Object> getAttrByProcessTotal(Integer sfdsKey, String processName, String partNumber) {
		final Map<String, Object> map = new HashMap<String,Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(GET_ATTR_BY_PROCESS_TOTAL);
			if (sfdsKey>0) {
				buffer.append(" and ad.sfds_key="+sfdsKey);
			}
			buffer.append(" and pr.process_name like '%"+processName.trim()+"%'" );
			buffer.append( " and p.part_number like'%"+partNumber.trim()+"%'");
			System.out.println(buffer);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						map.put(message, Success);
						map.put(data, rs.getInt("total"));
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}
	
	/**
	 * 
	 * 
	 * 
	 */
	private String QUERY_ATT_BY_PROCESS="select ad.attr_def_key,sc.sfds_id,ad.revision, p.part_number, pr.process_name, "
			+ " (select count(attr_def_step_key) from attr_def_step ads  where ads.attr_def_key = ad.attr_def_key) "
			+" from attr_def  ad, part p, process  pr,  sfds_config sc "
			+" where ad.sfds_key=sc.sfds_key "
			+"  and ad.process_key=pr.process_key"
			+"  and ad.part_key=p.part_key";
	
	public Map<String, Object> queryAttrByProcess(Integer start,Integer limit) {
		final Map<String, Object> map = new HashMap<>();
		final List<AttributeDef> resultList = new ArrayList<AttributeDef>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_ATT_BY_PROCESS);
			buffer.append(" ORDER BY ad.attr_def_key  asc  offset "+start +" limit "+ limit );
			logger.info("Select device and location sql is " + buffer.toString());
			System.out.println(buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						AttributeDef  attributeDef = new AttributeDef();
						attributeDef.setAttrDefKey(rs.getInt("attr_def_key"));
						attributeDef.setSfdsId(rs.getString("sfds_id"));
						attributeDef.setProcessName(rs.getString("process_name"));
						attributeDef.setPartNumber(rs.getString("part_number"));
						attributeDef.setSteps(rs.getInt("count"));
						attributeDef.setRevision(rs.getString("revision"));
						resultList.add(attributeDef);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/**
	 *  add Attribute By Process (sfdsId, processName,partNumber,revision)
	 * 
	 */
	private String INSERT_ATTR_BY_PROCESS = "INSERT INTO attr_def(sfds_key, process_key,part_key , revision) ";
	private String SELECT_COUNT_ATTR_PROCESS="SELECT COUNT(attr_def_key)  from attr_def where sfds_key = ? and process_key = ? and part_key =? and revision=? ";
	public Map<String, Object> addAttrByProcess(final Integer sfdsKey, final Integer processKey, final Integer partKey, final String revision) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final StringBuffer buffer = new StringBuffer();
		try {
			sfdcsjdbcTemplate.query(SELECT_COUNT_ATTR_PROCESS, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, sfdsKey);
					ps.setInt(2, processKey);
					ps.setInt(3, partKey);
					ps.setString(4, revision.trim());
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("count")>0) {
						map.put(message, failure);
						map.put(error, "This  ATTR    is existence");
					}else{
						buffer.append(INSERT_ATTR_BY_PROCESS+" SELECT "+sfdsKey +","+processKey +","+partKey+",'"+revision.trim()+"'");
						buffer.append(" WHERE NOT EXISTS (SELECT attr_def_key FROM attr_def WHERE sfds_key ="+sfdsKey + " and  process_key = "+processKey
								+ " and  part_key ="+partKey +" and revision = '"+revision.trim()+"') returning  attr_def_key");
						System.out.println(buffer);
						logger.info("Select device and location sql is " + buffer.toString());
						sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
							public void processRow(ResultSet rs)  throws SQLException{
									map.put(message, Success);
									map.put(data, rs.getInt("attr_def_key"));
							}
						});
					}
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/**
	 * 
	 * search Attribute with sfdsId, processName , partNumber
	 */
	public Map<String, Object> searchAttrByProcess(Integer sfdsKey, String processName, String partNumber,Integer start,Integer limit) {
		final Map<String, Object> map = new HashMap<>();
		final List<AttributeDef> resultList = new ArrayList<AttributeDef>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_ATT_BY_PROCESS);
			if (sfdsKey>0) {
				buffer.append(" and ad.sfds_key="+sfdsKey);
			}
			buffer.append(" and pr.process_name like '%"+processName.trim()+"%'" );
			buffer.append( " and p.part_number like'%"+partNumber.trim()+"%'");
			buffer.append(" ORDER BY ad.attr_def_key  asc  offset "+start +" limit "+ limit );
			logger.info("Select device and location sql is " + buffer.toString());
			System.out.println(buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
						AttributeDef  attributeDef = new AttributeDef();
						attributeDef.setAttrDefKey(rs.getInt("attr_def_key"));
						attributeDef.setSfdsId(rs.getString("sfds_id"));
						attributeDef.setProcessName(rs.getString("process_name"));
						attributeDef.setPartNumber(rs.getString("part_number"));
						attributeDef.setSteps(rs.getInt("count"));
						attributeDef.setRevision(rs.getString("revision"));
						resultList.add(attributeDef);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}
	
	
	
	/**
	 * 
	 * 
	 */
	
	private String QUERY_ATTR_GROUP_ATTR_NAME="select agan.attr_group_attr_name_key ,an.attr_name_key, an.name , an.user_prompt, ag.attr_group_key,ag.group_name "
			+ " from attr_group_attr_name agan , attr_group ag ,  attr_name an  "
			+ " where agan.attr_group_key = ag.attr_group_key "
			+ " and agan.attr_name_key = an.attr_name_key ";
	
	public Map<String, Object> queryAttrGroupAttrName() {
		final Map<String, Object> map = new HashMap<>();
		final List<AttrGroupAttrName> resultList = new ArrayList<AttrGroupAttrName>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_ATTR_GROUP_ATTR_NAME);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						AttrGroupAttrName attrGroupAttrName = new AttrGroupAttrName();
						attrGroupAttrName.setAttrGroupAttrNameKey(rs.getInt("attr_group_attr_name_key"));
						attrGroupAttrName.setAttrNameKey(rs.getInt("attr_name_key"));
						attrGroupAttrName.setAttrGroupKey(rs.getInt("attr_group_key"));
						attrGroupAttrName.setAttributeName(rs.getString("name"));
						attrGroupAttrName.setUserPrompt(rs.getString("user_prompt"));
						attrGroupAttrName.setGroupName(rs.getString("group_name"));
						resultList.add(attrGroupAttrName);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}
	
	
	/**
	 * 
	 *   add attributeName , userPrompt to attr_name
	 *   add groupName to attr_group
	 *   use their key  insert attr_group_attr_name
	 * 
	 */

	
	private String SEARCH_COUNT_OF_ATT_NAM_ATT_GROUP="select count(attr_group_attr_name_key)  "
			+ " from attr_group_attr_name agan , attr_group ag ,  attr_name an  "
			+ " where agan.attr_group_key = ag.attr_group_key "
			+ " and agan.attr_name_key = an.attr_name_key ";
	
	public Map<String, Object> addAttrGroupAttrName(final String attributeName, final String userPrompt,  final String groupName) {
		final Map<String, Object> map = new HashMap<String,Object>();
		StringBuffer buffer = new StringBuffer();
	    final	AttrGroupAttrName attrGroupAttrName = new AttrGroupAttrName();
		try {
			buffer.append(SEARCH_COUNT_OF_ATT_NAM_ATT_GROUP + " and an.name = '"+attributeName.trim()+"'");
			buffer.append(" and an.user_prompt = '"+userPrompt.trim()+"'");
			buffer.append(" and ag.group_name = '"+groupName.trim()+"'");
			logger.info("Verify existence attrNameAttrGroup" + buffer.toString());
			System.out.println("Verify existence attrNameAttrGroup"+buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("count")>0) {
						 map.put(message, failure);
						 map.put(error, "This Attribute  Name  exitance");
					}else {
							StringBuffer buffer1 = new StringBuffer();
							try{
								buffer1.append("select count(attr_name_key)  , max(attr_name_key)  from attr_name where name = '"
										+attributeName.trim()+"'");
								System.out.println(buffer1);
								logger.info("Verify existence attrName:"+buffer1.toString());
								System.out.println("Verify existence attrName:"+buffer1.toString());
								sfdcsjdbcTemplate.query(buffer1.toString(), new RowCallbackHandler(){
									public void processRow(ResultSet rs) throws SQLException  {
										 if (rs.getInt("count")>0) {
											 attrGroupAttrName.setAttrNameKey(rs.getInt("max"));
											 System.out.println("nameKey:"+attrGroupAttrName.getAttrNameKey());
											 logger.info("exitance attrName");
										}else{
											StringBuffer buffer01 = new StringBuffer();
											buffer01.append("insert into attr_name(name,user_prompt) values('"+attributeName.trim()+"','"+userPrompt.trim()+"') "+" returning  attr_name_key");
											 System.out.println(buffer01.toString());
												logger.info("start insert new attrName:"+buffer01.toString());
											 sfdcsjdbcTemplate.query(buffer01.toString(), new RowCallbackHandler(){
												 public void processRow(ResultSet rs) throws SQLException  {
														 attrGroupAttrName.setAttrNameKey(rs.getInt("attr_name_key"));
														 System.out.println("nameKey:"+attrGroupAttrName.getAttrNameKey());
												 }
											 });
										}
									}
								});
							}catch (Exception ex) {
								logger.error(ex.getMessage());
								String aString = ex.getMessage();
								String bString  = aString.substring(aString.indexOf("Detail"));
								String c = bString.substring(0,bString.indexOf(";"));
								System.out.println(bString);
								map.put(message,failure );
								map.put(error, c);
							}
							StringBuffer buffer2 = new StringBuffer();
							try{
								buffer2.append("select count(attr_group_key)  , max(attr_group_key) from attr_group where group_name = '"+groupName.trim()+"'");
								sfdcsjdbcTemplate.query(buffer2.toString(), new RowCallbackHandler(){
									public void processRow(ResultSet rs) throws SQLException  {
										 if (rs.getInt("count")>0) {
											 attrGroupAttrName.setAttrGroupKey(rs.getInt("max"));
											 System.out.println("groupKey:"+attrGroupAttrName.getAttrGroupKey());
										}else{
											StringBuffer buffer02 = new StringBuffer();
											buffer02.append("insert into attr_group(group_name)  values('"+groupName.trim()+"') "+" returning  attr_group_key");
											 System.out.println(buffer02.toString());
											 sfdcsjdbcTemplate.query(buffer02.toString(), new RowCallbackHandler(){
												 public void processRow(ResultSet rs) throws SQLException  {
														 attrGroupAttrName.setAttrGroupKey(rs.getInt("attr_group_key"));
														 System.out.println("groupKey:"+attrGroupAttrName.getAttrGroupKey());
												 }
											 });
										}
										
									}
								});
								 
							}catch (Exception ex) {
								logger.error(ex.getMessage());
								String aString = ex.getMessage();
								String bString  = aString.substring(aString.indexOf("Detail"));
								String c = bString.substring(0,bString.indexOf(";"));
								System.out.println(bString);
								map.put(message,failure );
								map.put(error, c);
							}
								
							StringBuffer buffer3  = new StringBuffer();
							try{
								 buffer3.append("insert into attr_group_attr_name(attr_group_key,attr_name_key) values("+attrGroupAttrName.getAttrGroupKey()+","+attrGroupAttrName.getAttrNameKey()+")"+" returning attr_group_attr_name_key");
								 System.out.println(buffer3.toString());
								 sfdcsjdbcTemplate.query(buffer3.toString(), new RowCallbackHandler(){
									 public void processRow(ResultSet rs) throws SQLException  {
											 attrGroupAttrName.setAttrGroupAttrNameKey(rs.getInt("attr_group_attr_name_key"));
											 map.put(message, Success);
											 map.put(data, attrGroupAttrName.getAttrGroupAttrNameKey());
									 }
								 });
							}catch (Exception ex) {
								logger.error(ex.getMessage());
								String aString = ex.getMessage();
								String bString  = aString.substring(aString.indexOf("Detail"));
								String c = bString.substring(0,bString.indexOf(";"));
								System.out.println(bString);
								map.put(message,failure );
								map.put(error, c);
							}
						}
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}
	/**
	 * 
	 * 
	 */
	private String SELECT_COUNT_ATTR_NAME = "select count(attr_name_key)  from attr_name where name=? ";
	private String INSERT_ATTR_NAME ="insert into attr_name(name,user_prompt)  values(?,?)   returning attr_name_key";
	private String SELECT_COUNT_ATTR_GROUP = "select count(attr_group_key) , max(attr_group_key) from attr_group where group_name = ?";
	private String INSERT_ATTR_GROUP ="insert into attr_group(group_name) values(?)  returning  attr_group_key";
	private String UPDATE_ATTR_NAME_ATTR_GROUP = "update attr_group_attr_name set attr_name_key = ? , attr_group_key = ?  where attr_group_attr_name_key = ?";
	
	public Map<String, Object> updateAttrGroupAttrName(final String attributeName, final String userPrompt, final String groupName,
			final Integer attrGroupAttrNameKey) {
		final Map<String, Object> map = new HashMap<String,Object>();
		final AttrGroupAttrName a = new AttrGroupAttrName();
		try {
			sfdcsjdbcTemplate.query(SELECT_COUNT_ATTR_NAME, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1,attributeName.trim());
					
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.getInt("count")>0) {
						map.put(message, failure);
						map.put(error, "This  Attribute Name   is existence");
					}else{
						logger.info("start insert new Attribute Name");
						sfdcsjdbcTemplate.query(INSERT_ATTR_NAME,new PreparedStatementSetter(){
							public void setValues(PreparedStatement ps) throws SQLException {
								ps.setString(1,attributeName.trim());
								ps.setString(2, userPrompt.trim());
							}
						}, new RowCallbackHandler() {
							public void processRow(ResultSet rs) throws SQLException {
								a.setAttrNameKey(rs.getInt("attr_name_key"));
								System.out.println("attrNameKey:"+a.getAttrNameKey());
								sfdcsjdbcTemplate.query(SELECT_COUNT_ATTR_GROUP, new PreparedStatementSetter(){
									public void setValues(PreparedStatement ps) throws SQLException {
										ps.setString(1,groupName.trim());
									}
								},new RowCallbackHandler() {
									public void processRow(ResultSet rs) throws SQLException {
										if (rs.getInt("count")>0) {
												a.setAttrGroupKey(rs.getInt("max"));
										}else{
											sfdcsjdbcTemplate.query(INSERT_ATTR_GROUP,new PreparedStatementSetter(){
												public void setValues(PreparedStatement ps) throws SQLException {
													ps.setString(1,groupName.trim());
												}
											}, new RowCallbackHandler() {
												public void processRow(ResultSet rs) throws SQLException {
													a.setAttrGroupKey(rs.getInt("attr_group_key"));
												}
											});
										}
									}
								});
								System.out.println("attrGroupKey"+a.getAttrGroupKey());
								int i = sfdcsjdbcTemplate.update(UPDATE_ATTR_NAME_ATTR_GROUP,new PreparedStatementSetter(){
									public void setValues(PreparedStatement ps) throws SQLException {
										ps.setInt(1, a.getAttrNameKey());
										ps.setInt(2, a.getAttrGroupKey());
										ps.setInt(3, attrGroupAttrNameKey);
									}
								});
								if (i>0) {
									map.put(message, Success);
								}
							}
						});
					}
				}
			});
			
			
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}
/*	
	public Map<String, Object> updateAttrGroupAttrName(String attributeName, String userPrompt, String groupName,
			Integer attrNameKey, Integer attrGroupKey) {
		Map<String, Object> map = new HashMap<String,Object>();
		String sqlAttrName ="update  attr_name set name='"+attributeName.trim()+"',"
				+"user_prompt='"+userPrompt.trim()+"' where attr_name_key="+attrNameKey; 
		String sqlAttrGroup = "update  attr_group set group_name='"+groupName+"' where attr_group_key="+attrGroupKey ;
		try {
			System.out.println(sqlAttrName);
			int i = sfdcsjdbcTemplate.update(sqlAttrName);
			try {
				System.out.println(sqlAttrGroup);
				int a = sfdcsjdbcTemplate.update(sqlAttrGroup);
				if (a>0) {
					map.put(message, Success);
					map.put(data, "");
				}
			} catch (Exception e) {
				map.put(message, failure);
				map.put(error, e.getMessage());
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String a = ex.getMessage();
			String bString  = a.substring(a.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}*/
	

	/**
	 * 
	 *  delete AttrGroupAttrName
	 */
	
	public String DELETE_ATTR_GROUP_ATTR_NAME ="";
	public Map<String, Object> deleteAttrGroupAttrName(Integer attrGroupAttrNameKey) {
		final Map<String, Object> map = new HashMap<String,Object>();
		String sql = "delete from attr_group_attr_name where attr_group_attr_name_key = "+attrGroupAttrNameKey;
		try {
			System.out.println(sql);
			int a = sfdcsjdbcTemplate.update(sql);
			if (a>0) {
				map.put(message, Success);
				map.put(data, "");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/**
	 * 
	 *  seacrh all AttrStep with one attrbutie
	 */
	public String QUERY_ATTR_STEP = "select ads.attr_def_step_key ,  ads.step , ads.released , ads.attr_name_key,ads.attr_group_key,ads.attr_mask, ag.group_name , an.name , an.user_prompt "
			+ " from attr_def_step ads , attr_group ag , attr_name an, attr_def ad "
			+ " where ads.attr_def_key = ad.attr_def_key "
			+" and ads.attr_group_key = ag.attr_group_key "
			+" and ads.attr_name_key = an.attr_name_key ";
	public Map<String, Object> queryAttrStep(Integer attrDefKey) {
		final Map<String, Object> map = new HashMap<>();
		final List<AttributeDefStep> resultList = new ArrayList<AttributeDefStep>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_ATTR_STEP+" and ads.attr_def_key = "+attrDefKey);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs)  throws SQLException{
						AttributeDefStep attributeDefStep = new AttributeDefStep();
						attributeDefStep.setAttrDefStepKey(rs.getInt("attr_def_step_key")); 
						attributeDefStep.setAttrNameKey(rs.getInt("attr_name_key"));
						attributeDefStep.setAttrGroupKey(rs.getInt("attr_group_key"));
						attributeDefStep.setStep(rs.getInt("step"));
						attributeDefStep.setReleased(rs.getBoolean("released"));
						attributeDefStep.setGroupName(rs.getString("group_name"));
						attributeDefStep.setAttributeName(rs.getString("name"));
						attributeDefStep.setUserPrompt(rs.getString("user_prompt"));
						attributeDefStep.setAttrMask(rs.getString("attr_mask"));
						resultList.add(attributeDefStep);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	public String ADD_ATTR_STEP="insert into attr_def_step(attr_def_key,step,released,attr_name_key,attr_group_key,attr_mask_type,attr_mask) ";
	public Map<String, Object> addAttrStep(Integer attrDefKey, Integer step, Boolean released, Integer attrNameKey,
			Integer attrGroupKey, Integer attrMaskType, String attrMask) {
		final Map<String, Object> map = new HashMap<String, Object>();
		String sql = ADD_ATTR_STEP +"SELECT "+ attrDefKey +","+step+","+released+","+attrNameKey+","+attrGroupKey+","+attrMaskType+",'"+attrMask.trim()+"'"
						+ "  WHERE NOT EXISTS (SELECT attr_def_step_key FROM attr_def_step WHERE step="+step+" and attr_def_key="+attrDefKey+")";
		logger.info("sql:"+sql);
		System.out.println(sql);
		try {
			int i = sfdcsjdbcTemplate.update(sql);
			System.out.println(i);
			if (i>0) {
				map.put(message, Success);
				map.put(data, "");
			}if (i==0) {
				map.put(message,failure );
				map.put(error, "This  Attr Step is existence");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	public String UPDATE_ATTR_STEP=" update attr_def_step set step =? , released =? , attr_name_key = ?, attr_group_key =? , attr_mask_type =? , attr_mask =?  where attr_def_step_key = ? ";

	public String SELECT_COUNT_ATTR_STEP="";
	public Map<String, Object> updateAttrStep( final Integer step, final Boolean released, final Integer attrNameKey,
			final Integer attrGroupKey, final Integer attrMaskType, final String attrMask, final Integer attrDefStepKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			int i = sfdcsjdbcTemplate.update(UPDATE_ATTR_STEP, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException{
						ps.setInt(1, step);
						ps.setBoolean(2, released);
						ps.setInt(3, attrNameKey);
						ps.setInt(4, attrGroupKey);
						ps.setInt(5, attrMaskType);
						ps.setString(6, attrMask);
						ps.setInt(7, attrDefStepKey);
				}
			});
			if (i>0) {
				map.put(message, Success);
				map.put(data, "");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	public String DELETE_ATTR_STEP =" delete from attr_def_step where attr_def_step_key = ?";
	public Map<String, Object> deleteAttrStep(final Integer attrDefStepKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		try {
			int i = sfdcsjdbcTemplate.update(DELETE_ATTR_STEP, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException{
						ps.setInt(1, attrDefStepKey);
				}
			});
			if (i>0) {
				map.put(message, Success);
				map.put(data, "");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	
	
	private String  GET_LOCATION_INFO_TOTAL="select count(location_key) as total from location  ";
	public Map<String, Object> getLocationInfoTotal() {
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(GET_LOCATION_INFO_TOTAL);
			System.out.println(buffer);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
						map.put(message, Success);
						map.put(data, rs.getInt("total"));
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		/*	String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}

	@Override
	public Map<String, Object> getLocationInfoTotal(Integer sfdsKey, String description, Integer unitStatusKey,
			Integer processKey,Integer deptKey) {
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(GET_LOCATION_INFO_TOTAL);
			if (sfdsKey > 0) {
				buffer.append(" and e.sfds_key = "+ sfdsKey);
			}if (unitStatusKey > 0) {
				buffer.append(" and  e.unit_status_key = "+ unitStatusKey);
			}if (processKey > 0) {
				buffer.append(" and e.process_key = " + processKey);
			}if (deptKey > 0) {
				buffer.append("  and e.dept_key = "+ deptKey);
			}
			System.out.println(buffer);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
						map.put(message, Success);
						map.put(data, rs.getInt("total"));
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		/*	String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}

	
	private String SEARCH_LOCATION_WITH_SFDS_KEY="select l.location_key,  l.description from location l , sfds_config sc where l.sfds_key = sc.sfds_key and l.sfds_key = ?";
	public Map<String, Object> searchLocationWithSfdsKey(final Integer sfdsKey) {
		final Map<String, Object> map = new HashMap<String,Object>();
		final List<LocationInfo> resultList = new ArrayList<LocationInfo>();
		try {	
			sfdcsjdbcTemplate.query(SEARCH_LOCATION_WITH_SFDS_KEY, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, sfdsKey);
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					LocationInfo location = new LocationInfo();
					location.setDescription(rs.getString("description"));
					location.setLocationKey(rs.getInt("location_key"));
					resultList.add(location);
					map.put(message, Success);
					map.put(data, resultList);
				}
			});

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	
	/**
	 * 
	 * 
	 * 
	 */
	private String SELECT_COUNT_SET_UP_DATA="select count(setup_data_key) from setup_data where sfds_key =?";
	private String DELETE_SET_UP_DATA ="delete from setup_data where sfds_key = ?";
	public Map<String, Object> addSetUpData(final Integer sfdsKey,  final String item) {
		final Map<String, Object> map = new HashMap<String,Object>();
		final String[] items = item.split("&");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into setup_data(sfds_key,line_num,item) values");
		for (int i = 0; i < items.length; i++) {
			if (i==items.length-1) {
				buffer.append("("+sfdsKey+","+(i+1)+",'"+items[i]+"')");
			}else {
				buffer.append("("+sfdsKey+","+(i+1)+",'"+items[i]+"'),");
			}
		}
		final String sql = buffer.toString();
		System.out.println(sql);
		try {
			int i = sfdcsjdbcTemplate.update(DELETE_SET_UP_DATA, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, sfdsKey);
						
				}
			});
			System.out.println(i);
			if (i<0) {
				
			}else {
				sfdcsjdbcTemplate.query(SELECT_COUNT_SET_UP_DATA,new PreparedStatementSetter(){
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, sfdsKey);
					}
				}, new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
							if (rs.getInt("count")>0) {
							
							}else{
								int i = sfdcsjdbcTemplate.update(sql);
								if (i>0) {
									map.put(message, Success);
									map.put(data, "");
								}
							}
					}
				});
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			map.put(message, failure);
			map.put(error, e.getMessage());
		}
		return map;
	}

	/**
	 * 
	 * 
	 */
	private String QUERY_SET_UP_DATA="select item from setup_data where sfds_key = ?";
	public Map<String, Object> querySetUpData(final Integer sfdsKey) {
		final Map<String, Object> map = new HashMap<String,Object>();
		final List<Map<String, String>> resultList  = new ArrayList<Map<String,String>>();
		try {	
			sfdcsjdbcTemplate.query(QUERY_SET_UP_DATA, new PreparedStatementSetter(){
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, sfdsKey);
				}
			},new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException {
					Map<String, String> a = new HashMap<String,String>();
					a.put("text", rs.getString("item"));
					resultList.add(a);
					map.put(message, Success);
					map.put(data, resultList);
				}
			});

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);
		}
		return map;
	}

	/**
	 * 
	 * get All Shop Floor Id
	 */
	private String  QUERY_ALL_SHOP_FLOOR_ID="select sfds_id , sfds_key from sfds_config";
	public Map<String, Object> queryAllShopFloorId() {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<ShopFloorID> resultList = new ArrayList<ShopFloorID>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_ALL_SHOP_FLOOR_ID);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException  {
						ShopFloorID shopFloorID = new ShopFloorID();
						shopFloorID.setSfdsKey(rs.getInt("sfds_key"));
						shopFloorID.setSfdsId(rs.getString("sfds_id"));
						resultList.add(shopFloorID);
						map.put(message, Success);
						map.put(data, resultList);
				
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
/*			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}

	/**
	 * 
	 * get All location
	 */
	private String QUERY_ALL_LOCATION="select description , location_key from location";
	public Map<String, Object> queryAllLocation() {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<Location> resultList = new ArrayList<Location>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_ALL_LOCATION);
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException  {
						Location location = new Location();
						location.setLocationKey(rs.getInt("location_key"));
						location.setDescription(rs.getString("description"));
						resultList.add(location);
						map.put(message, Success);
						map.put(data, resultList);
				
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
/*			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}

	@Override
	public Map<String, Object> searchLocation(String description) {
		final Map<String, Object> map = new HashMap<String, Object>();
		final List<Location> resultList = new ArrayList<Location>();
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append(QUERY_ALL_LOCATION+" where description like '%"+description.trim()+"%'");
			logger.info("Select device and location sql is " + buffer.toString());
			sfdcsjdbcTemplate.query(buffer.toString(), new RowCallbackHandler() {
				public void processRow(ResultSet rs) throws SQLException  {
						Location location = new Location();
						location.setLocationKey(rs.getInt("location_key"));
						location.setDescription(rs.getString("description"));
						resultList.add(location);
						map.put(message, Success);
						map.put(data, resultList);
				}
			});
		} catch (Exception ex) {
			logger.error(ex.getMessage());
/*			String aString = ex.getMessage();
			String bString  = aString.substring(aString.indexOf("Detail"));
			String c = bString.substring(0,bString.indexOf(";"));
			System.out.println(bString);
			map.put(message,failure );
			map.put(error, c);*/
			map.put(message,failure );
			map.put(error, ex.getMessage());
		}
		return map;
	}


}
