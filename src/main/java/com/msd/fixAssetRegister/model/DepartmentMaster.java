package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the department_master database table.
 * 
 */
@Entity
@Table(name="department_master")
@NamedQuery(name="DepartmentMaster.findAll", query="SELECT d FROM DepartmentMaster d")
public class DepartmentMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DEP_ID")
	private int depId;

	@Column(name="DEP_CODE")
	private String depCode;

	@Column(name="DEP_DES")
	private String depDes;

	//bi-directional many-to-one association to CompanyMaster
	@ManyToOne
	@JoinColumn(name="COM_ID")
	private CompanyMaster companyMaster;

	//bi-directional many-to-one association to SectionMaster
	@OneToMany(mappedBy="departmentMaster")
	private List<SectionMaster> sectionMasters;

    @Column(name = "USER_ID")
    private int userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTION_TIME")
    private Date actionTime;

	public DepartmentMaster() {
	}

	public int getDepId() {
		return this.depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public String getDepCode() {
		return this.depCode;
	}

	public void setDepCode(String depCode) {
		this.depCode = depCode;
	}

	public String getDepDes() {
		return this.depDes;
	}

	public void setDepDes(String depDes) {
		this.depDes = depDes;
	}

	public CompanyMaster getCompanyMaster() {
		return this.companyMaster;
	}

	public void setCompanyMaster(CompanyMaster companyMaster) {
		this.companyMaster = companyMaster;
	}

	public List<SectionMaster> getSectionMasters() {
		return this.sectionMasters;
	}

	public void setSectionMasters(List<SectionMaster> sectionMasters) {
		this.sectionMasters = sectionMasters;
	}

	public SectionMaster addSectionMaster(SectionMaster sectionMaster) {
		getSectionMasters().add(sectionMaster);
		sectionMaster.setDepartmentMaster(this);

		return sectionMaster;
	}

	public SectionMaster removeSectionMaster(SectionMaster sectionMaster) {
		getSectionMasters().remove(sectionMaster);
		sectionMaster.setDepartmentMaster(null);

		return sectionMaster;
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