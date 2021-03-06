package com.mm.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mm.tool.MyOpcode;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//员工
@Entity
@Table(name = "Employee")
public class CEntityEmployee {
	private int m_iEmployeeId; // 员工自增号,主键
	private String m_sEmployeeAccount; // 员工帐号
	private String m_sEmployeePassword; // 员工密码
	private String m_sEmployeeName; // 员工名
	private String m_sEmployeePhone; // 员工电话
	private String m_sEmployeeSex; // 员工性别
	private String m_sEmployeeDepartment; // 员工部门
	private String m_sEmployeeJob; // 员工职务
	private int m_iEmployeeType; // 员工类别 (0为销售人员,1为送货人员,2为售后人员,3为巡店人员,4为删除（即撤销）)

	private Set<CEntityMission> cEntityMissions = new HashSet<CEntityMission>();
	private Set<CEntityAttendance> cEntityAttendances = new HashSet<CEntityAttendance>();
	private Set<CEntitySuggest> cEntitySuggests = new HashSet<CEntitySuggest>();
	private Set<CEntityBussiness> cEntityBussinesses = new HashSet<CEntityBussiness>();
	private Set<CEntityClient> cEntityClients = new HashSet<CEntityClient>();
	private Set<CEntityClientSubmit> cEntityClientSubmits = new HashSet<CEntityClientSubmit>();
	private Set<CEntityVisitPlan> cEntityVisitPlans = new HashSet<CEntityVisitPlan>();
	private Set<CEntityVisitConclusion> cEntityVisitConclusions = new HashSet<CEntityVisitConclusion>();
	private Set<CEntityVisitDelay> cEntityVisitDelays = new HashSet<CEntityVisitDelay>();

	public CEntityEmployee() {
	}

	public static class BuildJsonObject {
		private JSONObject outjson = new JSONObject();

		public BuildJsonObject() {
		}

		public BuildJsonObject EmployeeId(int m_iEmployeeId) {
			outjson.put(MyOpcode.Employee.EmployeeId, m_iEmployeeId);
			return this;
		}

		public BuildJsonObject EmployeeAccount(String m_sEmployeeAccount) {
			outjson.put(MyOpcode.Employee.EmployeeAccount, m_sEmployeeAccount);
			return this;
		}

		public BuildJsonObject EmployeePassword(String m_sEmployeePassword) {
			outjson.put(MyOpcode.Employee.EmployeePassword,
							m_sEmployeePassword);
			return this;
		}

		public BuildJsonObject EmployeeName(String m_sEmployeeName) {
			outjson.put(MyOpcode.Employee.EmployeeName, m_sEmployeeName);
			return this;
		}

		public BuildJsonObject EmployeePhone(String m_sEmployeePhone) {
			outjson.put(MyOpcode.Employee.EmployeePhone, m_sEmployeePhone);
			return this;
		}

		public BuildJsonObject EmployeeSex(String m_sEmployeeSex) {
			outjson.put(MyOpcode.Employee.EmployeeSex, m_sEmployeeSex);
			return this;
		}

		public BuildJsonObject EmployeeDepartment(String m_sEmployeeDepartment) {
			outjson.put(MyOpcode.Employee.EmployeeDepartment,
					m_sEmployeeDepartment);
			return this;
		}

		public BuildJsonObject EmployeeJob(String m_sEmployeeJob) {
			outjson.put(MyOpcode.Employee.EmployeeJob, m_sEmployeeJob);
			return this;
		}

		public BuildJsonObject EmployeeType(int m_iEmployeeType) {
			outjson.put(MyOpcode.Employee.EmployeeType, m_iEmployeeType);
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
			outjson.put(MyOpcode.Employee.EmployeeList, array);
			return this;
		}

		public BuildJsonObject ToSingle(CEntityEmployee cEntityEmployee) {
			outjson.put(MyOpcode.Employee.EmployeeId,
					cEntityEmployee.m_iEmployeeId);
			outjson.put(MyOpcode.Employee.EmployeeAccount,
					cEntityEmployee.m_sEmployeeAccount);
			outjson.put(MyOpcode.Employee.EmployeePassword,
					cEntityEmployee.m_sEmployeePassword);
			outjson.put(MyOpcode.Employee.EmployeeName,
					cEntityEmployee.m_sEmployeeName);
			outjson.put(MyOpcode.Employee.EmployeePhone,
					cEntityEmployee.m_sEmployeePhone);
			outjson.put(MyOpcode.Employee.EmployeeSex,
					cEntityEmployee.m_sEmployeeSex);
			outjson.put(MyOpcode.Employee.EmployeeDepartment,
					cEntityEmployee.m_sEmployeeDepartment);
			outjson.put(MyOpcode.Employee.EmployeeJob,
					cEntityEmployee.m_sEmployeeJob);
			outjson.put(MyOpcode.Employee.EmployeeType,
					cEntityEmployee.m_iEmployeeType);
			return this;
		}

		public JSONObject build() {
			return outjson;
		}
	}

