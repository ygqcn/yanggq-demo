package com.primeton.ygq.demo.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.primeton.ygq.demo.exception.Response;
import com.primeton.ygq.demo.entity.Organization;
import com.primeton.ygq.demo.exception.DemoException;

/**
 * 组织机构服务接口
 * 
 * @author ygq
 * @date 2018/11/14 16:11
 */
public interface OrganizationService {

	/**
	 * 新增组织机构
	 * 
	 * @param org
	 *            组织实体
	 * @return
	 * @throws DemoException
	 */
	Response<Organization> createOrganization(Organization org) throws DemoException;

	/**
	 * 删除组织机构
	 * 
	 * @param orgId
	 *            组织机构ID
	 * @return
	 * @throws DemoException
	 */
	Response<Organization> removeOrganization(Integer orgId) throws DemoException;

	/**
	 * 修改组织机构
	 * 
	 * @param org
	 *            组织机构实体
	 * @return
	 * @throws DemoException
	 */
	Response<Organization> modifyOrganization(Organization org) throws DemoException;

	/**
	 * 查询组织机构
	 * 
	 * @param parentId
	 *            父节点id
	 * @return
	 * @throws DemoException
	 */
	Response<List<Organization>> queryOrganizations(Integer parentId) throws DemoException;

	/**
	 * 根据主键查询组织机构
	 * 
	 * @param orgId
	 *            组织机构id
	 * @return
	 * @throws DemoException
	 */
	Response<Organization> getOrganization(Integer orgId) throws DemoException;

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
	Response<PageInfo> queryOrganizationByPage(Integer pageIndex, Integer pageSize, Integer orgId) throws DemoException;

}
