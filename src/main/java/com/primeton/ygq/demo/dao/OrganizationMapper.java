package com.primeton.ygq.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.primeton.ygq.demo.entity.Organization;
import com.primeton.ygq.demo.entity.User;

@Mapper
@Repository
public interface OrganizationMapper {
	int insert(Organization org);

	int delete(Integer orgId);

	int update(Organization org);

	Organization get(Integer orgId);

	Organization query(String orgName);

	List<Organization> queryOrganizations();

	List<Organization> queryOrganizationChildrens(Integer parentId);

	List<User> queryOrganizationUsers(Integer orgId);

}
