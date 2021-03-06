package com.mm.bll;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.collections.map.LinkedMap;
import org.springframework.stereotype.Component;

import com.mm.entity.CEntityAddress;
import com.mm.entity.CEntityAdministrator;
import com.mm.entity.CEntityAttendance;
import com.mm.entity.CEntityBussiness;
import com.mm.entity.CEntityBussinessActivity;
import com.mm.entity.CEntityBussinessBadrecord;
import com.mm.entity.CEntityBussinessUndo;
import com.mm.entity.CEntityClient;
import com.mm.entity.CEntityEmployee;
import com.mm.entity.CEntityMission;
import com.mm.entity.CEntityMissionConclusion;
import com.mm.entity.CEntityMissionDelay;
import com.mm.entity.CEntityMissionUndo;
import com.mm.entity.CEntityNotice;
import com.mm.entity.CEntitySuggest;
import com.mm.entity.CEntityVisitConclusion;
import com.mm.entity.CEntityVisitPlan;
import com.mm.entity.CEntityVisitUndo;
import com.mm.entityarray.CEntityAttendanceArray;
import com.mm.entityarray.CEntityBussinessArray;
import com.mm.entityarray.CEntityClientArray;
import com.mm.entityarray.CEntityClientSubmitArray;
import com.mm.entityarray.CEntityEmployeeArray;
import com.mm.entityarray.CEntityMissionArray;
import com.mm.entityarray.CEntityNoticeArray;
import com.mm.entityarray.CEntityVisitPlanArray;

@Component("cBllFrameImpl")
public class CBllFrameImpl implements IBllFrame{

	/*
	 *------------------------------ 任务模块 missionmodule----------------------------
	 */
	
	private CBllMission cBllMission;
	
	@Resource
	public void setcBllMission(CBllMission cBllMission) {
		this.cBllMission = cBllMission;
	}


	/**
	 * 序号：missionmodule:1
	 * 功能：创建外勤任务
	 * 参数：cEntityEmployee(EmployeeId),cEntityMission(本表字段)
	 * 返回值:boolean
	 */
	public boolean createMission(CEntityEmployee cEntityEmployee,CEntityMission cEntityMission) {
		boolean bisCreate=cBllMission.createMission(cEntityEmployee, cEntityMission);
		return bisCreate;
	}
	
	/**
	 * 序号：missionmodule:2
	 * 功能：外勤人员接受任务
	 * 参数：cEntityMission(MissionId)
	 * 返回值:boolean
	 */
	public boolean acceptMission(CEntityMission cEntityMission) {
		boolean bisAccept=cBllMission.acceptMission(cEntityMission);
		return bisAccept;
	}


	/**
	 * 序号：missionmodule:3
	 * 功能：外勤人员提交任务及总结
	 * 参数：cEntityMission(MissionId),cEntityMissionConclusion(本表字段)
	 * 返回值:boolean
	 */
	public boolean submitMission(CEntityMission cEntityMission,CEntityMissionConclusion cEntityMissionConclusion) {
		boolean bisSubmit=cBllMission.submitMission(cEntityMission, cEntityMissionConclusion);
		return bisSubmit;
	}
	

	/**
	 * 序号：missionmodule:4
	 * 功能：审核任务
	 * 参数：  cEntityMission(MissionId)
	 * 		cEntityMissionConclusion(MissionConclusionId)
	 * 	    OperateState(MyConstant.MissionConclusion.*)
	 * 返回值:boolean
	 */
	public boolean checkMissionConclusion(CEntityMission cEntityMission,CEntityMissionConclusion cEntityMissionConclusion,int OperateState ) {
		boolean bisCheck=cBllMission.checkMissionConclusion(cEntityMission, cEntityMissionConclusion, OperateState);
		
		return bisCheck;
	}


	/**
	 * 序号：missionmodule:5
	 * 功能：撤销任务
	 * 参数：  cEntityMission(MissionId)
	 * 		cEntityMissionUndo(本表字段)
	 * 返回值:boolean
	 */
	public boolean undoMission(CEntityMission cEntityMission,CEntityMissionUndo cEntityMissionUndo) {
		boolean bisUndo=cBllMission.undoMission(cEntityMission, cEntityMissionUndo);
		
		return bisUndo;
	}
	
	/**
	 * 序号：missionmodule:6
	 * 功能：撤回任务
	 * 参数：  cEntityMissionUndo(MissionUndoId)
	 * 返回值:boolean
	 */
	public boolean recallMission(CEntityMissionUndo cEntityMissionUndo) {
		boolean bisRecall=cBllMission.recallMission(cEntityMissionUndo);
		return bisRecall;
	}

	/**
	 * 序号：missionmodule:7
	 * 功能：延期任务
	 * 参数：cEntityMission(MissionId,MissionDeadline),cEntityMissionDelay(本表字段)
	 * 返回值:boolean
	 */
	public boolean delayMission(CEntityMission cEntityMission,CEntityMissionDelay cEntityMissionDelay) {
		boolean bisDelay=cBllMission.delayMission(cEntityMission, cEntityMissionDelay);
		return bisDelay;
	}

	/**
	 * 序号：missionmodule:8
	 * 功能：废弃任务
	 * 参数：cEntityMission(MissionId)
	 * 返回值:boolean
	 */
	public boolean abandonMission(CEntityMission cEntityMission){
		boolean bisAbandon=cBllMission.abandonMission(cEntityMission);
		return bisAbandon;
		
	}
	
