package com.dgit.persistence;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private SqlSession session;
	
	private static String namespace="com.dgit.mapper.UserMapper";
	
	@Override
	public void insertUser(UserVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace+".insertUser", vo);
	}

	@Override
	public List<UserVO> checkID() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".checkid");
	}

	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace+".login",dto);
	}

	@Override
	public void addAttach(UserVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert(namespace+".addAttach",vo);
	}

	@Override
	public List<String> getAttach(String uid) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace+".getAttach",uid);
	}

	@Override
	public void delAttach(String fullname) throws Exception {
		// TODO Auto-generated method stub
		 session.delete(namespace+".delAttach",fullname);
		
	}
	
}
