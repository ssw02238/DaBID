package com.ssafy.api.service;

import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.request.UserUpdatePatchReq;
import com.ssafy.db.entity.Session;
import com.ssafy.db.entity.User;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	User createUser(String userName);
	User updateUser(String userId, UserUpdatePatchReq updateInfo);
	User getUserByUserId(String userId);
	void deleteUser(String userId);
	boolean checkUser(String userId);
}
