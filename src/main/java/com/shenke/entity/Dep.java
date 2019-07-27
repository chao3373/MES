package com.shenke.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 部门实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="t_dep")
public class Dep {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=50)
	private String text;

	@Override
	public String toString() {
		return "Dep{" +
				"id=" + id +
				", text='" + text + '\'' +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
