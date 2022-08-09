package com.everytime.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everytime.user.dao.UserDAO;
import com.everytime.user.model.User;

@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;

	public int addUser(String loginId, String password, String email, String name, String school, String schoolId) {
		return userDAO.insertUser(loginId, password, email, name, school, schoolId);
	}

	public User getUserByLoginIdAndPassword(String loginId, String password) {
		return userDAO.selectUserByLoginIdAndPassword(loginId, password);
	}
}
