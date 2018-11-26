package com.primeton.ygq.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.primeton.ygq.demo.common.Const;
import com.primeton.ygq.demo.exception.Response;
import com.primeton.ygq.demo.exception.ResponseCode;
import com.primeton.ygq.demo.dao.OrganizationMapper;
import com.primeton.ygq.demo.entity.Organization;
import com.primeton.ygq.demo.entity.User;
import com.primeton.ygq.demo.exception.DemoException;
import com.primeton.ygq.demo.service.OrganizationService;
import com.primeton.ygq.demo.util.EmptyUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 组织机构service层实现类
 * 
 * @author 杨功强
 */
@Service
@Transactional(rollbackFor = DemoException.class)
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {
	@Autowired
	private OrganizationMapper organizationMapper;

	@Override
	public Response<Organization> createOrganization(Organization org) throws DemoException {
		if (EmptyUtils.isEmpty(org.getOrgName())) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.PARAM_NOT_NULL.getCode(),
					ResponseCode.PARAM_NOT_NULL.getDec());
		}
		if (EmptyUtils.isEmpty(org.getParentId())) {
			org.setParentId(700038);
		}
		Response response = this.Exist(org.getOrgName(), Const.ORGNAME);
		if (!response.isSuccess()) {
			return response;
		}
		int count = organizationMapper.insert(org);
		if (count == 0) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.ORG_CREAT_REEOR.getCode(),
					ResponseCode.ORG_CREAT_REEOR.getDec());
		}
		return Response.createBySuccess("添加分组成功", org);
	}

	@Override
	public Response<Organization> removeOrganization(Integer orgId) throws DemoException {
		if (EmptyUtils.isEmpty(orgId)) {
			return Response.createByErrorCodeMessage(ResponseCode.PARAM_NOT_NULL.getCode(),
					ResponseCode.PARAM_NOT_NULL.getDec());
		}
		Organization organization = organizationMapper.get(orgId);
		if (organization == null) {
			return Response.createByErrorCodeMessage(ResponseCode.ORG_NOT_EXIST.getCode(),
					ResponseCode.ORG_NOT_EXIST.getDec());
		}
		// 判断该组织下还有没有成员
		List<User> users = organizationMapper.queryOrganizationUsers(orgId);
		if (users.size() > 0) {
			return Response.createByErrorCodeMessage(ResponseCode.ORG_USERS_ERROR.getCode(),
					ResponseCode.ORG_USERS_ERROR.getDec());
		}
		// 判断该组织下还有没有子机构
		List<Organization> orgs = organizationMapper.queryOrganizationChildrens(orgId);
		if (orgs.size() > 0) {
			return Response.createByErrorCodeMessage(ResponseCode.ORGCHILD_ERROR.getCode(),
					ResponseCode.ORGCHILD_ERROR.getDec());
		}
		int result = organizationMapper.delete(orgId);
		if (result == 0) {
			return Response.createByErrorCodeMessage(ResponseCode.REMOVE_ERROR.getCode(),
					ResponseCode.REMOVE_ERROR.getDec());
		}
		return Response.createBySuccess("删除成功", organization);
	}

	@Override
	public Response<Organization> modifyOrganization(Organization org) throws DemoException {
		if (EmptyUtils.isEmpty(org.getOrgName())) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.PARAM_NOT_NULL.getCode(),
					ResponseCode.PARAM_NOT_NULL.getDec());
		}
		Exist(org.getOrgName(), Const.ORGNAME);
		int result = organizationMapper.update(org);
		if (result == 0) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.UPDATE_ERROR.getCode(),
					ResponseCode.UPDATE_ERROR.getDec());
		}
		Organization Organizations = organizationMapper.query(org.getOrgName());
		return Response.createBySuccess("更新成功", Organizations);
	}

	/**
	 * 主键查询
	 *
	 * @param groupId
	 *            主键id
	 * @return
	 * @throws CommonException
	 */
	@Override
	@Transactional(readOnly = true)
	public Response<Organization> getOrganization(Integer groupId) {
		if (EmptyUtils.isEmpty(groupId)) {
			return Response.createByErrorCodeMessage(ResponseCode.PARAM_NOT_NULL.getCode(),
					ResponseCode.PARAM_NOT_NULL.getDec());
		}
		Organization organization = organizationMapper.get(groupId);
		if (organization == null) {
			return Response.createByErrorCodeMessage(ResponseCode.SELECT_ERROR.getCode(),
					ResponseCode.SELECT_ERROR.getDec());
		}
		return Response.createBySuccess("主键查询成功", organization);
	}

	/**
	 * 查询该组人员详情
	 *
	 * @param pageIndex
	 *            起始页数
	 * @param pageSize
	 *            每页数量
	 * @param groupId
	 *            组织机构id
	 * @return
	 * @throws DemoException
	 */
	@Override
	@Transactional(readOnly = true)
	public Response<PageInfo> queryOrganizationByPage(Integer pageIndex, Integer pageSize, Integer orgId)
			throws DemoException {
		if (EmptyUtils.isEmpty(orgId)) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.PARAM_NOT_NULL.getCode(),
					ResponseCode.PARAM_NOT_NULL.getDec());
		}
		if (EmptyUtils.isEmpty(pageSize)) {
			pageSize = 10;
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<User> list = organizationMapper.queryOrganizationUsers(orgId);
		if (list == null) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SELECT_ERROR.getCode(),
					ResponseCode.SELECT_ERROR.getDec());
		}
		PageInfo pageInfo = new PageInfo<>(list, pageSize);
		return Response.createBySuccess("查询成功", pageInfo);
	}

	/**
	 * 查询该机构下的所有子机构
	 *
	 * @param parentId
	 *            父节点id
	 * @return
	 * @throws DemoException
	 */
	@Override
	public Response<List<Organization>> queryOrganizations(Integer parentId) throws DemoException {
		List<Organization> organization = null;
		if (EmptyUtils.isEmpty(parentId)) {
			organization = organizationMapper.queryOrganizations();
		} else {
			organization = organizationMapper.queryOrganizationChildrens(parentId);
		}
		if (organization == null) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SELECT_ERROR.getCode(),
					ResponseCode.SELECT_ERROR.getDec());
		}
		return Response.createBySuccess("查询成功", organization);
	}

	/**
	 * 验正该机构是否存在
	 *
	 * @param str
	 * @param type
	 * @return
	 * @throws DemoException
	 */
	public Response<String> Exist(String str, String type) throws DemoException {
		if (Const.ORGNAME.equals(type)) {
			// 验正该机构是否存在
			Organization resultCount = organizationMapper.query(str);
			if (resultCount != null) {
				throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.ORGNAME_EXIST.getCode(),
						ResponseCode.ORGNAME_EXIST.getDec());
			}
		}
		return Response.createBySuccessMessage("校验成功");
	}

}
