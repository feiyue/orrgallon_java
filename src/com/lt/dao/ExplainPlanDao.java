package com.lt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lt.model.Bobasedata;

@Transactional
@Component("ExplainPlanDao")
public class ExplainPlanDao{

	private static Logger logger = Logger.getLogger("service");
	
	@Resource(name = "namedParameterJdbcTemplate")
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<Bobasedata> getAll() {
		logger.debug("Retrieving all persons");

		String sql = "SELECT T.DATACODE, T.DATANAME  FROM BO_BASEDATA T";

		// Maps a SQL result to a Java object
		RowMapper<Bobasedata> mapper = new RowMapper<Bobasedata>() {
			public Bobasedata mapRow(ResultSet rs, int rowNum) throws SQLException {
				Bobasedata bobasedata = new Bobasedata();
				bobasedata.setDatacode(rs.getString("DATACODE"));
				bobasedata.setDataname(rs.getString("DATANAME"));
				return bobasedata;
			}
		};

		return namedParameterJdbcTemplate.getJdbcOperations().query(sql, mapper);
	}
	
	public void insertXT1(){
		namedParameterJdbcTemplate.getJdbcOperations().execute("INSERT INTO XT1 VALUES(SYS_GUID())");
	}
	
	public int queryXT1(){
		return namedParameterJdbcTemplate.getJdbcOperations().queryForInt("SELECT COUNT(*) FROM XT1");
	}
	
	public void deleteExplainPlan(){
		namedParameterJdbcTemplate.getJdbcOperations().execute("DELETE FROM LT_PT");
	}
	
	public String executeExplainPlan(String sql){
		String statementId = UUID.randomUUID().toString().substring(1, 30);
		String epsql = "EXPLAIN PLAN SET STATEMENT_ID = '" + statementId + "' INTO LT_PT FOR " + sql;
		namedParameterJdbcTemplate.getJdbcOperations().execute(epsql);
		return statementId;
	}
	
	public String executeDisplayCursor(String sql){
		String tag = UUID.randomUUID().toString();
		
		sql = sql.toUpperCase();
		sql = sql.replaceFirst("SELECT", "SELECT /*" + tag + "*/ ");
		
		namedParameterJdbcTemplate.getJdbcOperations().execute(sql);
		
		return tag;
	}
	
	public List queryExplainPlan(final String statementId){
		StringBuffer querySql = new StringBuffer();
		
		querySql.append("SELECT LEVEL,");
		querySql.append("       T.ID,");
		querySql.append("       'treegrid-' || LEVEL || CASE");
		querySql.append("         WHEN LEVEL = 1 THEN");
		querySql.append("          ''");
		querySql.append("         ELSE");
		querySql.append("          ' treegrid-parent-' || (LEVEL - 1)");
		querySql.append("       END TREECLASS,");
		querySql.append("       T.PARENT_ID PID,");
		querySql.append("       T.DEPTH,");
		querySql.append("       T.POSITION,");
		querySql.append("       T.OPERATION || ' ' || T.OPTIONS OO,");
		querySql.append("       T.OBJECT_NAME,");
		querySql.append("       T.COST,");
		querySql.append("       T.CARDINALITY,");
		querySql.append("       T.BYTES");
		querySql.append("  FROM LT_PT T");
		querySql.append(" WHERE T.STATEMENT_ID = :STATEMENT_ID ");
		querySql.append(" START WITH T.ID = 0");
		querySql.append("CONNECT BY PRIOR T.ID = T.PARENT_ID ORDER SIBLINGS BY T.ID");
		
		return namedParameterJdbcTemplate.queryForList(querySql.toString(), new HashMap<String, String>(){{
			put("STATEMENT_ID", statementId);
		}});
	}
	
	public List queryDisplayCursor(final String sqlid, Map config){
		String querySql = "select * from table(dbms_xplan.display_cursor(:SQL_ID))";
		return namedParameterJdbcTemplate.queryForList(querySql, new HashMap<String, String>(){{
			put("SQL_ID", sqlid);
		}});
	}

}
