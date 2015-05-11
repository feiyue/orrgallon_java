package com.lt.servlet.oracle;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lt.service.ExplainPlanService;
import com.lt.util.GridUtil;

/**
 * Servlet implementation class ExplainPlanServlet
 */
@WebServlet(name="ExplainPlanServlet", urlPatterns="/explainplan")
public class ExplainPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExplainPlanServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputSql = request.getParameter("inputsql");
		/*try{
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			Connection conn = ds.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement("SELECT *FROM BO_BASEDATA");
			ResultSet rs = pstmt.executeQuery();
			
		}catch(Exception e){
			
		}*/
		
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		/*ExplainPlanDao explainPlanDao = (ExplainPlanDao) ac.getBean("ExplainPlanDao");
		String statementId = explainPlanDao.executeExplainPlan(inputSql);
		List list =  explainPlanDao.queryExplainPlan(statementId);*/
		
		ExplainPlanService e = (ExplainPlanService) ac.getBean("ExplainPlanService");
		List list = e.queryExplainPlan(inputSql);
		
		System.out.println(list.size());
		
		list = GridUtil.formatTree(list);
		JSONArray jsonArray = JSONArray.fromObject(list);
		request.setAttribute("epjsonlist", jsonArray);
		
		request.getRequestDispatcher("/pages/oracle/explainplan.jsp").forward(request, response);
	}

}
