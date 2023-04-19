package com.example.server;


import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ServerMapper {//class..
	 int selectId(String message);

}
