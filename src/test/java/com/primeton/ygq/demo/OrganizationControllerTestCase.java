package com.primeton.ygq.demo;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.primeton.ygq.demo.Application;
import com.primeton.ygq.demo.exception.Response;
import com.primeton.ygq.demo.controller.OrganizationController;
import com.primeton.ygq.demo.entity.Organization;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 分组测试
 * @author 杨功强
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class OrganizationControllerTestCase {

	@Autowired
	private OrganizationController OrganizationController;

	private Organization organization;

	@Test
	public void tsetOrganization() throws Exception {
		organization = this.initOrganization();
		// 创建分组测试
		this.createOrganization();
		// 更新分组测试
//		this.modifyOrganization();
//		// 查询分组测试
//		this.queryOrganization();
//		// 查询每组人员信息
//		this.queryOrganizationByPage();
//		// 主键查询
//		this.getOrganization();
//		// 删除分组测试
//		this.removeOrganization();
	}

	/**
	 * 创建分组测试
	 *
	 * @throws DemoException
	 */

	public void createOrganization() throws Exception {
		Response<Organization> response = OrganizationController.createOrganization(organization);
		Assert.assertEquals("添加分组成功", response.getMsg());
	}

	/**
	 * 创建组
	 *
	 * @return
	 */
	public Organization initOrganization() {
		Organization organization = new Organization();
		organization.setOrgId(888888);
		organization.setOrgName("单元测试0000");
		organization.setParentId(2);
		return organization;
	}

	/**
	 * 更新分组测试 .
	 * 
	 * @throws Exception
	 */
	public void modifyOrganization() throws Exception {
		organization.setOrgName("测试部cg");
		Response<Organization> response = OrganizationController.modifyOrganization(organization);
		Assert.assertEquals("更新成功", response.getMsg());
	}

	/**
	 * 查询每组人员信息
	 *
	 * @throws Exception
	 */
	public void queryOrganization() throws Exception {
		Response<List<Organization>> response = OrganizationController.queryOrganizations(0);
		Assert.assertEquals("查询成功", response.getMsg());
	}

	/**
	 * 主键查询分组测试
	 *
	 * @throws Exception
	 */
	public void getOrganization() throws Exception {
		Response<Organization> response = OrganizationController.getOrganization(organization.getOrgId());
		Assert.assertEquals("主键查询成功", response.getMsg());
	}

	/**
	 * 删除分组测试
	 *
	 * @throws Exception
	 */
	public void removeOrganization() throws Exception {
		Response<Organization> response = OrganizationController.removeOrganization(organization.getOrgId());
		Assert.assertEquals("删除成功", response.getMsg());
	}

	/**
	 * 条件查询分组测试
	 *
	 * @throws Exception
	 */
	public void queryOrganizationByPage() throws Exception {
		Response<PageInfo> response = OrganizationController.queryOrganizationByPage(1, 3, 700047);
		Assert.assertEquals("查询成功", response.getMsg());
	}

}
