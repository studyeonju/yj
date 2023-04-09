package com.example.server;


import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface serverMapper {//class..
	 int selectId(String message);

}
