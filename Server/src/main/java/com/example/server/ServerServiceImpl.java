package com.example.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ServerServiceImpl implements ServerService {

	@Autowired
	serverMapper serverMapper;
	
	@Override
	public int selectId(String message) {
		
		int yn =serverMapper.selectId(message);
		System.out.println("yn:"+yn);
		return yn;
	}

	

}
