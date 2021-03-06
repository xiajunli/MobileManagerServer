package com.mm.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityClient;
import com.mm.entity.CEntityClientSubmit;
import com.mm.entity.CEntityEmployee;
import com.mm.entityarray.CEntityClientArray;
import com.mm.entityarray.CEntityClientSubmitArray;
import com.mm.tool.MyConstant;
import com.mm.tool.MyOpcode;

@Component("cDaoClient")
public class CDaoClient extends SuperDAO {

	/**
	 * 序号：client:1
	 * 功能：增加客户
	 * 参数：cEntityClient(本表字段)
	 * 返回值:boolean
	 */
	public  boolean saveClient(CEntityClient cEntityClient) {
	
		boolean bisSave=false;
		
		try {
			this.getHibernateTemplate().save(cEntityClient);
			bisSave=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisSave;
	}
	
	/**
	 * 序号：client:2
	 * 功能：按员工号获得所有的客户
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityClientArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityClientArray queryAllClientByEmployeeId(CEntityEmployee cEntityEmployee) {
		String hql="from com.mm.entity.CEntityClient as client where EmployeeId=? and ClientState=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),MyConstant.Client.CLIENT_DISTRIBUTED});
		
		CEntityClientArray cEntityClientArray=new CEntityClientArray((List<CEntityClient>) findResult);
		
		return cEntityClientArray;
	}
	
	/**
	 * 序号：client:3
	 * 功能：修改客户员工号(并修改客户状态为已分配)
	 * 参数：cEntityEmployee(EmployeeId),cEntityClient(ClientId)
	 * 返回值:CEntityClientArray
	 */
	public boolean updateClientEmployeeId(CEntityClient cEntityClient,CEntityEmployee cEntityEmployee) {
		String hql="update com.mm.entity.CEntityClient as client set EmployeeId=?,ClientState=? where clientId=?";
		boolean bisUpdate=false;
		
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{cEntityEmployee.getM_iEmployeeId(),MyConstant.Client.CLIENT_DISTRIBUTED,cEntityClient.getM_iClientId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
	}
	
	/**
	 * 序号：client:4
	 * 功能：按客户状态获得所有的客户
	 * 参数：cEntityClient(ClientState)
	 * 返回值:CEntityClientArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityClientArray queryAllClientByClientState(CEntityClient cEntityClient) {
		String hql="from com.mm.entity.CEntityClient as client where ClientState=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityClient.getM_iClientState());
		CEntityClientArray cEntityClientArray=new CEntityClientArray((List<CEntityClient>) findResult);
		return cEntityClientArray;
	}
	
	/**
	 * 序号：client:5
	 * 功能：按客户号修改客户状态
	 * 参数：cEntityClient(ClientId)
	 * 	   OperateState(MyConstant.Client.*)
	 * 返回值:CEntityClientArray
	 */
	public boolean updateClientStateByClientId(CEntityClient cEntityClient,int OperateState){
		String hql="update com.mm.entity.CEntityClient as client set ClientState=? where ClientId=?";
		
		boolean bisUpdate=false;
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{OperateState,cEntityClient.getM_iClientId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
		
	}
	
	/**
	 * 序号：client:6
	 * 功能：按客户号获得客户详情
	 * 参数：cEntityClient(ClientId)
	 * 返回值:CEntityClient
	 */
	public CEntityClient queryClientByClientId(CEntityClient cEntityClient){
		CEntityClient findResult=(CEntityClient)this.getHibernateTemplate().get(CEntityClient.class, cEntityClient.getM_iClientId());
		return findResult;
	}
	
	/**
	 * 序号：client:7
	 * 功能：按员工号获取其在客户提交中的所有客户
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityClientArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityClientArray queryClientForSubmitEmployeeId(CEntityEmployee cEntityEmployee){
		String hql="from com.mm.entity.CEntityClientSubmit as clientsubmit where EmployeeId=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeId());
		CEntityClientSubmitArray cEntityClientSubmitArray=new CEntityClientSubmitArray((List<CEntityClientSubmit>) findResult);
		
		List<CEntityClient> cEntityClients=new ArrayList<CEntityClient>();
		for(int i=0;i<cEntityClientSubmitArray.getSize();i++){
			cEntityClients.add(cEntityClientSubmitArray.getItem(i).getcEntityClient());
		}
		CEntityClientArray cEntityClientArray=new CEntityClientArray(cEntityClients);
		return cEntityClientArray;
	}
	/**
	 * 序号：client:8
	 * 功能：查询所有类型的客户
	 * 参数：无
	 * 返回值:CEntityClientArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityClientArray queryAllClient(){
		String hql="from com.mm.entity.CEntityClient as  client";
		List<?> findResult=this.getHibernateTemplate().find(hql);
		CEntityClientArray cEntityClientArray=new CEntityClientArray((List<CEntityClient>) findResult);
		return cEntityClientArray;
	}

	
	/**
	 * 序号：client:9
	 * 功能：修改客户详情
	 * 参数：CEntityClient(本表字段)
	 * 返回值:boolean
	 */
	public boolean updateClient(CEntityClient cEntityClient){
		String hql="update com.mm.entity.CEntityClient as client set ClientName=?,ClientCompany=?,ClientPhone=?,ClientArea=?,ClientAddress=? where ClientId=?";
		boolean bisUpdate=false;
		
		try {
			this.getHibernateTemplate().bulkUpdate(hql,new Object[]{cEntityClient.getM_sClientName(),cEntityClient.getM_sClientCompany(),cEntityClient.getM_sClientPhone(),cEntityClient.getM_sClientArea(),cEntityClient.getM_sClientAddress(),cEntityClient.getM_iClientId()});
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bisUpdate;

		
	}
	
	/**
	 * 序号：client:10
	 * 功能：按员工号获取其已分配客户名
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:List<String>
	 */
	@SuppressWarnings("unchecked")
	public List<String> queryAllClientNameByEmployeeId(CEntityEmployee cEntityEmployee){
		String hql="select client.m_sClientName from com.mm.entity.CEntityClient as client where EmployeeId=?";
		List<String> result=this.getHibernateTemplate().find(hql,cEntityEmployee.getM_iEmployeeId());
		return result;
	}
	
	/**
	 * 序号：client:11
	 * 功能：按客户名获取客户详情
	 * 参数：CEntityClient(ClientName)
	 * 返回值:CEntityClientArray
	 */
	@SuppressWarnings("unchecked")
	public CEntityClientArray queryAllClientByClientName(CEntityClient cEntityClient){
		String hql="from com.mm.entity.CEntityClient as client where ClientName=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,cEntityClient.getM_sClientName());
		CEntityClientArray cEntityClientArray=new CEntityClientArray((List<CEntityClient>) findResult);
		return cEntityClientArray;
	}
	
	
	/**
	 * 序号：client:12
	 * 功能：按客户号将员工号设空，并修改客户状态为未分配
	 * 参数：CEntityClient(ClientId)
	 * 返回值:boolean
	 */
	public boolean updateClientEmployeeIdNUll(CEntityClient cEntityClient){
		String hql="update com.mm.entity.CEntityClient as client set EmployeeId=null,ClientState=0 where ClientId=?";
		boolean bisUpdate=false;
		try {
			this.getHibernateTemplate().bulkUpdate(hql,cEntityClient.getM_iClientId());
			bisUpdate=true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bisUpdate;
	
	}
//	public static void main(String[] args) {
//	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
//	"applicationContext.xml");
//	CDaoClient tt = (CDaoClient) ctx.getBean("cDaoClient");
//	tt.queryClientNumberOfEmployeeInfo();
//}
	
	
	/**
	 * 序号：client:13
	 * 功能：统计员工号，员工账号，员工名，客户拥有数量
	 * 参数：
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List queryClientNumberOfEmployeeInfo(){
		String hql="select employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName,sum(case when bb.m_iClientId=client.m_iClientId then 1 else 0  end)  from com.mm.entity.CEntityEmployee as employee ,com.mm.entity.CEntityClient as client left join employee.cEntityClients as bb where employee.m_iEmployeeType!=4  group by employee.m_iEmployeeId order by sum(case when bb.m_iClientId=client.m_iClientId then 1 else 0  end) desc ";
		List findResult=this.getHibernateTemplate().find(hql);
		return findResult;
	}
	
	/**
	 * 序号：client:14
	 * 功能：查询出已分配客户信息以及其员工信息
	 * 参数：
	 * 返回值:HashedMap(CEntityEmployee,CEntityClient)
	 */
	@SuppressWarnings("unchecked")
	public HashedMap queryClientDistributionWithEmployeeInfo(){
		String hql="select  employee.m_iEmployeeId,employee.m_sEmployeeAccount ,employee.m_sEmployeeName,client.m_iClientId,client.m_sClientName,client.m_sClientCompany,client.m_sClientAddress,client.m_sClientPhone,client.m_sClientArea,client.m_iClientState from com.mm.entity.CEntityEmployee as employee,com.mm.entity.CEntityClient as client left join employee.cEntityClients as bb where bb.m_iClientId=client.m_iClientId and client.m_iClientState=?";
		List<?> findResult=this.getHibernateTemplate().find(hql,MyConstant.Client.CLIENT_DISTRIBUTED);
		Iterator it = findResult.iterator();  
		HashedMap maps=new HashedMap();
		int i=0;
		while (it.hasNext()) {       
		    Object[] tuple = (Object[]) it.next();
		    HashedMap map=new HashedMap();
		    CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeId((Integer)tuple[0]).EmployeeAccount((String)tuple[1]).EmployeeName((String)tuple[2]).build();
		    CEntityClient cEntityClient=new CEntityClient.Builder().ClientId((Integer)tuple[3]).ClientName((String)tuple[4]).ClientCompany((String)tuple[5]).ClientAddress((String)tuple[6]).ClientPhone((String)tuple[7]).ClientArea((String)tuple[8]).ClientState((Integer)tuple[9]).build();
		    map.put(MyOpcode.Employee.Employee, cEntityEmployee);
		    map.put(MyOpcode.Client.Client, cEntityClient);
		    maps.put(i, map);
		    i++;
		 }    

		 return maps;
	}
	
	
	/**
	 * 序号：client:15
	 * 功能：查询未分配客户的客户号，客户名，客户公司
	 * 参数：
	 * 返回值:List(ClientId,ClientName,ClientCompany)
	 */
	@SuppressWarnings("unchecked")
	public List queryClientUnDistributionInfo(){
		String hql="select client.m_iClientId,client.m_sClientName,client.m_sClientCompany from com.mm.entity.CEntityClient as client where client.m_iClientState=?";
		List findResult=this.getHibernateTemplate().find(hql,MyConstant.Client.CLIENT_UNDISTRIBUTED);
		return findResult;
	}
	
	
	
}
