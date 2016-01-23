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
import com.mm.entity.CEntityVisitPlan;
import com.mm.entity.CEntityVisitUndo;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//获取所有撤销的拜访计划
@SuppressWarnings("serial")
public class WGetAllVisitUndoServlet extends HttpServlet {


	@SuppressWarnings("unchecked")
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
		LinkedMap findResult=iBllFrame.getAllVisitUndoInfo();
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
			CEntityVisitUndo cEntityVisitUndo=(CEntityVisitUndo)map.get(MyOpcode.VisitUndo.VisitUndo);
			jsonArray.add(toJson(cEntityEmployee, cEntityClient,cEntityVisitPlan,cEntityVisitUndo));
			
		}
		outjson.put(MyOpcode.VisitUndo.VisitUndoList, jsonArray);
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}


	private JSONObject toJson(CEntityEmployee cEntityEmployee,CEntityClient cEntityClient,CEntityVisitPlan cEntityVisitPlan,CEntityVisitUndo cEntityVisitUndo){
		JSONObject js=new JSONObject();
		js.put(MyOpcode.VisitUndo.VisitUndoId, cEntityVisitUndo.getM_iVisitUndoId());
		js.put(MyOpcode.VisitUndo.VisitUndoReason, cEntityVisitUndo.getM_sVisitUndoReason());
		js.put(MyOpcode.VisitUndo.VisitUndoTime, cEntityVisitUndo.getM_sVisitUndoTime());
		js.put(MyOpcode.VisitPlan.VisitPlanId, cEntityVisitPlan.getM_iVisitPlanId());
		js.put(MyOpcode.Employee.EmployeeId, cEntityEmployee.getM_iEmployeeId());
		js.put(MyOpcode.Employee.EmployeeAccount, cEntityEmployee.getM_sEmployeeAccount());
		js.put(MyOpcode.Employee.EmployeeName, cEntityEmployee.getM_sEmployeeName());
		js.put(MyOpcode.Client.ClientId, cEntityClient.getM_iClientId());
		js.put(MyOpcode.Client.ClientName, cEntityClient.getM_sClientName());
		return js;
		
	}

	

}
