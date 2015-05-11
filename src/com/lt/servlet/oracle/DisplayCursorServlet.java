/**
 * @author 许彬
 */
package com.lt.servlet.oracle;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.collections.MapUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lt.model.DisplayCursorModel;
import com.lt.service.ExplainPlanService;
import com.lt.util.GridUtil;

/**
 * @author 许彬
 *
 */
@WebServlet(name="DisplayCursorServlet", urlPatterns="/displaycursor")
public class DisplayCursorServlet extends HttpServlet {

	/**
	 * 
	 */
	public DisplayCursorServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputSql = request.getParameter("inputsql");
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		ExplainPlanService e = (ExplainPlanService) ac.getBean("ExplainPlanService");
		DisplayCursorModel displayCursorModel = GridUtil.parseList2DisplayCursorModel(e.queryDisplayCursor(inputSql));
		
		/*for (Object object : displayCursorModel.getExplainplan()) {
			System.out.println(MapUtils.getString((Map) object, "OPERATION"));
		}*/
		
		JSONArray jsonArray = JSONArray.fromObject(displayCursorModel.getExplainplan());
		request.setAttribute("dcjsonlist", jsonArray);
		
		request.getRequestDispatcher("/pages/oracle/displaycursor.jsp").forward(request, response);
	}

}
