/**
 * @author 许彬
 */
package com.lt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 许彬
 *
 */
@Component("TestDao")
public class TestDao {
	@Autowired  
    private JdbcTemplate jdbcTemplate;
}
