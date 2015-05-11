package com.lt.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lt.dao.CommonDao;
import com.lt.dao.ExplainPlanDao;


@Transactional
@Component("ExplainPlanService")
public class ExplainPlanService {

	protected static Logger logger = Logger.getLogger("service");
	
	private ExplainPlanDao explainPlanDao;
	
	private CommonDao commonDao;

	public CommonDao getCommonDao() {
		return commonDao;
	}

	@Resource(name="CommonDao")
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public ExplainPlanDao getExplainPlanDao() {
		return explainPlanDao;
	}

	@Resource(name="ExplainPlanDao")
	public void setExplainPlanDao(ExplainPlanDao explainPlanDao) {
		this.explainPlanDao = explainPlanDao;
	}
	
	public List<HashMap> queryExplainPlan(String querySql){
		explainPlanDao.deleteExplainPlan();
		String statementId = this.explainPlanDao.executeExplainPlan(querySql);
		return explainPlanDao.queryExplainPlan(statementId);
	}
	
	public List queryDisplayCursor(String querySql){
		String tag = explainPlanDao.executeDisplayCursor(querySql);
		String sqlid = commonDao.getSqlIdByTag(tag);
		return explainPlanDao.queryDisplayCursor(sqlid, null);
	}
	
	public int queryXT1(){
		explainPlanDao.insertXT1();
		return explainPlanDao.queryXT1();
	}
}
