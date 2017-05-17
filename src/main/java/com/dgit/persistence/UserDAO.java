package com.dgit.persistence;

import java.util.ArrayList;
import java.util.List;

import com.dgit.domain.UserVO;




public interface UserDAO {

	public void insertUser(UserVO vo)throws Exception; // 회원가입
	
	public List<UserVO> checkID() throws Exception; // 아이디 중복 체크
	
	public UserVO login(LoginDTO dto)throws Exception; // 로그인 

	public void addAttach(UserVO vo)throws Exception;
	
	public List<String> getAttach(String uid)throws Exception;	
	
	public void delAttach(String fullname) throws Exception;
	
}
