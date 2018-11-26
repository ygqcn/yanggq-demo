package com.primeton.ygq.demo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageInfo;
import com.primeton.ygq.demo.exception.Response;
import com.primeton.ygq.demo.entity.User;
import com.primeton.ygq.demo.exception.DemoException;

/**
 * 用户管理API接口
 * 
 * @author 杨功强
 *
 */
public interface UserService {
	/**
	 * 创建用户
	 * @param user
	 * @return
	 * @throws DemoException
	 */
	Response<User> createUser(User user)throws DemoException;
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 * @throws DemoException
	 */
	Response<User> removeUser(Integer id) throws DemoException;

	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 * @throws DemoException
	 */
	Response<User> modifyUser(User user) throws DemoException;

	/**
	 * 根据主键查询用户
	 * @param id
	 * @return
	 * @throws DemoException
	 */
	Response<User> getUser(Integer id) throws DemoException;

	/**
	 * 登录
	 * @param session
	 * @param userName
	 * @param passWord
	 * @return
	 * @throws DemoException
	 */
	Response<User> login(HttpSession session, User user) throws DemoException;

	/**
	 * 根据条件分页查询
	 * @param pageIndex
	 * @param pageSize
	 * @param userName
	 * @param orgId
	 * @return
	 * @throws DemoException
	 */
	Response<PageInfo> queryUsers(Integer pageIndex, Integer pageSize,String userName,Integer orgId) throws DemoException;

}
