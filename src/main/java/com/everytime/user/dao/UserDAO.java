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
			@Param("nickname") String nickname,
			@Param("school") String school,
			@Param("schoolId") String schoolId);

	public boolean existLoginId(String loginId);

	public boolean existNickname(String nickname);

	public User selectUserByLoginIdAndPassword(
			@Param("loginId") String loginId,
			@Param("password") String password);
}
