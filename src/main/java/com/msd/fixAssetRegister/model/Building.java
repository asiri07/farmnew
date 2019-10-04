package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the building database table.
 * 
 */
@Entity
@Table(name="building")
public class Building implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;

	@Column(name="BUILDING_CODE")
	private String buildingCode;

	@Column(name="DESCRIPTION")
	private String description;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="CITY_ID")
	private City city;

    //bi-directional many-to-one association to Floor
    @OneToMany(mappedBy="building")
    private List<Floor> flows;

    @Column(name = "USER_ID")
    private int userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTION_TIME")
    private Date actionTime;

	public Building() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBuildingCode() {
		return this.buildingCode;
	}

	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

    public List<Floor> getFlows() {
        return this.flows;
	}

    public void setFlows(List<Floor> flows) {
        this.flows = flows;
	}

    public Floor addFlow(Floor flow) {
        getFlows().add(flow);
		flow.setBuilding(this);

		return flow;
	}

    public Floor removeFlow(Floor flow) {
        getFlows().remove(flow);
		flow.setBuilding(null);

		return flow;
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