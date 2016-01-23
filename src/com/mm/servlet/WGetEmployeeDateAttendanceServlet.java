package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityAttendance;
import com.mm.entity.CEntityEmployee;
import com.mm.entityarray.CEntityAttendanceArray;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员按日期获取员工改日考勤记录
@SuppressWarnings("serial")
public class WGetEmployeeDateAttendanceServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		int EmployeeId=Integer.parseInt(request.getParameter(MyOpcode.Employee.EmployeeId));
		String AttendanceDate=new String(request.getParameter(MyOpcode.Attendance.AttendanceDate).getBytes("ISO-8859-1"),"utf-8");
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		CEntityAttendance cEntityAttendance=new CEntityAttendance.Builder().AttendanceDate(AttendanceDate).build();
		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId(EmployeeId).build();
		
		CEntityAttendanceArray cEntityAttendanceArray=iBllFrame.getEmployeeDateAttendance(cEntityEmployee, cEntityAttendance);
		
		JSONObject outjson=new CEntityAttendance.BuildJsonObject().MyJSONArray(cEntityAttendanceArray.toJsonArray()).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}

	
}