	public CEntityEmployee(int mIEmployeeId, String mSEmployeeAccount,
			String mSEmployeePassword, String mSEmployeeName,
			String mSEmployeePhone, String mSEmployeeSex,
			String mSEmployeeDepartment, String mSEmployeeJob,
			int mIEmployeeType) {
		m_iEmployeeId = mIEmployeeId;
		m_sEmployeeAccount = mSEmployeeAccount;
		m_sEmployeePassword = mSEmployeePassword;
		m_sEmployeeName = mSEmployeeName;
		m_sEmployeePhone = mSEmployeePhone;
		m_sEmployeeSex = mSEmployeeSex;
		m_sEmployeeDepartment = mSEmployeeDepartment;
		m_sEmployeeJob = mSEmployeeJob;
		m_iEmployeeType = mIEmployeeType;
	}

	public static class Builder {
		private int m_iEmployeeId; // 员工自增号,主键
		private String m_sEmployeeAccount; // 员工帐号
		private String m_sEmployeePassword; // 员工密码
		private String m_sEmployeeName; // 员工名
		private String m_sEmployeePhone; // 员工电话
		private String m_sEmployeeSex; // 员工性别
		private String m_sEmployeeDepartment; // 员工部门
		private String m_sEmployeeJob; // 员工职务
		private int m_iEmployeeType; // 员工类别

		// (0为销售人员,1为送货人员,2为售后人员,3为巡店人员,4为删除（即撤销）)

		public Builder() {

		}

		public Builder EmployeeId(int m_iEmployeeId) {
			this.m_iEmployeeId = m_iEmployeeId;
			return this;
		}

		public Builder EmployeeAccount(String m_sEmployeeAccount) {
			this.m_sEmployeeAccount = m_sEmployeeAccount;
			return this;
		}

		public Builder EmployeePassword(String m_sEmployeePassword) {
			this.m_sEmployeePassword = m_sEmployeePassword;
			return this;
		}

		public Builder EmployeeName(String m_sEmployeeName) {
			this.m_sEmployeeName = m_sEmployeeName;
			return this;
		}

		public Builder EmployeePhone(String m_sEmployeePhone) {
			this.m_sEmployeePhone = m_sEmployeePhone;
			return this;
		}

		public Builder EmployeeSex(String m_sEmployeeSex) {
			this.m_sEmployeeSex = m_sEmployeeSex;
			return this;
		}

		public Builder EmployeeDepartment(String m_sEmployeeDepartment) {
			this.m_sEmployeeDepartment = m_sEmployeeDepartment;
			return this;
		}

		public Builder EmployeeJob(String m_sEmployeeJob) {
			this.m_sEmployeeJob = m_sEmployeeJob;
			return this;
		}

		public Builder EmployeeType(int m_iEmployeeType) {
			this.m_iEmployeeType = m_iEmployeeType;
			return this;
		}

		public CEntityEmployee build() {
			return new CEntityEmployee(this);
		}
	}

	private CEntityEmployee(Builder b) {
		m_iEmployeeId = b.m_iEmployeeId;
		m_sEmployeeAccount = b.m_sEmployeeAccount;
		m_sEmployeePassword = b.m_sEmployeePassword;
		m_sEmployeeName = b.m_sEmployeeName;
		m_sEmployeePhone = b.m_sEmployeePhone;
		m_sEmployeeSex = b.m_sEmployeeSex;
		m_sEmployeeDepartment = b.m_sEmployeeDepartment;
		m_sEmployeeJob = b.m_sEmployeeJob;
		m_iEmployeeType = b.m_iEmployeeType;

	}

	@Id
	@GeneratedValue
	@Column(name = "EmployeeId")
	public int getM_iEmployeeId() {
		return m_iEmployeeId;
	}

	@Column(name = "EmployeeType")
	public int getM_iEmployeeType() {
		return m_iEmployeeType;
	}

	@Column(name = "EmployeeAccount")
	public String getM_sEmployeeAccount() {
		return m_sEmployeeAccount;
	}

	@Column(name = "EmployeeDepartment")
	public String getM_sEmployeeDepartment() {
		return m_sEmployeeDepartment;
	}

	@Column(name = "EmployeeJob")
	public String getM_sEmployeeJob() {
		return m_sEmployeeJob;
	}

	@Column(name = "EmployeeName")
	public String getM_sEmployeeName() {
		return m_sEmployeeName;
	}

	@Column(name = "EmployeePassword")
	public String getM_sEmployeePassword() {
		return m_sEmployeePassword;
	}

	@Column(name = "EmployeePhone")
	public String getM_sEmployeePhone() {
		return m_sEmployeePhone;
	}

