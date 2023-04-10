package com.example.server;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
@Repository
public class ServerServiceImpl implements ServerService {

	
	@Autowired
	SqlSessionTemplate sqlsession; 
	
	@Override
	public int selectId(String message) {
		int cnt = sqlsession.selectOne("selectId",message);
		return cnt;
	}

	

}
