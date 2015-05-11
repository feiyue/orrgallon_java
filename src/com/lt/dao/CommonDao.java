/**
 * @author 许彬
 */
package com.lt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 许彬
 *
 */
@Component("CommonDao")
public class CommonDao {

	private static Logger logger = Logger.getLogger("service");
	
	@Resource(name = "namedParameterJdbcTemplate")
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public String getSqlIdByTag(final String tag){
		List<Map<String, Object>> result = namedParameterJdbcTemplate.queryForList("SELECT *FROM V$SQL T WHERE T.SQL_TEXT LIKE '%' || :TAG || '%' AND T.SQL_TEXT NOT LIKE '%V$SQL%'", new HashMap<String, String>(){{
			put("TAG", tag);
		}});
		if(CollectionUtils.isNotEmpty(result)){
			for (Map<String, Object> map : result) {
				logger.debug(MapUtils.getString(map, "SQL_TEXT") + "/" + MapUtils.getString(map, "SQL_ID"));
			}
			return MapUtils.getString(result.get(0), "SQL_ID");
		}
		return null;
	}

}
