package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the company_master database table.
 * 
 */
@Entity
@Table(name="company_master")
@NamedQuery(name="CompanyMaster.findAll", query="SELECT c FROM CompanyMaster c")
public class CompanyMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="COM_ID")
	private int comId;

	@Column(name="COM_CODE")
	private String comCode;

	@Column(name="COM_DES")
	private String comDes;

	//bi-directional many-to-one association to DepartmentMaster
	@OneToMany(mappedBy="companyMaster")
	private List<DepartmentMaster> departmentMasters;

    @Column(name = "USER_ID")
    private int userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTION_TIME")
    private Date actionTime;

	public CompanyMaster() {
	}

	public CompanyMaster(int comId) {
		this.comId = comId;
	}

	public int getComId() {
		return this.comId;
	}

	public void setComId(int comId) {
		this.comId = comId;
	}

	public String getComCode() {
		return this.comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getComDes() {
		return this.comDes;
	}

	public void setComDes(String comDes) {
		this.comDes = comDes;
	}

	public List<DepartmentMaster> getDepartmentMasters() {
		return this.departmentMasters;
	}

	public void setDepartmentMasters(List<DepartmentMaster> departmentMasters) {
		this.departmentMasters = departmentMasters;
	}

	public DepartmentMaster addDepartmentMaster(DepartmentMaster departmentMaster) {
		getDepartmentMasters().add(departmentMaster);
		departmentMaster.setCompanyMaster(this);

		return departmentMaster;
	}

	public DepartmentMaster removeDepartmentMaster(DepartmentMaster departmentMaster) {
		getDepartmentMasters().remove(departmentMaster);
		departmentMaster.setCompanyMaster(null);

		return departmentMaster;
	}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }
}