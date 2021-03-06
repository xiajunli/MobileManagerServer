package com.mm.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityClient;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityVisitDelay;
import com.mm.entity.CEntityVisitPlan;
import com.mm.entityarray.CEntityVisitDealyArray;
import com.mm.tool.MyConstant;
import com.mm.tool.MyOpcode;

@Component("cDaoVisitDelay")
public class CDaoVisitDelay extends SuperDAO{

	/**
	 * 序号：visitdealy:1
	 * 功能：增加一条拜访延期
	 * 参数：cEntityEmployee(EmployeeId),cEntityVisitPlan(VisitPlanId),cEntityVisitDelay(本表字段)
	 * 返回值:boolean
	 */
	public boolean saveVisitDealy(CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan,CEntityVisitDelay cEntityVisitDelay) {
//		CEntityEmployee findEmployee=(CEntityEmployee)this.getHibernateTemplate().get(CEntityEmployee.class, cEntityEmployee.getM_iEmployeeId());
//		CEntityVisitPlan findVisitPlan=(CEntityVisitPlan)this.getHibernateTemplate().get(CEntityVisitPlan.class, cEntityVisitPlan.getM_iVisitPlanId());
//		cEntityVisitDelay.setcEntityVisitPlan(findVisitPlan);
		boolean bisSave=false;
		
		try {
			Serializable delayid=this.getHibernateTemplate().save(cEntityVisitDelay);
//			findEmployee.getcEntityVisitDelays().add(cEntityVisitDelay);
//			this.getHibernateTemplate().update(findEmployee);
			//至于为什么这么写，还是因为这样的性能远块与原来的
			String hql="update com.mm.entity.CEntityVisitDelay as visitdelay set VisitPlanId=?,EmployeeId=? where VisitDelayId=?";
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{cEntityVisitPlan.getM_iVisitPlanId(),cEntityEmployee.getM_iEmployeeId(),delayid});
			bisSave=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisSave;
	}
	
	/**
	 * 序号：visitdealy:2
	 * 功能：获得所有拜访延期
	 * 参数：无
	 * 返回值:CEntityVisitDealyArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityVisitDealyArray queryAllVisitDelay() {
		String hql="from com.mm.entity.CEntityVisitDelay as visitdelay ";
		List<?> findResult=this.getHibernateTemplate().find(hql);
		CEntityVisitDealyArray cEntityVisitDealyArray=new CEntityVisitDealyArray((List<CEntityVisitDelay>) findResult);
		return cEntityVisitDealyArray;

	}
	
	/**
	 * 序号：visitdealy:3
	 * 功能：按员工号获得所有拜访延期
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:LinkedMap
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllVisitDelayByEmployeeId(CEntityEmployee cEntityEmployee){
		String hql="select visitdelay.m_iVisitDelayId,visitdelay.m_sVisitDelayTime,visitdelay.m_sVisitDelayDeadline,visitplan.m_iVisitPlanId from com.mm.entity.CEntityVisitDelay as visitdelay,com.mm.entity.CEntityVisitPlan as visitplan,com.mm.entity.CEntityEmployee as employee left join employee.cEntityVisitDelays as bb where employee.m_iEmployeeId=? and visitplan.m_iVisitPlanId=visitdelay.cEntityVisitPlan.m_iVisitPlanId and bb.m_iVisitDelayId=visitdelay.m_iVisitDelayId order by visitdelay.m_sVisitDelayTime desc";
		List findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeId());
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityVisitDelay cEntityVisitDelay=new CEntityVisitDelay((Integer)tuple[0], (String)tuple[1], (String)tuple[2]);
		    CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId((Integer)tuple[3]).build();
		    map.put(MyOpcode.VisitDelay.VisitDelay, cEntityVisitDelay);
		    map.put(MyOpcode.VisitPlan.VisitPlan, cEntityVisitPlan);
		    maps.put(i, map);
		    i++;
		 }    
		
		return maps;
	} 
	
	
	/**
	 * 序号：visitdealy:4
	 * 功能：获取所有未删员工的拜访延期信息
	 * 参数：
	 * 返回值:LinkedMap(CEntityVisitDelay,CEntityVisitPlan,CEntityEmployee,CEntityClient)
	 */
	@SuppressWarnings("unchecked")
	public LinkedMap queryAllVisitDelayInfo(){
		String hql="select visitdelay.m_iVisitDelayId,visitdelay.m_sVisitDelayTime,visitdelay.m_sVisitDelayDeadline, bb.m_iVisitPlanId,employee.m_iEmployeeId,employee.m_sEmployeeAccount,bb.cEntityClient.m_iClientId from com.mm.entity.CEntityVisitDelay as visitdelay,com.mm.entity.CEntityEmployee as employee left join employee.cEntityVisitPlans as bb where bb.m_iVisitPlanId=visitdelay.cEntityVisitPlan.m_iVisitPlanId and employee.m_iEmployeeType!=? order by visitdelay.m_sVisitDelayTime desc";
		
		List findResult=this.getHibernateTemplate().find(hql,MyConstant.Employee.EMPLOYEE_DEL);
		Iterator it = findResult.iterator();  
		LinkedMap maps=new LinkedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    LinkedMap map=new LinkedMap();
		    CEntityVisitDelay cEntityVisitDelay=new CEntityVisitDelay((Integer)tuple[0], (String)tuple[1], (String)tuple[2]);
		    CEntityVisitPlan cEntityVisitPlan=new CEntityVisitPlan.Builder().VisitPlanId((Integer)tuple[3]).build();
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[4]).EmployeeAccount((String)tuple[5]).build();
		    CEntityClient cEntityClient=new CEntityClient.Builder().ClientId((Integer)tuple[6]).build();
		    map.put(MyOpcode.VisitDelay.VisitDelay, cEntityVisitDelay);
		    map.put(MyOpcode.VisitPlan.VisitPlan, cEntityVisitPlan);
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.Client.Client, cEntityClient);
		    maps.put(i, map);
		    i++;
		 }    
		
		return maps;
	}
	
	
	
}
