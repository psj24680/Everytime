package com.everytime.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.everytime.user.model.User;

@Repository
public interface UserDAO {

	public int insertUser(
			@Param("loginId") String loginId,
			@Param("password") String password,
			@Param("email") String email,
			@Param("name") String name,
			@Param("school") String school,
			@Param("schoolId") String schoolId);

	public User selectUserByLoginIdAndPassword(@Param("loginId") String loginId, @Param("password") String password);
}
