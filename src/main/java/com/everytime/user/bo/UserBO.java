package com.everytime.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everytime.user.dao.UserDAO;
import com.everytime.user.model.User;

@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;

	public int addUser(String loginId, String password, String email, String nickname, String school, String schoolId) {
		return userDAO.insertUser(loginId, password, email, nickname, school, schoolId);
	}

	public boolean existLoginId(String loginId) {
		return userDAO.existLoginId(loginId);
	}

	public boolean existNickname(String nickname) {
		return userDAO.existNickname(nickname);
	}

	public User getUserByLoginIdAndPassword(String loginId, String password) {
		return userDAO.selectUserByLoginIdAndPassword(loginId, password);
	}

	public User getUserById(int id) {
		return userDAO.selectUserById(id);
	}

	public int checkPw(String loginId, String password) {
		return userDAO.checkPw(loginId, password);
	}

	public int updatePasswordByLoginIdAndPassword(String loginId, String currentPassword, String password) {
		return userDAO.updatePasswordByLoginIdAndPassword(loginId, currentPassword, password);
	}
}
