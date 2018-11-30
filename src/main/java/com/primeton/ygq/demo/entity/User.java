package com.primeton.ygq.demo.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户对象user")
public class User implements Serializable {

	private static final long serialVersionUID = 5408699143801033394L;
	@ApiModelProperty("用户ID")
	private Integer id;
	@ApiModelProperty("用户名称")
	private String username;
	@ApiModelProperty("用户密码")
	private String password;
	@ApiModelProperty("用户分组id")
	private Integer orgId;
	@ApiModelProperty("分组名称")
	private String orgName;

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", orgId=" + orgId
				+ ", orgName=" + orgName + "]";
	}

	public User(Integer id, String username, String password, Integer orgId, String orgName) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.orgId = orgId;
		this.orgName = orgName;
	}

	public User(Integer id, String username, String password, Integer orgId) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.orgId = orgId;
	}

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}