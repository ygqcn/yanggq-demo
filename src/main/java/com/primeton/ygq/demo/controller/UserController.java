package com.primeton.ygq.demo.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageInfo;
import com.primeton.ygq.demo.exception.Response;
import com.primeton.ygq.demo.entity.User;
import com.primeton.ygq.demo.exception.DemoException;
import com.primeton.ygq.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户管理控制
 * 
 * @author 杨功强
 *
 */
@RestController
@Slf4j
@Api(value = "User", description = "用户管理")
@RequestMapping(value = "/api/users")
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 注册用户
	 * 
	 * @param user
	 * @return
	 * @throws DemoException
	 */
	@ApiOperation(value = "添加用户", response = Response.class)
	@ApiImplicitParams({ @ApiImplicitParam(value = "用户对象", name = "user", dataType = "User", required = true), })
	@PostMapping
	public Response<User> createUser(@RequestBody User user) throws DemoException {
		return userService.createUser(user);
	}

	/**
	 * 删除用户
	 *
	 * @param id
	 *            用户id
	 * @return
	 * @throws DemoException
	 */
	@ApiOperation(value = "删除用户", response = Response.class)
	@ApiImplicitParams({ @ApiImplicitParam(value = "用户id", name = "id", dataType = "Integer", required = true), })
	@DeleteMapping(value = "/{id}")
	public Response<User> removeUser(@PathVariable("id") Integer id) throws DemoException {
		return userService.removeUser(id);
	}

	/**
	 * 更新用户信息
	 *
	 * @param user
	 *            用户对象
	 * @return
	 * @throws DemoException
	 */
	@ApiOperation(value = "更新用户信息", response = Response.class)
	@ApiImplicitParams({ @ApiImplicitParam(value = "用户对象", name = "user", dataType = "User", required = true), })
	@PutMapping
	public Response<User> modifyUser(@RequestBody User user) throws DemoException {
		return userService.modifyUser(user);
	}

	/**
	 * 主键查询
	 *
	 * @param id
	 *            用户id
	 * @return
	 * @throws DemoException
	 */
	@ApiOperation(value = "主键查询", response = Response.class)
	@ApiImplicitParams({ @ApiImplicitParam(value = "用户id", name = "id", dataType = "Integer", required = true), })
	@GetMapping("/{id}")
	public Response<User> getUser(@PathVariable("id") Integer id) throws DemoException {
		return userService.getUser(id);
	}

	/**
	 * 根据条件分页查询
	 *
	 * @param pageIndex
	 *            起始页数
	 * @param pageSize
	 *            每页数量
	 * @param userName
	 *            用户名称
	 * @param orgId
	 *            组织id
	 * @return
	 * @throws DemoException
	 */
	@ApiOperation(value = "分页查询", response = Response.class)
	@ApiImplicitParams({ @ApiImplicitParam(value = "起始页数", name = "pageIndex", dataType = "int", required = true),
			@ApiImplicitParam(value = "每页数量", name = "pageSize", dataType = "int", required = true),
			@ApiImplicitParam(value = "用户名称", name = "userName", dataType = "String", required = true),
			@ApiImplicitParam(value = "组织id", name = "orgId", dataType = "int", required = true),

	})
	@GetMapping
	public Response<PageInfo> queryUsers(Integer pageIndex, Integer pageSize, String userName, Integer orgId)
			throws DemoException {
		return userService.queryUsers(pageIndex, pageSize, userName, orgId);
	}

	/**
	 * 用户登录
	 *
	 * @param user
	 *            用户对象
	 * @return
	 * @throws CommonException
	 */
	@ApiOperation(value = "用户登陆", response = Response.class)
	@ApiImplicitParams({ @ApiImplicitParam(value = "用户对象", name = "user", dataType = "User", required = true), })
	@PostMapping(value = "/actions/login")
	public Response<User> login(HttpSession session, @RequestBody User user) throws DemoException {
		return userService.login(session, user);
	}
}
