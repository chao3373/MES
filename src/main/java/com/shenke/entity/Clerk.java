package com.shenke.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *  职员实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="t_clerk")
public class Clerk {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=50)
	private String name;
	
	@Column(length=50)
	private String sex;
	
	@Column(length=50)
	private String duty;//职务

	private Integer age;//年龄

	@ManyToOne
	@JoinColumn(name="depId")
	private Dep dep;//所属部门

	@Override
	public String toString() {
		return "Clerk{" +
				"id=" + id +
				", name='" + name + '\'' +
				", sex='" + sex + '\'' +
				", duty='" + duty + '\'' +
				", age=" + age +
				", dep=" + dep +
				'}';
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public Dep getDep() {
		return dep;
	}

	public void setDep(Dep dep) {
		this.dep = dep;
	}
}