	/**
	 * 序号：missionmodule:9
	 * 功能：获取未接受任务
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray GetWaitTakeMissionArray(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=cBllMission.GetWaitTakeMissionArray(cEntityEmployee);
		return cEntityMissionArray;
	}

	/**
	 * 序号：missionmodule:10
	 * 功能：获取执行中任务
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray GetUnderWayMissionArray(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=cBllMission.GetUnderWayMissionArray(cEntityEmployee);
		return cEntityMissionArray;
	}

	/**
	 * 序号：missionmodule:11
	 * 功能：获取终止态的任务(未审核,已审核,已撤销,已失败)
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray GetCompleteMissionArray(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=cBllMission.GetCompleteMissionArray(cEntityEmployee);
		return cEntityMissionArray;
	}
	
	/**
	 * 序号：missionmodule:12
	 * 功能：获得任务总结
	 * 参数：CEntityMission(MissionId)
	 * 返回值:CEntityMissionConclusion
	 */
	public CEntityMissionConclusion GetMissionConclusion(CEntityMission cEntityMission) {
		CEntityMissionConclusion cEntityMissionConclusion=cBllMission.GetMissionConclusion(cEntityMission);
		
		return cEntityMissionConclusion;
	}
	/**
	 * 序号：missionmodule:13
	 * 功能：管理员获得所有待接受任务
	 * 参数：无
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray GetAllWaitTakeMissionArray(){
		CEntityMissionArray cEntityMissionArray=cBllMission.GetAllWaitTakeMissionArray();
		return cEntityMissionArray;
	}
	/**
	 * 序号：missionmodule:14
	 * 功能：管理员获得该员工所有任务
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray GetAllEmployeeMission(CEntityEmployee cEntityEmployee){
		CEntityMissionArray cEntityMissionArray=cBllMission.GetAllEmployeeMission(cEntityEmployee);
		return cEntityMissionArray;
	}
	/**
	 * 序号：missionmodule:15
	 * 功能：管理员获得该员工所有任务总结
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionConclusionArray
	 */
	public LinkedMap GetAllEmployeeMissionConclusion(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=cBllMission.GetAllEmployeeMissionConclusion(cEntityEmployee);
		return findResult;
	}
	/**
	 * 序号：missionmodule:16
	 * 功能：管理员获得任务总结详情
	 * 参数：CEntityMissionConclusion(MissionConclusionId)
	 * 返回值:CEntityMissionConclusion
	 */
	public CEntityMissionConclusion GetMissionConclusionDetail(CEntityMissionConclusion cEntityMissionConclusion){
		CEntityMissionConclusion findResult=cBllMission.GetMissionConclusionDetail(cEntityMissionConclusion);
		return findResult;
	}
	/**
	 * 序号：missionmodule:17
	 * 功能：管理员获得该员工所有的任务延期
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:LinkedMap
	 */
	public LinkedMap GetEmployeeMissionDelay(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=cBllMission.GetEmployeeMissionDelay(cEntityEmployee);
		return findResult;
	}
	/**
	 * 序号：missionmodule:18
	 * 功能：管理员获得所有未开始与执行中的任务信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap GetAllMissionRunning(){
		LinkedMap findResult=cBllMission.GetAllMissionRunning();
		return findResult;
	}
	/**
	 * 序号：missionmodule:19
	 * 功能：统计员工号，员工账号，员工名，任务需执行数量（未接受，执行中）
	 * 参数：
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List GetMissionRuningNumberOfEmployeeInfo(){
		List findResult=cBllMission.GetMissionRuningNumberOfEmployeeInfo();
		return findResult;
	}
	/**
	 * 序号：missionmodule:20
	 * 功能：管理员获得所有未审核与已过期的任务信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap GetAllMissionDeal(){
		LinkedMap findResult=cBllMission.GetAllMissionDeal();
		return findResult;
	}
	
	/**
	 * 序号：missionmodule:21
	 * 功能：管理员获得所有已过期与已审核的任务信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap GetAllMissionComplete() {
		LinkedMap findResult=cBllMission.GetAllMissionComplete();
		return findResult;
	}
	/**
	 * 序号：missionmodule:22
	 * 功能：管理员获得所有任务撤销及其员工信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap GetAllMissionUndoWithEmployeeInfo(){
		LinkedMap findResult=cBllMission.GetAllMissionUndoWithEmployeeInfo();
		return findResult;
	}
	/**
	 * 序号：missionmodule:23
	 * 功能：管理员获得所有任务总结及其员工信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap GetAllMissionConclusionWithEmployeeInfo(){
		LinkedMap findResult=cBllMission.GetAllMissionConclusionWithEmployeeInfo();
		return findResult;
	}
	/**
	 * 序号：missionmodule:24
	 * 功能：管理员获得所有任务延期及其员工信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap GetAllMissionDelayWithEmployeeInfo(){
		LinkedMap findReuslt=cBllMission.GetAllMissionConclusionWithEmployeeInfo();
		return findReuslt;
	}
	
	/**
	 * 序号：missionmodule:25
	 * 功能：判断是否有未处理的任务
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public boolean hasWaitDealMission(){
		boolean bishave=cBllMission.hasWaitDealMission();
		return bishave;
	}
	
	/**
	 * 序号：missionmodule:26
	 * 功能：根据员工号获取其绑定的任务信息
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray getEmployeeMissionBindInfo(CEntityEmployee cEntityEmployee){
		CEntityMissionArray findResult=cBllMission.getEmployeeMissionBindInfo(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * 序号：missionmodule:27
	 * 功能：根据员工号获取其未绑定的任务信息
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityMissionArray
	 */
	public CEntityMissionArray getEmployeeMissionUnBindInfo(CEntityEmployee cEntityEmployee){
		CEntityMissionArray findResult=cBllMission.getEmployeeMissionUnBindInfo(cEntityEmployee);
		return findResult;
	}
	/*
	 *------------------------------ 客户模块 clientmodule----------------------------
	 */
	private CBllClient cBllClient;
	
	@Resource
	public void setcBllClient(CBllClient cBllClient) {
		this.cBllClient = cBllClient;
	}


	/**
	 * 序号：clientmodule:1 
	 * 功能：创建客户
	 * 参数：cEntityClient(本表字段)
	 * 返回值:boolean
	 */
	public boolean createClient(CEntityClient cEntityClient){
		boolean bisCreate=cBllClient.createClient(cEntityClient);
		return bisCreate;
		
	}
	
	/**
	 * 序号：clientmodule:2 
	 * 功能：分配客户
	 * 参数：cEntityClient(ClientId),cEntityEmployee(EmployeeId),cEntityVisitPlan(本表字段)
	 * 返回值:boolean
	 */
	public boolean distributionClient(CEntityClient cEntityClient,CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan) {
		boolean bisDistribution=cBllClient.distributionClient(cEntityClient, cEntityEmployee, cEntityVisitPlan);
		return bisDistribution;
	}
	/**
	 * 序号：clientmodule:3 
	 * 功能：提交拜访
	 * 参数：cEntityEmployee(EmployeeId),cEntityVisitPlan(VisitPlanId),cEntityVisitConclusion(本表字段)
	 * 返回值:boolean
	 */
	public boolean submitVisit(CEntityEmployee cEntityEmployee,CEntityVisitPlan cEntityVisitPlan,CEntityVisitConclusion cEntityVisitConclusion){
		boolean bisSubmit=cBllClient.submitVisit(cEntityEmployee, cEntityVisitPlan, cEntityVisitConclusion);
		return bisSubmit;
	}

