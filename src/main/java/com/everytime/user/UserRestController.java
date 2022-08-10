package com.everytime.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.everytime.common.EncryptUtils;
import com.everytime.user.bo.UserBO;
import com.everytime.user.model.User;

@RestController
@RequestMapping("/user")
public class UserRestController {

	@Autowired
	private UserBO userBO;

	/**
	 * 회원가입
	 * 
	 * @param loginId
	 * @param password
	 * @param email
	 * @param nickname
	 * @param school
	 * @param schoolId
	 * @return
	 */
	@PostMapping("/sign_up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("email") String email,
			@RequestParam("nickname") String nickname,
			@RequestParam("school") String school,
			@RequestParam("schoolId") String schoolId) {
		Map<String, Object> map = new HashMap<>();

		// 아이디 중복확인
		boolean existLoginId = userBO.existLoginId(loginId);
		if (existLoginId == true) {
			map.put("result", "이미 사용 중인 아이디업니다. 확인 후 다시 입력하세요.");
			return map;
		}

		// 닉네임 중복확인
		boolean existNickname = userBO.existNickname(nickname);
		if (existNickname == true) {
			map.put("result", "이미 사용 중인 닉네임입니다. 확인 후 다시 입력하세요.");
			return map;
		}

		// 비밀번호 암호화
		String encryptPassword = EncryptUtils.md5(password);

		// DB insert
		int row = userBO.addUser(loginId, encryptPassword, email, nickname, school, schoolId);
		if (row == 1) {
			// 회원가입 성공
			map.put("result", "success");
		} else {
			// 회원가입 실패
			map.put("result", "회원가입을 실패했습니다. 다시 입력해주세요.");
		}

		return map;
	}

	/**
	 * 로그인
	 * 
	 * @param loginId
	 * @param password
	 * @return
	 */
	@PostMapping("/sign_in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpSession session) {
		Map<String, Object> map = new HashMap<>();

		String encryptPassword = EncryptUtils.md5(password);

		User user = userBO.getUserByLoginIdAndPassword(loginId, encryptPassword);
		if (user != null) {
			// 로그인 성공
			map.put("result", "success");

			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userNickname", user.getNickname());
			session.setAttribute("userSchoolId", user.getSchoolId());
		} else {
			// 로그인 실패
			map.put("result", "아이디나 비밀번호를 바르게 입력해주세요.");
		}

		return map;
	}

}
