package com.primeton.ygq.demo.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 组织机构实体类
 * 
 * @author 杨功强
 */
@ApiModel(description = "组织对象")
public class Organization implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("组织ID")
	private Integer orgId;
	@ApiModelProperty("组名")
	private String orgName;
	@ApiModelProperty("用户ID")
	private Integer parentId;

	public Organization() {
		super();
	}

	public Organization(Integer orgId, String orgName, Integer parentId) {
		this.orgId = orgId;
		this.orgName = orgName;
		this.parentId = parentId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Override
	public String toString() {
		return "Organization{" + "orgId=" + orgId + ", orgName='" + orgName + '\'' + ", parentId=" + parentId + '}';
	}
}
