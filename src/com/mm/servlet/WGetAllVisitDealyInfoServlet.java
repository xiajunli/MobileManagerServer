package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.LinkedMap;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityClient;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityVisitDelay;
import com.mm.entity.CEntityVisitPlan;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//获取所有员工的延期记录
@SuppressWarnings("serial")
public class WGetAllVisitDealyInfoServlet extends HttpServlet {


	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		LinkedMap findResult=iBllFrame.getAllVisitDelayInfo();
		JSONObject outjson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		Iterator ite=findResult.entrySet().iterator();
		while(ite.hasNext()){
			Map.Entry entry=(Map.Entry)ite.next();
			Object value=entry.getValue();
			LinkedMap map=(LinkedMap)value;
			
			CEntityEmployee cEntityEmployee=(CEntityEmployee)map.get(MyOpcode.Employee.Employee);
			CEntityClient cEntityClient=(CEntityClient)map.get(MyOpcode.Client.Client);
			CEntityVisitPlan cEntityVisitPlan=(CEntityVisitPlan)map.get(MyOpcode.VisitPlan.VisitPlan);
			CEntityVisitDelay cEntityVisitDelay=(CEntityVisitDelay)map.get(MyOpcode.VisitDelay.VisitDelay);
			jsonArray.add(toJson(cEntityEmployee, cEntityClient,cEntityVisitPlan,cEntityVisitDelay));
			
		}
		outjson.put(MyOpcode.VisitDelay.VisitDelayList, jsonArray);
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}


	private JSONObject toJson(CEntityEmployee cEntityEmployee,CEntityClient cEntityClient,CEntityVisitPlan cEntityVisitPlan,CEntityVisitDelay cEntityVisitDelay){
		JSONObject js=new CEntityVisitDelay.BuildJsonObject().ToSingle(cEntityVisitDelay).build();
		js.put(MyOpcode.Employee.EmployeeId, cEntityEmployee.getM_iEmployeeId());
		js.put(MyOpcode.Employee.EmployeeAccount, cEntityEmployee.getM_sEmployeeAccount());
//		js.put(MyOpcode.Employee.EmployeeName, cEntityEmployee.getM_sEmployeeName());
		js.put(MyOpcode.Client.ClientId, cEntityClient.getM_iClientId());
//		js.put(MyOpcode.Client.ClientName, cEntityClient.getM_sClientName());
		js.put(MyOpcode.VisitPlan.VisitPlanId, cEntityVisitPlan.getM_iVisitPlanId());
		return js;
		
	}
	

}
