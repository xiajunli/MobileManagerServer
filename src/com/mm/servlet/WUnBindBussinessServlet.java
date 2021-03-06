package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityBussiness;
import com.mm.entity.CEntityBussinessActivity;
import com.mm.tool.MyConstant;
import com.mm.tool.MyOpcode;
import com.mm.tool.MySpring;
//管理员解绑出差活动
@SuppressWarnings("serial")
public class WUnBindBussinessServlet extends HttpServlet {

	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
		
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
		int BussinessActivityType=Integer.parseInt(request.getParameter(MyOpcode.BussinessActivity.BussinessActivityType));
		int BussinessObjectId=Integer.parseInt(request.getParameter(MyOpcode.BussinessActivity.BussinessObjectId));
		CEntityBussinessActivity cEntityBussinessActivity=new CEntityBussinessActivity.Builder().BussinessActivityType(BussinessActivityType).BussinessObjectId(BussinessObjectId).BussinessBindType(MyConstant.BussinessActivity.BUSSINESSACTIVITY_NOBAND).build();
		
		boolean bisUnbind=iBllFrame.unbindBussinessActivity(cEntityBussinessActivity);
		
		JSONObject outjson=new CEntityBussiness.BuildJsonObject().Check(bisUnbind).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	
	}

	

}