	/**
	 * 序号：clientmodule:4 
	 * 功能：审核客户拜访
	 * 参数：ccEntityVisitPlan(VisitPlanId),cEntityVisitConclusion(VisitConclusionId)
	 * 	   OperateState(MyConstant.VisitConclusion.*)
	 * 返回值:boolean
	 */
	public boolean checkVisitConclusion(CEntityVisitPlan cEntityVisitPlan,CEntityVisitConclusion cEntityVisitConclusion,int OperateState) {
		boolean bisCheck=cBllClient.checkVisitConclusion(cEntityVisitPlan, cEntityVisitConclusion, OperateState);
		return bisCheck;
	}
	
	/**
	 * 序号：clientmodule:5
	 * 功能：提交客户
	 * 参数：cEntityClient(本表字段),cEntityEmployee(EmployeeId),cEntityClientSubmit(本表字段)
	 * 返回值:boolean
	 */
	public boolean submitCliet(CEntityEmployee cEntityEmployee,CEntityClient cEntityClient ) {
		boolean bisSubmit=cBllClient.submitCliet(cEntityEmployee, cEntityClient);
		return bisSubmit;
	}
	/**
	 * 序号：clientmodule:6
	 * 功能：客户考察
	 * 参数：cEntityClient(ClientId)
	 * 	   OperateState(MyConstant.ClientSubmit.*)
	 * 返回值:boolean
	 */
	public boolean checkClient(CEntityClient cEntityClient,int OperateState){
		boolean bisCheck=cBllClient.checkClient(cEntityClient, OperateState);
		return bisCheck;
	}

	/**
	 * 序号：clientmodule:7
	 * 功能：循环拜访
	 * 参数：cEntityVisitPlan(VisitPlanId,VisitPlanCycleType,VisitPlanCycleNumber,VisitPlanDays)
	 * 返回值:boolean
	 */
	public boolean setupVisitCycle(CEntityVisitPlan cEntityVisitPlan) {
		boolean bisSetup=cBllClient.setupVisitCycle(cEntityVisitPlan);
		return bisSetup;
	}
	/**
	 * 序号：clientmodule:8
	 * 功能：延期拜访计划
	 * 参数：cEntityVisitPlan(VisitPlanId,VisitPlanEndTime)
	 * 	   cEntityEmployee(EmployeeId)
	 * 返回值:boolean
	 */
	public boolean delayVisitPlan(CEntityVisitPlan cEntityVisitPlan,CEntityEmployee cEntityEmployee){
		boolean bisDelay=cBllClient.delayVisitPlan(cEntityVisitPlan, cEntityEmployee);
		return bisDelay;
	}

	/**
	 * 序号：clientmodule:9
	 * 功能：废弃拜访计划
	 * 参数：cEntityVisitPlan(VisitPlanId)
	 * 返回值:boolean
	 */
	public boolean abandonVisitPlan(CEntityVisitPlan cEntityVisitPlan){
		boolean bisAbandon=cBllClient.abandonVisitPlan(cEntityVisitPlan);
		return bisAbandon;
	}
	/**
	 * 序号：clientmodule:10
	 * 功能：撤销拜访计划
	 * 参数：cEntityVisitPlan(VisitPlanId)
	 * 	   cEntityVisitUndo(本表字段)
	 * 返回值:boolean
	 */
	public boolean undoVisitPlan(CEntityVisitPlan cEntityVisitPlan,CEntityVisitUndo cEntityVisitUndo) {
		boolean bisUndo=cBllClient.undoVisitPlan(cEntityVisitPlan, cEntityVisitUndo);
		return bisUndo;
	}
	
	/**
	 * 序号：clientmodule:11
	 * 功能：撤回拜访计划
	 * 参数：cEntityVisitUndo(VisitUndoId)
	 * 返回值:boolean
	 */
	public boolean recallVisitPlan(CEntityVisitUndo cEntityVisitUndo) {
		boolean bisRecall=cBllClient.recallVisitPlan(cEntityVisitUndo);
		return bisRecall;
	}
	/**
	 * 序号：clientmodule:12
	 * 功能：删除客户
	 * 参数：cEntityClient(ClientId)
	 * 返回值:boolean
	 */
	public boolean delClient(CEntityClient cEntityClient){
		boolean bisDel=cBllClient.delClient(cEntityClient);
		return bisDel;
	}
	
