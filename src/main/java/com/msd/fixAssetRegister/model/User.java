package com.msd.fixAssetRegister.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.*;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private int userId;

	private String password;

	@Column(name="USER_NAME")
	private String username;

	@Column(name="INDIVIDUAL")
	private int individual;

	@ManyToOne
	@JoinColumn(name = "USER_TYPE_ID")
	private UserType userType;

	@Column(name = "DEP_ID")
	private int depId;

	@Column(name = "CREATED_USER")
	private int createdUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ACTION_TIME")
	private Date actionTime;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public User(User user) {
		this.userId=user.getUserId();
		this.username =user.getUsername();
		this.password=user.getPassword();
		this.roles=user.getRoles();
	}

    public User() {
    }

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public int getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(int createdUser) {
		this.createdUser = createdUser;
	}

	public Date getActionTime() {
		return actionTime;
	}

	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public int getIndividual() {
		return individual;
	}

	public void setIndividual(int individual) {
		this.individual = individual;
	}
}
