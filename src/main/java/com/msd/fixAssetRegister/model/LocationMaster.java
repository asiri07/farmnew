package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the location_master database table.
 * 
 */
@Entity
@Table(name="location_master")
//@NamedQuery(name="LocationMaster.findAll", query="SELECT l FROM LocationMaster l")
public class LocationMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="LOC_ID")
	private int locId;

	@Column(name="LOC_CODE")
	private String locCode;

	@Column(name="LOC_DES")
	private String locDes;

	//bi-directional many-to-one association to SectionMaster
	@ManyToOne
	@JoinColumn(name="SEC_ID")
	private SectionMaster sectionMaster;

//	//bi-directional many-to-one association to AssetLocationDetail
//	@OneToMany(mappedBy="locationMaster",cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Asset> assets;
	@OneToMany
	@JoinColumn(name="LOC_ID")
	private  List<Asset> assets;

	@ManyToOne
	private Room room;

    @Column(name = "USER_ID")
    private int userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTION_TIME")
    private Date actionTime;


	public LocationMaster() {
	}

	public int getLocId() {
		return this.locId;
	}

	public void setLocId(int locId) {
		this.locId = locId;
	}

	public String getLocCode() {
		return this.locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public String getLocDes() {
		return this.locDes;
	}

	public void setLocDes(String locDes) {
		this.locDes = locDes;
	}

	public SectionMaster getSectionMaster() {
		return this.sectionMaster;
	}

	public void setSectionMaster(SectionMaster sectionMaster) {
		this.sectionMaster = sectionMaster;
	}

	public List<Asset> getAssets() {
		return assets;
	}

	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
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