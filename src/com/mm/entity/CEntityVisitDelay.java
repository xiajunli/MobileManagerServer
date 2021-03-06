package com.mm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mm.tool.MyOpcode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



//拜访延期
@Entity
@Table(name="VisitDelay")
public class CEntityVisitDelay {
	private int m_iVisitDelayId;			//拜访延期号，主键，自增
	private String m_sVisitDelayTime;		//拜访延期时间
	private String m_sVisitDelayDeadline;	//拜访延期期限
//	private int m_iEmployeeId;
//	@Column(name = "EmployeeId")
//	public int getM_iEmployeeId() {
//		return m_iEmployeeId;
//	}
//	
//
//	public void setM_iEmployeeId(int mIEmployeeId) {
//		m_iEmployeeId = mIEmployeeId;
//	}
//	
	private CEntityVisitPlan cEntityVisitPlan;
	
	public CEntityVisitDelay() {
	}

	public CEntityVisitDelay(int mIVisitDelayId, String mSVisitDelayTime,
			String mSVisitDelayDeadline) {
		m_iVisitDelayId = mIVisitDelayId;
		m_sVisitDelayTime = mSVisitDelayTime;
		m_sVisitDelayDeadline = mSVisitDelayDeadline;
	}

	
	
	
	public static class BuildJsonObject {
		private JSONObject outjson = new JSONObject();

		public BuildJsonObject() {
		}
		
		public BuildJsonObject VisitDelayId(int m_iVisitDelayId){
			outjson.put(MyOpcode.VisitDelay.VisitDelayId, m_iVisitDelayId);
			return this;
		}
		public BuildJsonObject VisitDelayTime(String m_sVisitDelayTime){
			outjson.put(MyOpcode.VisitDelay.VisitDelayTime, m_sVisitDelayTime);
			return this;
		}
		public BuildJsonObject VisitDelayDeadline(String m_sVisitDelayDeadline){
			outjson.put(MyOpcode.VisitDelay.VisitDelayDeadline, m_sVisitDelayDeadline);
			return this;
		}
		
		public BuildJsonObject Operation(int operation) {
			outjson.put(MyOpcode.Operation.OPERATION, operation);
			return this;
		}

		public BuildJsonObject Check(boolean check) {
			outjson.put(MyOpcode.Operation.CHECK, check);
			return this;
		}

		public BuildJsonObject MyJSONArray(JSONArray array) {
			outjson.put(MyOpcode.VisitDelay.VisitDelayList, array);
			return this;
		}
		
		public BuildJsonObject ToSingle(CEntityVisitDelay cEntityVisitDelay) {
			outjson.put(MyOpcode.VisitDelay.VisitDelayId, cEntityVisitDelay.m_iVisitDelayId);
			outjson.put(MyOpcode.VisitDelay.VisitDelayTime, cEntityVisitDelay.m_sVisitDelayTime);
			outjson.put(MyOpcode.VisitDelay.VisitDelayDeadline, cEntityVisitDelay.m_sVisitDelayDeadline);
			return this;
		}
		
		public JSONObject build() {
			return outjson;
		}
	}
	
	



	public static class Builder{
		private int m_iVisitDelayId;			//拜访延期号，主键，自增
		private String m_sVisitDelayTime;		//拜访延期时间
		private String m_sVisitDelayDeadline;	//拜访延期期限
		
		public Builder(){}
		
		public Builder VisitDelayId(int m_iVisitDelayId){
			this.m_iVisitDelayId=m_iVisitDelayId;
			return this;
		}
		
		public Builder VisitDelayTime(String m_sVisitDelayTime){
			this.m_sVisitDelayTime=m_sVisitDelayTime;
			return this;
		}
		
		public Builder VisitDelayDeadline(String m_sVisitDelayDeadline){
			this.m_sVisitDelayDeadline=m_sVisitDelayDeadline;
			return this;
		}
		
		public CEntityVisitDelay build(){
			return new CEntityVisitDelay(this);
		}
	}
	
	private CEntityVisitDelay(Builder b){
		m_iVisitDelayId=b.m_iVisitDelayId;
		m_sVisitDelayTime=b.m_sVisitDelayTime;
		m_sVisitDelayDeadline=b.m_sVisitDelayDeadline;
	}
	
	
	@Id
	@GeneratedValue
	@Column(name="VisitDelayId")
	public int getM_iVisitDelayId() {
		return m_iVisitDelayId;
	}
	@Column(name="VisitDelayDeadline")
	public String getM_sVisitDelayDeadline() {
		return m_sVisitDelayDeadline;
	}
	@Column(name="VisitDelayTime")
	public String getM_sVisitDelayTime() {
		return m_sVisitDelayTime;
	}
	@OneToOne
	@JoinColumn(name="VisitPlanId")
	public CEntityVisitPlan getcEntityVisitPlan() {
		return cEntityVisitPlan;
	}
	public void setcEntityVisitPlan(CEntityVisitPlan cEntityVisitPlan) {
		this.cEntityVisitPlan = cEntityVisitPlan;
	}
	public void setM_iVisitDelayId(int mIVisitDelayId) {
		m_iVisitDelayId = mIVisitDelayId;
	}
	public void setM_sVisitDelayDeadline(String mSVisitDelayDeadline) {
		m_sVisitDelayDeadline = mSVisitDelayDeadline;
	}
	public void setM_sVisitDelayTime(String mSVisitDelayTime) {
		m_sVisitDelayTime = mSVisitDelayTime;
	}
	
	
}
