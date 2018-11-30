package com.primeton.ygq.demo;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.primeton.ygq.demo.exception.Response;
import com.primeton.ygq.demo.controller.UserController;
import com.primeton.ygq.demo.entity.User;
import com.primeton.ygq.demo.exception.DemoException;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 分组测试
 * @author 杨功强
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class UserControllerTestCase {

	@Autowired
	private UserController userController;

	private User user;

	@Test
	public void testUser() throws Exception {
		user = this.initUser();
		// 用户注册测试User
		this.createUser();
		// 用户login测试
		this.login();
		// 用户更新测试
		this.modifyUser();
		// 用户查询测试
		this.queryUsers();
		// 用户主键查询
		this.getUser();
		// 用户删除
		this.removeUser();
	}

	/**
	 * 用户注册测试
	 *
	 * @throws DemoException
	 */
	public void createUser() throws DemoException {
		Response<User> response = userController.createUser(user);
		Assert.assertEquals("注册成功", response.getMsg());
	}

	/**
	 * 用户删除测试
	 *
	 * @throws DemoException
	 */
	public void removeUser() throws DemoException {
		Response<User> response = userController.removeUser(user.getId());
		Assert.assertNotNull(response.getStatus());
	}

	/**
	 * 用户更新测试
	 *
	 * @throws DemoException
	 */
	public void modifyUser() throws DemoException {
		user = this.initUser();
		user.setUsername("test更新");
		Response<User> response = userController.modifyUser(user);
		Assert.assertEquals("更新成功", response.getMsg());
	}

	/**
	 * 用户主键查询测试
	 *
	 * @throws DemoException
	 */
	public void getUser() throws DemoException {
		Response<User> response = userController.getUser(user.getId());
		Assert.assertEquals("主键查询成功", response.getMsg());
	}

	/**
	 * 用户分页查询测试
	 *
	 * @throws DemoException
	 */

	public void queryUsers() throws DemoException {
		Response<PageInfo> response = userController.queryUsers(2, 3, null, null);
		Assert.assertEquals("查询成功", response.getMsg());
		Assert.assertNotNull(response.getMessage());
	}

	/**
	 * 用户login测试
	 *
	 * @throws Exception
	 */
	public void login() throws Exception {

		user.setUsername("a");
		user.setPassword("123");
		Response<User> response = userController.login(session, user);
		Assert.assertNotNull(response.getMessage());
		Assert.assertEquals("登陆成功", response.getMsg());
	}

	/**
	 * 创建用户信息
	 *
	 * @return
	 */
	public User initUser() {
		User user = new User();
		user.setId(52);
		user.setOrgId(700041);
		user.setUsername("test最新");
		user.setPassword("123456");
		return user;
	}

	HttpSession session = new HttpSession() {

		@Override
		public void setMaxInactiveInterval(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void setAttribute(String arg0, Object arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void removeValue(String arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void removeAttribute(String arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void putValue(String arg0, Object arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean isNew() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void invalidate() {
			// TODO Auto-generated method stub

		}

		@Override
		public String[] getValueNames() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getValue(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public HttpSessionContext getSessionContext() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ServletContext getServletContext() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getMaxInactiveInterval() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public long getLastAccessedTime() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public String getId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getCreationTime() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Enumeration<String> getAttributeNames() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object getAttribute(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}
	};
}
