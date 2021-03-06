package com.mm.bll.listener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mm.dao.IDaoFrame;
import com.mm.entity.CEntityMission;
import com.mm.tool.MySpring;

public class CBllMissionListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		MySpring context=MySpring.getInstance();
		IDaoFrame iDaoFrame=(IDaoFrame)context.getContext().getBean("cDaoFrameImpl");
		System.out.println("cBllMissionClock=="+new Date());
		
		CEntityMission cEntityMission=new CEntityMission.Builder().MissionDeadline(getYesterdayPubdate()).build();
		
		boolean bisUpdate=iDaoFrame.updateMissionStateForOutTimeState(cEntityMission);
		System.out.println(bisUpdate);
		
	}
	//获得前一日日期 (因为现在是凌晨判断的是昨天为期限所过期的)
	private static String getYesterdayPubdate(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date resultDate=calendar.getTime();
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd");
		String result=sFormat.format(resultDate);
		return result;
		
	}
}
