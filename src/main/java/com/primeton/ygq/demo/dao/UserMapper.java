package com.primeton.ygq.demo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.primeton.ygq.demo.entity.User;

@Mapper
@Repository
public interface UserMapper {
	int insert(User u);

	int delete(Integer id);

	int update(User u);

	User get(Integer id);

	List<User> queryUsers(@Param("username") String username, @Param("orgId") Integer orgId);

	int counts(String userName);

	User checkUser(@Param("username") String username, @Param("password") String password);
}
