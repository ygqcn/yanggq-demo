package com.primeton.ygq.demo.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.primeton.ygq.demo.common.Const;
import com.primeton.ygq.demo.exception.Response;
import com.primeton.ygq.demo.exception.ResponseCode;
import com.primeton.ygq.demo.dao.UserMapper;
import com.primeton.ygq.demo.entity.User;
import com.primeton.ygq.demo.exception.DemoException;
import com.primeton.ygq.demo.service.UserService;
import com.primeton.ygq.demo.util.EmptyUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(rollbackFor = DemoException.class)
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	@Transactional
	public Response<User> createUser(User user) throws DemoException {
		if (EmptyUtils.isEmpty(user.getUsername()) || EmptyUtils.isEmpty(user.getPassword())
				|| EmptyUtils.isEmpty(user.getOrgId())) {
			return Response.createByErrorMessage("参数不能为空");
		}
		Response validResponse = this.Exist(user.getUsername(), user.getId(), Const.USERNAME);
		if (!validResponse.isSuccess()) {
			return validResponse;
		}
		if (!validResponse.isSuccess()) {
			return validResponse;
		}
		int count = userMapper.insert(user);
		if (count == 0) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.REGISTER_REEOR.getCode(),
					ResponseCode.REGISTER_REEOR.getDec());
		}
		return Response.createBySuccessMessage("注册成功");
	}

	@Override
	@Transactional
	public Response<User> removeUser(Integer id) throws DemoException {
		if (EmptyUtils.isEmpty(id)) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.PARAM_NOT_NULL.getCode(),
					ResponseCode.PARAM_NOT_NULL.getDec());
		}
		User user = userMapper.get(id);
		if (user == null) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.USER_NOT_EXIST.getCode(),
					ResponseCode.USER_NOT_EXIST.getDec());
		}
		user.setPassword(null);
		int count = userMapper.delete(id);
		if (count == 0) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.REMOVE_ERROR.getCode(),
					ResponseCode.REMOVE_ERROR.getDec());
		}
		return Response.createBySuccess("删除成功", user);
	}

	@Override
	@Transactional
	public Response<User> modifyUser(User user) throws DemoException {
		if (EmptyUtils.isEmpty(user.getId()) || EmptyUtils.isEmpty(user.getUsername())) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.PARAM_NOT_NULL.getCode(),
					ResponseCode.PARAM_NOT_NULL.getDec());
		}
		int count = userMapper.update(user);
		if (count == 0) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.UPDATE_ERROR.getCode(),
					ResponseCode.UPDATE_ERROR.getDec());
		}
		User userNew = userMapper.get(user.getId());
		userNew.setPassword(null);
		return Response.createBySuccess("更新成功", userNew);
	}

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 *            用户id
	 * @return
	 * @throws DemoException
	 */
	@Override
	public Response<User> getUser(Integer id) throws DemoException {
		if (EmptyUtils.isEmpty(id)) {
			throw new DemoException(HttpStatus.OK, ResponseCode.PARAM_NOT_NULL.getCode(),
					ResponseCode.PARAM_NOT_NULL.getDec());
		}
		User user = userMapper.get(id);
		if (user == null) {
			throw new DemoException(HttpStatus.OK, ResponseCode.SELECT_ERROR.getCode(),
					ResponseCode.SELECT_ERROR.getDec());
		}
		user.setPassword(null);
		return Response.createBySuccess("主键查询成功", user);
	}

	/**
	 * 分页查询
	 *
	 * @param pageIndex
	 *            起始页数
	 * @param pageSize
	 *            每页大小
	 * @return
	 * @throws DemoException
	 */
	@Override
	public Response<PageInfo> queryUsers(Integer pageIndex, Integer pageSize, String userName, Integer orgId)
			throws DemoException {
		if (EmptyUtils.isEmpty(pageIndex)) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.PARAM_NOT_NULL.getCode(),
					ResponseCode.PARAM_NOT_NULL.getDec());
		}
		if (EmptyUtils.isEmpty(pageSize)) {
			pageSize = 10;
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<User> users = userMapper.queryUsers(userName, orgId);
		if (users == null) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.SELECT_ERROR.getCode(),
					ResponseCode.SELECT_ERROR.getDec());
		}
		PageInfo pageInfo = new PageInfo<>(users, pageSize);
		return Response.createBySuccess("查询成功", pageInfo);
	}

	/**
	 * 用户登录
	 *
	 * @param userName
	 *            用户名称
	 * @param passWord
	 *            用户密码
	 * @return
	 * @throws DemoException
	 */
	@Override
	public Response<User> login(HttpSession session, User user) throws DemoException {
		if (EmptyUtils.isEmpty(user.getUsername()) || EmptyUtils.isEmpty(user.getPassword())) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.PARAM_NOT_NULL.getCode(),
					ResponseCode.PARAM_NOT_NULL.getDec());
		}
		// 用户登陆检测
		User result = userMapper.checkUser(user.getUsername(), user.getPassword());
		if (result == null) {
			throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.USERNAME_PASS_ERROR.getCode(),
					ResponseCode.USERNAME_PASS_ERROR.getDec());
		}
		session.setAttribute("currentUser", result);
		return Response.createBySuccess("登陆成功", result);
	}

	/**
	 * 验证用户是否存在
	 *
	 * @param str
	 * @param type
	 * @return
	 * @throws CommonException
	 */
	public Response<String> Exist(String str, Integer id, String type) throws DemoException {
		if (Const.USERNAME.equals(type)) {
			if (!EmptyUtils.isEmpty(id)) {
				User user = userMapper.get(id);
				if (user != null) {
					throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.USERNAME_EXIST.getCode(),
							ResponseCode.USERNAME_EXIST.getDec());
				}
			}
			int resultCount = userMapper.counts(str);
			if (resultCount > 0) {
				throw new DemoException(HttpStatus.INTERNAL_SERVER_ERROR, ResponseCode.USERNAME_EXIST.getCode(),
						ResponseCode.USERNAME_EXIST.getDec());
			}
		}
		return Response.createBySuccessMessage("校验成功");
	}

}
