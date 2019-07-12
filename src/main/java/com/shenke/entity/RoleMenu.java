package com.shenke.entity;

import javax.persistence.*;

/**
 * 角色菜单关联实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="t_roleMenu")
public class RoleMenu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="roleId")
	private Role role;//角色
	
	@ManyToOne
	@JoinColumn(name="menuId")
	private Menu menu;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Override
	public String toString() {
		return "RoleMenu{" +
				"id=" + id +
				", role=" + role +
				", menu=" + menu +
				'}';
	}
}
