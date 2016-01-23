package com.mm.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityAttendance;
import com.mm.entity.CEntityEmployee;
import com.mm.entityarray.CEntityAttendanceArray;
import com.mm.tool.MyOpcode;

@Component("cDaoAttendance")
public class CDaoAttendance extends SuperDAO {

	
	/**
	 * ��ţ�attendance:1
	 * ���ܣ��������뿼�ڱ�
	 * ������cEntityAttendanceArray
	 * ����ֵ:��
	 */
	public void saveAttendances(
			final CEntityAttendanceArray cEntityAttendanceArray) {
		this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				for (int i = 0; i < cEntityAttendanceArray.getSize(); i++) {
					session.save(cEntityAttendanceArray.getItem(i));
					if (i % 10 == 0) {
						session.flush();
						session.clear();
					}
				}
				return true;
			}
		});
	}
	
	
	/**
	 * ��ţ�attendance:2
	 * ���ܣ���Ա���ż��������ڽ���ǩ��
	 * ������cEntityEmployee(EmployeeId),cEntityAttendance(AttendanceRegisterTime,AttendanceDate)
	 * ����ֵ:boolean
	 */
	public boolean updateAttendanceRegisterTimeByEmployeeIdAndAttendanceDate(CEntityAttendance cEntityAttendance,CEntityEmployee cEntityEmployee){
		String hql="update com.mm.entity.CEntityAttendance as attendance set AttendanceRegisterTime=? where AttendanceDate=? and EmployeeId=?";
		boolean bisUpdate=false;
		
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{cEntityAttendance.getM_sAttendanceRegisterTime(),cEntityAttendance.getM_sAttendanceDate(),cEntityEmployee.getM_iEmployeeId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
	}
	
	/**
	 * ��ţ�attendance:3
	 * ���ܣ���Ա���ż��������ڽ���ǩ��
	 * ������cEntityEmployee(EmployeeId),cEntityAttendance(AttendanceSignoutTime,AttendanceDate)
	 * ����ֵ:boolean
	 */
	public boolean updateAttendanceSignoutTimeByEmployeeIdAndAttendanceDate(CEntityAttendance cEntityAttendance,CEntityEmployee cEntityEmployee){
		String hql="update com.mm.entity.CEntityAttendance as attendance set AttendanceSignoutTime=? where AttendanceDate=? and EmployeeId=?";
		boolean bisUpdate=false;
		
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{cEntityAttendance.getM_sAttendanceSignoutTime(),cEntityAttendance.getM_sAttendanceDate(),cEntityEmployee.getM_iEmployeeId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
	}
	
	/**
	 * ��ţ�attendance:4
	 * ���ܣ������ڻ�����еĿ���
	 * ������cEntityAttendance(AttendanceDate)
	 * ����ֵ:CEntityAttendanceArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityAttendanceArray queryAllAttendanceByAttendanceDate(CEntityAttendance cEntityAttendance){
		String hql="from com.mm.entity.CEntityAttendance as attendance where AttendanceDate=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityAttendance.getM_sAttendanceDate());
		
		CEntityAttendanceArray cEntityAttendanceArray=new CEntityAttendanceArray((List<CEntityAttendance>) findResult);
		
		return cEntityAttendanceArray;
	}
	
	
	/**
	 * ��ţ�attendance:5
	 * ���ܣ���Ա���ź͵������ڲ�ѯ����
	 * ������cEntityAttendance(AttendanceDate),CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityAttendance
	 */
	public CEntityAttendance queryAttendanceByEmployeeIdAndAttendanceDate(CEntityAttendance cEntityAttendance,CEntityEmployee cEntityEmployee){
		String hql="select new CEntityAttendance(attendance.m_iAttendanceId,attendance.m_sAttendanceRegisterTime,attendance.m_sAttendanceSignoutTime,attendance.m_sAttendanceDate) from com.mm.entity.CEntityAttendance as attendance where AttendanceDate=? and EmployeeId=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityAttendance.getM_sAttendanceDate(),cEntityEmployee.getM_iEmployeeId()});
		

		Iterator<?> iterator = findResult.listIterator();
		CEntityAttendance result = null;
		if (iterator.hasNext()) {
			result = (CEntityAttendance) iterator.next();
		}
		return result; 
	}
	
//	public static void main(String[] args) {
//		CEntityAttendance cEntityAttendance=new CEntityAttendance.Builder().AttendanceDate("2015/10/30").build();
//		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
//		"applicationContext.xml");
//		CDaoAttendance tt = (CDaoAttendance) ctx.getBean("cDaoAttendance");
//		tt.queryAllAttendanceWithEmployeeDateByAttendaceData(cEntityAttendance);
//	}
	
	/**
	 * ��ţ�attendance:6
	 * ���ܣ������ڻ�����еĿ������ݣ���Ա���ţ�Ա���˺ţ�Ա����
	 * ������cEntityAttendance(AttendanceDate)
	 * ����ֵ:HashedMap
	 */
	@SuppressWarnings("unchecked")
	public HashedMap queryAllAttendanceWithEmployeeDateByAttendaceData(CEntityAttendance cEntityAttendance){
		String hql="select attendance.m_iAttendanceId,attendance.m_sAttendanceRegisterTime,attendance.m_sAttendanceSignoutTime,employee.m_iEmployeeId ,employee.m_sEmployeeAccount,employee.m_sEmployeeName from com.mm.entity.CEntityAttendance as attendance left join attendance.cEntityEmployee , com.mm.entity.CEntityEmployee as employee where attendance.m_sAttendanceDate=? and attendance.cEntityEmployee.m_iEmployeeId=employee.m_iEmployeeId ";
		List findResult=this.getHibernateTemplate().find(hql,cEntityAttendance.getM_sAttendanceDate());
		
		
		Iterator it = findResult.iterator();  
		HashedMap maps=new HashedMap();
		int i=0;
		 while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    HashedMap map=new HashedMap();
		    CEntityAttendance mapattendance=new CEntityAttendance.Builder().AttendanceId((Integer)tuple[0]).AttendanceRegisterTime((String)tuple[1]).AttendanceSignoutTime((String)tuple[2]).build();
		    CEntityEmployee mapEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[3]).EmployeeAccount((String)tuple[4]).EmployeeName((String)tuple[5]).build();
		    map.put(MyOpcode.Attendance.Attendance, mapattendance);
		    map.put(MyOpcode.Employee.Employee, mapEmployee);
		    maps.put(i, map);
		    i++;
		 }    
		 

		 return maps;
	}
	
	/**
	 * ��ţ�attendance:7
	 * ���ܣ���Ա���Ų�ѯ���п��ڼ�¼
	 * ������CEntityEmployee(EmployeeId)
	 * ����ֵ:CEntityAttendanceArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityAttendanceArray queryAllEmployeeAttendance(CEntityEmployee cEntityEmployee){
		String hql="select new CEntityAttendance(attendance.m_iAttendanceId,attendance.m_sAttendanceRegisterTime,attendance.m_sAttendanceSignoutTime,attendance.m_sAttendanceDate) from com.mm.entity.CEntityAttendance as attendance where  EmployeeId=? order by AttendanceDate desc";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeId());
		CEntityAttendanceArray cEntityAttendanceArray=new CEntityAttendanceArray((List<CEntityAttendance>) findResult);

		return cEntityAttendanceArray; 
	}
	
	
	/**
	 * ��ţ�attendance:8
	 * ���ܣ���Ա���ź����ڲ�ѯ���ڼ�¼
	 * ������CEntityEmployee(EmployeeId),CEntityAttendance(AttendanceDate)
	 * ����ֵ:CEntityAttendanceArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityAttendanceArray queryAtteandenByEmployeeIdandAttendanceOldDate(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance){
		String hql="select new CEntityAttendance(attendance.m_iAttendanceId,attendance.m_sAttendanceRegisterTime,attendance.m_sAttendanceSignoutTime,attendance.m_sAttendanceDate) from com.mm.entity.CEntityAttendance as attendance where  EmployeeId=? and AttendanceDate=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),cEntityAttendance.getM_sAttendanceDate()});
		CEntityAttendanceArray cEntityAttendanceArray=new CEntityAttendanceArray((List<CEntityAttendance>) findResult);
		return cEntityAttendanceArray;
	}
	
	
	/**
	 * ��ţ�attendance:9
	 * ���ܣ���Ա���������ڲ�ѯ���ڼ�¼
	 * ������CEntityEmployee(EmployeeName),CEntityAttendance(AttendanceDate)
	 * ����ֵ:HashedMap
	 */
	@SuppressWarnings("unchecked")
	public HashedMap queryAllAttendanceWithEmployeeDateByEmployeeName(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance){
		String hql="select attendance.m_iAttendanceId,attendance.m_sAttendanceRegisterTime,attendance.m_sAttendanceSignoutTime,employee.m_iEmployeeId ,employee.m_sEmployeeAccount,employee.m_sEmployeeName from com.mm.entity.CEntityAttendance as attendance left join attendance.cEntityEmployee , com.mm.entity.CEntityEmployee as employee where attendance.m_sAttendanceDate=? and attendance.cEntityEmployee.m_iEmployeeId=employee.m_iEmployeeId and employee.m_sEmployeeName=? ";
		List findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityAttendance.getM_sAttendanceDate(),cEntityEmployee.getM_sEmployeeName()});
		Iterator it = findResult.iterator();  
		HashedMap maps=new HashedMap();
		int i=0;
		 while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    HashedMap map=new HashedMap();
		    CEntityAttendance mapattendance=new CEntityAttendance.Builder().AttendanceId((Integer)tuple[0]).AttendanceRegisterTime((String)tuple[1]).AttendanceSignoutTime((String)tuple[2]).build();
		    CEntityEmployee mapEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[3]).EmployeeAccount((String)tuple[4]).EmployeeName((String)tuple[5]).build();
		    map.put(MyOpcode.Attendance.Attendance, mapattendance);
		    map.put(MyOpcode.Employee.Employee, mapEmployee);
		    maps.put(i, map);
		    i++;
		 }    
		 

		 return maps;
	}
	
	

	/**
	 * ��ţ�attendance:10
	 * ���ܣ�����ݻ�ȡ����δɾԱ����Ա���ţ�Ա���˺ţ�Ա���������ڴ���(ǩ��������ǩ�˴���)���ٵ�������ǩ���ٵ�������ǩ�˳ٵ���������©ǩ������ǩ���ٵ�©ǩ������ǩ��©ǩ������
	 * ������year(yyyy)
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryStatisticalByYear(String year){
		String hql="select employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName," +
				"sum(case when attendance.m_sAttendanceRegisterTime is not null or attendance.m_sAttendanceRegisterTime!='' then 1 else 0 end)," +
				"sum(case when attendance.m_sAttendanceSignoutTime is not null or attendance.m_sAttendanceSignoutTime!='' then 1 else 0  end)," +
				"sum(case when attendance.m_sAttendanceRegisterTime not  between '08:30' and '09:00' then 1 else 0  end) ," +
				"sum(case when attendance.m_sAttendanceSignoutTime not   between '16:30' and '17:00' then 1 else 0  end)," +
				"sum(case when attendance.m_sAttendanceRegisterTime is  null or attendance.m_sAttendanceRegisterTime='' then 1 else 0 end)," +
				"sum(case when attendance.m_sAttendanceSignoutTime is null or attendance.m_sAttendanceSignoutTime='' then 1 else 0 end) " +
				" from com.mm.entity.CEntityEmployee as employee ,com.mm.entity.CEntityAttendance as attendance " +
				"where attendance.cEntityEmployee.m_iEmployeeId=employee.m_iEmployeeId " +
				"and employee.m_iEmployeeType!=4 " +
				"and attendance.m_sAttendanceDate like '%"+year+"%'" +
				" group by employee.m_iEmployeeId ";
		List findResult=this.getHibernateTemplate().find(hql);
		return findResult;
	}
	
	
//	public static void main(String[] args) {
//	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
//	"applicationContext.xml");
//	CDaoAttendance tt = (CDaoAttendance) ctx.getBean("cDaoAttendance");
//	tt.queryStatisticalByYearAndMonth("2015","12");
//}
	/**
	 * ��ţ�attendance:11
	 * ���ܣ�����ݣ��·ݻ�ȡ����δɾԱ����Ա���ţ�Ա���˺ţ�Ա���������ڴ���(ǩ��������ǩ�˴���)���ٵ�������ǩ���ٵ�������ǩ�˳ٵ���������©ǩ������ǩ���ٵ�©ǩ������ǩ��©ǩ������
	 * ������year(yyyy)��month(mm)
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryStatisticalByYearAndMonth(String year,String month){
		String mix=year+"/"+month;
		String hql="select employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName," +
				"sum(case when attendance.m_sAttendanceRegisterTime is not null or attendance.m_sAttendanceRegisterTime!='' then 1 else 0 end)," +
				"sum(case when attendance.m_sAttendanceSignoutTime is not null or attendance.m_sAttendanceSignoutTime!='' then 1 else 0  end)," +
				"sum(case when attendance.m_sAttendanceRegisterTime not  between '08:30' and '09:00' then 1 else 0  end) ," +
				"sum(case when attendance.m_sAttendanceSignoutTime not   between '16:30' and '17:00' then 1 else 0  end)," +
				"sum(case when attendance.m_sAttendanceRegisterTime is  null or attendance.m_sAttendanceRegisterTime='' then 1 else 0 end)," +
				"sum(case when attendance.m_sAttendanceSignoutTime is null or attendance.m_sAttendanceSignoutTime='' then 1 else 0 end)" +
				"  from com.mm.entity.CEntityEmployee as employee ," +
				"com.mm.entity.CEntityAttendance as attendance " +
				"where attendance.cEntityEmployee.m_iEmployeeId=employee.m_iEmployeeId and " +
				"employee.m_iEmployeeType!=4 and attendance.m_sAttendanceDate like '%"+mix+"%' " +
						"group by employee.m_iEmployeeId ";
		List findResult=this.getHibernateTemplate().find(hql);
		Iterator it=findResult.iterator();
		while (it.hasNext()) {       
			Object[] tuple = (Object[]) it.next();
			System.out.println((Integer)tuple[0]+"---"+(String)tuple[1]+"---"+(String)tuple[2]+"---"+(Long)tuple[3]+"---"+(Long)tuple[4]+"---"+(Long)tuple[5]+"---"+(Long)tuple[6]+"---"+(Long)tuple[7]+"---"+(Long)tuple[8]);
		 }   
		return findResult;
	}
	
	
	/**
	 * ��ţ�attendance:12
	 * ���ܣ���Ա��������ݣ��·ݻ�ȡ����δɾԱ����Ա���ţ�Ա���˺ţ�Ա���������ڴ���(ǩ��������ǩ�˴���)���ٵ�������ǩ���ٵ�������ǩ�˳ٵ���������©ǩ������ǩ���ٵ�©ǩ������ǩ��©ǩ������
	 * ������year(yyyy)��month(mm)��cEntityEmployee(EmployeeName)
	 * ����ֵ:List
	 */
	@SuppressWarnings("unchecked")
	public List queryStatisticalByYearMonthAndEmployeeName(CEntityEmployee cEntityEmployee,String year,String month){
		//����Ӧ����Bll��д������д�˶�д�ˡ�
		String mix="";
		if(!month.equals("ȫ��")){
			mix=year+"/"+month;
		}else{
			mix=year;
		}
		String hql="select employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName,sum(case when attendance.m_sAttendanceRegisterTime is not null or attendance.m_sAttendanceRegisterTime!='' then 1 else 0 end),sum(case when attendance.m_sAttendanceSignoutTime is not null or attendance.m_sAttendanceSignoutTime!='' then 1 else 0  end),sum(case when attendance.m_sAttendanceRegisterTime not  between '08:30' and '09:00' then 1 else 0  end) ,sum(case when attendance.m_sAttendanceSignoutTime not   between '16:30' and '17:00' then 1 else 0  end),sum(case when attendance.m_sAttendanceRegisterTime is  null or attendance.m_sAttendanceRegisterTime='' then 1 else 0 end),sum(case when attendance.m_sAttendanceSignoutTime is null or attendance.m_sAttendanceSignoutTime='' then 1 else 0 end)  from com.mm.entity.CEntityEmployee as employee ,com.mm.entity.CEntityAttendance as attendance where attendance.cEntityEmployee.m_iEmployeeId=employee.m_iEmployeeId and employee.m_sEmployeeName=? and attendance.m_sAttendanceDate like '%"+mix+"%' group by employee.m_iEmployeeId ";
		List findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_sEmployeeName());
		Iterator it=findResult.iterator();
		while (it.hasNext()) {       
			Object[] tuple = (Object[]) it.next();
			System.out.println((Integer)tuple[0]+"---"+(String)tuple[1]+"---"+(String)tuple[2]+"---"+(Long)tuple[3]+"---"+(Long)tuple[4]+"---"+(Long)tuple[5]+"---"+(Long)tuple[6]+"---"+(Long)tuple[7]+"---"+(Long)tuple[8]);
		 }   
		return findResult;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}