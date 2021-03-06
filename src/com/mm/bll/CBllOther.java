package com.mm.bll;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections.map.LinkedMap;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mm.dao.IDaoFrame;
import com.mm.entity.CEntityAddress;
import com.mm.entity.CEntityAdministrator;
import com.mm.entity.CEntityAttendance;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityNotice;
import com.mm.entity.CEntitySuggest;
import com.mm.entityarray.CEntityAttendanceArray;
import com.mm.entityarray.CEntityEmployeeArray;
import com.mm.entityarray.CEntityNoticeArray;
import com.mm.tool.MyConstant;

@Component("cBllOther")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class CBllOther {
	private IDaoFrame iDaoFrame;

	@Resource(name = "cDaoFrameImpl")
	public void setiDaoFrame(IDaoFrame iDaoFrame) {
		this.iDaoFrame = iDaoFrame;
	}
	
	
	/**
	 * 序号：othermodule:1 
	 * 功能：外勤人员签到
	 * 参数：cEntityEmployee(EmployeeId),cEntityMission(AttendanceRegisterTime) 
	 * 返回值:boolean
	 */
	public boolean registerAttendance(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance){
		cEntityAttendance.setM_sAttendanceDate(getNewPubdate());
		boolean bisRegister=iDaoFrame.updateAttendanceRegisterTimeByEmployeeIdAndAttendanceDate(cEntityAttendance, cEntityEmployee);
		return bisRegister;
	}
	
	/**
	 * 序号：othermodule:2
	 * 功能：外勤人员签退
	 * 参数：cEntityEmployee(EmployeeId),cEntityMission(AttendanceSignoutTime) 
	 * 返回值:boolean
	 */
	public boolean signoutAttendance(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance){
		cEntityAttendance.setM_sAttendanceDate(getNewPubdate());
		boolean bisSignout=iDaoFrame.updateAttendanceSignoutTimeByEmployeeIdAndAttendanceDate(cEntityAttendance, cEntityEmployee);
		return bisSignout;
	}
	
	
	
	/**
	 * 序号：othermodule:3
	 * 功能：外勤人员登录
	 * 参数：cEntityEmployee(EmployeeAccount,EmployeePassword)
	 * 返回值:CEntityEmployee
	 */
	public CEntityEmployee loginEmployee(CEntityEmployee cEntityEmployee){
		CEntityEmployee findResult=iDaoFrame.queryEmployeeByAccountAndPassword(cEntityEmployee);
		return findResult;
	}
	
	
	
	/**
	 * 序号：othermodule:4
	 * 功能：外勤人员进入考勤
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityAttendance
	 */
	public CEntityAttendance enterAttendanceForEmployee(CEntityEmployee cEntityEmployee){
		CEntityAttendance cEntityAttendance=new CEntityAttendance.Builder().AttendanceDate(getNewPubdate()).build();
		CEntityAttendance findResult=iDaoFrame.queryAttendanceByEmployeeIdAndAttendanceDate(cEntityAttendance, cEntityEmployee);
		return findResult;
	}
	/**
	 * 序号：othermodule:5
	 * 功能：外勤人员进入通讯录
	 * 参数：无
	 * 返回值:CEntityEmployeeArray
	 */
	public CEntityEmployeeArray enterCommunition(){
		CEntityEmployeeArray cEntityEmployeeArray=iDaoFrame.queryAllEmployee();
		return cEntityEmployeeArray;
	}
	/**
	 * 序号：othermodule:6
	 * 功能：进入通知公告
	 * 参数：无
	 * 返回值:CEntityNoticeArray
	 */
	public CEntityNoticeArray enterNotice(){
		CEntityNoticeArray cEntityNoticeArray=iDaoFrame.queryAllNotice();
		return cEntityNoticeArray;
	}
	
	
	/**
	 * 序号：othermodule:7
	 * 功能：提交投诉建议
	 * 参数：CEntityEmployee(EmployeeId),cEntitySuggest(本表字段)
	 * 返回值:boolean
	 */
	public boolean submitSuggest(CEntityEmployee cEntityEmployee,CEntitySuggest cEntitySuggest){
		boolean bisSubmit=iDaoFrame.saveSuggest(cEntityEmployee, cEntitySuggest);
		return bisSubmit;
	}
	
	/**
	 * 序号：othermodule:8
	 * 功能：管理员登录
	 * 参数：CEntityAdministrator(AdministratorAccount,AdministratorPassword)
	 * 返回值:boolean
	 */
	public boolean aministorLogin(CEntityAdministrator cEntityAdministrator){
		CEntityAdministrator findResult=iDaoFrame.queryAdministrator(cEntityAdministrator);
		boolean bisLogin=false;
		if(findResult!=null){
			bisLogin=true;
		}
		return bisLogin;
	}
	
	/**
	 * 序号：othermodule:9
	 * 功能：管理员删除员工
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:boolean
	 */
	public boolean deleteEmployee(CEntityEmployee cEntityEmployee){
		cEntityEmployee.setM_iEmployeeType(MyConstant.Employee.EMPLOYEE_DEL);
		boolean bisDelete=iDaoFrame.updateEmployeeType(cEntityEmployee);
		return bisDelete;
	}
	/**
	 * 序号：othermodule:10
	 * 功能：管理员修改员工
	 * 参数：CEntityEmployee(本表字段)
	 * 返回值:boolean
	 */
	public boolean updateEmployee(CEntityEmployee cEntityEmployee){
		boolean bisUpdate=iDaoFrame.updateEmployeeByEmployeeId(cEntityEmployee);
		return bisUpdate;
	}
	
	/**
	 * 序号：othermodule:11
	 * 功能：管理员增加员工
	 * 参数：CEntityEmployee(本表字段)
	 * 返回值:boolean
	 */
	public boolean addEmployee(CEntityEmployee cEntityEmployee){
		boolean bisAdd=iDaoFrame.saveEmployee(cEntityEmployee);
		
		//增加start---增加员工的时候，在考勤表初始当日的考勤记录
		String m_date=getNewPubdate();
		List<CEntityAttendance> cEntityAttendances=new ArrayList<CEntityAttendance>();
		CEntityAttendance cEntityAttendance=new CEntityAttendance.Builder().AttendanceDate(m_date).build();
		cEntityAttendance.setcEntityEmployee(cEntityEmployee);
		cEntityAttendances.add(cEntityAttendance);
		CEntityAttendanceArray cEntityAttendanceArray=new CEntityAttendanceArray(cEntityAttendances);
		iDaoFrame.saveAttendances(cEntityAttendanceArray);
		//增加end
		return bisAdd;
	}
	
	
	/**
	 * 序号：othermodule:12
	 * 功能：获得当天所有的考勤记录
	 * 参数：CEntityAttendance(AttendanceDate)
	 * 返回值:CEntityAttendanceArray
	 */
	public HashedMap queryAllAttendanceWithEmployeeDateByAttendaceData(CEntityAttendance cEntityAttendance){
		HashedMap findReuslt=iDaoFrame.queryAllAttendanceWithEmployeeDateByAttendaceData(cEntityAttendance);
		return findReuslt;
		
	}

	/**
	 * 序号：othermodule:13
	 * 功能：管理员获得所有删除的员工
	 * 参数：无
	 * 返回值:CEntityAttendanceArray
	 */
	public CEntityEmployeeArray getAllDelEmployee(){
		CEntityEmployee cEntityEmployee=new CEntityEmployee.Builder().EmployeeType(MyConstant.Employee.EMPLOYEE_DEL).build();
		CEntityEmployeeArray cEntityEmployeeArray=iDaoFrame.queryAllEmployeeByType(cEntityEmployee);
		return cEntityEmployeeArray;
	}
	
	/**
	 * 序号：othermodule:14
	 * 功能：管理员获得所有未删员工的姓名
	 * 参数：无
	 * 返回值:List<String>
	 */
	public List<String> getAllEmployeeName(){
		List<String> result=iDaoFrame.queryAllEmployeeName();
		return result;
	}
	
	
	/**
	 * 序号：othermodule:15
	 * 功能：管理员按员工名进行搜索
	 * 参数：CEntityEmployee(EmployeeName)
	 * 返回值:CEntityEmployeeArray
	 */
	public CEntityEmployeeArray getEmployeeSearchResult(CEntityEmployee cEntityEmployee){
		CEntityEmployeeArray cEntityEmployeeArray=iDaoFrame.queryAllEmployeeByEmployeeName(cEntityEmployee);
		return cEntityEmployeeArray;
	}
	
	/**
	 * 序号：othermodule:16
	 * 功能：管理员按员工部门进行搜索
	 * 参数：CEntityEmployee(EmployeeDepartment)
	 * 返回值:CEntityEmployeeArray
	 */
	public CEntityEmployeeArray getDepartmentEmployee(CEntityEmployee cEntityEmployee){
		CEntityEmployeeArray cEntityEmployeeArray=iDaoFrame.queryAllEmployeeByEmployeeDepartment(cEntityEmployee);
		return cEntityEmployeeArray;
	}
	
	
	/**
	 * 序号：othermodule:17
	 * 功能：管理员获取员工所有的考勤记录
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityEmployeeArray
	 */
	public CEntityAttendanceArray getAllEmployeeAttendance(CEntityEmployee cEntityEmployee){
		CEntityAttendanceArray cEntityAttendanceArray=iDaoFrame.queryAllEmployeeAttendance(cEntityEmployee);
		return cEntityAttendanceArray;
	}
	/**
	 * 序号：othermodule:18
	 * 功能：管理员按日期查询员工该日考勤记录
	 * 参数：CEntityEmployee(EmployeeId)，CEntityAttendance(AttendanceDate)
	 * 返回值:CEntityAttendanceArray
	 */
	public CEntityAttendanceArray getEmployeeDateAttendance(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance){
		CEntityAttendanceArray cEntityAttendanceArray=iDaoFrame.queryAtteandenByEmployeeIdandAttendanceOldDate(cEntityEmployee, cEntityAttendance);
		return cEntityAttendanceArray;
	}
	/**
	 * 序号：othermodule:19
	 * 功能：管理员按日期和员工名查询员工该日考勤记录
	 * 参数：CEntityEmployee(EmployeeId)，CEntityAttendance(AttendanceDate)
	 * 返回值:CEntityAttendanceArray
	 */
	public HashedMap getSearchEmployeeDate(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance){
		HashedMap findResult=iDaoFrame.queryAllAttendanceWithEmployeeDateByEmployeeName(cEntityEmployee, cEntityAttendance);
		return findResult;
	}
	/**
	 * 序号：othermodule:20
	 * 功能：管理员按年统计所有员工的考勤记录
	 * 参数：year(yyyy)
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List getAttendanceStatisticalYear(String year){
		List findResult=iDaoFrame.queryStatisticalByYear(year);
		return findResult;
	}
	/**
	 * 序号：othermodule:21
	 * 功能：管理员按年,月统计所有员工的考勤记录
	 * 参数：year(yyyy),month(mm)
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List getAttendanceStatisticalByYearAndMonth(String year,String month){
		List findResult=iDaoFrame.queryStatisticalByYearAndMonth(year,month);
		return findResult;
	}
	/**
	 * 序号：othermodule:22
	 * 功能：管理员按年,月,员工名统计员工的考勤记录
	 * 参数：year(yyyy),month(mm)，CEntityEmployee(EmployeeName)
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List getAttendanceStatisticalByYearMonthAndEmployeeName(String year,String month,CEntityEmployee cEntityEmployee){
		List findResult=iDaoFrame.queryStatisticalByYearMonthAndEmployeeName(cEntityEmployee, year, month);
		return findResult;
	}
	
	/**
	 * 序号：othermodule:23
	 * 功能：管理员增加一条通知公告
	 * 参数：cEntityNotice(本表字段)
	 * 返回值:boolean 
	 */
	public boolean createNotice(CEntityNotice cEntityNotice){
		cEntityNotice.setM_sNoticeTime(getNewPubdate());
		boolean bisAdd=iDaoFrame.saveNotice(cEntityNotice);
		return bisAdd;
	}
	
	/**
	 * 序号：othermodule:24
	 * 功能：获取所有的投诉建议
	 * 参数：
	 * 返回值:LinkedMap 
	 */
	public LinkedMap getAllSuggestInfo(){
		LinkedMap findResult=iDaoFrame.queryAllSuggestInfo();
		return findResult;
	}
	
	
	/**
	 * 序号：othermodule:25
	 * 功能：员工增加一条地址
	 * 参数：
	 * 返回值:boolean 
	 */
	public boolean createAddress(CEntityAddress cEntityAddress){
		boolean bisSave=iDaoFrame.saveAddress(cEntityAddress);
		return bisSave;
	}
	
	
	/**
	 * 序号：othermodule:26
	 * 功能：按员工号及查询天数获取地址
	 * 参数：
	 * 返回值:LinkedMap 
	 */
	public LinkedMap getAddressInfo(CEntityEmployee cEntityEmployee,int days){
		LinkedMap findReult=iDaoFrame.queryAddressEmployeeInfoWithTime(cEntityEmployee, days);
		return findReult;
	}
	
	/**
	 * 序号：othermodule:27
	 * 功能：获取所有未删员工的信息
	 * 参数：
	 * 返回值:CEntityEmployeeArray 
	 */
	public CEntityEmployeeArray getAllNoDelEmployee(){
		CEntityEmployeeArray findResult=iDaoFrame.queryAllEmployee();
		return findResult;
	}
	
	
	
	//获得当日日期，仅在本类中使用
	private static String getNewPubdate(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		Date resultDate=calendar.getTime();
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy/MM/dd");
		String result=sFormat.format(resultDate);
		return result;
		
	}
	
}
