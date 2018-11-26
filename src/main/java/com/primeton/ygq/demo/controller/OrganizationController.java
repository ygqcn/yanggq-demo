package com.primeton.ygq.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.primeton.ygq.demo.exception.Response;
import com.primeton.ygq.demo.entity.Organization;
import com.primeton.ygq.demo.exception.DemoException;
import com.primeton.ygq.demo.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "Organization", description = "组织机构")
@RequestMapping(value = "/api/orgs")
@RestController
public class OrganizationController {
	@Autowired
	private OrganizationService OrganizationService;

	/**
	 * 创建组织机构
	 *
	 * @param organization
	 *            组织机构对象
	 * @return
	 * @throws DemoException
	 */
	@PostMapping
	@ApiOperation(value = "创建分组", response = Response.class)
	@ApiImplicitParams({
			@ApiImplicitParam(value = "组织机构对象", name = "organization", dataType = "Organization", required = true), })
	public Response<Organization> createOrganization(@RequestBody Organization organization) throws DemoException {
		return OrganizationService.createOrganization(organization);
	}

	/**
	 * 更改组织机构
	 *
	 * @param organization
	 *            组织机构对象
	 * @return
	 * @throws DemoException
	 */
	@PutMapping
	@ApiOperation(value = "更新分组", response = Response.class)
	@ApiImplicitParams({
			@ApiImplicitParam(value = "组织机构对象", name = "organization", dataType = "Organization", required = true), })
	public Response<Organization> modifyOrganization(@RequestBody Organization organization) throws DemoException {
		return OrganizationService.modifyOrganization(organization);
	}

	/**
	 * 删除该组织
	 *
	 * @param orgId
	 *            组织id
	 * @return
	 * @throws DemoException
	 */
	@DeleteMapping("/{orgId}")
	@ApiOperation(value = "删除分组", response = Response.class)
	@ApiImplicitParams({ @ApiImplicitParam(value = "主键id", name = "orgId") })
	public Response<Organization> removeOrganization(@PathVariable("orgId") Integer orgId) throws DemoException {
		return OrganizationService.removeOrganization(orgId);
	}

	/**
	 * 主键查询
	 *
	 * @param groupId
	 *            组织id
	 * @return
	 * @throws DemoException
	 */
	@ApiOperation(value = "根据主键查询", response = Response.class)
	@ApiImplicitParams({ @ApiImplicitParam(value = "部门id", name = "orgId", dataType = "Integer", required = true), })
	@GetMapping("/{orgId}")
	public Response<Organization> getOrganization(@PathVariable("orgId") Integer orgId) throws DemoException {
		return OrganizationService.getOrganization(orgId);
	}

	/**
	 * 查询该组人员详情
	 *
	 * @param pageIndex
	 *            起始页数
	 * @param pageSize
	 *            每页数量
	 * @param orgId
	 *            组织机构id
	 * @return
	 * @throws DemoException
	 */
	@GetMapping
	@ApiOperation(value = "查询每组人员信息", response = Response.class)
	@ApiImplicitParams({ @ApiImplicitParam(value = "起始页数", name = "pageIndex", dataType = "int", required = true),
			@ApiImplicitParam(value = "每页数量", name = "pageSize", dataType = "int", required = true),
			@ApiImplicitParam(value = "组织机构id", name = "orgId", dataType = "Integer", required = true) })
	public Response<PageInfo> queryOrganizationByPage(Integer pageIndex, Integer pageSize, Integer orgId)
			throws DemoException {
		return OrganizationService.queryOrganizationByPage(pageIndex, pageSize, orgId);
	}

	/**
	 * 查询该机构下的所有子机构
	 * 
	 * @param parentId
	 *            父节点id
	 * @return
	 * @throws DemoException
	 */

	@ApiOperation(value = "查询所有机构", response = Response.class)
	@GetMapping(value = "/actions/query")
	@ApiImplicitParams({ @ApiImplicitParam(value = "上级id", name = "parentId", dataType = "int", required = true), })
	public Response<List<Organization>> queryOrganizations(@RequestParam(value = "parentId") Integer parentId)
			throws DemoException {
		return OrganizationService.queryOrganizations(parentId);
	}
}
