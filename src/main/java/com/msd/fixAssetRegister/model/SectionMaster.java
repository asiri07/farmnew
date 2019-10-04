package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the section_master database table.
 * 
 */
@Entity
@Table(name="section_master")
public class SectionMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SEC_ID")
	private int secId;

	@Column(name="SEC_CODE")
	private String secCode;

	@Column(name="SEC_DES")
	private String secDes;

	//bi-directional many-to-one association to LocationMaster
	@OneToMany(mappedBy="sectionMaster")
	private List<LocationMaster> locationMasters;

	//bi-directional many-to-one association to DepartmentMaster
	@ManyToOne
	@JoinColumn(name="DEP_ID")
	private DepartmentMaster departmentMaster;

    @Column(name = "USER_ID")
    private int userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTION_TIME")
    private Date actionTime;

	public SectionMaster() {
	}

	public int getSecId() {
		return this.secId;
	}

	public void setSecId(int secId) {
		this.secId = secId;
	}

	public String getSecCode() {
		return this.secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public String getSecDes() {
		return this.secDes;
	}

	public void setSecDes(String secDes) {
		this.secDes = secDes;
	}

	public List<LocationMaster> getLocationMasters() {
		return this.locationMasters;
	}

	public void setLocationMasters(List<LocationMaster> locationMasters) {
		this.locationMasters = locationMasters;
	}

	public LocationMaster addLocationMaster(LocationMaster locationMaster) {
		getLocationMasters().add(locationMaster);
		locationMaster.setSectionMaster(this);

		return locationMaster;
	}

	public LocationMaster removeLocationMaster(LocationMaster locationMaster) {
		getLocationMasters().remove(locationMaster);
		locationMaster.setSectionMaster(null);

		return locationMaster;
	}

	public DepartmentMaster getDepartmentMaster() {
		return this.departmentMaster;
	}

	public void setDepartmentMaster(DepartmentMaster departmentMaster) {
		this.departmentMaster = departmentMaster;
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