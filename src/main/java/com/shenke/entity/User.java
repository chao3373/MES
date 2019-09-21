package com.shenke.entity;

import javax.persistence.*;

/*
 * 用户实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="t_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;//用户id

	@Column(length = 50)
	private Integer userNumber;//员工编号
	
	@Column(length=50)
	private String userName;//用户名
	
	@Column(length=50)
	private String password;//密码
	
	@Column(length=50)
	private String trueName;//真实姓名

	@Column(length = 10)
	private String sex;//性别

	@Column(length=1000)
	private String remarks;//备注
	
	@Transient
	private String roles;//所有用的角色

	@Transient
	private String gongxus;//擅长的工序

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userNumber=" + userNumber +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", trueName='" + trueName + '\'' +
				", sex='" + sex + '\'' +
				", remarks='" + remarks + '\'' +
				", roles='" + roles + '\'' +
				", gongxus='" + gongxus + '\'' +
				'}';
	}

	public String getGongxus() {
		return gongxus;
	}

	public void setGongxus(String gongxus) {
		this.gongxus = gongxus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(Integer userNumber) {
		this.userNumber = userNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}