	@Column(name = "EmployeeSex")
	public String getM_sEmployeeSex() {
		return m_sEmployeeSex;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE,
			CascadeType.REMOVE })
	@JoinColumn(name = "EmployeeId")
	public Set<CEntityMission> getcEntityMissions() {
		return cEntityMissions;
	}

	@OneToMany(mappedBy = "cEntityEmployee", fetch = FetchType.EAGER, cascade = {
			CascadeType.MERGE, CascadeType.REMOVE })
	public Set<CEntityAttendance> getcEntityAttendances() {
		return cEntityAttendances;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE,
			CascadeType.REMOVE })
	@JoinColumn(name = "EmployeeId")
	public Set<CEntitySuggest> getcEntitySuggests() {
		return cEntitySuggests;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE,
			CascadeType.REMOVE })
	@JoinColumn(name = "EmployeeId")
	public Set<CEntityBussiness> getcEntityBussinesses() {
		return cEntityBussinesses;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE,
			CascadeType.REMOVE })
	@JoinColumn(name = "EmployeeId")
	public Set<CEntityClient> getcEntityClients() {
		return cEntityClients;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE,
			CascadeType.REMOVE })
	@JoinColumn(name = "EmployeeId")
	public Set<CEntityClientSubmit> getcEntityClientSubmits() {
		return cEntityClientSubmits;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE,
			CascadeType.REMOVE })
	@JoinColumn(name = "EmployeeId")
	public Set<CEntityVisitPlan> getcEntityVisitPlans() {
		return cEntityVisitPlans;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE,
			CascadeType.REMOVE })
	@JoinColumn(name = "EmployeeId")
	public Set<CEntityVisitConclusion> getcEntityVisitConclusions() {
		return cEntityVisitConclusions;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE,
			CascadeType.REMOVE })
	@JoinColumn(name = "EmployeeId")
	public Set<CEntityVisitDelay> getcEntityVisitDelays() {
		return cEntityVisitDelays;
	}

	public void setcEntityVisitDelays(Set<CEntityVisitDelay> cEntityVisitDelays) {
		this.cEntityVisitDelays = cEntityVisitDelays;
	}

	public void setcEntityVisitConclusions(
			Set<CEntityVisitConclusion> cEntityVisitConclusions) {
		this.cEntityVisitConclusions = cEntityVisitConclusions;
	}

	public void setcEntityVisitPlans(Set<CEntityVisitPlan> cEntityVisitPlans) {
		this.cEntityVisitPlans = cEntityVisitPlans;
	}

	public void setcEntityClientSubmits(
			Set<CEntityClientSubmit> cEntityClientSubmits) {
		this.cEntityClientSubmits = cEntityClientSubmits;
	}

	public void setcEntityClients(Set<CEntityClient> cEntityClients) {
		this.cEntityClients = cEntityClients;
	}

	public void setcEntityBussinesses(Set<CEntityBussiness> cEntityBussinesses) {
		this.cEntityBussinesses = cEntityBussinesses;
	}

	public void setcEntitySuggests(Set<CEntitySuggest> cEntitySuggests) {
		this.cEntitySuggests = cEntitySuggests;
	}

	public void setcEntityAttendances(Set<CEntityAttendance> cEntityAttendances) {
		this.cEntityAttendances = cEntityAttendances;
	}

	public void setcEntityMissions(Set<CEntityMission> cEntityMissions) {
		this.cEntityMissions = cEntityMissions;
	}

	public void setM_iEmployeeId(int mIEmployeeId) {
		m_iEmployeeId = mIEmployeeId;
	}

	public void setM_iEmployeeType(int mIEmployeeType) {
		m_iEmployeeType = mIEmployeeType;
	}

	public void setM_sEmployeeAccount(String mSEmployeeAccount) {
		m_sEmployeeAccount = mSEmployeeAccount;
	}

	public void setM_sEmployeeDepartment(String mSEmployeeDepartment) {
		m_sEmployeeDepartment = mSEmployeeDepartment;
	}

	public void setM_sEmployeeJob(String mSEmployeeJob) {
		m_sEmployeeJob = mSEmployeeJob;
	}

	public void setM_sEmployeeName(String mSEmployeeName) {
		m_sEmployeeName = mSEmployeeName;
	}

	public void setM_sEmployeePassword(String mSEmployeePassword) {
		m_sEmployeePassword = mSEmployeePassword;
	}

	public void setM_sEmployeePhone(String mSEmployeePhone) {
		m_sEmployeePhone = mSEmployeePhone;
	}

	public void setM_sEmployeeSex(String mSEmployeeSex) {
		m_sEmployeeSex = mSEmployeeSex;
	}

	@Override
	public String toString() {
		return "CEntityEmployee [m_iEmployeeId=" + m_iEmployeeId
				+ ", m_iEmployeeType=" + m_iEmployeeType
				+ ", m_sEmployeeAccount=" + m_sEmployeeAccount
				+ ", m_sEmployeeDepartment=" + m_sEmployeeDepartment
				+ ", m_sEmployeeJob=" + m_sEmployeeJob + ", m_sEmployeeName="
				+ m_sEmployeeName + ", m_sEmployeePassword="
				+ m_sEmployeePassword + ", m_sEmployeePhone="
				+ m_sEmployeePhone + ", m_sEmployeeSex=" + m_sEmployeeSex + "]";
	}

	
}
