package com.msd.fixAssetRegister.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the flow database table.
 * 
 */
@Entity
@Table(name="floor")
public class Floor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private int id;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "FLOOR_CODE")
	private String floorCode;

	//bi-directional many-to-one association to Building
	@ManyToOne
	private Building building;

	//bi-directional many-to-one association to Room
	@OneToMany(mappedBy = "floor")
	private List<Room> rooms;

    @Column(name = "USER_ID")
    private int userId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTION_TIME")
    private Date actionTime;

	public Floor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFloorCode() {
		return floorCode;
	}

	public void setFloorCode(String floorCode) {
		this.floorCode = floorCode;
	}

	public Building getBuilding() {
		return this.building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public List<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public Room addRoom(Room room) {
		getRooms().add(room);
		room.setFloor(this);

		return room;
	}

	public Room removeRoom(Room room) {
		getRooms().remove(room);
		room.setFloor(null);

		return room;
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