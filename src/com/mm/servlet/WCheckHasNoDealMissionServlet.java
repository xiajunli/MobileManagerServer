package com.mm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mm.bll.IBllFrame;
import com.mm.entity.CEntityClient;
import com.mm.tool.MySpring;
//判断是否还有未处理的任务
@SuppressWarnings("serial")
public class WCheckHasNoDealMissionServlet extends HttpServlet {


	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset = utf-8");
	
		PrintWriter out = response.getWriter();
		MySpring context=MySpring.getInstance();
		IBllFrame iBllFrame=(IBllFrame)context.getContext().getBean("cBllFrameImpl");
	
		boolean bisDel=iBllFrame.hasWaitDealMission();

		JSONObject outjson=new CEntityClient.BuildJsonObject().Check(bisDel).build();
		System.out.println(outjson);
		out.println(outjson);
		out.flush();
		out.close();
	}

	
}