	/**
	 * 序号：clientmodule:13
	 * 功能：进入未开始拜访计划 
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:boolean
	 */
	public CEntityVisitPlanArray GetNoStartVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=cBllClient.GetNoStartVisitPlan(cEntityEmployee);
		return cEntityVisitPlanArray;
	}

	/**
	 * 序号：clientmodule:14
	 * 功能：进入执行中拜访计划
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray GetUnderwayVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=cBllClient.GetUnderwayVisitPlan(cEntityEmployee);
		return cEntityVisitPlanArray;
	}

	/**
	 * 序号：clientmodule:15
	 * 功能：进入终止态拜访计划（未审核,已审核,已撤销,已失败）
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	public CEntityVisitPlanArray GetCompleteVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=cBllClient.GetCompleteVisitPlan(cEntityEmployee);
		return cEntityVisitPlanArray;
	}

	/**
	 * 序号：clientmodule:16
	 * 功能：查看客户详情
	 * 参数：cEntityClient(ClientId)
	 * 返回值:CEntityClient
	 */
	public CEntityClient GetClientInfo(CEntityClient cEntityClient){
		CEntityClient findResult=cBllClient.GetClientInfo(cEntityClient);
		return findResult;
	}
	/**
	 * 序号：clientmodule:17
	 * 功能：查看客户提交纪录
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientSubmitArray GetClientSubmit(CEntityEmployee cEntityEmployee){
		CEntityClientSubmitArray cEntityClientSubmitArray=cBllClient.GetClientSubmit(cEntityEmployee);
		return cEntityClientSubmitArray;
	}
	/**
	 * 序号：clientmodule:18
	 * 功能：进入拜访客户
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientArray GetEmployeeClient(CEntityEmployee cEntityEmployee){
		CEntityClientArray cEntityClientArray=cBllClient.GetEmployeeClient(cEntityEmployee);
		return cEntityClientArray;
	}
	
	/**
	 * 序号：clientmodule:19
	 * 功能：得到所有的客户
	 * 参数：无
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientArray GetAllClient(){
		CEntityClientArray cEntityClientArray=cBllClient.GetAllClient();
		return cEntityClientArray;
	}
	/**
	 * 序号：clientmodule:20
	 * 功能：按员工号获得所有客户
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientArray GetAllEmployeeClient(CEntityEmployee cEntityEmployee){
		CEntityClientArray cEntityClientArray=cBllClient.GetAllEmployeeClient(cEntityEmployee);
		return cEntityClientArray;
	}
	
	/**
	 * 序号：clientmodule:21
	 * 功能：修改客户详情
	 * 参数：CEntityClient(本表字段)
	 * 返回值:boolean
	 */
	public boolean UpdateClient(CEntityClient cEntityClient){
		boolean bisUpdate=cBllClient.UpdateClient(cEntityClient);
		return bisUpdate;
	}
	
	/**
	 * 序号：clientmodule:22
	 * 功能：管理员得到员工分配的客户名
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:List<String>
	 */
	public List<String> GetAllEmployeeClientName(CEntityEmployee cEntityEmployee){
		List<String> result=cBllClient.GetAllEmployeeClientName(cEntityEmployee);
		return result;
	}
	/**
	 * 序号：clientmodule:23
	 * 功能：管理员按客户名搜索所有的客户信息
	 * 参数：CEntityClient(ClientName)
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientArray GetAllClientByClientName(CEntityClient cEntityClient){
		CEntityClientArray cEntityClientArray=cBllClient.GetAllClientByClientName(cEntityClient);
		return cEntityClientArray;
	}
	/**
	 * 序号：clientmodule:24
	 * 功能：管理员取消分配
	 * 参数：CEntityClient(ClientName)
	 * 返回值:CEntityClientArray
	 */
	public boolean cancleClientDistuibution(CEntityClient cEntityClient){
		boolean bisCancle=cBllClient.cancleClientDistuibution(cEntityClient);
		return bisCancle;
	}
	/**
	 * 序号：clientmodule:25
	 * 功能：管理员获得员工的所有拜访计划含客户信息
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:HashedMap
	 */
	public HashedMap getAllEmployeeVisitPlan(CEntityEmployee cEntityEmployee){
		HashedMap findResult=cBllClient.getAllEmployeeVisitPlan(cEntityEmployee);
		return findResult;
	}
	/**
	 * 序号：clientmodule:26
	 * 功能：管理员查看拜访计划详情
	 * 参数：CEntityVisitPlan(VisitPlanId)
	 * 返回值:CEntityVisitConclusion
	 */
	public CEntityVisitConclusion getVisitConclusionDetail(CEntityVisitPlan cEntityVisitPlan){
		CEntityVisitConclusion cEntityVisitConclusion=cBllClient.getVisitConclusionDetail(cEntityVisitPlan);
		return cEntityVisitConclusion;
	}
	
	/**
	 * 序号：clientmodule:27
	 * 功能：管理员查看该员工所有的拜访总结
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray
	 */
	public LinkedMap getEmployeeVisitConclusion(CEntityEmployee cEntityEmployee) {
		LinkedMap find=cBllClient.getEmployeeVisitConclusion(cEntityEmployee);
		return find;
	}
	
	/**
	 * 序号：clientmodule:28
	 * 功能：管理员查看拜访计划详情
	 * 参数：CEntityVisitConclusion(VisitConclusionId)
	 * 返回值:CEntityVisitConclusion
	 */
	public CEntityVisitConclusion getVisitConclusion(CEntityVisitConclusion cEntityVisitConclusion){
		CEntityVisitConclusion findResult=cBllClient.getVisitConclusion(cEntityVisitConclusion);
		return findResult;
		
	}
	/**
	 * 序号：clientmodule:29
	 * 功能：管理员获得该员工所有的拜访延期
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitDealyArray
	 */
	public LinkedMap getEmployeeVisitDealy(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=cBllClient.getEmployeeVisitDealy(cEntityEmployee);
		return findResult;
	}
	/**
	 * 序号：clientmodule:30
	 * 功能：管理员获得所有未分配的客户
	 * 参数：无
	 * 返回值:CEntityClientArray
	 */
	public CEntityClientArray getNodistributionClient(){
		CEntityClientArray findResult=cBllClient.getNodistributionClient();
		return findResult;
	}
	/**
	 * 序号：clientmodule:31
	 * 功能：管理员获得员工分配客户情况
	 * 参数：无
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List getClientStatisticalNumber(){
		List findResult=cBllClient.getClientStatisticalNumber();
		return findResult;
	}
	/**
	 * 序号：clientmodule:32
	 * 功能：管理员获得分配客户信息
	 * 参数：无
	 * 返回值:HashedMap
	 */
	public HashedMap getClientDistribution() {
		HashedMap findResult=cBllClient.getClientDistribution();
		return findResult;
	}
	
	/**
	 * 序号：clientmodule:33
	 * 功能：管理员获得未审核客户信息
	 * 参数：无
	 * 返回值:LinkedMap
	 */
	public LinkedMap getNoCheckClient(){
		LinkedMap findResult=cBllClient.getNoCheckClient();
		return findResult;
	}
	/**
	 * 序号：clientmodule:34
	 * 功能：管理员获得所有已删客户信息
	 * 参数：无
	 * 返回值:LinkedMap
	 */
	public CEntityClientArray queryDelClient(){
		CEntityClientArray findResult=cBllClient.queryDelClient();
		return findResult;
	}
	/**
	 * 序号：clientmodule:35
	 * 功能：判断是否有未审核的客户
	 * 参数：无
	 * 返回值:boolean(true:有未审核的,false:没有)
	 */
	public boolean hasNocheckClient() {
		boolean bishas=cBllClient.hasNocheckClient();
		return bishas;
	}
	
	/**
	 * 序号：clientmodule:36
	 * 功能：得到所有未开始，执行中的拜访计划
	 * 参数：无
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllVisitPlanAllRunning(){
		LinkedMap findResult=cBllClient.getAllVisitPlanAllRunning();
		return findResult;
	}
	/**
	 * 序号：clientmodule:37
	 * 功能：得到未分配客户的客户号，客户名，客户公司
	 * 参数：无
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List getClientUndistriInfo(){
		List findReuslt=cBllClient.getClientUndistriInfo();
		return findReuslt;
	}
	
		/**
	 * 序号：clientmodule:38
	 * 功能：得到所有未审核，已过期的拜访计划
	 * 参数：无
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllVisitPlanWaitDeal(){
		LinkedMap findResult=cBllClient.getAllVisitPlanWaitDeal();
		return findResult;
	}
	
	
	/**
	 * 序号：clientmodule:39
	 * 功能：获取所有员工的已审核，已失败拜访信息
	 * 参数：无
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllVisitPlanComplete(){
		LinkedMap findResult=cBllClient.getAllVisitPlanComplete();
		return findResult;
	}
	
	
	/**
	 * 序号：clientmodule:40
	 * 功能：获取所有未删员工的撤销拜访信息
	 * 参数：无
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllVisitUndoInfo(){
		LinkedMap findResult=cBllClient.getAllVisitUndoInfo();
		return findResult;
	}
	
	/**
	 * 序号：clientmodule:41
	 * 功能：获取所有未删员工的拜访总结及信息
	 * 参数：无
	 * 返回值:LinkedMap(CEntityEmployee,CEntityClient,CEntityVisitPlan,CEntityVisitConclusion)
	 */
	public LinkedMap getAllVisitConclusionInfo(){
		LinkedMap findResult=cBllClient.getAllVisitConclusionInfo();
		return findResult;
	}
	
	/**
	 * 序号：clientmodule:42
	 * 功能：获取所有未删员工的拜访延期信息
	 * 参数：无
	 * 返回值:LinkedMap(CEntityEmployee,CEntityClient,CEntityVisitPlan,CEntityVisitDelay)
	 */
	public LinkedMap getAllVisitDelayInfo(){
		LinkedMap findReuslt=cBllClient.getAllVisitDelayInfo();
		return findReuslt;
	}
	
	
	/**
	 * 序号：clientmodule:43
	 * 功能：判断是否还有未处理的拜访计划
	 * 参数：无
	 * 返回值:boolean 
	 */
	public boolean hasWaitDealVisit() {
		boolean bishas=cBllClient.hasWaitDealVisit();
		return bishas;
	}
	/**
	 * 序号：clientmodule:44
	 * 功能：根据员工号获取其绑定的拜访计划信息
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray 
	 */
	public CEntityVisitPlanArray getBindVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=cBllClient.getBindVisitPlan(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	
	
	/**
	 * 序号：clientmodule:45
	 * 功能：根据员工号获取其未绑定的拜访计划信息
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityVisitPlanArray 
	 */
	public CEntityVisitPlanArray getUnBindVisitPlan(CEntityEmployee cEntityEmployee){
		CEntityVisitPlanArray cEntityVisitPlanArray=cBllClient.getUnBindVisitPlan(cEntityEmployee);
		return cEntityVisitPlanArray;
	}
	/*
	 *------------------------------ 出差模块bussinessmodule----------------------------
	 */

	private CBllBussiness cBllBussiness;
	
	@Resource
	public void setcBllBussiness(CBllBussiness cBllBussiness) {
		this.cBllBussiness = cBllBussiness;
	}


	/**
	 * 序号：bussinessmodule:1 
	 * 功能：创建出差
	 * 参数：cEntityEmployee(EmployeeId),cEntityBussiness(本表字段) 
	 * 返回值:boolean
	 */
	public boolean createBussiness(CEntityBussiness cEntityBussiness, CEntityEmployee cEntityEmployee) {
		boolean bisCreate=cBllBussiness.createBussiness(cEntityBussiness, cEntityEmployee);
		
		return bisCreate;
	}
	/**
	 * 序号：bussinessmodule:2
	 * 功能：邦定出差活动
	 * 参数：
	 * 返回值:boolean
	 */
	public boolean bindBussinessActivity(CEntityBussinessActivity cEntityBussinessActivity,CEntityBussiness cEntityBussiness) {
		boolean bisUpdate=cBllBussiness.bindBussinessActivity(cEntityBussinessActivity,cEntityBussiness);
		return bisUpdate;
	}
	
	/**
	 * 序号：bussinessmodule:3
	 * 功能：解绑出差活动
	 * 参数：
	 * 返回值:boolean
	 */
	public boolean unbindBussinessActivity(CEntityBussinessActivity cEntityBussinessActivity) {
		boolean bisupdate=cBllBussiness.unbindBussinessActivity(cEntityBussinessActivity);
		return bisupdate;
	}

	/**
	 * 序号：bussinessmodule:4
	 * 功能：出差登记
	 * 参数：cEntityBussiness(BussinessId,BussinessRegisterTime)
	 * 返回值:boolean
	 */
	public boolean registerBussiness(CEntityBussiness cEntityBussiness){
		boolean bisRegister=cBllBussiness.registerBussiness(cEntityBussiness);
		return bisRegister;
	}
	
	/**
	 * 序号：bussinessmodule:5
	 * 功能：抵达目的地登记
	 * 参数：cEntityBussiness(BussinessId,BussinessInAddress,BussinessInTime)
	 * 返回值:boolean
	 */
	public boolean inregisterBussiness(CEntityBussiness cEntityBussiness) {
		boolean bisIn=cBllBussiness.inregisterBussiness(cEntityBussiness);
		return bisIn;
	}
	
	/**
	 * 序号：bussinessmodule:6
	 * 功能：离开目的地登记
	 * 参数：cEntityBussiness(BussinessId,BussinessOutAddress,BussinessOutTime)
	 * 返回值:boolean
	 */
	public boolean outregisterBussiness(CEntityBussiness cEntityBussiness) {
		boolean bisOut=cBllBussiness.outregisterBussiness(cEntityBussiness);
		
		return bisOut;
	}
		
	/**
	 * 序号：bussinessmodule:7
	 * 功能：归来登记
	 * 参数：cEntityBussiness(BussinessId,BussinessReturnTime)
	 * 返回值:boolean
	 */
	public boolean returnregisterBussiness(CEntityBussiness cEntityBussiness) {
		boolean bisReturn=cBllBussiness.returnregisterBussiness(cEntityBussiness);
		return bisReturn;
	}
	
	/**
	 * 序号：bussinessmodule:8
	 * 功能：审核出差通过
	 * 参数：cEntityBussiness(BussinessId)
	 * 返回值:boolean
	 */
	public boolean checkBussinessPass(CEntityBussiness cEntityBussiness) {
		boolean bisCheck=cBllBussiness.checkBussinessPass(cEntityBussiness);
		return bisCheck;
	}
	
	/**
	 * 序号：bussinessmodule:9
	 * 功能：审核出差不通过
	 * 参数：cEntityBussiness(BussinessId)
	 * 	   cEntityBussinessBadrecord(本表字段)
	 * 返回值:boolean
	 */
	public boolean checkBussinessNopass(CEntityBussiness cEntityBussiness ) {
		boolean bisCheck=cBllBussiness.checkBussinessNopass(cEntityBussiness);
		return bisCheck;
	}
	
	/**
	 * 序号：bussinessmodule:10
	 * 功能：外勤人员进入出差(当前仅有一个未登记或执行中出差)
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityBussiness
	 */
	public CEntityBussiness GetBussinessRunningState(CEntityEmployee cEntityEmployee) {
		CEntityBussiness cEntityBussiness=cBllBussiness.GetBussinessRunningState(cEntityEmployee);
		return cEntityBussiness;
	}

	/**
	 * 序号：bussinessmodule:11
	 * 功能：外勤人员进入出差纪录
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityBussinessArray
	 */
	public CEntityBussinessArray GetBussinessRecall(CEntityEmployee cEntityEmployee){
		CEntityBussinessArray cEntityBussinessArray=cBllBussiness.GetBussinessRecall(cEntityEmployee);
		return cEntityBussinessArray;
	}
	/**
	 * 序号：bussinessmodule:12
	 * 功能：查看出差纪录详情
	 * 参数：cEntityBussiness(BussinessId)
	 * 返回值:CEntityBussiness
	 */
	public CEntityBussiness GetBussinessDetail(CEntityBussiness cEntityBussiness){
		CEntityBussiness findResult=cBllBussiness.GetBussinessDetail(cEntityBussiness);
		return findResult;
	}
	/**
	 * 序号：bussinessmodule:13
	 * 功能：获取出差活动
	 * 参数：cEntityBussiness(BussinessId)
	 * 返回值:HashMap<String, Object>
	 */
	public HashMap<String, Object> GetGetBussinessActivity(CEntityBussiness cEntityBussiness){
		HashMap<String, Object> result=cBllBussiness.GetGetBussinessActivity(cEntityBussiness);
		return result;
	}
	/**
	 * 序号：bussinessmodule:14
	 * 功能：判断出差是否有绑定的出差活动
	 * 参数：CEntityBussiness(BussinessId)
	 * 返回值:boolean
	 */
	public boolean checkBussinesHavaBussinessActivity(CEntityBussiness cEntityBussiness){
		boolean bishave=cBllBussiness.checkBussinesHavaBussinessActivity(cEntityBussiness);
		return bishave;
	}
	
	
	/**
	 * 序号：bussinessmodule:15
	 * 功能：撤销出差，不设计出差活动
	 * 参数：CEntityBussiness(BussinessId),CEntityBussinessUndo(本表字段)
	 * 返回值:boolean
	 */
	public boolean undoBussiness(CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo) {
		boolean bisUndo=cBllBussiness.undoBussiness(cEntityBussiness, cEntityBussinessUndo);
		return bisUndo;
	}
	
	/**
	 * 序号：bussinessmodule:15
	 * 功能：撤销出差，并撤销出差活动
	 * 参数：CEntityBussiness(BussinessId),CEntityBussinessUndo(本表字段)
	 * 返回值:boolean
	 */
	public boolean undoBussinessWithUndoBussinessActivity(CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo){
		boolean bisUndo=cBllBussiness.undoBussinessWithUndoBussinessActivity(cEntityBussiness, cEntityBussinessUndo);
		return bisUndo;
	}
	
	/**
	 * 序号：bussinessmodule:16
	 * 功能：撤销出差，解绑出差活动
	 * 参数：CEntityBussiness(BussinessId),CEntityBussinessUndo(本表字段)
	 * 返回值:boolean
	 */
	public boolean undoBussinessWithUnbindBussinessActivity(CEntityBussiness cEntityBussiness,CEntityBussinessUndo cEntityBussinessUndo){
		boolean bisUndo=cBllBussiness.undoBussinessWithUnbindBussinessActivity(cEntityBussiness, cEntityBussinessUndo);
		return bisUndo;
	}
	

	/**
	 * 序号：bussinessmodule:17
	 * 功能：审核出差通过，但要添加一条出差不良记录
	 * 参数：CEntityBussiness(BussinessId),CEntityBussinessBadrecord(本表字段)
	 * 返回值:boolean
	 */
	public boolean checkBussinessPassWithBadrecall(CEntityBussiness cEntityBussiness,CEntityBussinessBadrecord cEntityBussinessBadrecord){
		boolean bischeck=cBllBussiness.checkBussinessPassWithBadrecall(cEntityBussiness, cEntityBussinessBadrecord);
		return bischeck;
	}
	/**
	 * 序号：bussinessmodule:18
	 * 功能：得到该员工的所有出差不良记录
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:LinkedMap
	 */
	public LinkedMap getEmployeeBussinessBadrecall(CEntityEmployee cEntityEmployee){
		LinkedMap findResult=cBllBussiness.getEmployeeBussinessBadrecall(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * 序号：bussinessmodule:19
	 * 功能：获取所有未删员工的出差信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllBussinessRunning(){
		LinkedMap findReuslt=cBllBussiness.getAllBussinessRunning();
		return findReuslt;
	}
	
	/**
	 * 序号：bussinessmodule:20
	 * 功能：获取可增出差的员工信息（即员工没有未登记，执行中，未审核的出差）
	 * 参数：
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List getBussinessAddOkEmployee(){
		List findReuslt=cBllBussiness.getBussinessAddOkEmployee();
		return findReuslt;
	}
	
	/**
	 * 序号：bussinessmodule:21
	 * 功能：获取所有未删员工的待处理出差信息(未审核)
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllBussinessWaitDeal(){
		LinkedMap findResult=cBllBussiness.getAllBussinessWaitDeal();
		return findResult;
	}
	
	/**
	 * 序号：bussinessmodule:22
	 * 功能：获取所有未删员工的已结束出差信息（审核通过，审核不通过）
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllBussinessComplete(){
		LinkedMap findResult=cBllBussiness.getAllBussinessComplete();
		return findResult;
	}
	
	/**
	 * 序号：bussinessmodule:23
	 * 功能：获取所有未删员工出差撤销记录
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllBussinessUndoInfo(){
		LinkedMap findResult=cBllBussiness.getAllBussinessUndoInfo();
		return findResult;
	}
	
	/**
	 * 序号：bussinessmodule:24
	 * 功能：获取所有未删员工的出差不良记录信息
	 * 参数：
	 * 返回值:LinkedMap
	 */
	public LinkedMap getAllBussinessBadrecordInfo(){
		LinkedMap findResult=cBllBussiness.getAllBussinessBadrecordInfo();
		return findResult;
	}
	
	/**
	 * 序号：bussinessmodule:25
	 * 功能：判断是否还有未处理的出差记录
	 * 参数：
	 * 返回值:boolean
	 */
	public boolean hasWaitDealBussiness(){
		boolean bishas=cBllBussiness.hasWaitDealBussiness();
		return bishas;
	}
	/*
	 *------------------------------ 其他模块othermodule----------------------------
	 */
	private CBllOther cBllOther;
	
	
	@Resource
	public void setcBllOther(CBllOther cBllOther) {
		this.cBllOther = cBllOther;
	}


	/**
	 * 序号：othermodule:1 
	 * 功能：外勤人员签到
	 * 参数：cEntityEmployee(EmployeeId),cEntityMission(AttendanceRegisterTime) 
	 * 返回值:boolean
	 */
	public boolean registerAttendance(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance) {
		boolean bisRegister=cBllOther.registerAttendance(cEntityEmployee, cEntityAttendance);
		return bisRegister;
	}
	
	/**
	 * 序号：othermodule:2
	 * 功能：外勤人员签退
	 * 参数：cEntityEmployee(EmployeeId),cEntityMission(AttendanceSignoutTime) 
	 * 返回值:boolean
	 */
	public boolean signoutAttendance(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance) {
		boolean bisSignout=cBllOther.signoutAttendance(cEntityEmployee, cEntityAttendance);
		return bisSignout;
	}

	/**
	 * 序号：othermodule:3
	 * 功能：外勤人员登录
	 * 参数：cEntityEmployee(EmployeeAccount,EmployeePassword)
	 * 返回值:CEntityEmployee
	 */
	public CEntityEmployee loginEmployee(CEntityEmployee cEntityEmployee) {
		CEntityEmployee findResult=cBllOther.loginEmployee(cEntityEmployee);
		return findResult;
	}
	
	/**
	 * 序号：othermodule:4
	 * 功能：外勤人员进入考勤
	 * 参数：cEntityEmployee(EmployeeId)
	 * 返回值:CEntityAttendance
	 */
	public CEntityAttendance enterAttendanceForEmployee(CEntityEmployee cEntityEmployee){
		CEntityAttendance findResult=cBllOther.enterAttendanceForEmployee(cEntityEmployee);
		return findResult;
	}
	/**
	 * 序号：othermodule:5
	 * 功能：外勤人员进入通讯录
	 * 参数：无
	 * 返回值:CEntityEmployeeArray
	 */
	public CEntityEmployeeArray enterCommunition(){
		CEntityEmployeeArray cEntityEmployeeArray=cBllOther.enterCommunition();
		return cEntityEmployeeArray;
	}
	/**
	 * 序号：othermodule:6
	 * 功能：进入通知公告
	 * 参数：无
	 * 返回值:CEntityNoticeArray
	 */
	public CEntityNoticeArray enterNotice(){
		CEntityNoticeArray cEntityNoticeArray=cBllOther.enterNotice();
		return cEntityNoticeArray;
	}
	/**
	 * 序号：othermodule:7
	 * 功能：提交投诉建议
	 * 参数：CEntityEmployee(EmployeeId),cEntitySuggest(本表字段)
	 * 返回值:boolean
	 */
	public boolean submitSuggest(CEntityEmployee cEntityEmployee,CEntitySuggest cEntitySuggest){
		boolean bisSubmit=cBllOther.submitSuggest(cEntityEmployee, cEntitySuggest);
		return bisSubmit;
	}
	/**
	 * 序号：othermodule:8
	 * 功能：管理员登录
	 * 参数：CEntityAdministrator(AdministratorAccount,AdministratorPassword)
	 * 返回值:boolean
	 */
	public boolean aministorLogin(CEntityAdministrator cEntityAdministrator){
		boolean bisLogin=cBllOther.aministorLogin(cEntityAdministrator);
		return bisLogin;
	}
	
	/**
	 * 序号：othermodule:9
	 * 功能：管理员删除员工
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:boolean
	 */
	public boolean deleteEmployee(CEntityEmployee cEntityEmployee){
		boolean bisDelete=cBllOther.deleteEmployee(cEntityEmployee);
		return bisDelete;
	}

	/**
	 * 序号：othermodule:10
	 * 功能：管理员修改员工
	 * 参数：CEntityEmployee(本表字段)
	 * 返回值:boolean
	 */
	public boolean updateEmployee(CEntityEmployee cEntityEmployee){
		boolean bisUpdate=cBllOther.updateEmployee(cEntityEmployee);
		return bisUpdate;
	}
	
	/**
	 * 序号：othermodule:11
	 * 功能：管理员增加员工
	 * 参数：CEntityEmployee(本表字段)
	 * 返回值:boolean
	 */
	public boolean addEmployee(CEntityEmployee cEntityEmployee){
		boolean bisAdd=cBllOther.addEmployee(cEntityEmployee);
		return bisAdd;
	}
	
	/**
	 * 序号：othermodule:12
	 * 功能：获得当天所有的考勤记录
	 * 参数：CEntityAttendance(AttendanceDate)
	 * 返回值:CEntityAttendanceArray
	 */
	public HashedMap queryAllAttendanceWithEmployeeDateByAttendaceData(CEntityAttendance cEntityAttendance){
		HashedMap findResult=cBllOther.queryAllAttendanceWithEmployeeDateByAttendaceData(cEntityAttendance);
		return findResult;
	}
	
	/**
	 * 序号：othermodule:13
	 * 功能：管理员获得所有删除的员工
	 * 参数：无
	 * 返回值:CEntityAttendanceArray
	 */
	public CEntityEmployeeArray getAllDelEmployee(){
		CEntityEmployeeArray cEntityEmployeeArray=cBllOther.getAllDelEmployee();
		return cEntityEmployeeArray;
	}
	/**
	 * 序号：othermodule:14
	 * 功能：管理员获得所有未删员工的姓名
	 * 参数：无
	 * 返回值:List<String>
	 */
	public List<String> getAllEmployeeName(){
		List<String> result=cBllOther.getAllEmployeeName();
		return result;
	}
	
	/**
	 * 序号：othermodule:15
	 * 功能：管理员按员工名进行搜索
	 * 参数：CEntityEmployee(EmployeeName)
	 * 返回值:CEntityEmployeeArray
	 */
	public CEntityEmployeeArray getEmployeeSearchResult(CEntityEmployee cEntityEmployee){
		CEntityEmployeeArray cEntityEmployeeArray=cBllOther.getEmployeeSearchResult(cEntityEmployee);
		return cEntityEmployeeArray;
	}
	/**
	 * 序号：othermodule:16
	 * 功能：管理员按员工部门进行搜索
	 * 参数：CEntityEmployee(EmployeeDepartment)
	 * 返回值:CEntityEmployeeArray
	 */
	public CEntityEmployeeArray getDepartmentEmployee(CEntityEmployee cEntityEmployee){
		CEntityEmployeeArray cEntityEmployeeArray=cBllOther.getDepartmentEmployee(cEntityEmployee);
		return cEntityEmployeeArray;
	}
	
	
	/**
	 * 序号：othermodule:17
	 * 功能：管理员获取员工所有的考勤记录
	 * 参数：CEntityEmployee(EmployeeId)
	 * 返回值:CEntityEmployeeArray
	 */
	public CEntityAttendanceArray getAllEmployeeAttendance(CEntityEmployee cEntityEmployee) {
		CEntityAttendanceArray cEntityAttendanceArray=cBllOther.getAllEmployeeAttendance(cEntityEmployee);
		return cEntityAttendanceArray;
	}
	
	/**
	 * 序号：othermodule:18
	 * 功能：管理员按日期查询员工该日考勤记录
	 * 参数：CEntityEmployee(EmployeeId)，CEntityAttendance(AttendanceDate)
	 * 返回值:CEntityAttendanceArray
	 */
	public CEntityAttendanceArray getEmployeeDateAttendance(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance){
		CEntityAttendanceArray cEntityAttendanceArray=cBllOther.getEmployeeDateAttendance(cEntityEmployee, cEntityAttendance);
		return cEntityAttendanceArray;
	}
	/**
	 * 序号：othermodule:19
	 * 功能：管理员按日期和员工名查询员工该日考勤记录
	 * 参数：CEntityEmployee(EmployeeId)，CEntityAttendance(AttendanceDate)
	 * 返回值:CEntityAttendanceArray
	 */
	public HashedMap getSearchEmployeeDate(CEntityEmployee cEntityEmployee,CEntityAttendance cEntityAttendance){
		HashedMap findResult=cBllOther.getSearchEmployeeDate(cEntityEmployee, cEntityAttendance);
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
		List findReuslt=cBllOther.getAttendanceStatisticalYear(year);
		return findReuslt;
	}
	/**
	 * 序号：othermodule:21
	 * 功能：管理员按年,月统计所有员工的考勤记录
	 * 参数：year(yyyy),month(mm)
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List getAttendanceStatisticalByYearAndMonth(String year,String month){
		List findReuslt=cBllOther.getAttendanceStatisticalByYearAndMonth(year,month);
		return findReuslt;
	}
	/**
	 * 序号：othermodule:22
	 * 功能：管理员按年,月,员工名统计员工的考勤记录
	 * 参数：year(yyyy),month(mm)，CEntityEmployee(EmployeeName)
	 * 返回值:List
	 */
	@SuppressWarnings("unchecked")
	public List getAttendanceStatisticalByYearMonthAndEmployeeName(String year,String month,CEntityEmployee cEntityEmployee){
		List findResult=cBllOther.getAttendanceStatisticalByYearMonthAndEmployeeName(year, month, cEntityEmployee);
		return findResult;
	}
	
	/**
	 * 序号：othermodule:23
	 * 功能：管理员增加一条通知公告
	 * 参数：cEntityNotice(本表字段)
	 * 返回值:boolean 
	 */
	public boolean createNotice(CEntityNotice cEntityNotice){
		boolean bisAdd=cBllOther.createNotice(cEntityNotice);
		return bisAdd;
	}
	
	/**
	 * 序号：othermodule:24
	 * 功能：获取所有的投诉建议
	 * 参数：
	 * 返回值:LinkedMap 
	 */
	public LinkedMap getAllSuggestInfo(){
		LinkedMap findResult=cBllOther.getAllSuggestInfo();
		return findResult;
		
	}
	
	/**
	 * 序号：othermodule:25
	 * 功能：员工增加一条地址
	 * 参数：
	 * 返回值:boolean 
	 */
	public boolean createAddress(CEntityAddress cEntityAddress) {
		boolean bisCre=cBllOther.createAddress(cEntityAddress);
		return bisCre;
	}
	
	/**
	 * 序号：othermodule:26
	 * 功能：按员工号及查询天数获取地址
	 * 参数：
	 * 返回值:LinkedMap 
	 */
	public LinkedMap getAddressInfo(CEntityEmployee cEntityEmployee,int days) {
		LinkedMap findResult=cBllOther.getAddressInfo(cEntityEmployee, days);
		return findResult;
	}
	
	/**
	 * 序号：othermodule:27
	 * 功能：获取所有未删员工的信息
	 * 参数：
	 * 返回值:CEntityEmployeeArray 
	 */
	public CEntityEmployeeArray getAllNoDelEmployee(){
		CEntityEmployeeArray findResult=cBllOther.getAllNoDelEmployee();
		return findResult;
	}
}
